package ui;

import model.Recipe;
import model.RecipesList;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static ui.AppOperations.loadSaveData;
import static ui.AppOperations.saveYourRecipesList;

// https://www.youtube.com/watch?v=KOI1WbkKUpQ
// Represents the Gui that appears when a user decides to view their current recipes
public class RecipeSelector extends JFrame implements ActionListener {

    RecipesList yourRecipesList;
    JList<Recipe> list = new JList<>();
    DefaultListModel<Recipe> model = new DefaultListModel<>();

    JLabel recipeLabel = new JLabel();
    JLabel ingredientsLabel = new JLabel();
    JPanel panel = new JPanel();
    JButton select = new JButton("Select Recipe");
    JButton remove = new JButton("Delete Recipe");
    JSplitPane splitPane = new JSplitPane();

    // MODIFIES: this
    // EFFECTS: Loads selector frame
    public RecipeSelector() {
        setupData();

        recipeLabel.setFont(new Font("Serif", Font.BOLD, 18));
        ingredientsLabel.setFont(new Font("Serif", Font.PLAIN, 15));

        list.setModel(model);

        for (int i = 0; i < yourRecipesList.recipes.size(); i++) {
            model.addElement(yourRecipesList.recipes.get(i));
        }

        addListeners();

        list.getSelectionModel().addListSelectionListener(e -> {
            Recipe recipeSelected = list.getSelectedValue();
            recipeLabel.setText(recipeSelected.getName() + " Ingredients:");
            ingredientsLabel.setText(recipeSelected.displayIngredients());
            panel.add(select);
            panel.add(remove);
        });

        splitPane.setLeftComponent(new JScrollPane(list));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(recipeLabel);
        panel.add(ingredientsLabel);

        splitPane.setRightComponent(panel);

        add(splitPane);
    }

    // EFFECTS: loads current save data to yourRecipesList
    public void setupData() {
        try {
            yourRecipesList = loadSaveData();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // MODIFIES: this
    // EFFECTS: Observes current buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("select")) {
            chosen();
        } else if (e.getActionCommand().equals("delete")) {
            Recipe recipeSelected = list.getSelectedValue();
            yourRecipesList.removeRecipe(recipeSelected);
            try {
                saveYourRecipesList(yourRecipesList, "./data/UserSave.txt");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            update();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds listeners to buttons
    public void addListeners() {
        select.addActionListener(this);
        select.setActionCommand("select");
        remove.addActionListener(this);
        remove.setActionCommand("delete");
    }

    // MODIFIES: this
    // EFFECTS: passes chosen recipe to recipeReader and disposes of frame
    public void chosen() {
        Recipe recipeSelected = list.getSelectedValue();

        RecipeReader recipeViewer = new RecipeReader(recipeSelected);
        recipeViewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        recipeViewer.setSize(1000, 750);
        recipeViewer.setTitle("Recipe Viewer");
        try {
            recipeViewer.setIconImage(ImageIO.read(new File("./data/SuChefHat.jpg")));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        recipeViewer.setVisible(true);

        this.dispose();
    }

    // MODIFIES: this
    // EFFECTS: Closes current frame and reloads to list will be updated
    public void update() {
        dispose();
        RecipeSelector selectorGUI = new RecipeSelector();
        selectorGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        selectorGUI.setSize(750, 500);
        selectorGUI.setTitle("Recipe Selector");
        try {
            selectorGUI.setIconImage(ImageIO.read(new File("./data/SuChefHat.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        selectorGUI.setVisible(true);
    }
}

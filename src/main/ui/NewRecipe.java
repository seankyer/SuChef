package ui;

import exceptions.IngredientException;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static java.lang.Integer.parseInt;
import static ui.AppOperations.loadSaveData;
import static ui.AppOperations.saveYourRecipesList;

// Represents the Gui that appears when a user decides to create a new recipe
public class NewRecipe extends JFrame implements ActionListener {

    RecipesList yourRecipesList;

    String recipeName = "";
    IngredientsList ingredients = new IngredientsList();
    InstructionList instructions = new InstructionList();
    int count;

    JLabel enterName;
    JTextField name;
    JButton beginWriting;

    JPanel buttonPanel = new JPanel();
    JPanel fieldsPanel = new JPanel();

    JTextField ovenTimer = new JTextField("0", 10);
    JTextField instruction = new JTextField(10);
    JTextField ingredient = new JTextField(10);
    JTextField ammount = new JTextField();
    JTextField units = new JTextField();
    JLabel savedMessage = new JLabel("Your recipe has been saved!");
    JLabel typeOvenTimer = new JLabel("Enter Oven Timer in Seconds (leave as 0 if not required)");
    JLabel typeInstruction = new JLabel();
    JLabel typeIngredient = new JLabel("Enter Ingredient Name");
    JLabel typeAmmount = new JLabel("Enter Ingredient Ammount (Whole integer units)");
    JLabel typeUnits = new JLabel("Enter Ingredient Units");
    JButton addInstruction = new JButton("Add Instruction");
    JButton finishedInstruction = new JButton("Finished Instructions");
    JButton addIngredient = new JButton("Add Ingredient");
    JButton finishedIngredients = new JButton("Finished Ingredients");

    // EFFECTS: Creates layout and adds fields for NewRecipe GUI
    public NewRecipe() {
        count = 1;

        buttonPanel.setLayout(new FlowLayout());
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));

        enterName = new JLabel("Enter name of new Recipe!");
        enterName.setFont(new Font("Calibri", Font.PLAIN, 20));
        fieldsPanel.add(enterName);

        name = new JTextField(10);
        fieldsPanel.add(name);

        beginWriting = new JButton("Begin Writing");
        buttonPanel.add(beginWriting);

        beginWriting.addActionListener(this);
        beginWriting.setActionCommand("startIngredients");

        add(fieldsPanel, BorderLayout.PAGE_START);
        add(buttonPanel, BorderLayout.PAGE_END);

        addIngredient.addActionListener(this);
        addIngredient.setActionCommand("nextIngredient");
        finishedIngredients.addActionListener(this);
        finishedIngredients.setActionCommand("finishedIngredients");

        typeInstruction.setText("Enter instruction for step " + count);

        addInstruction.addActionListener(this);
        addInstruction.setActionCommand("addInstruction");

        finishedInstruction.addActionListener(this);
        finishedInstruction.setActionCommand("finish");
    }

    // MODIFIES: this
    // EFFECTS: Observes buttons in RecipeWriter
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("startIngredients")) {
            initializeIngredients();
            revalidate();
            repaint();
        } else if (e.getActionCommand().equals("nextIngredient")) {
            tryIngredients();
        } else if (e.getActionCommand().equals("finishedIngredients")) {
            finishedIngredients();
        } else if (e.getActionCommand().equals("addInstruction")) {
            addInstruction();
        } else if (e.getActionCommand().equals("finish")) {
            if (!instruction.getText().equals("")) {
                addInstruction();
            }
            removeInstructionWriterElements();
            try {
                compileRecipe();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            revalidate();
            repaint();
        }
    }

    // MODIFIES: this
    // EFFECTS: If there is data written in current ingredient name, add to list of ingredients and continue. Otherwise
    //          continue to next step
    public void finishedIngredients() {
        checkLastAdd();
        removeIngredientWriterElements();
        addInstructionWriterElements();
        revalidate();
        repaint();
    }

    // EFFECTS: Checks if any data has been written inside of the ingredient textfield
    public void checkLastAdd() {
        if (!ingredient.getText().equals("")) {
            try {
                addIngredients();
            } catch (IngredientException e) {
                e.getMessage();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Builds the frame for adding ingredients
    public void initializeIngredients() {
        try {
            recipeName = name.getText();
            System.out.println(recipeName);
            addIngredients();
        } catch (Exception exc) {
            // Do nothing
        }
        fieldsPanel.remove(name);
        fieldsPanel.remove(enterName);
        buttonPanel.remove(beginWriting);

        addIngredientWriterElements();
    }

    // MODIFIES: this
    // EFFECTS: adds the current ingredient to the list of ingredients
    public void addIngredients() throws IngredientException {
        String ingredientName = ingredient.getText();
        int ingredientAmmount = parseInt(ammount.getText());
        String ingredientUnit = units.getText();
        ingredients.addIngredient(new Ingredient(ingredientName, ingredientAmmount, ingredientUnit));

        ingredient.setText("");
        ammount.setText("");
        units.setText("");
        System.out.println(ingredients.groceryList());
    }

    // MODIFIES: this
    // EFFECTS: Tries to add the current ingredient
    public void tryIngredients() {
        try {
            addIngredients();
        } catch (IngredientException ingredientException) {
            ingredientException.getMessage();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the current instruction to the lits of instructions
    public void addInstruction() {
        String instructionString = instruction.getText();
        int ovenTimerSet = parseInt(ovenTimer.getText());

        instructions.addInstruction(new Instruction(count, instructionString, ovenTimerSet));
        System.out.println(instructions.displayInstructionForStage(count));

        instruction.setText("");
        ovenTimer.setText("0");

        count++;
    }

    // MODIFIES: this
    // EFFECTS: Creates the ingredient writer fields
    public void addIngredientWriterElements() {
        fieldsPanel.add(typeIngredient);
        fieldsPanel.add(ingredient);
        fieldsPanel.add(typeAmmount);
        fieldsPanel.add(ammount);
        fieldsPanel.add(typeUnits);
        fieldsPanel.add(units);
        buttonPanel.add(finishedIngredients);
        buttonPanel.add(addIngredient);
    }

    // MODIFIES: this
    // EFFECTS: deconstructs the ingredient writer fields
    public void removeIngredientWriterElements() {
        fieldsPanel.remove(typeIngredient);
        fieldsPanel.remove(ingredient);
        fieldsPanel.remove(typeAmmount);
        fieldsPanel.remove(ammount);
        fieldsPanel.remove(typeUnits);
        fieldsPanel.remove(units);
        buttonPanel.remove(finishedIngredients);
        buttonPanel.remove(addIngredient);
    }

    // MODIFIES: this
    // EFFECTS: builds the instruction writer fields
    public void addInstructionWriterElements() {
        fieldsPanel.add(typeInstruction);
        fieldsPanel.add(instruction);
        fieldsPanel.add(typeOvenTimer);
        fieldsPanel.add(ovenTimer);
        buttonPanel.add(finishedInstruction);
        buttonPanel.add(addInstruction);
    }

    // MODIFIES: this
    // EFFECTS: deconstructs the instruction writer fields
    public void removeInstructionWriterElements() {
        fieldsPanel.remove(typeInstruction);
        fieldsPanel.remove(instruction);
        fieldsPanel.remove(typeOvenTimer);
        fieldsPanel.remove(ovenTimer);
        buttonPanel.remove(addInstruction);
        buttonPanel.remove(finishedInstruction);
    }

    // MODIFIES: yourRecipesList, UserSave.txt
    // EFFECTS: Adds built recipe to yourRecipesList and automatically saves it
    public void compileRecipe() throws IOException {
        loadData();
        yourRecipesList.addRecipe(new Recipe(recipeName, ingredients, instructions));
        saveYourRecipesList(yourRecipesList, "./data/UserSave.txt");
        fieldsPanel.add(savedMessage);
    }

    // MODIFIES: yourRecipesList
    // EFFECTS: loads current save data to yourRecipesList
    public void loadData() {
        try {
            yourRecipesList = loadSaveData();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

package ui;

import exceptions.NoTimerException;
import model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the Gui that appears when a user selects a recipe they would like to be walked through
public class RecipeReader extends JFrame implements ActionListener {

    JLabel recipeName = new JLabel();
    JLabel confirmLabel = new JLabel();
    JLabel ingredientsLabel = new JLabel();
    JButton confirmIngredients = new JButton("Confirm");

    JLabel showStepNumber;
    JLabel currentStep;
    JButton nextStep;
    JButton startTimer;
    JButton finish;

    public Recipe chosenRecipe;
    static int count;

    // MODIFIES: this
    // EFFECTS: Creates elements for frame
    public RecipeReader(Recipe recipe) {
        count = 1;

        setRecipe(recipe);
        addElements();

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setBackground(Color.LIGHT_GRAY);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));

        confirmIngredients.addActionListener(this);
        confirmIngredients.setActionCommand("Ingredients Confirmed");

        startTimer = new JButton("Start Timer");
        nextStep = new JButton("Next Step");
        currentStep = new JLabel();
        showStepNumber = new JLabel();

        showStepNumber.setText("Step " + count);
        showStepNumber.setFont(new Font("Calibri", Font.BOLD, 18));

        currentStep.setText(chosenRecipe.displayCurrentInstruction(count).getInstruction());
        currentStep.setFont(new Font("Calibri", Font.PLAIN, 14));

        nextStep.addActionListener(this);
        nextStep.setActionCommand("Next Step");
    }

    // EFFECTS: Observes buttons on frame
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Ingredients Confirmed")) {
            ingredientsConfirmed();
        } else if (e.getActionCommand().equals("Next Step")) {
            incrementStep();
        } else if (e.getActionCommand().equals("Start Timer")) {
            try {
                chosenRecipe.displayCurrentInstruction(count).beginOvenTimer();
            } catch (NoTimerException noTimerException) {
                noTimerException.getMessage();
            }
        } else if (e.getActionCommand().equals("Finish")) {
            closeFrame();
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads steps for recipe
    public void ingredientsConfirmed() {
        remove(confirmIngredients);
        remove(confirmLabel);
        remove(ingredientsLabel);

        add(showStepNumber);
        add(currentStep);
        add(nextStep);

        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: Shows next instruction for recipe
    public void incrementStep() {
        count = count + 1;
        showStepNumber.setText("Step " + count);
        currentStep.setText(chosenRecipe.displayCurrentInstruction(count).getInstruction());
        if (count >= chosenRecipe.getLengthOfInstructionList()) {
            remove(nextStep);
            finish = new JButton("End");
            finish.addActionListener(this);
            finish.setActionCommand("Finish");
            add(finish);
            revalidate();
            repaint();
        }
        if (chosenRecipe.displayCurrentInstruction(count).containsTimer()) {
            displayTimer();
        } else {
            remove(startTimer);
        }
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: Shows timer if the current instruction contains one
    public void displayTimer() {
        try {
            startTimer = new JButton(
                    "Start " + chosenRecipe.displayCurrentInstruction(count).displayTimer() + " Second Timer"
            );
        } catch (NoTimerException e) {
            e.getMessage();
        }
        startTimer.addActionListener(this);
        startTimer.setActionCommand("Start Timer");
        add(startTimer);
    }

    // MODIFIES: this
    // EFFECTS: Closes current frame
    public void closeFrame() {
        super.dispose();
    }

    // MODIFIES: this
    // EFFECTS: Builds confirmation frame
    public void addElements() {
        recipeName.setText(chosenRecipe.getName());
        recipeName.setFont(new Font("Calibri", Font.PLAIN, 30));
        confirmLabel.setText("Please confirm that you have all the required ingredients: ");
        confirmLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        ingredientsLabel.setText(chosenRecipe.displayIngredients());
        ingredientsLabel.setFont(new Font("Calibri", Font.PLAIN, 20));

        add(recipeName);
        add(confirmLabel);
        add(ingredientsLabel);
        add(confirmIngredients);
    }

    // EFFECTS: sets chosen recipe
    public void setRecipe(Recipe recipe) {
        chosenRecipe = recipe;
    }
}

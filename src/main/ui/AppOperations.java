package ui;

import exceptions.IngredientException;
import exceptions.NoTimerException;
import model.*;
import model.Ingredient;
import persistence.Reader;
import persistence.Writer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// Represent the operations the Terminal and Gui application may need to perform during operation
public class AppOperations {

    // EFFECTS: Using console, creates a Recipe and adds it to yourRecipes
    static void recipeWriter(RecipesList yourRecipesList) throws IngredientException {
        Scanner scanner = new Scanner(System.in);
        String recipeName;

        // Greeting to recipeWriter
        System.out.println("You have chosen recipeWriter");
        System.out.println("Enter name of new recipe: ");
        // These are the variables that will become the Recipe Object
        recipeName = scanner.nextLine(); // A
        IngredientsList yourIngredientsList = new IngredientsList(); // B
        InstructionList yourInstructionsList = new InstructionList(); // C

        ingredientsWriter(yourIngredientsList); // Write grocery list
        instructionWriter(yourInstructionsList); // Write instructions

        System.out.println("You're done writing your recipe!");
        // Creates recipe with objects
        Recipe yourRecipe = new Recipe(recipeName, yourIngredientsList, yourInstructionsList);
        yourRecipesList.addRecipe(yourRecipe);

        System.out.println("Your updated list of Recipes are below: ");
        System.out.println(yourRecipesList.displayRecipes());
    }

    // EFFECTS: Walks user through the steps of writing the ingredients list for their recipe
    static void ingredientsWriter(IngredientsList yourIngredientsList) throws IngredientException {
        Scanner scanner = new Scanner(System.in);

        // Ingredients list construction
        System.out.println("We are going to create the list of ingredients for this recipe.");
        String choice = "";
        String name = "";
        int amount = 0;
        String unit = "";
        // Loops for each ingredient they would like to add
        for (int i = 0; !(choice.equals("yes")); i++) {
            System.out.println("Enter name of ingredient number " + (i + 1));
            name = scanner.nextLine();
            System.out.println("Enter amount of ingredient number " + (i + 1) + " (as an integer)");
            amount = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter units of ingredient number " + (i + 1));
            unit = scanner.nextLine();
            Ingredient yourIngredient = new Ingredient(name, amount, unit); // User gathered variables are set
            yourIngredientsList.addIngredient(yourIngredient); // Ingredient is added to ingredients list
            System.out.println("Current ingredients list: ");
            System.out.println(yourIngredientsList.groceryList());
            System.out.println("Are you finished adding ingredients? (yes/no)");
            choice = scanner.nextLine();
        }
    }

    // EFFECTS: Walks user through the steps of writing the instructions list for their recipe
    static void instructionWriter(InstructionList yourInstructionsList) {
        // Creates instructions for recipe
        System.out.println("Next we are going to create a set of instructions for your recipe");
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        int stage = 0;
        String instruction = "";
        int timer = 0;
        // Loops for each instruction added
        for (int i = 1; !(choice.equals("yes")); i++) {
            stage = i;
            System.out.println("Enter instructions for stage " + stage + ".");
            instruction = scanner.nextLine();
            System.out.println("Enter the time in seconds for Oven Timer. (If no oven timer is needed type 0)");
            timer = Integer.parseInt(scanner.nextLine());
            Instruction yourInstruction = new Instruction(i, instruction, timer); // User gathered variables are set
            yourInstructionsList.addInstruction(yourInstruction); // Ingredient is added to ingredients list
            System.out.println("Instruction number " + stage + ":");
            System.out.println(yourInstructionsList.displayInstructionForStage(stage).getInstruction());
            System.out.println("Are you finished adding instructions? (yes/no)");
            choice = scanner.nextLine();
        }
    }

    // EFFECTS: Recites the chosen recipe back to the user, starting oven timers and confirming ingredients along the
    //          way
    static void recipeReader(RecipesList yourRecipes) {
        Scanner scanner = new Scanner(System.in);
        //Greeting to recipeReader
        System.out.println("You have chosen recipeReader");
        System.out.println("To return to main menu type 'back'");
        System.out.println("Which recipe would you like to read? (Enter the corresponding number)");
        System.out.println(yourRecipes.displayRecipes());
        // Choosing which recipe to select
        int selection = 0;
        selection = Integer.parseInt(scanner.nextLine());
        Recipe chosenRecipe = yourRecipes.getRecipe(selection);
        // Confirming ingredients
        confirmIngredients(chosenRecipe);
        // Walking through the steps
        readRecipe(chosenRecipe);
        System.out.println("You are done! Enjoy your yummy meal.");
    }

    // EFFECTS: Confirms the user has the ingredients required for the recipe
    static void confirmIngredients(Recipe chosenRecipe) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(chosenRecipe.displayIngredients());

        System.out.println("Confirm that you have all the ingredients on hand and are ready to begin. (Enter)");
        scanner.nextLine();
    }

    // EFFECTS: Walks through the steps of the chosen recipe
    static void readRecipe(Recipe chosenRecipe) {
        Scanner scanner = new Scanner(System.in);
        Instruction currentInstruction;

        System.out.println("Starting cooking session!");
        for (int i = 1; !(i == (chosenRecipe.getLengthOfInstructionList() + 1)); i++) {
            currentInstruction = chosenRecipe.displayCurrentInstruction(i);
            System.out.println("Step " + i + ": " + currentInstruction.getInstruction());
            if (currentInstruction.containsTimer()) {
                System.out.println("There is a timer for this step. Press 'Enter' to begin timer.");
                scanner.nextLine();
                try {
                    System.out.println("Your " + currentInstruction.displayTimer() + " second timer has begun.");
                } catch (NoTimerException e) {
                    e.getMessage();
                }
                try {
                    System.out.println(currentInstruction.beginOvenTimer());
                } catch (NoTimerException e) {
                    e.getMessage();
                }
            }
            endStepMessage();
        }
    }

    public static void endStepMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("When you are finished this step press 'Enter'");
        scanner.nextLine();
    }

    // EFFECTS: Saves the current RecipesList
    static void saveYourRecipesList(RecipesList yourRecipesList, String path) throws IOException {
        Writer dataWriter = new Writer();
        dataWriter.saveData(yourRecipesList, path);
        System.out.println("Goodbye");
    }

    // EFFECTS: Loads the current RecipesList
    static RecipesList loadRecipes(RecipesList recipes, String saveFile) throws IOException, ClassNotFoundException {
        File file = new File(saveFile);
        if (file.length() > 0) {
            Reader dataReader = new Reader();
            recipes = dataReader.readData(recipes, saveFile);
        }
        return recipes;
    }

    // EFFECTS: Returns the loaded save
    public static RecipesList loadSaveData() throws IOException, ClassNotFoundException {
        String saveFile = "./data/UserSave.txt";
        RecipesList yourRecipesList = new RecipesList();
        yourRecipesList = loadRecipes(yourRecipesList, saveFile);
        return yourRecipesList;
    }
}

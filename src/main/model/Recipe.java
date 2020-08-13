package model;

import java.io.Serializable;

// Represents a singular recipe which is comprised of a name, list of ingredients and list of instructions
public class Recipe implements Serializable {

    // Recipe is made of an IngredientsList and InstructionList

    IngredientsList ingredients;
    InstructionList instructionList;
    String name;

    // REQUIRES: name, ingredients and instructions must not be empty
    // EFFECTS: Constructs a recipe
    public Recipe(String name, IngredientsList ingredients, InstructionList instructionlist) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructionList = instructionlist;
    }

    // EFFECTS: returns the name of this recipe
    public String getName() {
        return this.name;
    }

    // EFFECTS: Returns the groceryList needed in String form for this recipe
    public String displayIngredients() {
        return this.ingredients.groceryList();
    }

    // EFFECTS: Return length of this.instructionList
    public int getLengthOfInstructionList() {
        return instructionList.getLengthOfInstructions();
    }

    // EFFECTS: Given a stage, display the current step in the recipe
    public Instruction displayCurrentInstruction(int stage) {
        return this.instructionList.displayInstructionForStage(stage);
    }

    @Override
    public String toString() {
        return name;
    }
}

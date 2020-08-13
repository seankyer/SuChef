package model;

import exceptions.IngredientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {

    Recipe rec1;
    Recipe rec2;
    Recipe rec3;

    InstructionList instructionList1;
    InstructionList instructionList2;
    InstructionList instructionList3;

    IngredientsList ingList1;
    IngredientsList ingList2;
    IngredientsList ingList3;

    Ingredient ingredient1;
    Ingredient ingredient2;
    Ingredient ingredient3;

    Instruction instruction1;
    Instruction instruction2;
    Instruction instruction3;

    @BeforeEach
    public void setup() throws IngredientException {
        instructionList1 = new InstructionList();
        instructionList2 = new InstructionList();
        instructionList3 = new InstructionList();

        instruction1 = new Instruction(1, "Mix Eggs", 0);
        instruction2 = new Instruction(99, "Cook Eggs", 60);
        instruction3 = new Instruction(2, "Smash Eggs", 0);

        instructionList1.addInstruction(instruction1);
        instructionList1.addInstruction(instruction2);
        instructionList1.addInstruction(instruction3);
        instructionList2.addInstruction(instruction1);
        instructionList2.addInstruction(instruction2);
        instructionList2.addInstruction(instruction3);
        instructionList3.addInstruction(instruction1);
        instructionList3.addInstruction(instruction2);
        instructionList3.addInstruction(instruction3);

        ingList1 = new IngredientsList();
        ingList2 = new IngredientsList();
        ingList3 = new IngredientsList();

        ingredient1 = new Ingredient("Egg", 3, "Whole");
        ingredient2 = new Ingredient("Flour", 8, "Cups");
        ingredient3 = new Ingredient("Water", 500, "Millilitres");

        ingList1.addIngredient(ingredient1);
        ingList1.addIngredient(ingredient2);
        ingList1.addIngredient(ingredient3);
        ingList2.addIngredient(ingredient1);
        ingList2.addIngredient(ingredient2);
        ingList2.addIngredient(ingredient3);
        ingList3.addIngredient(ingredient1);
        ingList3.addIngredient(ingredient2);
        ingList3.addIngredient(ingredient3);

        rec1 = new Recipe("test1", ingList1, instructionList1);
        rec2 = new Recipe("test2", ingList2, instructionList2);
        rec3 = new Recipe("test3", ingList3, instructionList3);
    }

    @Test
    public void getNameTest() {
        assertEquals("test1", rec1.getName());
        assertEquals("test2", rec2.getName());
        assertEquals("test3", rec3.getName());
    }

    @Test
    public void displayIngredientsTest() {
        assertEquals(" 1. 3 Whole Egg 2. 8 Cups Flour 3. 500 Millilitres Water", rec1.displayIngredients());
    }

    @Test
    public void displayCurrentInstructionTest() {
        assertEquals(instruction1, rec1.displayCurrentInstruction(1));
        assertEquals(instruction2, rec1.displayCurrentInstruction(2));
        assertEquals(instruction3, rec1.displayCurrentInstruction(3));
    }

    @Test
    public void getLengthofInstructionsListTest() {
        assertEquals(3, rec1.getLengthOfInstructionList());
    }

    @Test
    public void toStringTest() {
        assertEquals("test1", rec1.toString());
    }


}

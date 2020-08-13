package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class RecipesListTest {
    Recipe rec1;
    Recipe rec2;
    Recipe rec3;
    RecipesList recipeList0;

    InstructionList instructionList1;
    InstructionList instructionList2;
    InstructionList instructionList3;

    IngredientsList ingList1;
    IngredientsList ingList2;
    IngredientsList ingList3;

    @BeforeEach
    public void setup() {
        instructionList1 = new InstructionList();
        instructionList2 = new InstructionList();
        instructionList3 = new InstructionList();

        ingList1 = new IngredientsList();
        ingList2 = new IngredientsList();
        ingList3 = new IngredientsList();

        rec1 = new Recipe("test1", ingList1, instructionList1);
        rec2 = new Recipe("test2", ingList2, instructionList2);
        rec3 = new Recipe("test3", ingList3, instructionList3);

        recipeList0 = new RecipesList();
    }

    @Test
    public void addRecipeTest() {
        recipeList0.addRecipe(rec1);
        recipeList0.addRecipe(rec3);

        assertTrue(recipeList0.recipes.contains(rec1));
        assertTrue(recipeList0.recipes.contains(rec3));
        assertFalse(recipeList0.recipes.contains(rec2));
    }

    @Test
    public void displayRecipesTest() {
        recipeList0.addRecipe(rec1);
        recipeList0.addRecipe(rec2);
        recipeList0.addRecipe(rec3);

        assertEquals(" 1. test1, 2. test2, 3. test3,", recipeList0.displayRecipes());
    }

    @Test
    public void getRecipesTest() {
        recipeList0.addRecipe(rec1);
        recipeList0.addRecipe(rec2);
        recipeList0.addRecipe(rec3);

        assertEquals(rec2, recipeList0.getRecipe(2));
        assertEquals(rec1, recipeList0.getRecipe(1));
        assertEquals(rec3, recipeList0.getRecipe(3));
    }

    @Test
    public void removeRecipeTest() {
        recipeList0.addRecipe(rec1);
        recipeList0.addRecipe(rec2);
        recipeList0.addRecipe(rec3);
        recipeList0.removeRecipe(rec2);

        assertFalse(recipeList0.recipes.contains(rec2));
        assertTrue(recipeList0.recipes.contains(rec1));
        assertTrue(recipeList0.recipes.contains(rec3));
    }

}

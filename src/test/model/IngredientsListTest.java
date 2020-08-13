package model;

import exceptions.IngredientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientsListTest {

    IngredientsList ingList1;
    IngredientsList ingList2;
    IngredientsList ingList3;

    Ingredient ingredient1;
    Ingredient ingredient2;
    Ingredient ingredient3;

    @BeforeEach
    public void setup() throws IngredientException {
        ingList1 = new IngredientsList();
        ingList2 = new IngredientsList();
        ingList3 = new IngredientsList();

        ingredient1 = new Ingredient("Egg", 3, "Whole");
        ingredient2 = new Ingredient("Flour", 8, "Cups");
        ingredient3 = new Ingredient("Water", 500, "Millilitres");
    }

    @Test
    public void addIngredientTest() {
        ingList1.addIngredient(ingredient1);
        ingList2.addIngredient(ingredient1);
        ingList2.addIngredient(ingredient2);

        assertTrue(ingList1.containsIngredient(ingredient1));
        assertTrue(ingList2.containsIngredient(ingredient1));
        assertTrue(ingList2.containsIngredient(ingredient2));
        assertFalse(ingList2.containsIngredient(ingredient3));
        assertFalse(ingList3.containsIngredient(ingredient1));
    }

    @Test
    public void removeIngredientTest() {
        ingList1.addIngredient(ingredient1);
        ingList1.addIngredient(ingredient2);
        ingList1.addIngredient(ingredient3);
        ingList2.addIngredient(ingredient2);
        ingList3.addIngredient(ingredient1);
        ingList3.addIngredient(ingredient2);
        ingList3.addIngredient(ingredient3);

        ingList1.removeIngredient(ingredient1);
        ingList2.removeIngredient(ingredient2);
        ingList3.removeIngredient(ingredient2);

        assertFalse(ingList1.containsIngredient(ingredient1));
        assertFalse(ingList2.containsIngredient(ingredient2));
        assertFalse(ingList3.containsIngredient(ingredient2));
        assertTrue(ingList1.containsIngredient(ingredient2));
    }

    @Test
    public void containsIngredientTest() {
        ingList1.addIngredient(ingredient1);
        ingList1.addIngredient(ingredient2);
        ingList1.addIngredient(ingredient3);
        ingList3.addIngredient(ingredient1);
        ingList3.addIngredient(ingredient2);


        assertTrue(ingList1.containsIngredient(ingredient1));
        assertTrue(ingList1.containsIngredient(ingredient2));
        assertTrue(ingList1.containsIngredient(ingredient3));
        assertFalse(ingList2.containsIngredient(ingredient1));
        assertFalse(ingList2.containsIngredient(ingredient2));
        assertFalse(ingList2.containsIngredient(ingredient3));
        assertTrue(ingList3.containsIngredient(ingredient1));
        assertTrue(ingList3.containsIngredient(ingredient2));
        assertFalse(ingList3.containsIngredient(ingredient3));
    }

    @Test
    public void groceryListTest() {
        ingList1.addIngredient(ingredient1);
        ingList1.addIngredient(ingredient2);
        ingList1.addIngredient(ingredient3);
        ingList2.addIngredient(ingredient2);
        ingList3.addIngredient(ingredient1);
        ingList3.addIngredient(ingredient2);

        assertEquals(" 1. 3 Whole Egg 2. 8 Cups Flour 3. 500 Millilitres Water", ingList1.groceryList());
        assertEquals(" 1. 8 Cups Flour", ingList2.groceryList());
        assertEquals(" 1. 3 Whole Egg 2. 8 Cups Flour", ingList3.groceryList());
    }
}

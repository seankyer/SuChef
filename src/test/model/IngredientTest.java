package model;

import exceptions.IngredientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

    Ingredient ingredient1;
    Ingredient ingredient2;
    Ingredient ingredient3;

    @BeforeEach
    public void setup() throws IngredientException {
        ingredient1 = new Ingredient("Egg", 3, "Whole");
        ingredient2 = new Ingredient("Flour", 8, "Cups");
        ingredient3 = new Ingredient("Water", 500, "Millilitres");
    }

    @Test
    public void getIngredientNameTest() {
        assertEquals("Egg", ingredient1.getIngredientName());
        assertEquals("Flour", ingredient2.getIngredientName());
        assertEquals("Water", ingredient3.getIngredientName());
    }

    @Test
    public void getIngredientAmountTest() {
        assertEquals(3, ingredient1.getIngredientAmount());
        assertEquals(8, ingredient2.getIngredientAmount());
        assertEquals(500, ingredient3.getIngredientAmount());
    }

    @Test
    public void getIngredientUnitTest() {
        assertEquals("Whole", ingredient1.getIngredientUnit());
        assertEquals("Cups", ingredient2.getIngredientUnit());
        assertEquals("Millilitres", ingredient3.getIngredientUnit());
    }

}

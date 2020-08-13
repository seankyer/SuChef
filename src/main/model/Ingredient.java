package model;

import exceptions.IngredientException;

import java.io.Serializable;

// Represents an ingredient as apart of an ingredients list. An ingredient has a name, amount, and units
public class Ingredient implements Serializable {

    String name;
    int amount;
    String unit;

    // REQUIRES: name, amount and units must not be empty. All Strings must start with capital
    // EFFECTS: Creates grocery object, throws IngredientException if Ingredient fails to construct
    public Ingredient(String name, int amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    // EFFECTS: Returns name of Ingredient
    public String getIngredientName() {
        return this.name;
    }

    // EFFECTS: Returns amount of ingredient
    public int getIngredientAmount() {
        return this.amount;
    }

    // EFFECTS: Returns units of given Ingredient
    public String getIngredientUnit() {
        return this.unit;
    }
}

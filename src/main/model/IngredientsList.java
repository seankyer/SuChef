package model;

import java.io.Serializable;
import java.util.ArrayList;

// Represents an ingredients list that comprises part of a recipe
public class IngredientsList implements Serializable {

    // Add ingredient will add a type of ingredient String with a quantity String + int/double
    // remove ingredient will remove an ingredient of given index to amend for user error.
    // Return grocery list for user to see their shopping list

    ArrayList<Ingredient> ingredients;

    // EFFECTS: Constructs an IngredientList
    public IngredientsList() {
        ingredients = new ArrayList<Ingredient>();
    }

    // REQUIRES: Ingredient must be instantiated
    // MODIFIES: this
    // EFFECTS: Adds a given ingredient to list of ingredients
    public void addIngredient(Ingredient ing) {
        ingredients.add(ing);
    }

    // REQUIRES: Ingredient must be in the IngredientsList
    // MODIFIES: this
    // EFFECTS: Removes given ingredient from IngredientsList
    public void removeIngredient(Ingredient ing) {
        ingredients.remove(ing);
    }

    // EFFECTS: Returns true if Ingredient is present in IngredientsList
    public Boolean containsIngredient(Ingredient ing) {
        return ingredients.contains(ing);
    }

    // REQUIRES: IngredientsList must have at least one Ingredient in it
    // EFFECTS: Return a grocery list consisting of all items on ingredient list in String form
    public String groceryList() {
        String grocery;
        int pos;

        grocery = "";
        for (int i = 0; i <= (ingredients.size() - 1); i++) {
            pos = (i + 1);
            grocery = grocery.concat(" " + pos + ". "
                    + ingredients.get(i).amount + " "
                    + ingredients.get(i).unit + " "
                    + ingredients.get(i).name);
        }
        return grocery;
    }
}

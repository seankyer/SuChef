package model;

import java.io.Serializable;
import java.util.ArrayList;

// Represents the list of all of the users recipes
public class RecipesList implements Serializable {

    public ArrayList<Recipe> recipes;

    // REQUIRES: There can only ever be one RecipeList object at one given time
    // EFFECTS: Creates a RecipeList Object
    public RecipesList() {
        this.recipes = new ArrayList<Recipe>();
    }

    // MODIFIES: this
    // EFFECTS: adds given recipe to this.recipes
    public void addRecipe(Recipe r) {
        this.recipes.add(r);
    }

    // MODIFIES: this
    // EFFECTS: removes given recipe from this.recipes
    public void removeRecipe(Recipe r) {
        this.recipes.remove(r);
    }

    // REQUIRES: 0 < 1 < length of this.recipes
    // EFFECTS: Given an integer, returns that recipe in the list of recipes
    public Recipe getRecipe(int i) {
        return this.recipes.get(i - 1);
    }

    // EFFECTS: Displays list of recipe names
    public String displayRecipes() {
        int pos;
        String recipe;

        recipe = "";
        for (int i = 0; i <= (recipes.size() - 1); i++) {
            pos = (i + 1);
            recipe = recipe + (" " + pos + ". "
                    + recipes.get(i).getName()) + ",";
        }
        return recipe;
    }

}

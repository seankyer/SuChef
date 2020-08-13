package persistence;

import model.RecipesList;
import java.io.Serializable;

// Represents a save which initializes the RecipesList variable
public class Save implements Serializable {
    RecipesList savedRecipes;
}

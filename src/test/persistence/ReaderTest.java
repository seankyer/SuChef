package persistence;

import model.RecipesList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {

    String path = "./data/Test1.txt";
    Reader reader;
    RecipesList testRecipesList;

    @BeforeEach
    void setup() {
        reader = new Reader();
        testRecipesList = new RecipesList();
        try {
            testRecipesList = reader.readData(testRecipesList, path);
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            fail("ClassNotFoundException not allowed in Setup");
        }
    }

    @Test
    void recipeListSavedTest() {
        assertEquals(" 1. Test Recipe, 2. Apple Sauce,", testRecipesList.displayRecipes());
    }
}

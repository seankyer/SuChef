package persistence;

import model.IngredientsList;
import model.InstructionList;
import model.Recipe;
import model.RecipesList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {

    String path;
    Writer writer;
    Reader reader;
    RecipesList testRecipesList;
    RecipesList readableTest;
    IngredientsList ingredients1;
    InstructionList instructions1;
    Recipe test1;
    Recipe test2;
    Recipe test3;
    Recipe test4;
    PrintWriter fileClear;

    @BeforeEach
    void setup() throws FileNotFoundException {
        testRecipesList = new RecipesList();
        test1 = new Recipe("test1", ingredients1, instructions1);
        test2 = new Recipe("test2", ingredients1, instructions1);
        test3 = new Recipe("test3", ingredients1, instructions1);
        test4 = new Recipe("test4", ingredients1, instructions1);

        testRecipesList.addRecipe(test1);
        testRecipesList.addRecipe(test2);
        testRecipesList.addRecipe(test3);
        testRecipesList.addRecipe(test4);

        path = "./data/Test2.txt";
        writer = new Writer();
        reader = new Reader();
        readableTest = new RecipesList();

        fileClear = new PrintWriter(path);
        fileClear.print("");
        fileClear.close();
    }

    @Test
    void savingToFileTest() {
        try {
            writer.saveData(testRecipesList, path);
        } catch (IOException e) {
            fail("IOException not allowed in savingToFileTest");
        }
    }

    @Test
    void readableSaveFileTest() throws IOException, ClassNotFoundException {
        writer.saveData(testRecipesList, path);
        readableTest = reader.readData(readableTest, path);

        assertEquals(" 1. test1, 2. test2, 3. test3, 4. test4,", readableTest.displayRecipes());
    }
}

package persistence;

import model.RecipesList;

import java.io.*;

// Represents a reader which is capable of streaming a .txt file into usable object data
public class Reader implements Serializable {

    // EFFECTS: Constructs a reader object
    public Reader() {

    }


    // REQUIRES: ./data/UserSave.txt must not be empty
    // MODIFIES: yourRecipesList
    // EFFECTS: Reads object saved in save file and returns the object to be saved to yourRecipesList
    public RecipesList readData(RecipesList yourRecipesList, String path) throws IOException, ClassNotFoundException {
        Save save = new Save();

        File f = new File(path);
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);

        save = (Save) ois.readObject();
        yourRecipesList = save.savedRecipes;
        return yourRecipesList;
    }


}

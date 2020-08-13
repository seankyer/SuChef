package persistence;

import model.RecipesList;

import java.io.*;

// https://www.youtube.com/watch?v=qo9S2CeoqQE
// Represents a writer which is capable of writing object data to a .txt file to save user data
public class Writer implements Serializable {

    // EFFECTS: Constructs writer object
    public Writer() {

    }

    // MODIFIES: ./data/UserSave.txt (save file)
    // EFFECTS: Overwrites current save file with sessions yourRecipesList
    public void saveData(RecipesList yourRecipesList, String path) throws IOException {
        Save obj = new Save();
        obj.savedRecipes = yourRecipesList;

        File f = new File(path);
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
    }
}

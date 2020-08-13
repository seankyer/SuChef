package ui;

import exceptions.IngredientException;
import exceptions.NoTimerException;

import java.io.IOException;
import java.io.Serializable;

// for Terminal Application
import static java.lang.Integer.valueOf;
import static ui.TerminalApp.startTerminalApp;

public class Main implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IngredientException,
            NoTimerException {
//        startTerminalApp();

        Gui myGui = new Gui("Su Chef");
        myGui.setVisible(true);
    }
}

package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// Represents the graphical user interface which greets the user on startup
public class Gui extends JFrame implements ActionListener {

    // EFFECTS: Creates JFrame for landing page
    public Gui(String title) throws IOException {
        super(title);

        // Sets Chef Hat Image
        this.setIconImage(ImageIO.read(new File("./data/SuChefHat.jpg")));

        this.setSize(700, 190);
        this.setLocation(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setUpGreeting(this);

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setBackground(Color.YELLOW);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GREEN));

        // Create Bottom Panel
        JButton readButton = new JButton("View Recipes");
        JButton writeButton = new JButton("Write new Recipe");

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new LineBorder(Color.BLACK, 3));
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainContainer.add(bottomPanel, BorderLayout.SOUTH);

        writeButton.addActionListener(this);
        writeButton.setActionCommand("Write");

        readButton.addActionListener(this);
        readButton.setActionCommand("Read");

        bottomPanel.add(readButton);
        bottomPanel.add(writeButton);
    }

    // EFFECTS: Creates greeting messages for landing page
    public void setUpGreeting(Gui gui) {
        JLabel welcomeMessage = new JLabel();
        welcomeMessage.setText("Su Chef");
        welcomeMessage.setFont(new Font("Calibri", Font.BOLD, 44));

        JLabel logLine = new JLabel();
        logLine.setText("Your Personal Cooking Assistant");
        logLine.setFont(new Font("Calibri", Font.ITALIC, 24));

        gui.add(logLine, SwingConstants.CENTER);
        logLine.setAlignmentX(Component.CENTER_ALIGNMENT);
        gui.add(welcomeMessage, SwingConstants.CENTER);
        welcomeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // MODIFIES: this
    // EFFECTS: Observes buttons on landing page
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Write")) {
            try {
                startUpWriter();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else if (action.equals("Read")) {
            try {
                startUpReader();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    // EFFECTS: Creates JFrame for RecipeWriter
    public void startUpWriter() throws IOException {
        NewRecipe writerGUI = new NewRecipe();
        writerGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        writerGUI.setSize(400, 300);
        writerGUI.setTitle("Recipe Writer");
        writerGUI.setIconImage(ImageIO.read(new File("./data/SuChefHat.jpg")));
        writerGUI.setVisible(true);
    }

    // EFFECTS: Creates JFrame for RecipeReader
    public void startUpReader() throws IOException {
        RecipeSelector selectorGUI = new RecipeSelector();
        selectorGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        selectorGUI.setSize(750, 500);
        selectorGUI.setTitle("Recipe Selector");
        selectorGUI.setIconImage(ImageIO.read(new File("./data/SuChefHat.jpg")));
        selectorGUI.setVisible(true);
    }
}
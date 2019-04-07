
/**
 * Menu.java
 * @author Jordan Fitzgerald
 * @date Feb 18, 2019
 */

import java.awt.Panel;
import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

public class Menu extends Panel {
    private static final int PADDING = 30;

    private ArrayList<Button> buttons;
    private GridBagLayout grid;

    /**
     * Adds a button to the panel and sets up the correct sizing. This is not
     * particularly efficient but it's called a total only three times when the
     * Menu class is instantiated. So it's not really a big deal.
     */
    private void createButton(String text) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipady = PADDING;

        Button newButton = new Button(text);

        newButton.setActionCommand(text);
        newButton.addActionListener(GameWindow.instance);

        buttons.add(newButton);

        // Loop through the buttons and set the correct weights.
        int index = 0;
        for (Button button : buttons) {
            constraints.weightx = 1.0d / (double) buttons.size();
            constraints.gridx = index;

            grid.setConstraints(button, constraints);

            index++;
        }

        add(newButton);
    }

    /**
     * Constructor for Menu. Creates a collection for buttons and creates the
     * buttons themselves.
     */
    Menu() {
        buttons = new ArrayList<>();

        grid = new GridBagLayout();
        setLayout(grid);

        createButton("New Game");
        createButton("Reset");
        createButton("Quit");

        setVisible(true);
    }
}

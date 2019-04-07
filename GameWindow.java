
/**
 * @author Kim Buckner
 * Date: Feb 01, 2017
 *
 * This is the actual "game". Will have to make some major changes.
 * This is just a "hollow" shell.
 *
 * When you get done, I should see the buttons at the top in the "play" area
 * (NOT a pull-down menu). The only one that should do anything is Quit.
 *
 * Should also see something that shows where the 4x4 board and the "spare"
 * tiles will be when we get them stuffed in.
 *
 * This COULD be part of a package but I choose to make the starting point NOT a
 * package. However all other added elements can certainly be sub-packages, and
 * probably should be. 
 * 
 * Mar 6, 2019: Added method to handle tile movement
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameWindow extends JFrame implements ActionListener {
    public static final long serialVersionUID = 1;

    public static GameWindow instance = new GameWindow();

    public static int TILE_SIZE = 100;

    private GridBagLayout mainGrid;
    private Menu menu;

    private MazeReader reader;

    private Tile selectedTile = null;

    private Maze board;

    private Shelf rightShelf;
    private Shelf leftShelf;

    private GameWindow() {
        super("Group Mira aMaze");

        final int screenHeight = (int) Toolkit.getDefaultToolkit()
                .getScreenSize().getHeight();

        if (screenHeight < 1000) {
            TILE_SIZE = (screenHeight - 100) / 10;
        }
    }

    /**
     * For the buttons
     * 
     * @param e is the ActionEvent
     * 
     *          BTW can ask the event for the name of the object generating
     *          event. The odd syntax for non-java people is that "exit" for
     *          instance is converted to a String object, then that object's
     *          equals() method is called.
     */

    public void actionPerformed(ActionEvent e) {
        if ("Quit".equals(e.getActionCommand())) {
            System.exit(0);
        }

        if ("Reset".equals(e.getActionCommand())) {
            leftShelf.reset();
            board.reset();
            rightShelf.reset();
        }

        if ("New Game".equals(e.getActionCommand())) {

        }
    }

    /**
     * Establishes the initial board
     */
    public void setUp() {
        try {
            reader = new MazeReader("../input/default.mze");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Error reading default maze file.", "Error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(0);
        }

        mainGrid = new GridBagLayout();
        menu = new Menu();

        setLayout(mainGrid);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = 0;
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = .3;
        constraints.weighty = .1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;

        mainGrid.setConstraints(menu, constraints);

        add(menu);

        leftShelf = new Shelf(0);

        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weighty = .9;
        constraints.anchor = GridBagConstraints.SOUTHWEST;

        mainGrid.setConstraints(leftShelf, constraints);

        add(leftShelf);

        board = new Maze();
        board.loadTiles(null);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;

        mainGrid.setConstraints(board, constraints);

        add(board);

        rightShelf = new Shelf(8);

        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.SOUTHEAST;

        mainGrid.setConstraints(rightShelf, constraints);

        add(rightShelf);
    }

    public void handleTileSelection(Tile tile) {
        // selectedTile, if not null, is the first tile we clicked in the
        // placing sequence. i.e., it's the one we're going to place when
        // we click again.

        // Handles all other swap attempts
        if (selectedTile != null && tile.getInPlay()) {
            // Not sure if the selected tile should become
            // unselected when trying to swap tiles
            selectedTile.resetBackground();
            selectedTile = null;

            tile.resetBackground();

            return;
        }

        if (selectedTile == null && tile.getInPlay()) {
            selectedTile = tile;
        } else if (selectedTile != null) {
            boolean newInPlay = tile.getInPlay();

            tile.setPosition(selectedTile.getPosition());
            tile.setInPlay(selectedTile.getInPlay());

            selectedTile.setInPlay(newInPlay);
            selectedTile.resetBackground();

            selectedTile = null;

            tile.resetBackground();
        }
    }

    public MazeReader getMazeReader() {
        return reader;
    }
};
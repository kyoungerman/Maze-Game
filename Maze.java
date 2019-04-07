
/**
 * Maze.java
 * @author Addison Dugal, Jordan Fitzgerald
 * @date Feb 18, 2019
 */

import java.util.*;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Maze extends Panel {
    private ArrayList<Tile> tiles;
    private boolean solved = false;

    private GridBagLayout grid;

    Maze() {
        grid = new GridBagLayout();
        grid.columnWidths = grid.rowHeights = new int[] { GameWindow.TILE_SIZE,
                GameWindow.TILE_SIZE, GameWindow.TILE_SIZE,
                GameWindow.TILE_SIZE };
        setLayout(grid);
    }

    void loadTiles(String folderName) {
        tiles = new ArrayList<>();

        int id = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                Tile tile = new Tile(id, false);

                GridBagConstraints constraints = new GridBagConstraints();

                constraints.gridx = x;
                constraints.gridy = y;
                constraints.fill = GridBagConstraints.BOTH;

                grid.setConstraints(tile, constraints);

                add(tile);
                tiles.add(tile);

                id++;
            }
        }

        setVisible(true);
    }

    public void update() {
        for (Tile tile : tiles) {
            if (!tile.getInPlay() || tile.getID() != tile.getPosition()) {
                solved = false;
                return;
            }
        }

        solved = true;
    }

    public void reset() {
        for (Tile tile : tiles) {
            tile.setPosition(tile.getID());
            tile.setInPlay(false);
            tile.resetBackground();
        }
    }
}
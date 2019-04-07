
/**
 * Shelf.java
 * @author Jordan Fitzgerald
 * @date Feb 18, 2019
 */

import java.awt.Panel;
import java.util.ArrayList;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

public class Shelf extends Panel {
    private ArrayList<Tile> tiles;
    private GridBagLayout grid;

    Shelf(int idOffset) {
        tiles = new ArrayList<>();
        grid = new GridBagLayout();

        grid.columnWidths = new int[] { GameWindow.TILE_SIZE };
        grid.rowHeights = new int[] { GameWindow.TILE_SIZE + 5,
                GameWindow.TILE_SIZE + 5, GameWindow.TILE_SIZE + 5,
                GameWindow.TILE_SIZE + 5, GameWindow.TILE_SIZE + 5,
                GameWindow.TILE_SIZE + 5, GameWindow.TILE_SIZE + 5,
                GameWindow.TILE_SIZE };

        setLayout(grid);

        for (int i = 0; i < 8; i++) {
            Tile tile = new Tile(idOffset + i, true);

            GridBagConstraints constraints = new GridBagConstraints();

            constraints.gridy = i;
            constraints.fill = GridBagConstraints.BOTH;

            if (i + 1 < 8)
                constraints.insets = new Insets(0, 0, 5, 0);

            grid.setConstraints(tile, constraints);

            add(tile);

            tiles.add(tile);
        }
    }

    void reset() {
        for (Tile tile : tiles) {
            tile.setPosition(tile.getID());
            tile.setInPlay(true);
            tile.resetBackground();
        }
    }
}

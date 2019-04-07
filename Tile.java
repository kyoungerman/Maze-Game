
/**
 * Tile.java
 * @author Kevin Youngerman, Addison Dugal, Jordan Fitzgerald
 * @date Feb 15, 2019
 * 
 * Mar 6, 2019:  Removed images, added colors and text display
 * Mar 29, 2019: Updates colors, added gridlines, removed text
 */

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Tile extends JLabel implements MouseListener {
    private static final Color BLANK = new Color(100, 100, 100);
    private static final Color IN_PLAY = Color.WHITE;
    private static final Color SELECTED = new Color(160, 160, 160);

    private int rotation = 0;
    private int id;
    private int position;
    private boolean inPlay;
    private boolean shelved;

    private ArrayList<Line2D.Float> lines;

    Tile(int id, boolean inPlay) {
//        super(Integer.toString(id), SwingConstants.CENTER);

        this.id = id;

        // Only tiles constructed with inPlay=true
        // are on the shelves.
        shelved = inPlay;

        setBackground(IN_PLAY);

        setOpaque(true);
        setVisible(true);
//        setFont(new Font(getFont().getName(), Font.PLAIN, 30));

        addMouseListener(this);

        setPosition(id);
        setInPlay(inPlay);
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        final float scale = (float) GameWindow.TILE_SIZE / 100.0f;

        graphics2D.scale(scale, scale);

        if (!inPlay) {
            // Grid lines
            graphics2D.setColor(Color.DARK_GRAY);

            graphics2D.drawLine(0, 0, 0, 99);
            graphics2D.drawLine(0, 0, 99, 0);

            // The four rightmost tiles will meet this condition
            // and therefore will have a line draw on the right
            // side.
            if ((id + 1) % 4 == 0 || shelved) {
                graphics2D.drawLine(99, 0, 99, 99);
            }

            // The bottom four tiles will have a bottom grid
            // line.
            if (id > 11 || shelved) {
                graphics2D.drawLine(0, 99, 99, 99);
            }
        } else {
            graphics2D.setStroke(new BasicStroke(2));
            graphics2D.setColor(Color.BLACK);

            for (Line2D.Float line : lines) {
                graphics2D.draw(line);
            }
        }
    }

    // rotates a tile piece 90 degrees.
    void rotate() {
        rotation = rotation + 90;
    }

    // -----Setter methods----//
    void setPosition(int pos) {
//        setText(Integer.toString(pos));
        position = pos;
        lines = GameWindow.instance.getMazeReader().getPiece(pos);
    }

    void setInPlay(boolean inPlay) {
        this.inPlay = inPlay;

        if (inPlay) {
            setForeground(Color.BLACK);
        } else {
            setBackground(BLANK);
            setForeground(BLANK);
        }
    }

    // ----Accessor methods----//
    int getID() {
        return id;
    }

    int getRotation() {
        return rotation;
    }

    int getPosition() {
        return position;
    }

    boolean getInPlay() {
        return inPlay;
    }

    public void resetBackground() {
        if (inPlay) {
            setBackground(IN_PLAY);
        } else {
            setBackground(BLANK);
        }
    }

    public void mousePressed(MouseEvent event) {
        if (inPlay) {
            setBackground(SELECTED);
        }

        GameWindow.instance.handleTileSelection(this);
    }

    // The rest of these are necessary to implement the
    // MouseListener but we don't need to use them.

    public void mouseClicked(MouseEvent event) {
    }

    public void mouseEntered(MouseEvent event) {
    }

    public void mouseExited(MouseEvent event) {
    }

    public void mouseReleased(MouseEvent event) {
    }
};
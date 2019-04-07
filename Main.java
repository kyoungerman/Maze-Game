/**
 * @author Kim Buckner
 * Date: Feb 01, 2017
 *
 *
 * A starting point for the COSC 3011 programming assignment
 * Probably need to fix a bunch of stuff, but this compiles and runs.
 *
 * This COULD be part of a package but I choose to make the starting point NOT a
 * package. However all other added elements can certainly be sub-packages, and
 * probably should be. 
 *
 * Main should NEVER do much more than this in any program that is
 * user-interface intensive, such as this one.
 * 
 *  Maybe it will work now on Windows with Eclipse 2018-12 and Java11
 */

//package game;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // have to override the default layout to reposition things!!!!!!!
        
        GameWindow.instance.setSize(new Dimension(900, 1000));

        // So the debate here was, do I make the GameWindow object the game
        // or do I make main() the game, manipulating a window?
        // Should GameWindow methods know what they store?
        // Answer is, have the "game" do it.

        GameWindow.instance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameWindow.instance.getContentPane().setBackground(new Color(80, 80, 80));
        GameWindow.instance.setUp();

        // May or may not need this

        GameWindow.instance.setVisible(true);

        // You will HAVE to read some documentation and catch exceptions so get
        // used
        // to it.

        try {
            UIManager.setLookAndFeel(
                    "javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException e) {
            // handle possible exception
        } catch (ClassNotFoundException e) {
            // handle possible exception
        } catch (InstantiationException e) {
            // handle possible exception
        } catch (IllegalAccessException e) {
            // handle possible exception
        }

    }

};

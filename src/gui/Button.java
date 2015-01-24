package gui;

import java.awt.Rectangle;

import processing.core.*;
import static gui.StrandedApplet.*;

public class Button {
    //hitbox for the button
    private Rectangle rect;
    //picture of the button
    private PImage image;
    
    public Button(int x, int y, int width, int height, PImage image) {
        rect = new Rectangle(x, y, width, height);
        this.image = image;
    }
    
    /**
     * @param x coordinate of mouse click
     * @param y coordinate of mouse click
     * @return true if the coordinates are in the button
     */
    public boolean isClicked(float x, float y) {
        return rect.contains((int)x, (int)y);
    }
}

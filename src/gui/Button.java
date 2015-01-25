package gui;

import java.awt.Rectangle;

import processing.core.*;
import static gui.StrandedApplet.*;

public class Button {
    //hitbox for the button
    private Rectangle rect;
    //picture of the button
    private PImage image;
    
    /**
     * @param x coordinate of top-left corner in relation to the control box
     * @param y coordinate of top-left corner in relation to the control box
     * @param width of button
     * @param height of button
     * @param image of button
     */
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
        //sets the incoming mouse coordinates to be relative to the control box
        x -= (GAME_WIDTH - SIDEBAR_WIDTH);
        y -= (GAME_HEIGHT - SIDEBAR_HEIGHT);
        return rect.contains((int)x, (int)y);
    }
    
    public PImage getImage() {
        return image;
    }
    
    public int getx() {
        return rect.x;
    }
    
    public int gety() {
        return rect.y;
    }
    
    public int getWidth() {
        return rect.width;
    }
    
    public int getHeight() {
        return rect.height;
    }
}

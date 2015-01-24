package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import processing.core.*;

public class StrandedApplet extends PApplet {
    
    // Sizes of "frames" on the screen
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    public static final int VIEW_HEIGHT = 400;
    public static final int VIEW_WIDTH = 800;
    public static final int TERMINAL_HEIGHT = 200;
    public static final int TERMINAL_WIDTH = 500;
    public static final int CONTROL_HEIGHT = 200;
    public static final int CONTROL_WIDTH = 300;
    
    // Locations of "frames" on the screen
    public static final int VIEW_X = 0;
    public static final int VIEW_Y = 0;
    public static final int TERMINAL_X = 0;
    public static final int TERMINAL_Y = GAME_HEIGHT - TERMINAL_HEIGHT;
    public static final int CONTROL_X = TERMINAL_WIDTH;
    public static final int CONTROL_Y = GAME_HEIGHT - CONTROL_HEIGHT;
    
    private PImage background;

    private Printer printer;
    
    public void setup() {
        printer = new Printer(this);
        
        background = loadImage("pictures/background.png");
        
        //temp
        printer.print("some text");
        printer.print("more text");
        printer.print("third text");
        printer.print("fourth text");
        
        size(GAME_WIDTH, GAME_HEIGHT);
    }
    
    public void draw() {
        background(0);
        fill(255, 0, 0);
        ellipse(width / 2, height / 2, 50, 50);
        
        // Draw Background
        image(background, 0, 0);
        
        // Draw View
        pushMatrix();
            translate(VIEW_X, VIEW_Y);
        popMatrix();
        
        // Draw Terminal
        pushMatrix();
            translate(TERMINAL_X, TERMINAL_Y);
            printer.draw();
        popMatrix();
        
        // Draw Control
        pushMatrix();
            translate(CONTROL_X, CONTROL_Y);
        popMatrix();
    }
}

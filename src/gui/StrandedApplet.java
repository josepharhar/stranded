package gui;

import processing.core.*;

public class StrandedApplet extends PApplet {
    
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    public static final int TERMINAL_HEIGHT = 200;
    public static final int TERMINAL_WIDTH = 400;

    private Printer printer;
    
    public void setup() {
        printer = new Printer(this);
        
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
        
        pushMatrix();
            translate(0, TERMINAL_HEIGHT);
            printer.draw();
        popMatrix();
    }
}

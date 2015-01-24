package gui;

import processing.core.*;

public class StrandedApplet extends PApplet {
    
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Printer printer;
    
    public void setup() {
        printer = new Printer(this);
        
        //temp
        printer.print("some text");
        printer.print("more text");
        
        size(WIDTH, HEIGHT);
    }
    
    public void draw() {
        background(0);
        fill(255, 0, 0);
        ellipse(width / 2, height / 2, 50, 50);
        
        printer.draw();
    }
}

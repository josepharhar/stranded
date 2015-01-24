package gui;

import processing.core.*;

public class StrandedApplet extends PApplet {
    //This is my change
    public static void main(String[] args) {
        PApplet.main(new String[] { "--present", "MyProcessingSketch" });
    }
    
    public void setup() {
        size(800, 600);
    }
    
    public void draw() {
        background(0);
        fill(255, 0, 0);
        ellipse(width / 2, height / 2, 50, 50);
    }
}

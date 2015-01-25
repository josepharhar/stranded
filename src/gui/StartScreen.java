package gui;

import java.util.LinkedList;
import java.util.List;

import processing.core.PVector;

public class StartScreen {
    
    private StrandedApplet applet;
    
    private List<Star> stars;
    
    public StartScreen(StrandedApplet applet) {
        this.applet = applet;
        stars = new LinkedList<Star>();
    }
    
    // Represents one of the stars
    class Star {
        public PVector loc;
        public PVector velocity;
        public float r;
        public float a;
        
        public Star(float x, float y, float r) {
            loc = new PVector(x, y);
//            velocity = new PVector()
            this.r = r;
            this.a = 1;
        }
        
        public void move() {
            
        }
    }
    
}

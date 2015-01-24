package gui;

import java.util.ArrayList;
import java.util.List;

import processing.core.*;

public class Printer {
    //The PApplet to draw to
    private StrandedApplet applet;
    
    private List<String> textQueue;
    private PFont font;
    
    public Printer(StrandedApplet applet) {
        this.applet = applet;
        
        //Set up the text font and size once here
        //applet.textSize(32);
        font = applet.createFont("Monospaced.plain", 16);
        applet.textFont(font);
        
        textQueue = new ArrayList<String>();
    }
    
    public void print(String text) {
        if (textQueue.size() > 3) {
            textQueue.remove(0);
            textQueue.add(text);
        }
    }
    
    public void clear() {
        textQueue.clear();
    }
    
    /**
     * To be called every frame
     * @precondition The screen has been translated to the
     *          correct location for the "textbox" on screen
     */
    public void draw() {
        applet.text("asdf", 10, 10);
    }
}

package gui;

import java.util.ArrayList;
import java.util.List;

import processing.core.*;

import static gui.StrandedApplet.*;

public class Printer {
    //Maximum number of lines to print out at once
    public static final int MAX_TERMINAL_LINES = 3;
    
    //The PApplet to draw to
    private StrandedApplet applet;
    
    //The List that contains all of the text
    //the front of the list is the bottom, or newest, line
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
        if (textQueue.size() >= MAX_TERMINAL_LINES) {
            textQueue.remove(0);
        }
        textQueue.add(text);
    }
    
    public void clear() {
        textQueue.clear();
    }
    
    /**
     * To be called every frame
     * @precondition The screen has been translated to the
     *               top-left corner of the "terminal"
     */
    public void draw() {
        //green color for text
        applet.fill(0, 255, 0);
        for (int i = 0; i < textQueue.size(); i++) {
            applet.text(textQueue.get(i), 0, 12 * i);
        }
    }
}

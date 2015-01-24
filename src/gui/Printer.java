package gui;

import java.util.ArrayList;
import java.util.List;

import processing.core.*;

import static gui.StrandedApplet.*;

public class Printer {
    //Maximum number of lines to print out at once
    public static final int MAX_TERMINAL_LINES = 10;
    //Maximum number of chars before wrapping the line
    public static final int MAX_TERMINAL_WIDTH = 50;
    
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
        font = applet.createFont("Monospaced.bold", 18);
        applet.textFont(font);
        
        textQueue = new ArrayList<String>();
    }
    
    public void print(String text) {
        if (textQueue.size() >= MAX_TERMINAL_LINES) {
            textQueue.remove(textQueue.size() - 1);
        }
        textQueue.add(0, text);
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
        //translate to the bottom of the terminal
        applet.translate(0, TERMINAL_HEIGHT);
        //green color for text
        applet.fill(0, 255, 0);
        for (int i = 0; i < textQueue.size(); i++) {
            String output = "";
            if (i == 0) {
                output += ">";
            } else {
                output += " ";
            }
            output += textQueue.get(i);
            applet.text(output, 10, -18 * i - 14);
        }
    }
}

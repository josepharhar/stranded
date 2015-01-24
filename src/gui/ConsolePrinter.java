package gui;

import java.util.ArrayList;
import java.util.List;

import processing.core.*;

import static gui.StrandedApplet.*;

public class ConsolePrinter {
    //Maximum number of lines to print out at once
    public static final int MAX_TERMINAL_LINES = 10;
    //Maximum number of chars before wrapping the line
    public static final int MAX_TERMINAL_WIDTH = 43;
    
    //The PApplet to draw to
    private StrandedApplet applet;
    
    //The List that contains all of the text
    //the front of the list is the bottom, or newest, line
    private List<TextLine> textQueue;
    private PFont font;
    
    public ConsolePrinter(StrandedApplet applet) {
        this.applet = applet;
        
        //Set up the text font and size once here
        font = applet.createFont("Monospaced.bold", 18);
        applet.textFont(font);
        
        textQueue = new ArrayList<TextLine>();
    }
    
    public void print(String text, int color) {
        print(text, color, false);
    }
    
    private void print(String text, int color, boolean isWrapped) {
        if (text.length() > MAX_TERMINAL_WIDTH) {
            int splitPos = MAX_TERMINAL_WIDTH;
            while (text.charAt(splitPos) != ' ') {
                splitPos--;
            }
            print(text.substring(0, splitPos), color, false);
            print(text.substring(splitPos), color, true);
            return;
        }
        if (textQueue.size() >= MAX_TERMINAL_LINES) {
            textQueue.remove(textQueue.size() - 1);
        }
        textQueue.add(0, new TextLine(text, color, isWrapped));
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
        
        boolean usedCarrot = false;
        for (int i = 0; i < textQueue.size(); i++) {
            String output = "";
            if (!usedCarrot && !textQueue.get(i).isWrapped) {
                usedCarrot = true;
                if ((System.currentTimeMillis() / 500) % 2 == 0) {
                    output += ">";
                } else {
                    output += " ";
                }
            } else {
                output += " ";
            }
            output += textQueue.get(i).text;
            
            applet.fill(textQueue.get(i).lineColor);
            applet.text(output, 10, -18 * i - 14);
        }
    }
    
    // Class that represents a line of text and includes formatting
    class TextLine {
        //color int determined by processing
        public int lineColor;
        
        //is this line part of a wrapped line?
        public boolean isWrapped;
        
        public String text;
        
        public TextLine(String text, int lineColor, boolean isWrapped) {
            this.text = text;
            this.lineColor = lineColor;
            this.isWrapped = isWrapped;
        }
    }
}

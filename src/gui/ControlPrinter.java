package gui;

import static gui.StrandedApplet.*;
import main.Game;
import processing.core.*;

public class ControlPrinter {

    public int taskPosition = 0;

    //The PApplet to draw to
    private StrandedApplet applet;
    
    //The game to get data from
    private Game game;
    
    private PFont font;
    
    public ControlPrinter(StrandedApplet applet, Game game) {
        this.applet = applet;
        this.game = game;

        //Set up the text font and size once here
        //already done by ConsolePrinter
//        font = applet.createFont("Monospaced.bold", 18);
//        applet.textFont(font);
    }
    
    public void draw() {
        //translate to the bottom-left text position
        applet.translate(20, CONTROL_HEIGHT - 100);
        if (game.tasks.size() > 0) {
            applet.text(game.tasks.get(taskPosition).getName(), 0, 0);
        } else {
            applet.text("No tasks available", 0, 0);
        }
    }
    
}

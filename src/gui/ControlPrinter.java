package gui;

import static gui.StrandedApplet.*;
import main.Game;
import processing.core.*;
import tasks.Task;

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
        if (taskPosition >= game.tasks.size() && taskPosition != 0) {
            taskPosition--; 
        }
        //translate to the bottom-left text position
        applet.translate(20, SIDEBAR_HEIGHT);
        
        if (game.tasks.size() > 0) {
            Task currentTask = game.tasks.get(taskPosition);
            
            applet.text("Task: " + currentTask.getName(), 0, -100);
//            applet.text("Cost: " + currentTask.getCost(), 0, 80);
//            applet.text("Reward", x, y);
//            applet.text("Penalty: " + currentTask.getP)
            applet.text("Difficulty: " + currentTask.getDifficulty(), 0, -80);
            applet.text("Skill: " + currentTask.getPrimarySkill(), 0, -60);
        } else {
            applet.text("No tasks available", 0, 0);
        }
    }
    
}

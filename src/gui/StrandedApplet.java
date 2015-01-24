package gui;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import main.Game;
import processing.core.*;
import tasks.Task;
import audio.*;

public class StrandedApplet extends PApplet {
       
    // Sizes of "frames" on the screen
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    public static final int VIEW_HEIGHT = 400;
    public static final int VIEW_WIDTH = 800;
    public static final int TERMINAL_HEIGHT = 200;
    public static final int TERMINAL_WIDTH = 500;
    public static final int CONTROL_HEIGHT = 200;
    public static final int CONTROL_WIDTH = 300;
    
    // Locations of "frames" on the screen
    public static final int VIEW_X = 0;
    public static final int VIEW_Y = 0;
    public static final int TERMINAL_X = 0;
    public static final int TERMINAL_Y = GAME_HEIGHT - TERMINAL_HEIGHT;
    public static final int CONTROL_X = TERMINAL_WIDTH;
    public static final int CONTROL_Y = GAME_HEIGHT - CONTROL_HEIGHT;

    private Game game;
    
    private PImage background;

    public ConsolePrinter consolePrinter;
    public ControlPrinter controlPrinter;
    
    private Button leftButton;
    private Button centerButton;
    private Button rightButton;
    
    public Audio mainAudio;
    
    public static void main(String[] args) {
        PApplet.main(new String[] { "--present", "gui.StrandedApplet" });
    }
    
    public void setup() {
        game = new Game(this);
        
        consolePrinter = new ConsolePrinter(this);
        controlPrinter = new ControlPrinter(this, game);
        
        leftButton = new Button(26, 156, 72, 32, loadImage("pictures/leftButton.png"));
        centerButton = new Button(leftButton.getx() + leftButton.getWidth() + 16, 156, 72, 32, loadImage("pictures/centerButton.png"));
        rightButton = new Button(centerButton.getx() + centerButton.getWidth() + 16, 156, 72, 32, loadImage("pictures/rightButton.png"));
        
        background = loadImage("pictures/background.png");
        
        mainAudio = new Audio(this);
        
        try {
            mainAudio.startMainAudio();
        } catch (IOException e) {
             //TODO Auto-generated catch block
            e.printStackTrace();
        }
        //mainAudio.startMainAudio();
        
        size(GAME_WIDTH, GAME_HEIGHT);
        
        game.start();
    }
    
    public void draw() {
        // Draw Background
        image(background, 0, 0);
        
        // Draw View
        pushMatrix();
            translate(VIEW_X, VIEW_Y);
        popMatrix();
        
        // Draw Terminal
        pushMatrix();
            translate(TERMINAL_X, TERMINAL_Y);
            consolePrinter.draw();
        popMatrix();
        
        // Draw Control
        pushMatrix();
            translate(CONTROL_X, CONTROL_Y);
            image(leftButton.getImage(), leftButton.getx(), leftButton.gety());
            image(centerButton.getImage(), centerButton.getx(), centerButton.gety());
            image(rightButton.getImage(), rightButton.getx(), rightButton.gety());
            controlPrinter.draw();
        popMatrix();
        
        game.updateTasks();
        
    }
    
    public void mousePressed() {
        float x = mouseX;
        float y = mouseY;
        if (leftButton.isClicked(x, y)) {
            System.out.println("left");
            controlPrinter.taskPosition -= 1;
            if (controlPrinter.taskPosition < 0) {
                controlPrinter.taskPosition = game.tasks.size() - 1;
            }
            this.mainAudio.sideBeep();
        } else if (centerButton.isClicked(x, y)) {
            System.out.println("center");
            if (game.tasks.size() > 0) {
                game.assignTask(game.tasks.get(controlPrinter.taskPosition), game.characters.get(0));
            }
            controlPrinter.taskPosition -= 1;
            if (controlPrinter.taskPosition < 0) {
                controlPrinter.taskPosition = game.tasks.size() - 1;
            }
            this.mainAudio.centerBeep();
        } else if (rightButton.isClicked(x, y)) {
            System.out.println("right");
            controlPrinter.taskPosition += 1;
            if (controlPrinter.taskPosition >= game.tasks.size()) {
                controlPrinter.taskPosition = 0;
            }
            this.mainAudio.sideBeep();
        }
    }
    
}

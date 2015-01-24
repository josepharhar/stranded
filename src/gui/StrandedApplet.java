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
    

    private int taskPosition = 0;
    private Game game;
    
    private PImage background;

    public Printer printer;
    
    private Button leftButton;
    private Button centerButton;
    private Button rightButton;
    
    public Audio mainAudio;
    
    public static void main(String[] args) {
        PApplet.main(new String[] { "--present", "gui.StrandedApplet" });
    }
    
    public void setup() {
        printer = new Printer(this);
        leftButton = new Button(26, 156, 72, 32, loadImage("pictures/leftButton.png"));
        centerButton = new Button(leftButton.getx() + leftButton.getWidth() + 16, 156, 72, 32, loadImage("pictures/centerButton.png"));
        rightButton = new Button(centerButton.getx() + centerButton.getWidth() + 16, 156, 72, 32, loadImage("pictures/rightButton.png"));
        
        background = loadImage("pictures/background.png");
        
        mainAudio = new Audio(this);
        
        //temp
        for (int i = 0; i < 15; i++) {
            printer.print("text #" + i);
        }
        try {
            mainAudio.startMainAudio();
        } catch (IOException e) {
             //TODO Auto-generated catch block
            e.printStackTrace();
        }
        //mainAudio.startMainAudio();
        
        size(GAME_WIDTH, GAME_HEIGHT);
        
        game = new Game(this);
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
            printer.draw();
        popMatrix();
        
        // Draw Control
        pushMatrix();
            translate(CONTROL_X, CONTROL_Y);
            image(leftButton.getImage(), leftButton.getx(), leftButton.gety());
            image(centerButton.getImage(), centerButton.getx(), centerButton.gety());
            image(rightButton.getImage(), rightButton.getx(), rightButton.gety());
        popMatrix();
        
        game.updateTasks();
        
        if (game.tasks.size() > 0) {
            text(game.tasks.get(taskPosition).getName(), 525, 450);
        } else {
            text("No tasks available", 525, 450);
        }
    }
    
    public void mousePressed() {
        float x = mouseX;
        float y = mouseY;
        if (leftButton.isClicked(x, y)) {
            System.out.println("left");
            taskPosition -= 1;
            if (taskPosition < 0) {
                taskPosition = game.tasks.size() - 1;
            }
            this.mainAudio.sideBeep();
        } else if (centerButton.isClicked(x, y)) {
            System.out.println("center");
            if (game.tasks.size() > 0) {
                game.assignTask(game.tasks.get(taskPosition), game.characters.get(0));
            }
            taskPosition -= 1;
            if (taskPosition < 0) {
                taskPosition = game.tasks.size() - 1;
            }
            this.mainAudio.centerBeep();
        } else if (rightButton.isClicked(x, y)) {
            System.out.println("right");
            taskPosition += 1;
            if (taskPosition >= game.tasks.size()) {
                taskPosition = 0;
            }
            this.mainAudio.sideBeep();
        }
    }
    
}

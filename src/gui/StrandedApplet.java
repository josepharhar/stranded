package gui;

import java.io.IOException;

import main.Game;
import processing.core.*;
import audio.*;

public class StrandedApplet extends PApplet {
       
    // Sizes of "frames" on the screen
    //game is the ENTIRE size
    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = 600;
    public static final int VIEW_HEIGHT = 400;
    public static final int VIEW_WIDTH = 800;
    public static final int TERMINAL_HEIGHT = 200;
    public static final int TERMINAL_WIDTH = 700;
    public static final int SIDEBAR_HEIGHT = 600;
    public static final int SIDEBAR_WIDTH = 300;
    
    // Locations of "frames" on the screen
    public static final int VIEW_X = 0;
    public static final int VIEW_Y = 0;
    public static final int TERMINAL_X = 0;
    public static final int TERMINAL_Y = GAME_HEIGHT - TERMINAL_HEIGHT;
    public static final int SIDEBAR_X = TERMINAL_WIDTH;
    public static final int SIDEBAR_Y = 0;

    private Game game;
    
    private PImage background;

    public ConsolePrinter consolePrinter;
    public ControlPrinter controlPrinter;
    
    public SidebarItem currentSidebar;
    public TaskList taskList;
    public CharacterList characterList;
    //public JobDetail jobDetail;
    
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
        
        taskList = new TaskList(this, game);
        characterList = new CharacterList(this, game);
        currentSidebar = taskList;
        
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
        
        if (frame != null) {
            frame.setResizable(true);
            frame.setSize(GAME_WIDTH, GAME_HEIGHT);
        }
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
        
        // Draw Sidebar
        pushMatrix();
            translate(SIDEBAR_X, SIDEBAR_Y);
            currentSidebar.draw();
//            image(leftButton.getImage(), leftButton.getx(), leftButton.gety());
//            image(centerButton.getImage(), centerButton.getx(), centerButton.gety());
//            image(rightButton.getImage(), rightButton.getx(), rightButton.gety());
//            controlPrinter.draw();
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
            if (game.tasks.size() > 0 && game.characters.size() > 0) {
                game.assignTask(game.tasks.get(controlPrinter.taskPosition), game.characters.get(0));
                controlPrinter.taskPosition -= 1;
                if (controlPrinter.taskPosition < 0) {
                    controlPrinter.taskPosition = Math.max(game.tasks.size() - 1, 0);
                }
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

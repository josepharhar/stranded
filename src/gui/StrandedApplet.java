package gui;

import java.io.IOException;

import main.Game;
import processing.core.*;
import audio.*;
import static main.Resource.*;

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
    
    // int that represents what screen should be shown
    // 0 is the start screen, 1 is the game, 2 is endgame
    private int gameStage;
    
    private StartScreen startScreen;

    private Game game;
    
    private PImage background;

    public ConsolePrinter consolePrinter;
    public ControlPrinter controlPrinter;
    
    public SidebarItem currentSidebar;
    public TaskList taskList;
    public CharacterList characterList;
    //public JobDetail jobDetail;
    
    private Button dialog;
    
    public Audio mainAudio;
    
    public static void main(String[] args) {
        PApplet.main(new String[] { "--present", "gui.StrandedApplet" });
    }
    
    public void setup() {
        gameStage = 0;
        startScreen = new StartScreen(this);
        
        game = new Game(this);
        
        consolePrinter = new ConsolePrinter(this);
        controlPrinter = new ControlPrinter(this, game);
        
        taskList = new TaskList(this, game);
        characterList = new CharacterList(this, game);
        currentSidebar = taskList;
        
        background = loadImage("pictures/background.png");
        
        mainAudio = new Audio(this);
        
        try {
            mainAudio.startMainAudio();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //mainAudio.startMainAudio();
        
        if (frame != null) {
            frame.setResizable(true);
            frame.setSize(GAME_WIDTH, GAME_HEIGHT);
        }
        size(GAME_WIDTH, GAME_HEIGHT);
    }
    
    public void draw() {
        if (gameStage == 1) {
            drawGame();
        } else if (gameStage == 0) {
            startScreen.draw();
        }
    }
    
    public void mousePressed() {
        if (gameStage == 1) {
            clickGame();
        } else if (gameStage == 0) {
            game.start();
            gameStage = 1;
        }
    }
    
    // Called when the screen is clicked during gameStage = 1
    private void clickGame() {
        //click dialog
        if (dialog != null) {
            if (dialog.isClicked(mouseX, mouseY)) {
                dialog = null;
            }
        }
        
        //click sidebar
        float x = mouseX - SIDEBAR_X;
        float y = mouseY - SIDEBAR_Y;
        currentSidebar.click(x, y);
    }
    
    // Draws and runs the main game
    private void drawGame() {
        // Draw Background
        image(background, 0, 0);
        
        // Draw View
        pushMatrix();
            translate(VIEW_X, VIEW_Y);
            drawView();
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
        popMatrix();
        
        // Draw Dialog
        if (dialog != null) {
            dialog.draw(this);
        }
        
        game.updateTasks();
    }
    
    private void drawView() {
        textAlign(CENTER, CENTER);
        textSize(18);
        
        int x = 400;
        int y = 30;
        
        //draw rectangle behind text
        fill(0);
        rect(x - 150, y - 20, 300, 300);

        fill(0, 255, 0);
        
        text("Scrap", x, y);
        y += 20;
        text(String.valueOf(game.resources.getResource(SCRAP)), x, y);
        y += 20;
        text("Electronics", x, y);
        y += 20;
        text(String.valueOf(game.resources.getResource(ELECTRONICS)), x, y);
        y += 20;
        text("Fuel", x, y);
        y += 20;
        text(String.valueOf(game.resources.getResource(FUEL)), x, y);
        y += 20;
        text("Station Health", x, y);
        y += 20;
        text(String.valueOf(game.resources.getResource(STATION_HEALTH)), x, y);
        y += 20;
        text("Station Defenses", x, y);
        y += 20;
        text(String.valueOf(game.resources.getResource(STATION_DEFENSES)), x, y);
        y += 20;
        text("Morale", x, y);
        y += 20;
        text(String.valueOf(game.resources.getResource(MORALE)), x, y);
        y += 20;
        
        
    }
    
}

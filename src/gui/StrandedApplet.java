package gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.io.IOException;

import main.Game;
import processing.core.PApplet;
import timing.GameTimer;
import audio.Audio;

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
    // 0 is the start screen, 1 is the game, 2 is endgame, 3 is start screen with story text
    public int gameStage;

    public TaskList taskList;
    public CharacterList characterList;
    public SidebarItem currentSidebar;
    
    private StartScreen startScreen;
    public GameScreen gameScreen;
    private EndScreen endScreen;

    public Game game;

    public ConsolePrinter consolePrinter;
    
    public Audio mainAudio;
    
    public StrandedApplet(Frame strandedFrame) {
        super.frame = strandedFrame;
    }
    
    public void setup() {
        game = new Game(this);
        
        startScreen = new StartScreen(this);
        gameScreen = new GameScreen(this);
        endScreen = new EndScreen(this);
        
        consolePrinter = new ConsolePrinter(this);
        
        mainAudio = new Audio(this);

        try {
            mainAudio.startMainAudio();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //mainAudio.startMainAudio();

        size(GAME_WIDTH, GAME_HEIGHT);
        
        //change some frame settings now that the embedded processing applet is in the frame
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
    
    public void draw() {
        GameTimer.update();
        if (gameStage == 1) {
            gameScreen.draw();
        } else if (gameStage == 0) {
            startScreen.drawStart();
        } else if (gameStage == 2) {
            endScreen.draw();
        } else if (gameStage == 3) {
            startScreen.drawStory();
        }
    }
    
    public void mousePressed() {
        if (GameTimer.isPaused()) {
            GameTimer.startTime();
        }
        if (gameStage == 1) {
            gameScreen.clickGame();
        } else if (gameStage == 0) {
            //game.start();
            gameStage = 3;
        } else if (gameStage == 3) {
            game.start();
            gameStage = 1;
        }
    }
    
    public void keyPressed() {
        if ((key == 'p' || key == 'P') && gameStage == 1) {
            GameTimer.stopTime();
        }
        //THIS IS TEMPORARY
        if (key == '|') {
            game.isWon = true;
            gameStage = 2;
        } else if (key == '}') {
            game.isWon = false;
            gameStage = 2;
        }
    }
    
}

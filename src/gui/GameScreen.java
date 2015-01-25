package gui;

import static main.Resource.ELECTRONICS;
import static main.Resource.FUEL;
import static main.Resource.MORALE;
import static main.Resource.SCRAP;
import static main.Resource.STATION_DEFENSES;
import static main.Resource.STATION_HEALTH;

import java.util.LinkedList;
import java.util.List;

import processing.core.PImage;
import processing.core.PVector;
import timing.DelayedAction;
import timing.GameTimer;

public class GameScreen {
    
    private StrandedApplet applet;
    
    private List<Star> stars;

    private PImage background;
    
    private Button dialog;
    
    public GameScreen(StrandedApplet applet) {
        this.applet = applet;
        stars = new LinkedList<Star>();
        
        background = applet.loadImage("pictures/background.png");

        applet.taskList = new TaskList(applet, applet.game);
        applet.characterList = new CharacterList(applet, applet.game);
        applet.currentSidebar = applet.taskList;
        
        captainFrames = new PImage[5];
        captainFrames[0] = applet.loadImage("pictures/animations/captain_1.png");
        captainFrames[1] = applet.loadImage("pictures/animations/captain_2.png");
        captainFrames[2] = applet.loadImage("pictures/animations/captain_3.png");
        captainFrames[3] = applet.loadImage("pictures/animations/captain_4.png");
        captainFrames[4] = applet.loadImage("pictures/animations/captain_5.png");
        captainAnimationStartTime = GameTimer.getTime();
        
        doorFrames = new PImage[6];
        doorFrames[0] = applet.loadImage("pictures/animations/door_1.png");
        doorFrames[1] = applet.loadImage("pictures/animations/door_2.png");
        doorFrames[2] = applet.loadImage("pictures/animations/door_3.png");
        doorFrames[3] = applet.loadImage("pictures/animations/door_4.png");
        doorFrames[4] = applet.loadImage("pictures/animations/door_5.png");
        doorFrames[5] = applet.loadImage("pictures/animations/door_6.png");
        
        characterFrames = new PImage[5];
        characterFrames[0] = applet.loadImage("pictures/animations/blank.png");
        characterFrames[1] = applet.loadImage("pictures/animations/person_1.png");
        characterFrames[2] = applet.loadImage("pictures/animations/person_2.png");
        characterFrames[3] = applet.loadImage("pictures/animations/person_3.png");
        characterFrames[4] = applet.loadImage("pictures/animations/person_4.png");
        
        animationType = 0;
        animStartTime = GameTimer.getTime();
    }
    
    public void draw() {
        applet.background(0);
        drawStars();
        drawGame();
        drawAnimation();
        if (GameTimer.isPaused()) {
            applet.fill(60, 100);
            applet.rect(0, 0, applet.width, applet.height);
            applet.fill(255);
            applet.textSize(25);
            applet.text("GAME PAUSED", 100, 100);
        }
    }
    
    // Variables for the state of animations
    int charFrameIndex = 0;
    PImage[] characterFrames;
    int[] charxloc;
    int[] charyloc;
    //0 is coming in, 1 is leaving, 2 is waiting, 3 is nobody
    int animationType;
    long animStartTime;
    
    int doorFrameIndex = 0;
    PImage[] doorFrames;
    
    int captainFrameIndex = 0;
    PImage[] captainFrames;
    long captainAnimationStartTime;
    
    public void switchAnimation(int newAnimationType) {
        System.out.println("switching animation to " + newAnimationType);
        animationType = newAnimationType;
        animStartTime = GameTimer.getTime();
    }
    
    // Draws the animation of characters
    private void drawAnimation() {

//        GameTimer.addAction(new DelayedAction(1500) {
//            @Override
//            public void complete() {
//                
//            }
//        });
        
        long currentTime = GameTimer.getTime();
        
        double captainDiff = (currentTime - captainAnimationStartTime) / 1000.0;
        if (captainDiff < 2) {
            captainFrameIndex = 0;
        } else if (captainDiff < 2.5) {
            captainFrameIndex = 1;
        } else if (captainDiff < 3) {
            captainFrameIndex = 2;
        } else if (captainDiff < 3.5) {
            captainFrameIndex = 3;
        } else if (captainDiff < 4) {
            captainFrameIndex = 4;
        } else {
            captainAnimationStartTime = GameTimer.getTime();
        }
        
        double charDiff = (currentTime - animStartTime) / 1000.0;
        if (animationType == 0) {
            if (charDiff < 0.25) {
                charFrameIndex = 0;
                doorFrameIndex = 1;
            } else if (charDiff < 0.5) {
                doorFrameIndex = 2;
            } else if (charDiff < 0.75) {
                doorFrameIndex = 3;
            } else if (charDiff < 1.0) {
                doorFrameIndex = 4;
            } else if (charDiff < 1.25) {
                doorFrameIndex = 5;
                charFrameIndex = 1;
            } else if (charDiff < 1.75) {
                charFrameIndex = 2;
            } else if (charDiff < 2.0) {
                charFrameIndex = 3;
            } else if (charDiff < 2.25) {
                charFrameIndex = 4;
            } else {
                switchAnimation(2);
            }
        } else if (animationType == 1) {
            if (charDiff < 0.25) {
                charFrameIndex = 3;
                doorFrameIndex = 5;
            } else if (charDiff < 0.5) {
                charFrameIndex = 2;
            } else if (charDiff < 0.75) {
                charFrameIndex = 1;
            } else if (charDiff < 1.0) {
                charFrameIndex = 0;
                doorFrameIndex = 4;
            } else if (charDiff < 1.25) {
                doorFrameIndex = 3;
            } else if (charDiff < 1.5) {
                doorFrameIndex = 2;
            } else if (charDiff < 1.75) {
                doorFrameIndex = 1;
            } else {
                switchAnimation(3);
            }
        } else if (animationType == 2) {
            doorFrameIndex = 5;
            charFrameIndex = 4;
        } else {
            if (applet.game.characters.isEmpty()) {
                charFrameIndex = 0;
                doorFrameIndex = 0;
            } else {
                switchAnimation(0);
            }
        }
        
        applet.image(doorFrames[doorFrameIndex], 72, 232);
        applet.image(characterFrames[charFrameIndex], 98, 232);
        applet.image(captainFrames[captainFrameIndex], 568, 255);
        
    }
    
    // Draws and runs the main game
    private void drawGame() {
        // Draw Background
        applet.image(background, 0, 0);
        
        // Draw View
        applet.pushMatrix();
            applet.translate(applet.VIEW_X, applet.VIEW_Y);
            drawView();
        applet.popMatrix();
        
        // Draw Terminal
        applet.pushMatrix();
            applet.translate(applet.TERMINAL_X, applet.TERMINAL_Y);
            applet.consolePrinter.draw();
        applet.popMatrix();
        
        // Draw Sidebar
        applet.pushMatrix();
            applet.translate(applet.SIDEBAR_X, applet.SIDEBAR_Y);
            applet.currentSidebar.draw();
            applet.popMatrix();
        
        // Draw Dialog
        if (dialog != null) {
            dialog.draw(applet);
        }
        
        applet.game.updateTasks();
    }
    
    private void drawView() {
        applet.textAlign(applet.LEFT, applet.CENTER);
        applet.textSize(16);
        
        int x = 300;
        int y = 10;

        applet.fill(255);
        
        int yspacing = 18;
        
        applet.text("SCRAP", x, y);
        y += yspacing;
        applet.text(" " + String.valueOf(applet.game.resources.getResource(SCRAP)), x, y);
        y += yspacing;
        applet.text("ELECTRONICS", x, y);
        y += yspacing;
        applet.text(" " + String.valueOf(applet.game.resources.getResource(ELECTRONICS)), x, y);
        y += yspacing;
        applet.text("FUEL", x, y);
        y += yspacing;
        applet.text(" " + String.valueOf(applet.game.resources.getResource(FUEL)), x, y);
        y += yspacing;
        applet.text("STATION_HEALTH", x, y);
        y += yspacing;
        applet.text(" " + String.valueOf(applet.game.resources.getResource(STATION_HEALTH)), x, y);
        y += yspacing;
        applet.text("STATION_DEFENSES", x, y);
        y += yspacing;
        applet.text(" " + String.valueOf(applet.game.resources.getResource(STATION_DEFENSES)), x, y);
        y += yspacing;
        applet.text("MORALE", x, y);
        y += yspacing;
        applet.text(" " + String.valueOf(applet.game.resources.getResource(MORALE)), x, y);
        y += 30;
        
        applet.textSize(14);
        applet.text("Assigning:", x, y);
        y += yspacing;
        
        //textSize(16);
        if (!applet.game.characters.isEmpty()) {
            applet.text(" " + applet.game.characters.get(0).getName(), x, y);
        } else {
            applet.text(" nobody to assign", x, y);
        }
    }
    
 // Called when the screen is clicked during gameStage = 1
    public void clickGame() {
        //click dialog
        if (dialog != null) {
            if (dialog.isClicked(applet.mouseX, applet.mouseY)) {
                dialog = null;
            }
        }
        
        //click sidebar
        float x = applet.mouseX - applet.SIDEBAR_X;
        float y = applet.mouseY - applet.SIDEBAR_Y;
        applet.currentSidebar.click(x, y);
    }
    
    public void drawStars() {
        // Remove stars
        for (int i = stars.size() - 1; i >= 0; i--) {
            Star star = stars.get(i);
            if (star.loc.x < 200) {
                stars.remove(i);
            }
        }
        
        // Add stars
        if (Math.random() > 0.2 && !GameTimer.isPaused()) {
            float x = 800f;
            float y = applet.random(0, 300);
            float r = applet.random(1f, 2f);
            stars.add(new Star(x, y, r));
        }
        
        // Draw stars
        applet.noStroke();
        applet.fill(255);
        for (Star star : stars) {
            if (!GameTimer.isPaused()) {
                star.move();
            }
            applet.ellipse(star.loc.x, star.loc.y, star.r * 2, star.r * 2);
        }
        
        // Draw text
        applet.textAlign(applet.CENTER, applet.CENTER);
        applet.fill(0, 255, 0);
        applet.textSize(48);
        applet.text("STRANDED", applet.GAME_WIDTH / 2, applet.GAME_HEIGHT / 2);
        applet.textSize(18);
        applet.text("click to start", applet.GAME_WIDTH / 2, applet.GAME_HEIGHT / 2 + 100);
    }
    
    // Represents one of the stars
    class Star {
        public PVector loc;
        public PVector velocity;
        public float r;
        
        public Star(float x, float y, float r) {
            loc = new PVector(x, y);
            this.r = r;
            velocity = new PVector((r-.2f) * (r-.2f) * -2, 0);
        }
        
        public void move() {
            //velocity.mult(1 + (r / 100));
            loc.add(velocity);
        }
    }
    
}

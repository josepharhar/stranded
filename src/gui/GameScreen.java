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
    }
    
    public void draw() {
        applet.background(0);
        drawStars();
        drawGame();
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
//        String nextLine = "";
//        if ((System.currentTimeMillis() / 500) % 2 == 0) {
//            nextLine += ">";
//        } else {
//            nextLine += " ";
//        }
//        textSize(14);
//        nextLine += " What do we do now?";
//        text(nextLine, x, y);
//        y += yspacing;
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
            if (star.loc.x - star.r > applet.GAME_WIDTH / 2 ||
                    star.loc.x + star.r < -applet.GAME_WIDTH / 2 ||
                    star.loc.y - star.r > applet.GAME_HEIGHT / 2 ||
                    star.loc.y + star.r < -applet.GAME_HEIGHT / 2) {
                stars.remove(i);
            }
        }
        
        // Add stars
        if (Math.random() > 0.5) {
            float x = applet.random(-50, 50);
            float y = applet.random(-50, 50);
            float r = applet.random(0.5f, 0.7f);
            stars.add(new Star(x, y, r));
        }
        
        // Draw stars
        applet.noStroke();
        applet.fill(255);
        applet.pushMatrix();
            applet.translate(applet.GAME_WIDTH / 2, applet.GAME_HEIGHT / 2);
            for (Star star : stars) {
                star.move();
                applet.ellipse(star.loc.x, star.loc.y, star.r * 2, star.r * 2);
            }
        applet.popMatrix();
        
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
            velocity = new PVector(x, y);
            //velocity.normalize();
            velocity.mult(0.05f);
            this.r = r;
        }
        
        public void move() {
            velocity.mult(1 + (r / 100));
            loc.add(velocity);
            this.r *= 1.01f;
        }
    }
    
}

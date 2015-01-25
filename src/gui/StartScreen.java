package gui;

import java.util.LinkedList;
import java.util.List;

import processing.core.PVector;

public class StartScreen {
    
    private StrandedApplet applet;
    
    private List<Star> stars;
    
    public StartScreen(StrandedApplet applet) {
        this.applet = applet;
        stars = new LinkedList<Star>();
    }
    
    public void drawStart() {
        draw();
        // Draw text
        applet.textAlign(applet.CENTER, applet.CENTER);
        applet.fill(0, 255, 0);
        applet.textSize(48);
        applet.text("STRANDED", applet.GAME_WIDTH / 2, applet.GAME_HEIGHT / 2);
        applet.textSize(18);
        applet.text("click to start", applet.GAME_WIDTH / 2, applet.GAME_HEIGHT / 2 + 100);
    }
    
    public void drawStory() {
        draw();
        // Draw Text
        applet.textAlign(applet.CENTER, applet.CENTER);
        applet.fill(0, 255, 0);
        applet.textSize(24);
        String output = "Good evening Captain! During your adventures in space,\n you and your crew have become trapped on a space station,\n and your ship has become " + 
                "completely ruined!\n In order to escape from this abandoned station,\n you must direct your crew to find and build parts for a new ship.\n" +
                "Issue orders to your crew using the menus,\n and try to build your way to victory!\n But be careful, danger lurks around every corner!\n\nclick to begin...";
        applet.text(output, applet.GAME_WIDTH / 2, 300);
    }
    
    private void draw() {
        applet.background(0);
        
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

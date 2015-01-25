package gui;

import processing.core.*;
import timing.GameTimer;
import gui.StartScreen.Star;

import java.util.LinkedList;
import java.util.List;

public class EndScreen {
    
    private StrandedApplet applet;
    
    private PImage[] escape;
    private PImage[] explode;
    private PImage ship;
    
    private int currentFrame;
    
    private long startTime;
    private long currentTime;
    private double timeDiff;
    
    private List<Star> stars;
    
    public EndScreen(StrandedApplet applet) {
        stars = new LinkedList<Star>();
        
        while (stars.size() < 200) {
            stars.add(new Star(applet.random(0, applet.GAME_WIDTH), applet.random(0, applet.GAME_HEIGHT), applet.random(1f, 2f)));
        }
        
        this.applet = applet;
        
        escape = new PImage[8];
        escape[0] = applet.loadImage("pictures/end/escape_1.png");
        escape[1] = applet.loadImage("pictures/end/escape_2.png");
        escape[2] = applet.loadImage("pictures/end/escape_3.png");
        escape[3] = applet.loadImage("pictures/end/escape_4.png");
        escape[4] = applet.loadImage("pictures/end/escape_5.png");
        escape[5] = applet.loadImage("pictures/end/escape_6.png");
        escape[6] = applet.loadImage("pictures/end/escape_7.png");
        escape[7] = applet.loadImage("pictures/end/escape_8.png");
        
        explode = new PImage[9];
        explode[0] = applet.loadImage("pictures/end/explode_1.png");
        explode[1] = applet.loadImage("pictures/end/explode_2.png");
        explode[2] = applet.loadImage("pictures/end/explode_3.png");
        explode[3] = applet.loadImage("pictures/end/explode_4.png");
        explode[4] = applet.loadImage("pictures/end/explode_5.png");
        explode[5] = applet.loadImage("pictures/end/explode_6.png");
        explode[6] = applet.loadImage("pictures/end/explode_7.png");
        explode[7] = applet.loadImage("pictures/end/explode_8.png");
        explode[8] = applet.loadImage("pictures/end/explode_9.png");
        
        ship = applet.loadImage("pictures/end/ship_big.png");
        
        startTime = System.currentTimeMillis();
    }
    
    public void draw() {
        applet.background(0);
        
        currentTime = System.currentTimeMillis();
        timeDiff = currentTime - startTime;
        
        drawStars();
        
        if (applet.game.isWon) {
            drawWin();
        } else {
            drawLose();
        }
        
    }
    
    private void drawWin() {
        timeDiff = (currentTime - startTime) / 1000.0;
        
        if (timeDiff < 0.5) {
            currentFrame = 0;
        } else if (timeDiff < 1.0) {
            currentFrame = 1;
        } else if (timeDiff < 1.5) {
            currentFrame = 2;
        } else if (timeDiff < 2.0) {
            currentFrame = 3;
        } else if (timeDiff < 2.5) {
            currentFrame = 4;
        } else if (timeDiff < 3.0) {
            currentFrame = 5;
        } else if (timeDiff < 3.5) {
            currentFrame = 6;
        } else if (timeDiff < 4.0) {
            currentFrame = 7;
        }
        
        if (timeDiff < 4.0) {
            applet.image(escape[currentFrame], 400, 300);
        } else {
            applet.fill(0, 255, 0);
            applet.textAlign(applet.CENTER, applet.CENTER);
            applet.textSize(48);
            applet.text("You Escaped!", applet.GAME_WIDTH / 2, applet.GAME_HEIGHT / 2 - 100);
            applet.image(ship, 450, 300);
            moveStars();
        }
    }
    
    private void drawLose() {
        timeDiff = (currentTime - startTime) / 1000.0;
        
        if (timeDiff < 0.5) {
            currentFrame = 0;
        } else if (timeDiff < 1.0) {
            currentFrame = 1;
        } else if (timeDiff < 1.5) {
            currentFrame = 2;
        } else if (timeDiff < 2.0) {
            currentFrame = 3;
        } else if (timeDiff < 2.5) {
            currentFrame = 4;
        } else if (timeDiff < 3.0) {
            currentFrame = 5;
        } else if (timeDiff < 3.5) {
            currentFrame = 6;
        } else if (timeDiff < 4.0) {
            currentFrame = 7;
        } else if (timeDiff < 4.5) {
            currentFrame = 8;
        }
        
        if (timeDiff < 4.5) {
            applet.image(explode[currentFrame], 400, 300);
        } else {
            applet.fill(0, 255, 0);
            applet.textAlign(applet.CENTER, applet.CENTER);
            applet.textSize(48);
            applet.text("Game Over", applet.GAME_WIDTH / 2, applet.GAME_HEIGHT / 2 - 100);
        }
    }
    
    private void moveStars() {
        // Remove stars
        for (int i = stars.size() - 1; i >= 0; i--) {
            Star star = stars.get(i);
            if (star.loc.x < 0) {
                stars.remove(i);
            }
        }
        
        // Add stars
        if (Math.random() > 0.2) {
            float x = applet.GAME_WIDTH + 100f;
            float y = applet.random(0, applet.GAME_HEIGHT);
            float r = applet.random(1f, 2f);
            stars.add(new Star(x, y, r));
        }
        
        // Move stars
        for (Star star : stars) {
            star.move();
        }
    }
    
    private void drawStars() {
        applet.noStroke();
        applet.fill(255);
        for (Star star : stars) {
            applet.ellipse(star.loc.x, star.loc.y, star.r * 2, star.r * 2);
        }
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

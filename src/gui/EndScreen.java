package gui;

import processing.core.*;

public class EndScreen {
    
    private StrandedApplet applet;
    
    private PImage[] escape;
    private PImage[] explode;
    
    private int currentFrame;
    
    private long startTime;
    
    public EndScreen(StrandedApplet applet) {
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
        
        startTime = System.currentTimeMillis();
    }
    
    public void draw() {
//        if (win) {
//            drawWin();
//        } else {
//            drawLose();
//        }
    }
    
    private void drawWin() {
        applet.background(0);
        
        long currentTime = System.currentTimeMillis();
        double timeDiff = (currentTime - startTime) / 1000.0;
        
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
        
        applet.image(escape[currentFrame], 400, 300);
    }
    
    private void drawLose() {
        
    }
}

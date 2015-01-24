package test;

import gui.*;
import processing.core.*;

public class Test extends PApplet {
    public static void main(String[] args) {

    }
    
    public void setup() {
        size(600, 800);
        String[] fonts = PFont.list();
        for (String s : fonts) {
            System.out.println(s);
        }
    }
    
    public void draw() {
        background(128);
    }
}

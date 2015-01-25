package gui;

import main.Game;

public class SidebarItem {
    
    protected String name;
    protected StrandedApplet applet;
    protected Game game;
    
    public SidebarItem(String name, StrandedApplet applet, Game game) {
        this.name = name;
        this.applet = applet;
        this.game = game;
    }
    
    public String getName() {
        return name;
    }
    
    /**
     * Draws the sidebar in the sidebar pane
     * @precondition The applet has translated to the top-left corner of the sidebar
     */
    public void draw() {
        //draw name of the sidebar
        applet.fill(0, 255, 0);
        applet.text(name, 10, 20);
    }
}

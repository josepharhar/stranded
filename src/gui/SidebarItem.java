package gui;

import main.Game;
import static gui.StrandedApplet.*;

public abstract class SidebarItem {
    
    protected String name;
    protected StrandedApplet applet;
    protected Game game;
    
    private Button buttonTasks;
    private Button buttonCharacters;
    
    public SidebarItem(String name, StrandedApplet applet, Game game) {
        this.name = name;
        this.applet = applet;
        this.game = game;
        
        buttonTasks = new Button(applet.SIDEBAR_WIDTH / 2 - 72 - 10, applet.SIDEBAR_HEIGHT - 40, 72, 32, applet.loadImage("pictures/leftButton.png"));
        buttonCharacters = new Button(applet.SIDEBAR_WIDTH / 2 + 10, applet.SIDEBAR_HEIGHT - 40, 72, 32, applet.loadImage("pictures/rightButton.png"));
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
        applet.textAlign(applet.CENTER, applet.TOP);
        applet.textSize(24);
        applet.fill(0, 255, 0);
        applet.text(name, SIDEBAR_WIDTH / 2, 10);
        
        //draw the task and character buttons
        buttonTasks.draw(applet);
        buttonCharacters.draw(applet);
        
        //prepare implementing classes for drawing
        applet.textSize(18);
        applet.textAlign(applet.LEFT, applet.TOP);
    }
    
    //Handles mouse clicks. called when the mouse is clicked inside of the sidebar area
    public void click(float mousex, float mousey) {
        //check to see if mouse clicked on task list or character list buttons
        if (buttonTasks.isClicked(mousex, mousey)) {
            applet.currentSidebar = applet.taskList;
        } else if (buttonCharacters.isClicked(mousex, mousey)) {
            applet.currentSidebar = applet.characterList;
        }
    }
}

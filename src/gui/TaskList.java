package gui;

import main.Game;

public class TaskList extends SidebarItem {
    
    public TaskList(StrandedApplet applet, Game game) {
        super("Task List", applet, game);
    }
    
    public void draw() {
        super.draw();
        
        for (int i = 0; i < game.tasks.size(); i++) {
            applet.text(game.tasks.get(i).getName(), 10, 18 * i);
        }
    }
}

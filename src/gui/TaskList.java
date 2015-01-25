package gui;

import main.Game;

public class TaskList extends SidebarItem {
    
    public TaskList(StrandedApplet applet, Game game) {
        super("Task List", applet, game);
    }
    
    public void draw() {
        super.draw();
        
        for (int i = 0; i < game.tasks.size(); i++) {
            String text = game.tasks.get(i).getName();
            
            //location for text to show up
            int x = 20;
            int y = 30 * i + 40;
            
            //draw rectangle (button) under the text
            applet.fill(128);
            applet.rect(x - 4, y + 2, text.length() * 11 + 4, 23);
            
            applet.fill(0, 255, 0);
            applet.text(game.tasks.get(i).getName(), x, y);
        }
    }
}

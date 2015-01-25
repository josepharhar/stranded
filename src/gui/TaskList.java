package gui;

import java.awt.Rectangle;

import tasks.Task;
import main.Game;

public class TaskList extends SidebarItem {
    
    public TaskList(StrandedApplet applet, Game game) {
        super("Task List", applet, game);
    }
    
    public void draw() {
        super.draw();
        
        //location for text to show up
        int x = 20;
        int y = 10;
        
        for (int i = 0; i < game.tasks.size(); i++) {
            String text = game.tasks.get(i).getName();
            
            y += 40;
            
            //draw rectangle (button) under the text
            applet.strokeWeight(2);
            applet.stroke(192);
            applet.fill(128);
            applet.rect(x - 4, y + 2, text.length() * 11 + 4, 23, 5, 5, 5, 5);
            
            applet.fill(0, 255, 0);
            applet.text(game.tasks.get(i).getName(), x, y);
        }

        for (Task task : game.taskRunner.pendingTasks) {
            String text = task.getName();
            
            y += 40;

            //draw rectangle (button) under the text
            applet.fill(128);
            applet.rect(x - 4, y + 2, text.length() * 11 + 4, 23);
            
            applet.fill(255, 255, 0);
            applet.text(task.getName(), x, y);
        }
    }
    
    public void click(float mousex, float mousey) {
        super.click(mousex, mousey);
        for (int i = 0; i < game.tasks.size(); i++) {
            String text = game.tasks.get(i).getName();
            
            //location for text to show up
            int x = 20;
            int y = 40 * i + 40;
            
            Rectangle button = new Rectangle(x - 4, y + 2, text.length() * 11 + 4, 23);
            
            if (button.contains(mousex, mousey)) {
                applet.currentSidebar = new TaskDetail(applet, game, game.tasks.get(i));
            }
        }
    }
}

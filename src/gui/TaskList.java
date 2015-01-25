package gui;

import java.awt.Rectangle;
import java.util.List;

import tasks.Task;
import main.Game;

public class TaskList extends SidebarItem {
    
    public TaskList(StrandedApplet applet, Game game) {
        super("Task List", applet, game);
    }
    
    public void draw() {
        super.draw();
        
        //location for text to show up
        int x = BUTTON_X_SPACING;
        int y = BUTTON_INIT_SPACING;
        List<Task> taskList = game.tasks.getList();
        for (int i = 0; i < taskList.size(); i++) {
            String text = taskList.get(i).getName();
            
            y += BUTTON_SPACING;
            
            //draw rectangle (button) under the text
            applet.strokeWeight(2);
            applet.stroke(192);
            applet.fill(128);
            applet.rect(x - 4, y + 2, text.length() * 11 + 4, 23, 5, 5, 5, 5);
            
            if (taskList.get(i).isExpires()) {
                applet.fill(255,0,0);
            } else {
                applet.fill(0, 255, 0);
            }
            applet.text(taskList.get(i).getName(), x, y);
        }

        for (Task task : game.taskRunner.pendingTasks) {
            String text = task.getName();
            
            y += BUTTON_SPACING;

            //draw rectangle (button) under the text
            applet.strokeWeight(2);
            applet.stroke(192);
            applet.fill(128);
            applet.rect(x - 4, y + 2, text.length() * 11 + 4, 23);
            
            applet.fill(255, 255, 0);
            applet.text(task.getName(), x, y);
        }
    }
    
    public void click(float mousex, float mousey) {
        super.click(mousex, mousey);
        List<Task> taskList = game.tasks.getList();
        for (int i = 0; i < taskList.size(); i++) {
            String text = taskList.get(i).getName();
            
            //location for text to show up
            int x = 20;
            int y = BUTTON_SPACING * i + BUTTON_INIT_SPACING + BUTTON_SPACING;
            
            Rectangle button = new Rectangle(x - 4, y + 2, text.length() * 11 + 4, 23);
            
            if (button.contains(mousex, mousey)) {
                applet.currentSidebar = new TaskDetail(applet, game, taskList.get(i));
            }
        }
    }
}

package gui;

import main.Game;
import tasks.Task;

public class TaskDetail extends SidebarItem {
    
    //The job that this detail is showing
    private Task task;
    
    public TaskDetail(StrandedApplet applet, Game game, Task task) {
        super("Task Detail", applet, game);
        this.task = task;
    }
    
    public void draw() {
        super.draw();
    }
}

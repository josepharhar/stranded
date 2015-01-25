package gui;

import tasks.Task;

public class TaskDetail extends SidebarItem {
    
    //The job that this detail is showing
    private Task task;
    
    public TaskDetail(StrandedApplet applet, Task task) {
        super("Task Detail", applet);
        this.task = task;
    }
    
    public void draw() {
        super.draw();
    }
}

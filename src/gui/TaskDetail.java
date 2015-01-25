package gui;

import java.util.Map;

import main.Game;
import main.Resource;
import tasks.Task;

public class TaskDetail extends SidebarItem {
    
    //The job that this detail is showing
    private Task task;
    
    private Button buttonAssign;
    
    public TaskDetail(StrandedApplet applet, Game game, Task task) {
        super("Task Detail", applet, game);
        this.task = task;
//        buttonAssign = new Button()
    }
    
    public void draw() {
        super.draw();
        
        int x = 20;
        int y = 40;
        
        //Name
        applet.fill(0, 128, 0);
        applet.text("Task Name: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        applet.text(" " + task.getName(), x, y);

        //Costs
        applet.fill(0, 128, 0);
        applet.text("Costs: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        for (Map.Entry<Resource, Double> cost : task.getCosts().entrySet()) {
            applet.text(" " + cost.getKey() + ": " + cost.getValue(), x, y);
            y += 30;
        }

        //Rewards
        applet.fill(0, 128, 0);
        applet.text("Rewards: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        for (Map.Entry<Resource, Double> reward : task.getRewards().entrySet()) {
            applet.text(" " + reward.getKey() + ": " + reward.getValue(), x, y);
            y += 30;
        }
        
        //Penalties
        applet.fill(0, 128, 0);
        applet.text("Penalties: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        for (Map.Entry<Resource, Double> penalty : task.getPenalty().entrySet()) {
            applet.text(" " + penalty.getKey() + ": " + penalty.getValue(), x, y);
            y += 30;
        }

        //Difficulty
        applet.fill(0, 128, 0);
        applet.text("Difficulty: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        applet.text(" " + task.getDifficulty(), x, y);
        
        //Skill
        applet.fill(0, 128, 0);
        applet.text("Primary Skill: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        applet.text(" " + task.getPrimarySkill(), x, y);
        
        //Expiration
        applet.fill(0, 128, 0);
        applet.text("Expiration: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        applet.text(" " + task.getExpirationTime(), x, y);
    }
    
    public void click(float mousex, float mousey) {
        
    }
}

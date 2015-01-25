package gui;

import java.util.Map;

import main.Game;
import main.Resource;
import tasks.Task;

public class TaskDetail extends SidebarItem {

    // The job that this detail is showing
    private Task task;

    private Button buttonAssign;

    public TaskDetail(StrandedApplet applet, Game game, Task task) {
        super("Task Detail", applet, game);
        this.task = task;
        buttonAssign = new Button(applet.SIDEBAR_WIDTH / 2 - 72,
                applet.SIDEBAR_HEIGHT - 90, 144, 32,
                applet.loadImage("pictures/buttons/assign_button.png"));
    }

    public void draw() {
        super.draw();

        int x = 20;
        int y = 40;

        // Name
        applet.fill(0, 128, 0);
        applet.text("Task Name: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        applet.text(" " + task.getName(), x, y);
        y += 30;

        // Costs
        applet.fill(0, 128, 0);
        applet.text("Costs: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        for (Map.Entry<Resource, Double> cost : task.getCosts().entrySet()) {
            applet.text(" " + cost.getKey() + ": " + cost.getValue(), x, y);
            y += 30;
        }

        // Rewards
        applet.fill(0, 128, 0);
        applet.text("Rewards: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        for (Map.Entry<Resource, Double> reward : task.getRewards().entrySet()) {
            applet.text(" " + reward.getKey() + ": " + reward.getValue(), x, y);
            y += 30;
        }

        // Penalties
        applet.fill(0, 128, 0);
        applet.text("Penalties: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        for (Map.Entry<Resource, Double> penalty : task.getPenalty().entrySet()) {
            applet.text(" " + penalty.getKey() + ": " + penalty.getValue(), x,
                    y);
            y += 30;
        }

        // Difficulty
        applet.fill(0, 128, 0);
        applet.text("Difficulty: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        if (task.getDifficulty() < 0) {
            applet.text(" N/A", x, y);
        } else {
            applet.text(" " + task.getDifficulty(), x, y);
        }
        y += 30;

        // Skill
        applet.fill(0, 128, 0);
        applet.text("Primary Skill: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        applet.text(" " + task.getPrimarySkill(), x, y);
        y += 30;

        // Expiration
        if (task.isExpires()) {
            applet.fill(0, 128, 0);
            applet.text("Expiration: ", x, y);
            y += 30;
            applet.fill(0, 255, 0);
            applet.text(" " + (task.getTimeToExpiration() / 1000L) + " seconds", x, y);
            y += 30;
        }

        // Assign Button
        buttonAssign.draw(applet);
        
        if (task.isExpired()) {
            applet.currentSidebar = applet.taskList;
        }
    }

    public void click(float mousex, float mousey) {
        super.click(mousex, mousey);
        if (buttonAssign.isClicked(mousex, mousey)) {
            if (game.tasks.size() > 0 && game.characters.size() > 0) {
                game.assignTask(task, game.characters.get(0));
            }
            applet.mainAudio.centerBeep();
            applet.gameScreen.switchAnimation(1);
            applet.currentSidebar = applet.taskList;
        }
    }
}

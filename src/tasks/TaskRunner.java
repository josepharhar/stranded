package tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.Game;
import main.Resources;
import characters.Character;

public class TaskRunner {
    private Game game;
    private List<Task> pendingTasks = new ArrayList<Task>();

    public TaskRunner(Game game) {
        this.game = game;
    }
    
    public List<Task> getCompletedTasks() {
        ArrayList<Task> rtn = new ArrayList<>();
        long now = System.currentTimeMillis();
        Iterator<Task> iter = pendingTasks.iterator();
        while (iter.hasNext()) {
            Task t = iter.next();
            if (t.getCompletionTime() < now) {
                rtn.add(t);
                finishTask(t);
                iter.remove();
            }
        }
        return rtn;
    }
    
    public void startTask(Task job) {
        game.resources.subtract(job.getCosts());
        job.setCompletionTime(System.currentTimeMillis() + 60 * 1000L);
        pendingTasks.add(job);
    }

    public void finishTask(Task job) {
        job.setCompleted(true);
        job.setSucceeded(true);
        game.resources.add(job.getRewards());
    }
}

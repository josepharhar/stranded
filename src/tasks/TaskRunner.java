package tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.Game;
import characters.Character;
import characters.Skill;

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
        job.setCompletionTime(System.currentTimeMillis() + 20 * 1000L);
        pendingTasks.add(job);
    }

    public void finishTask(Task job) {
        job.setCompleted(true);
        boolean succeeded = succeeds(job);
        job.setSucceeded(succeeded);
        if (succeeded) {
            game.resources.add(job.getRewards());
            job.getCharacter().levelUp(job);
        } else {
            game.resources.subtract(job.getPenalty());
        }
    }
    
    
    private boolean succeeds(Task job) {
        dp("Checking job " + job.getName() + " with crew " + job.getCharacter().getName());
        Character c = job.getCharacter();
        Skill primarySkill = job.getPrimarySkill();
        Map<Skill, Double> map = c.getSkills();
        double characterSkill = map.get(primarySkill);
        double characterLuck = c.getLuck();
        double difficulty = job.getDifficulty();
        
        double roll = characterSkill + (10 + characterLuck) * Math.random() * Math.random();
        dp("Character rolled: " + roll + " to beat " + (difficulty + 10));
        return roll > difficulty + 10;
    }
    
    private void dp(String s) {
        System.out.println(s);
    }
}

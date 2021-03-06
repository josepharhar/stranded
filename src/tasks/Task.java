package tasks;
import java.util.HashMap;
import java.util.Map;

import main.Resource;
import timing.DelayedAction;
import characters.Character;
import characters.Skill;

public class Task {
    private DelayedAction expirationAction;
    private boolean expired = false;
    private boolean completed = false;
    private boolean succeeded = false;
    private boolean canRetry = false;
    private Character character;
    private String name;
    private Skill primarySkill;
    private int difficulty;
    private int risk;
    private Map<Resource, Double> costs = new HashMap<>();
    private Map<Resource, Double> rewards = new HashMap<>();
    private Map<Resource, Double> penalty = new HashMap<>();
    private Task followUpTask;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public boolean getSucceeded() {
        return succeeded;
    }
    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }
    public Character getCharacter() {
        return character;
    }
    public void setCharacter(Character character) {
        this.character = character;
    }
    public Skill getPrimarySkill() {
        return primarySkill;
    }
    public void setPrimarySkill(Skill primarySkill) {
        this.primarySkill = primarySkill;
    }
    public int getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    public int getRisk() {
        return risk;
    }
    public void setRisk(int risk) {
        this.risk = risk;
    }
    public Map<Resource, Double> getCosts() {
        return costs;
    }
    public void setCosts(Map<Resource, Double> costs) {
        this.costs = costs;
    }
    public Map<Resource, Double> getRewards() {
        return rewards;
    }
    public void setRewards(Map<Resource, Double> rewards) {
        this.rewards = rewards;
    }
    public Map<Resource, Double> getPenalty() {
        return penalty;
    }
    public boolean isExpires() {
        return expirationAction != null;
    }
    public boolean isExpired() {
        return expired;
    }
    public long getTimeToExpiration() {
        if (expirationAction != null) {
            return expirationAction.getTimeRemaining();
        } else {
            return Long.MAX_VALUE;
        }
    }
    public void setExpired(boolean expired) {
        this.expired = expired;
    }
    public boolean getCanRetry() {
        return canRetry;
    }
    public void setCanRetry(boolean canRetry) {
        this.canRetry = canRetry;
    }
    public void setPenalty(Map<Resource, Double> penalty) {
        this.penalty = penalty;
    }
    public Task getFollowUpTask() {
        return followUpTask;
    }
    public void setFollowUpTask(Task followUpTask) {
        this.followUpTask = followUpTask;
    }
    public void setExpirationAction(DelayedAction action) {
        this.expirationAction = action;
    }
}

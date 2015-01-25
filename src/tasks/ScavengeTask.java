package tasks;

import characters.Skill;

public class ScavengeTask extends Task{
    int value;
    
    public ScavengeTask(int value) {
        this.setName("Scavenge");
        //this.setRewards();
    }
    
    public Task getFollowUpTask() {
        return new ScavengeTask(value+1);
    }
    public boolean getCanRetry() {
        return true;
    }
    public Skill getPrimarySkill() {
        return Skill.SCAVENGING;
    }
    
}

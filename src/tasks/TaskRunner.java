package tasks;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.Game;
import characters.Character;
import characters.Skill;

public class TaskRunner {
    private Game game;
    public List<Task> pendingTasks = new ArrayList<Task>();

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
        checkRisk(job.getCharacter(),job);
        if (succeeded) {
            game.resources.add(job.getRewards());
            levelUp(job.getCharacter(),job);
        } else {
            game.resources.subtract(job.getPenalty());
            if(job.getPrimarySkill() == Skill.FIGHTING) {
                survive(job.getCharacter(),job);
            }
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
    
    public void levelUp(Character character, Task job) {
        int difficulty = job.getDifficulty();
        Skill skill = job.getPrimarySkill();
        Map<Skill, Double> map = character.getSkills();
        double characterSkill = map.get(skill);
        double characterLuck = character.getLuck();
        
        double roll = characterSkill + (characterLuck) * (character.getLearning_potential() - 5) * Math.random() * Math.random();
        dp("Character rolled: " + roll/2 + " to beat " + (15 - difficulty) + " and level up.");
        if(roll/2 > 15 - difficulty) {
            map.put(skill,characterSkill+1);
        }
    }
    
    public void survive(Character character, Task job) {
        int difficulty = job.getDifficulty();
        Skill skill = job.getPrimarySkill();
        Map<Skill, Double> map = character.getSkills();
        double characterSkill = map.get(skill);
        double characterLuck = character.getLuck();
        
        double damRoll = 100 - (Math.random()*(2*characterLuck)) - (Math.random()*(characterSkill/2));
        if(damRoll < 0) {
            return;
        }
        character.setHealth((int)(character.getHealth() - damRoll));
        dp("Character's health is now "+ character.getHealth());
        if(character.getHealth() <= 0) {
            game.print("Oh no! " + character.getName() + " has died!",new Color(255,0,0));
            job.setCharacter(null);
        }
    }
    public void checkRisk(Character character, Task job) {
        int risk = job.getRisk();
        
        int roll = (int)Math.random()*20;
        if (risk > roll) {
            double damRoll = 100 - (Math.random()*(5*character.getLuck()));
            if(damRoll < 0) {
                return;
            }
            character.setHealth((int)(character.getHealth() - damRoll));
            dp("Character's health is now "+ character.getHealth() + " due to risk damage!");
            if(character.getHealth() <= 0) {
                game.print("Oh no! " + character.getName() + " has died!",new Color(255,0,0));
                job.setCharacter(null);
            }
        }
    }
    
    private void dp(String s) {
        System.out.println(s);
    }
}

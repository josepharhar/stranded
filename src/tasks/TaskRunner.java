package tasks;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.Game;
import main.Resource;
import characters.Character;
import characters.Skill;

public class TaskRunner {
    private Game game;
    public List<Task> pendingTasks = new ArrayList<Task>();

    public TaskRunner(Game game) {
        this.game = game;
    }
    
    public void startTask(Task job) {
        job.getCharacter().setAvailable(false);
        game.resources.subtract(job.getCosts());
        pendingTasks.add(job);
    }

    public void finishTask(Task job) {
        pendingTasks.remove(job);
        job.getCharacter().setAvailable(true);
        job.setCompleted(true);
        boolean succeeded = succeeds(job);
        job.setSucceeded(succeeded);
        checkRisk(job.getCharacter(),job);
        if (succeeded && job.getCharacter() != null) {
            if (job.getPrimarySkill() != Skill.NONE) {
                game.resources.add(job.getRewards());
                levelUp(job.getCharacter(),job);
            }
            if(job.getRewards().containsKey(Resource.HEALING)) {
                job.getCharacter().setHealth(job.getCharacter().getHealth() + job.getRewards().get(Resource.HEALING).intValue());
            }
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
        if (primarySkill == Skill.NONE) {
            return true;
        }
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
        
        double damRoll = 100 - (Math.random()*(2*characterLuck)) - (Math.random()*(characterSkill/2)) + (difficulty*(Math.random()));
        if(damRoll < 0) {
            return;
        }
        character.setHealth((int)(character.getHealth() - damRoll));
        dp("Character's health is now "+ character.getHealth());
        game.print(character.getName() + " lost " + (int)damRoll + " health and is now at " + character.getHealth() +".");
        if(character.getHealth() <= 0) {
            game.print("Oh no! " + character.getName() + " has died!",new Color(255,0,0));
            job.setCharacter(null);
        }
    }
    public void checkRisk(Character character, Task job) {
        int risk = job.getRisk();
        dp("RIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIISK");
        
        int roll = (int)(Math.random()*20);
        dp("Player rolled "+roll+" over "+risk+" for risk calc.");
        if (risk > roll) {
            double damRoll = 100 - (Math.random()*(5*character.getLuck()));
            if(damRoll < 0) {
                return;
            }
            character.setHealth((int)(character.getHealth() - damRoll));
            dp("Character's health is now "+ character.getHealth() + " due to risk damage!");
            game.print(character.getName() + " lost " + (int)damRoll + " health due to risk and is now at " + character.getHealth() +".");
            if(character.getHealth() <= 0) {
                String deathString = util.RandomDeathAccessor.get();
                game.print("Oh no! " + character.getName() + deathString,new Color(255,0,0));
                job.setCharacter(null);
            }
        }
    }
    
    private void dp(String s) {
        System.out.println(s);
    }
}

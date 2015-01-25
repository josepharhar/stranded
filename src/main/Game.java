package main;

import gui.StrandedApplet;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import tasks.BasicTaskCreator;
import tasks.RandomTaskCreator;
import tasks.RestTask;
import tasks.StorylineTaskCreator;
import tasks.Task;
import tasks.TaskCreator;
import tasks.TaskRunner;
import util.RandomNumberGenerator;
import util.RandomPhraseAccessor;
import characters.BasicCharacterCreator;
import characters.Character;
import characters.CharacterCreator;
import characters.Skill;

public class Game {
    public static Game game;
    
    private StrandedApplet applet;
    public List<Task> tasks = new ArrayList<Task>();
    public List<Character> characters = new ArrayList<Character>();
    public Resources resources = new Resources();
    public TaskRunner taskRunner = new TaskRunner(this);
    private TaskCreator rtg = new RandomTaskCreator();
    private long nextRandomTask;
    
    
    public Game(StrandedApplet applet) {
        this.applet = applet;
        Game.game = this;
    }

    public void start() {
        nextRandomTask = System.currentTimeMillis() + 1000 * (30 + RandomNumberGenerator.getRandomInteger(90));
        TaskCreator storyline = new StorylineTaskCreator();
        tasks.add(storyline.createTask());
        RestTask rest = new RestTask();
        rest.setName("Rest");
        tasks.add(rest);
        TaskCreator taskCreator = new BasicTaskCreator();
        CharacterCreator characterCreator = new BasicCharacterCreator();
        for (int i = 0; i < 4; i++) {
            tasks.add(taskCreator.createTask());
            characters.add(characterCreator.createCharacter());
        }
        promptNextCharacter();
    }
    
    public void promptNextCharacter() {
        Character c = characters.get(0);
        applet.consolePrinter.print(c.getFirstName() + " " + c.getLastName() + ": " + RandomPhraseAccessor.get(), applet.color(0, 128, 0));
        applet.mainAudio.updateBeep();
    }
    
    public void assignTask(Task task, Character character) {
        if (!resources.trySubtract(task.getCosts())) {
            applet.consolePrinter.print("Insufficient Resources", applet.color(255, 255, 0));
            return;
        }
        applet.consolePrinter.print("Task: " + task.getName() + " assigned to character: " + character.getFirstName(), applet.color(255, 255, 0));
        task.setCharacter(character);
        taskRunner.startTask(task);
        tasks.remove(task);
        characters.remove(0);
        if(task.getPrimarySkill() == Skill.FIGHTING) {
            applet.mainAudio.fightSounds();
        }
        updateTasks();
        if (characters.size() > 0) {
            promptNextCharacter();
        }
    }
    
    public void updateTasks() {
        handleRandomTask();
        boolean shouldNotify = characters.size() == 0;
        for (Task t : taskRunner.getCompletedTasks()) {
            if (t.getSucceeded()) {
                applet.consolePrinter.print("Task succeeded: " + t.getName(), applet.color(0, 255, 0));
                if (t.getFollowUpTask() != null) {
                    tasks.add(t.getFollowUpTask());
                }
            } else {
                if (t.getCanRetry()) {
                    applet.consolePrinter.print("Retryable task failed: " + t.getName(), applet.color(255, 0, 0));
                    tasks.add(t);
                } else {
                    applet.consolePrinter.print("Task failed: " + t.getName(), applet.color(255, 0, 0));
                }
            }
            if (t.getCharacter() != null)
               characters.add(t.getCharacter());
        }
        long now = System.currentTimeMillis();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.isExpires() && t.getExpirationTime() < now) {
                t.setCompleted(true);
                t.setSucceeded(false);
                resources.subtract(t.getPenalty());
                applet.consolePrinter.print("Task " + t.getName() + " has expired", applet.color(128,0,0));
                applet.mainAudio.failSound();
                tasks.remove(i);
                i--;
            }
        }
        if (shouldNotify && characters.size() > 0) {
            promptNextCharacter();
        }
    }
    
    private void handleRandomTask() {
        if (nextRandomTask < System.currentTimeMillis()) {
            nextRandomTask = System.currentTimeMillis() + 1000 * (30 + RandomNumberGenerator.getRandomInteger(90));
            try {
                Task t = rtg.createTask();
                tasks.add(t);
                print("Random event caused new task! " + t.getName());
            } catch (Exception ex) {
                System.err.println("Don't worry, just debug msg");
                ex.printStackTrace();
            }
            
        }
    }
    
    public void print(String str) {
        applet.consolePrinter.print(str, applet.color(100,100,100));
    }
    public void print(String str,Color color) {
        applet.consolePrinter.print(str, color.getRGB());
    }
}

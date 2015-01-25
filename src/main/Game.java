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
import timing.GameTimer;
import util.RandomNumberGenerator;
import util.RandomPhraseAccessor;
import characters.BasicCharacterCreator;
import characters.Character;
import characters.CharacterCreator;
import characters.RandomCharacterCreator;
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
    private RandomCharacterCreator rcc = new RandomCharacterCreator();    
    
    public Game(StrandedApplet applet) {
        this.applet = applet;
        GameTimer.initializeTime();
        Game.game = this;
    }

    public void start() {
        GameTimer.startTime();
        tasks.add(new RestTask());
        nextRandomTask = System.currentTimeMillis() + 1000 * (60 + RandomNumberGenerator.getRandomInteger(30));
        TaskCreator storyline = new StorylineTaskCreator();
        tasks.add(storyline.createTask());
        TaskCreator taskCreator = new BasicTaskCreator();
        CharacterCreator characterCreator = new BasicCharacterCreator();
        for (int i = 0; i < 3; i++) {
            characters.add(characterCreator.createCharacter());
        }
        try {
            while (true) {
                tasks.add(taskCreator.createTask());
            }
        } catch (Exception ex) {
            
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
                    if (t.getFollowUpTask() instanceof RestTask) {
                        tasks.add(0, t.getFollowUpTask());
                    } else {
                        tasks.add(t.getFollowUpTask());
                    }
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
            nextRandomTask = System.currentTimeMillis() + 1000 * (10 + RandomNumberGenerator.getRandomInteger(25));
            try {
                if (Math.random() < 0.08) {
                    Character newCrew = rcc.createCharacter();
                    characters.add(newCrew);
                    print(newCrew.getName() + " has joined your crew!", Color.BLUE);
                }
                Task t = rtg.createTask();
                for (Task ot : tasks) {
                    if (ot.getName().equals(t.getName())) return;
                }
                for (Task ot : taskRunner.pendingTasks) {
                    if (ot.getName().equals(t.getName())) return;
                }
                tasks.add(t);
                print("Random event caused new task! " + t.getName());
            } catch (Exception ex) {
                System.err.println("Don't worry, just debug msg");
                ex.printStackTrace();
            }
            
        }
    }
    
    public void checkLoss() {
        if(this.taskRunner.pendingTasks.isEmpty() && this.characters.isEmpty()) {
            //FAIL;
            applet.mainAudio.failJingle();
        }
    }
    
    public void print(String str) {
        applet.consolePrinter.print(str, applet.color(100,100,100));
    }
    public void print(String str,Color color) {
        applet.consolePrinter.print(str, color.getRGB());
    }
}

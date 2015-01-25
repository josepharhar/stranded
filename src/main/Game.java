package main;

import gui.StrandedApplet;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tasks.BasicTaskCreator;
import tasks.RandomTaskCreator;
import tasks.StorylineTaskCreator;
import tasks.Task;
import tasks.TaskCreator;
import tasks.TaskRunner;
import timing.DelayedAction;
import timing.GameTimer;
import util.RandomPhraseAccessor;
import characters.BasicCharacterCreator;
import characters.Character;
import characters.CharacterCreator;
import characters.RandomCharacterCreator;
import characters.Skill;

public class Game {
    public static Game game;
    
    private DelayedAction rta;
    private DelayedAction rca;
    private StrandedApplet applet;
    public AvailableTasks tasks = new AvailableTasks();
    public List<Character> characters = new ArrayList<Character>();
    public Resources resources = new Resources();
    public TaskRunner taskRunner = new TaskRunner(this);
    private TaskCreator rtg = new RandomTaskCreator();
    private RandomCharacterCreator rcc = new RandomCharacterCreator();    
    
    public Game(StrandedApplet applet) {
        this.applet = applet;
        GameTimer.initializeTime();
        Game.game = this;
    }

    public void start() {
        GameTimer.startTime();
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
        
        long timeUntilFirstTask = (long) ((15 + 30 * Math.random()) * 1000L);
        rta = new DelayedAction(timeUntilFirstTask) {
            @Override
            public void complete() {
                addRandomTask();
            }
        };
        GameTimer.addAction(rta);
        
        long timeUntilFirstCharacter = (long) ((120 + 120 * Math.random()) * 1000L);
        rca = new DelayedAction(timeUntilFirstCharacter) {
            @Override
            public void complete() {
                addRandomCharacter();
            }
        };
        GameTimer.addAction(rca);
        
        promptNextCharacter();
    }
    
    public void promptNextCharacter() {
        Character c = characters.get(0);
        applet.consolePrinter.print(c.getName() + ": " + RandomPhraseAccessor.get(), applet.color(0, 128, 0));
        applet.mainAudio.updateBeep();
    }
    
    public void assignTask(final Task task, Character character) {
        if (!resources.trySubtract(task.getCosts())) {
            applet.consolePrinter.print("Insufficient Resources", applet.color(255, 255, 0));
            return;
        }
        applet.consolePrinter.print("Task: " + task.getName() + " assigned to character: " + character.getFirstName(), applet.color(255, 255, 0));
        task.setCharacter(character);
        taskRunner.startTask(task);
        tasks.remove(task);
        long timeToComplete = (long) ((10 + 40*Math.random()) * 1000L);
        GameTimer.addAction(new DelayedAction(timeToComplete) {
            @Override
            public void complete() {
                taskRunner.finishTask(task);
                if (task.getSucceeded() && task.getCharacter() != null) {
                    applet.consolePrinter.print("Task succeeded: " + task.getName(), applet.color(0, 255, 0));
                    if (task.getFollowUpTask() != null) {
                        tasks.add(task.getFollowUpTask());
                    }
                } else {
                    if (task.getCanRetry()) {
                        applet.consolePrinter.print("Retryable task failed: " + task.getName(), applet.color(255, 0, 0));
                        tasks.add(task);
                    } else {
                        applet.consolePrinter.print("Task failed: " + task.getName(), applet.color(255, 0, 0));
                    }
                }
                if (task.getCharacter() != null) {
                   characters.add(task.getCharacter());
                }
            }
        });
        characters.remove(0);
        if(task.getPrimarySkill() == Skill.FIGHTING) {
            applet.mainAudio.fightSounds();
        }
        if (characters.size() > 0) {
            promptNextCharacter();
        }
    }
    
    public void update() {
        if (rca.getTimeRemaining() < -1000) {
            addRandomCharacter();
        }
        if (rta.getTimeRemaining() < -1000) {
            addRandomTask();
        }
        Iterator<Task> iter = tasks.transientTasksIterator();
        while (iter.hasNext()) {
            Task t = iter.next();
            if (t.isExpired()) {
                t.setCompleted(true);
                t.setSucceeded(false);
                resources.subtract(t.getPenalty());
                applet.consolePrinter.print("Task " + t.getName() + " has expired", applet.color(128,0,0));
                applet.mainAudio.failSound();
                iter.remove();
            }
        }
    }
    
    private void addRandomCharacter() {
        Character newCrew = rcc.createCharacter();
        characters.add(newCrew);
        print(newCrew.getName() + " has joined your crew!", Color.BLUE);
        
        long timeUntilNext = (long) ((120 + 120 * Math.random()) * 1000L);
        rca = new DelayedAction(timeUntilNext) {
            @Override
            public void complete() {
                addRandomCharacter();
            }
        };
        GameTimer.addAction(rca);
    }
    
    private void addRandomTask() {
        Task t = rtg.createTask();
        for (Task ot : tasks.getList()) {
            if (ot.getName().equals(t.getName())) return;
        }
        for (Task ot : taskRunner.pendingTasks) {
            if (ot.getName().equals(t.getName())) return;
        }
        tasks.add(t);
        print("Random event caused new task! " + t.getName());
        
        long timeUntilNext = (long) ((15 + 30 * Math.random()) * 1000L);
        rta = new DelayedAction(timeUntilNext) {
            @Override
            public void complete() {
                addRandomTask();
            }
        };
        GameTimer.addAction(rta);
    }
    
    public void checkLoss() {
        if((this.taskRunner.pendingTasks.isEmpty() && this.characters.isEmpty()) || this.resources.getResource(Resource.STATION_HEALTH) == 0) {
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

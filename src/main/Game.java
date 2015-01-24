package main;

import gui.StrandedApplet;

import java.util.ArrayList;
import java.util.List;

import audio.Audio;
import processing.core.PApplet;
import tasks.BasicTaskCreator;
import tasks.Task;
import tasks.TaskCreator;
import tasks.TaskRunner;
import util.RandomPhraseAccessor;
import characters.BasicCharacterCreator;
import characters.Character;
import characters.CharacterCreator;

public class Game {
    private StrandedApplet applet;
    public List<Task> tasks = new ArrayList<Task>();
    public List<Character> characters = new ArrayList<Character>();
    public Resources resources = new Resources(5);
    public TaskRunner taskRunner = new TaskRunner(this);
    
    
    public Game(StrandedApplet applet) {
        this.applet = applet;
    }

    public void start() {
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
        applet.consolePrinter.print(c.getFirstName() + " " + c.getLastName() + ": " + RandomPhraseAccessor.getRandomPhrase());
        applet.mainAudio.updateBeep();
    }
    
    public void assignTask(Task task, Character character) {
        applet.consolePrinter.print("Task: " + task.getName() + " assigned to character: " + character.getFirstName());
        task.addCharacter(character);
        taskRunner.startTask(task);
        tasks.remove(task);
        characters.remove(0);
        updateTasks();
        if (characters.size() > 0) {
            promptNextCharacter();
        }
    }
    
    public void updateTasks() {
        boolean shouldNotify = characters.size() == 0;
        for (Task t : taskRunner.getCompletedTasks()) {
            applet.consolePrinter.print("Task " + (t.isSuccessful() ? "failed" : "succeeded") + ": " + t.getName());
            characters.addAll(t.getCharacters());
        }
        if (shouldNotify && characters.size() > 0) {
            promptNextCharacter();
        }
    }
}

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
    public TaskRunner taskRunner = new TaskRunner();
    
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
        applet.printer.print(c.getFirstName() + " " + c.getLastName() + ": " + RandomPhraseAccessor.getRandomPhrase());
        applet.mainAudio.updateBeep();
    }
    
    public void assignTask(Task task, Character character) {
        applet.printer.print("Task: " + task.getName() + " assigned to character: " + character.getFirstName());
        task.addCharacter(character);
        applet.printer.print("Task: " + task.getName() + " completed with status: " + (taskRunner.completeTask(task) ? "complete" : "failed"));
        characters.add(characters.remove(0));
        promptNextCharacter();
    }
}

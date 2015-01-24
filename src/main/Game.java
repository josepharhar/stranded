package main;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import tasks.BasicTaskCreator;
import tasks.Task;
import tasks.TaskCreator;
import characters.BasicCharacterCreator;
import characters.Character;
import characters.CharacterCreator;

public class Game {
    private List<Task> tasks = new ArrayList<Task>();
    private List<Character> characters = new ArrayList<Character>();
    
    public Game() {
        
    }

    public void start() {
        TaskCreator taskCreator = new BasicTaskCreator();
        CharacterCreator characterCreator = new BasicCharacterCreator();
        for (int i = 0; i < 2; i++) {
            tasks.add(taskCreator.createTask());
            characters.add(characterCreator.createCharacter());
        }
        PApplet.main(new String[] { "--present", "gui.StrandedApplet" });
    }
}

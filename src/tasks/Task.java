package tasks;
import characters.Character;

import java.util.List;
import java.util.ArrayList;

public class Task {
    //task name
    private String name;
    
    //list of characters
    List<Character> characters = new ArrayList<Character>();
    
    //skills needed
    private int fighting;
    private int scavenging;
    private int engineering;   
    
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<Character> getCharacters() {
        return characters;
    }


    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }


    public int getFighting() {
        return fighting;
    }


    public void setFighting(int fighting) {
        this.fighting = fighting;
    }


    public int getScavenging() {
        return scavenging;
    }


    public void setScavenging(int scavenging) {
        this.scavenging = scavenging;
    }


    public int getEngineering() {
        return engineering;
    }


    public void setEngineering(int engineering) {
        this.engineering = engineering;
    }

    //addCharacter
    public void addCharacter(Character crew)
    {
        characters.add(crew);
    }

}

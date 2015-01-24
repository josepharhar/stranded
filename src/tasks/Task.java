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
    
    //character effects
    private int fear;
    private int insanity;
    private int boredom;
    private int experience; 
    private int damage;
    
    
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


    public int getFear() {
        return fear;
    }


    public void setFear(int fear) {
        this.fear = fear;
    }


    public int getInsanity() {
        return insanity;
    }


    public void setInsanity(int insanity) {
        this.insanity = insanity;
    }


    public int getBoredom() {
        return boredom;
    }


    public void setBoredom(int boredom) {
        this.boredom = boredom;
    }


    public int getExperience() {
        return experience;
    }


    public void setExperience(int experience) {
        this.experience = experience;
    }


    public int getDamage() {
        return damage;
    }


    public void setDamage(int damage) {
        this.damage = damage;
    }


    //addCharacter
    public void addCharacter(Character crew)
    {
        characters.add(crew);
    }

}

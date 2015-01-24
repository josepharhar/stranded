package characters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static util.RandomNumberGenerator.*;

public class RandomCharacterCreator implements CharacterCreator {
    private static List<String> firsts = new ArrayList<String>();
    private static List<String> lasts = new ArrayList<String>();
    List<Character> characters = new ArrayList<Character>();
    
    static {
        Scanner scanFirsts, scanLasts;
        try {
            scanFirsts = new Scanner(new File("firstNames"));
            scanLasts = new Scanner(new File("lastNames"));
            
            while(scanFirsts.hasNext()) {
                firsts.add(scanFirsts.next());
            }
            scanFirsts.close();
            while(scanLasts.hasNext()) {
                lasts.add(scanLasts.next());
            }
            scanLasts.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }        
    }
    
    private List<Character> characterList;
    
    public RandomCharacterCreator() {
        characterList = generateCharacters("firstNames","lastNames",10);
    }
    
    public Character createCharacter() {
        return characterList.get((int) (Math.random() * characterList.size()));
    }
    
    //@Override
    private List<Character> generateCharacters(String fileName, String fileName2, int num) {
        //String[] firsts = new String[20];
        //String[] lasts = new String[20];
        //List<Character> characters = new ArrayList<Character>();
        while(num-- > 0) {
            int i = getRandomInteger(firsts.size());
            int j = getRandomInteger(lasts.size());
            
            Character c = new Character();
            c.setDeception(getRandomInteger(20));
            c.setEngineering(getRandomInteger(20));
            c.setFighting(getRandomInteger(20));
            c.setFirstName(firsts.get(i));
            c.setHealth(70 + getRandomInteger(50));
            c.setLastName(lasts.get(j));
            c.setLearning_potential(getRandomInteger(20));
            c.setLoyalty(getRandomInteger(20));
            c.setLuck(getRandomInteger(20));
            c.setPrompt("");
            characters.add(c);
        }
        return characters;
    }
}

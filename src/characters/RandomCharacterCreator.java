package characters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

import static util.RandomNumberGenerator.*;

public class RandomCharacterCreator implements CharacterCreator {
    
    private List<Character> characterList;
    
    public RandomCharacterCreator() {
        characterList = generateCharacters("Names",10);
    }
    
    public Character createCharacter() {
        return new Character();
    }
    
    //@Override
    private List<Character> generateCharacters(String fileName, int num) {
        String[] firsts = new String[20];
        String[] lasts = new String[20];
        String[] phrases = new String[20];
        int i = 0;
        List<Character> characters = new ArrayList<Character>();
        
        Scanner scan = new Scanner(fileName);
        while(scan.next() != "0") {
            firsts[i] = scan.next();
            i++;
        }
        i = 0;
        scan.nextLine();
        while(scan.next() != "0") {
            lasts[i] = scan.next();
            i++;
        }
        i = 0;
        scan.nextLine();
        while(scan.next() != "0") {
            phrases[i] = scan.next();
            i++;
        }
        scan.close();
        while(num-- > 0) {
            i = getRandomInteger(20);
            int j = getRandomInteger(20);
            int k = getRandomInteger(20);
            
            Character c = new Character();
            c.setDeception(getRandomInteger(20));
            c.setEngineering(getRandomInteger(20));
            c.setFighting(getRandomInteger(20));
            c.setFirstName(firsts[i]);
            c.setHealth(70 + getRandomInteger(50));
            c.setLastName(lasts[j]);
            c.setLearning_potential(getRandomInteger(20));
            c.setLoyalty(getRandomInteger(20));
            c.setLuck(getRandomInteger(20));
            c.setPrompt(phrases[k]);
            characters.add(c);
        }
        return characters;
    }
}

package characters;

import static util.RandomNumberGenerator.getRandomInteger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import util.RandomNameAccessor;

public class RandomCharacterCreator implements CharacterCreator {
    
    @Override
    public Character createCharacter() {
        Character c = new Character();
        c.setDeception(getRandomInteger(20));
        c.setFirstName(RandomNameAccessor.getFirstName());
        c.setHealth(70 + getRandomInteger(50));
        c.setLastName(RandomNameAccessor.getLastName());
        c.setLearning_potential(getRandomInteger(20));
        c.setLoyalty(getRandomInteger(20));
        c.setLuck(getRandomInteger(20));
        
        Map<Skill, Double> skills = c.getSkills();
        skills.put(Skill.ENGINEERING, (double) getRandomInteger(20));
        skills.put(Skill.FIGHTING, (double) getRandomInteger(20));
        skills.put(Skill.SCAVENGING, (double) getRandomInteger(20));
        
        return(c);
    }
}

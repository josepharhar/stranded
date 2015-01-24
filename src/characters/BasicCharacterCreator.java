package characters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import characters.Character;
import main.Resource;

import org.json.*;

public class BasicCharacterCreator implements CharacterCreator {
    
    private List<Character> characterList;
    
    public BasicCharacterCreator() {
        characterList = readCharacterFile("BasicCharacters");
    }
    
    @Override
    public Character createCharacter() {
        if (characterList.size() < 1) {
            throw new RuntimeException("out of basic characters");
        }
        return characterList.remove((int) (Math.random() * characterList.size()));
    }
    
    private List<Character> readCharacterFile(String fileName) {
        Scanner scanner = null;
        StringBuilder builder = new StringBuilder();
        try {
            scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            scanner.close();
        }
        List<Character> characters = new ArrayList<Character>();
        try {
            JSONArray array = new JSONArray(builder.toString());
            int length = array.length();
            for (int i = 0; i < length; i++) {
                JSONObject obj = array.getJSONObject(i);
                Character c = new Character();
                c.setDeception(obj.getInt("deception"));
                c.setFirstName(obj.getString("firstName"));
                c.setHealth(obj.getInt("health"));
                c.setLastName(obj.getString("lastName"));
                c.setLearning_potential(obj.getInt("learningPotential"));
                c.setLoyalty(obj.getInt("loyalty"));
                c.setLuck(obj.getInt("luck"));
                
                Map<Skill, Double> skillMap = new HashMap<>();
                JSONObject skills = obj.getJSONObject("skills");
                for (String skill : JSONObject.getNames(skills)) {
                    skillMap.put(Skill.valueOf(skill), skills.getDouble(skill));
                }
                c.setSkills(skillMap);
                    
                characters.add(c);
            }
            return characters;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

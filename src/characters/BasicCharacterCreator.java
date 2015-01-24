package characters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import characters.Character;

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
        return characterList.get((int) (Math.random() * characterList.size()));
    }
    
    private List<Character> readCharacterFile(String fileName) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine() + "\n");
        }
        List<Character> characters = new ArrayList<Character>();
        try {
            JSONObject object = new JSONObject(builder.toString());
            JSONArray array = object.getJSONArray("basicCharacters");
            int length = array.length();
            for (int i = 0; i < length; i++) {
                JSONObject obj = array.getJSONObject(i);
                Character c = new Character();
                c.setFirstName(obj.getString("firstName"));
                c.setLastName(obj.getString("lastName"));
                c.setDeception(obj.getInt("deception"));
                c.setEngineering(obj.getInt("engineering"));
                c.setFighting(obj.getInt("fighting"));
                c.setFirstName(obj.getString("firstName"));
                c.setHealth(obj.getInt("health"));
                c.setLastName(obj.getString("lastName"));
                c.setLearning_potential(obj.getInt("learningPotential"));
                c.setLoyalty(obj.getInt("loyalty"));
                c.setLuck(obj.getInt("luck"));
                characters.add(c);
            }
            return characters;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

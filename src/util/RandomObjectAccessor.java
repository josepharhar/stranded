package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;

import characters.Character;

public class RandomObjectAccessor {
    public static List<String> randomObjects;
    
    static {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("RandomObjects"));
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine() + "\n");
            }
            randomObjects = new ArrayList<String>();
            JSONArray array = new JSONArray(builder.toString());
            int length = array.length();
            for (int i = 0; i < length; i++) {
                randomObjects.add(array.getString(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            scanner.close();
        }
    }
    
    public static String getRandomObject() {
        return randomObjects.get(RandomNumberGenerator.getRandomInteger(randomObjects.size()));
    }
}

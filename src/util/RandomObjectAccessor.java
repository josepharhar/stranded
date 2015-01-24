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
        Scanner scanner;
        try {
            scanner = new Scanner(new File("RandomObjects"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine() + "\n");
        }
        randomObjects = new ArrayList<String>();
        try {
            JSONArray array = new JSONArray(builder.toString());
            int length = array.length();
            for (int i = 0; i < length; i++) {
                randomObjects.add(array.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public static String getRandomObject() {
        return randomObjects.get(RandomNumberGenerator.getRandomInteger(randomObjects.size()));
    }
}

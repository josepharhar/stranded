package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;

import characters.Character;

public abstract class RandomListAccessor {
    
    protected static List<String> readList(String file) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(file));
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine() + "\n");
            }
            List<String> list = new ArrayList<String>();
            JSONArray array = new JSONArray(builder.toString());
            int length = array.length();
            for (int i = 0; i < length; i++) {
                list.add(array.getString(i));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}

package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RandomNameAccessor {
    private static List<String> firsts = new ArrayList<>();
    private static List<String> lasts = new ArrayList<>();

    static {
        try (Scanner ls = new Scanner(new File("lists/LastNames"));
                Scanner fs = new Scanner(new File("lists/FirstNames"))) 
                {
            while (fs.hasNext()) {
                firsts.add(fs.next());
            }
            
            while (ls.hasNext()) {
                lasts.add(ls.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String getFirstName() {
        return firsts.get(RandomNumberGenerator.getRandomInteger(firsts.size()));
    }
    
    public static String getLastName() {
        return lasts.get(RandomNumberGenerator.getRandomInteger(lasts.size()));
    }
}

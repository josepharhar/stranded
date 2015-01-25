package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;

import characters.Character;

public class RandomObjectAccessor extends RandomListAccessor {
    private static List<String> list;
    public static String get() {
        if (list == null) list = readList("lists/RandomObjects");
        return list.get(RandomNumberGenerator.getRandomInteger(list.size()));
    }
}
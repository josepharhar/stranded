package util;

import java.util.List;

public class RandomDeathAccessor extends RandomListAccessor {
    private static List<String> list;
    public static String get() {
        if (list == null) list = readList("resources/lists/RiskyDeaths");
        return list.get(RandomNumberGenerator.getRandomInteger(list.size()));
    }
}
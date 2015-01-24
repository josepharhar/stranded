package util;

public abstract class RandomNumberGenerator {
    public static int getRandomInteger(int max) {
        return (int) (Math.random() * max);
    }
}

package util;

public abstract class RandomNumberGenerator {
    public static int getRandomInteger(int max) {
        return (int) (Math.random() * max);
    }
    
    public static int getRandomSet(int type) {
        switch (type) {
        case 0:
            return getRandomInteger(20);
        case 1:
            return getRandomInteger(10);
        case 2:
            return getRandomInteger(5);
        case 3:
            return getRandomInteger(100);
        case 4:
            return getRandomInteger(24) + 38;
        case 5:
            return getRandomInteger(10) + 5;
        case 6:
            return getRandomInteger(10) + 15;
        case 7:
            return getRandomInteger(50) + 50;
        case 8:
            return getRandomInteger(5) + 8;
        case 9:
            return getRandomInteger(300) + 10;
        default:
            return getRandomInteger(20);
        }
    }
}

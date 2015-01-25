package timing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameTimer {
    private static List<DelayedAction> actions = new ArrayList<>();
    private static boolean running;
    private static long timeBase;
    private static long timeStoppedAt;
    
    public static long getTime() {
        if (running) {
            return System.currentTimeMillis() - timeBase;
        } else {
            return timeStoppedAt - timeBase;
        }
    }
    
    public static long getTimeSeconds() {
        return getTime() / 1000L;
    }
    
    public static void initializeTime() {
        timeBase = System.currentTimeMillis();
        timeStoppedAt = System.currentTimeMillis();
        running = false;
    }
    
    public static void startTime() {
        if (!running) {
            running = true;
            timeBase = timeBase + System.currentTimeMillis() - timeStoppedAt;
        }
    }
    
    public static void stopTime() {
        if (running) {
            timeStoppedAt = System.currentTimeMillis();
            running = false;
        }
    }
    
    public static boolean isPaused() {
        return !running;
    }
    
    public static void update() {
        Iterator<DelayedAction> iter = actions.iterator();
        while (iter.hasNext()) {
            DelayedAction a = iter.next();
            if (a.getTimeRemaining() < 0) {
                a.complete();
                iter.remove();
            }
        }
    }
    
    public static void addAction(DelayedAction action) {
        actions.add(action);
    }
}

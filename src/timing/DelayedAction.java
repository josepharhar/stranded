package timing;

public abstract class DelayedAction {
    private long completesAt;
    
    public DelayedAction(long delay) {
        completesAt = GameTimer.getTime() + delay;
    }
    
    public long getCompletionTime() {
        return completesAt;
    }
    
    public long getTimeRemaining() {
        return completesAt - GameTimer.getTime();
    }
    
    public abstract void complete();
}

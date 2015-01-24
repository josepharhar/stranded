package tasks;

public class BasicTaskCreator implements TaskCreator{

    @Override
    public Task createTask() {
        // TODO Auto-generated method stub
        Task job = new Task();
        
        String name = "Task1";
        int fighting = (int) (10 * Math.random());
        int engineering = (int) (10 * Math.random());
        int scavenging = (int) (10 * Math.random());
        
        job.setName(name);
        job.setFighting(fighting);
        job.setEngineering(engineering);
        job.setScavenging(scavenging);
            
        return job;
    }

}

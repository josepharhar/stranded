package tasks;

public class StorylineTaskCreator extends BasicTaskCreator {
    
    public StorylineTaskCreator() {
        taskList = readTaskFile("lists/StoryTasks");
    }
    
    @Override
    public Task createTask() {
        for (int i = 1; i < taskList.size(); i++) {
            taskList.get(i - 1).setFollowUpTask(taskList.get(i)); 
        }
        return taskList.get(0);
    }
}

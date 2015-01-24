package tasks;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import org.json.*;

import characters.Character;

public class BasicTaskCreator implements TaskCreator{
    
    private List<Task> taskList;
    
    public BasicTaskCreator(){
        taskList = readTaskFile("BasicTasks");
    }

    @Override
    public Task createTask() {
        if (taskList.size() < 1) {
            throw new RuntimeException("out of basic tasks");
        }
        return taskList.remove((int) (Math.random() * taskList.size()));
    }
    
    private List<Task> readTaskFile(String fileName) {
        Scanner scanner = null;
        StringBuilder builder = new StringBuilder();
        try {
            scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            scanner.close();
        }
        List<Task> tasks = new ArrayList<Task>();
        try {
            JSONArray array = new JSONArray(builder.toString());
            int length = array.length();
            for (int i = 0; i < length; i++) {
                JSONObject obj = array.getJSONObject(i);
                Task t = new Task();
                t.setName(obj.getString("name"));
                t.setEngineering(obj.getInt("engineering"));
                t.setScavenging(obj.getInt("scavenging"));
                t.setFighting(obj.getInt("fighting"));
                tasks.add(t);
            }
            return tasks;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}


package tasks;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import main.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import characters.Skill;

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
                t.setDifficulty(obj.getInt("difficulty"));
                t.setPrimarySkill(Skill.valueOf(obj.getString("skill")));
                if (obj.has("rewards")) {
                    Map<Resource, Double> map = new HashMap<>();
                    JSONObject rewards = obj.getJSONObject("rewards");
                    for (String resource : JSONObject.getNames(rewards)) {
                        map.put(Resource.valueOf(resource), rewards.getDouble(resource));
                    }
                }
                if (obj.has("costs")) {
                    Map<Resource, Double> map = new HashMap<>();
                    JSONObject costs = obj.getJSONObject("costs");
                    for (String resource : JSONObject.getNames(costs)) {
                        map.put(Resource.valueOf(resource), costs.getDouble(resource));
                    }
                }
                if (obj.has("penalty")) {
                    Map<Resource, Double> map = new HashMap<>();
                    JSONObject penalty = obj.getJSONObject("penalty");
                    for (String resource : JSONObject.getNames(penalty)) {
                        map.put(Resource.valueOf(resource), penalty.getDouble(resource));
                    }
                }
                if (obj.has("duration")) {
                    t.setExpires(true);
                    t.setExpirationTime(System.currentTimeMillis() + obj.getInt("duration") * 1000L);
                }
                tasks.add(t);
            }
            return tasks;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}


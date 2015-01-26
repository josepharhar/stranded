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

import timing.DelayedAction;
import timing.GameTimer;
import characters.Skill;
import static util.RandomNumberGenerator.getRandomSet;

public class RandomTaskCreator implements TaskCreator {
    
    @Override
    public Task createTask() {
        List<Task> taskList = readTaskFile("resources/lists/RandomTasks");
        return taskList.get((int) (Math.random() * taskList.size()));
    }
    
    protected List<Task> readTaskFile(String fileName) {
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
                final Task t = new Task();
                t.setName(obj.getString("name"));
                t.setDifficulty(getRandomSet(obj.getInt("difficulty")));
                t.setPrimarySkill(Skill.valueOf(obj.getString("skill")));
                if (obj.has("rewards")) {
                    Map<Resource, Double> map = new HashMap<>();
                    JSONObject rewards = obj.getJSONObject("rewards");
                    for (String resource : JSONObject.getNames(rewards)) {
                        map.put(Resource.valueOf(resource), (double) getRandomSet(rewards.getInt(resource)));
                    }
                    t.setRewards(map);
                }
                if (obj.has("costs")) {
                    Map<Resource, Double> map = new HashMap<>();
                    JSONObject costs = obj.getJSONObject("costs");
                    for (String resource : JSONObject.getNames(costs)) {
                        map.put(Resource.valueOf(resource), (double) getRandomSet(costs.getInt(resource)));
                    }
                    t.setCosts(map);
                }
                if (obj.has("penalty")) {
                    Map<Resource, Double> map = new HashMap<>();
                    JSONObject penalty = obj.getJSONObject("penalty");
                    for (String resource : JSONObject.getNames(penalty)) {
                        map.put(Resource.valueOf(resource), (double) getRandomSet(penalty.getInt(resource)));
                    }
                    t.setPenalty(map);
                }
                if (obj.has("duration")) {
                    
                    DelayedAction expiration = new DelayedAction(getRandomSet(obj.getInt("duration")) * 1000L) {
                        @Override
                        public void complete() {
                            t.setExpired(true);
                        }
                    };
                    GameTimer.addAction(expiration);
                    t.setExpirationAction(expiration);
                }
                if (obj.has("canRetry")) {
                    t.setCanRetry(obj.getBoolean("canRetry"));
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

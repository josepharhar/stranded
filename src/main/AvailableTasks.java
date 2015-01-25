package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import tasks.Task;
import characters.Skill;

public class AvailableTasks {
    private List<Task> tasks = new ArrayList<>();
    private Task scavengeTask = createScavengeTask();
    private Task healTask = createHealTask();
    
    public List<Task> getList() {
        List<Task> rtn = new ArrayList<>();
        rtn.add(healTask);
        rtn.add(scavengeTask);
        rtn.addAll(tasks);
        return rtn;
    }
    
    public void add(Task t) {
        tasks.add(t);
    }
    
    public void remove(Task t) {
        if (t == scavengeTask) {
            scavengeTask = createScavengeTask();
        } else if (t == healTask) {
            healTask = createHealTask();
        } else {
            tasks.remove(t);
        }
    }

    private Task createHealTask() {
        Task rtn = new Task();
        rtn.setName("Heal");
        rtn.setDifficulty(-10);
        rtn.setPrimarySkill(Skill.NONE);
        rtn.setRisk(-10);
        Map<Resource, Double> rewards = new HashMap<>();
        rewards.put(Resource.HEALING, Math.ceil(Math.random() * 20));
        rtn.setRewards(rewards);
        return rtn;
    }

    private int scavengeTasks = 0;
    private Task createScavengeTask() {
        scavengeTasks++;
        Task rtn = new Task();
        rtn.setName("Scavenge");
        rtn.setDifficulty(scavengeTasks);
        rtn.setPrimarySkill(Skill.SCAVENGING);
        rtn.setRisk(scavengeTasks);
        Map<Resource, Double> rewards = new HashMap<>();
        rewards.put(Resource.SCRAP, Math.ceil(Math.random() * 20));
        rewards.put(Resource.ELECTRONICS, Math.ceil(Math.random() * 20));
        rtn.setRewards(rewards);
        return rtn;
    }

    public Iterator<Task> transientTasksIterator() {
        return tasks.iterator();
    }
}
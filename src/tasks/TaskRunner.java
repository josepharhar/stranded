package tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.Game;
import main.Resources;
import characters.Character;

public class TaskRunner {
    private Game game;
    private List<Task> pendingTasks = new ArrayList<Task>();

    public TaskRunner(Game game) {
        this.game = game;
    }

    public int[] sumStats(List<Character> crew) {
        double totalLuck = 0;
        for (Character c : crew) {
            totalLuck += c.getLuck();
        }

        totalLuck = totalLuck * Math.random();
        totalLuck = 1 + (totalLuck / 10);

        int totalFighting = 0;
        for (Character c : crew) {
            totalFighting += c.getFighting();
        }

        int totalEngineering = 0;
        for (Character c : crew) {
            totalEngineering += c.getEngineering();
        }

        int totalScavenging = 0;
        for (Character c : crew) {
            totalScavenging += c.getScavenging();
        }

        totalEngineering *= totalLuck;
        totalFighting *= totalLuck;
        totalScavenging *= totalLuck;

        totalEngineering = (int) (totalEngineering * Math.random());
        totalFighting = (int) (totalFighting * Math.random());
        totalScavenging = (int) (totalScavenging * Math.random());

        int[] totalStats = { totalEngineering, totalFighting, totalScavenging };

        return totalStats;

    }

    public int[] changeCharacter(int postive, int negative) {
        int fear = (int) (negative * Math.random());
        int insanity = (int) (negative * Math.random());
        int boredom = (int) (negative * Math.random());
        int experience = (int) (postive * Math.random());
        int damage = (int) (negative * Math.random());

        int[] taskEffects = { fear, insanity, boredom, experience, damage };

        return taskEffects;
    }
    
    public List<Task> getCompletedTasks() {
        ArrayList<Task> rtn = new ArrayList<>();
        long now = System.currentTimeMillis();
        Iterator<Task> iter = pendingTasks.iterator();
        while (iter.hasNext()) {
            Task t = iter.next();
            if (t.getCompletionTime() < now) {
                rtn.add(t);
                endTask(t);
                iter.remove();
            }
        }
        return rtn;
    }
    
    public void startTask(Task job) {
        job.setCompletionTime(System.currentTimeMillis() + 60 * 1000L);
        pendingTasks.add(job);
    }

    public void endTask(Task job) {
        List<Character> crew = job.getCharacters();
        int[] crewStats = sumStats(crew);

        if ((crewStats[0] >= job.getEngineering())
                && (crewStats[1] >= job.getFighting())
                && (crewStats[2] >= job.getScavenging())) {
            int[] statChanges = changeCharacter(10, 4);
            int totalMental = statChanges[0] + statChanges[1] + statChanges[2];

            for (Character c : crew) {
                // changes in loyalty
                totalMental = (int) (totalMental * Math.random());
                c.setLoyalty(c.getLoyalty() - totalMental);

                // changes in skills
                int exp = (int) (((c.getLearning_potential() / 10) + 1) * statChanges[3]);
                int scavExp = (int) (exp * Math.random());
                int engiExp = (int) (exp * Math.random());
                ;
                int fightExp = (int) (exp * Math.random());

                c.setScavenging(c.getScavenging() + scavExp);
                c.setEngineering(c.getEngineering() + engiExp);
                c.setFighting(c.getFighting() + fightExp);

                // damage dealt
                int dmg = (int) (statChanges[4] * Math.random());
                c.setHealth(c.getHealth() - dmg);
            }
            job.setSuccessful(true);
        } else {
            int[] statChanges = changeCharacter(4, 10);
            int totalMental = statChanges[0] + statChanges[1] + statChanges[2];

            for (Character c : crew) {
                // changes in loyalty
                totalMental = (int) (totalMental * Math.random());
                c.setLoyalty(c.getLoyalty() - totalMental);

                // changes in skills
                int exp = (int) (((c.getLearning_potential() / 10) + 1) * statChanges[3]);
                int scavExp = (int) (exp * Math.random());
                int engiExp = (int) (exp * Math.random());
                ;
                int fightExp = (int) (exp * Math.random());

                c.setScavenging(c.getScavenging() + scavExp);
                c.setEngineering(c.getEngineering() + engiExp);
                c.setFighting(c.getFighting() + fightExp);

                // damage dealt
                int dmg = (int) (statChanges[4] * Math.random());
                c.setHealth(c.getHealth() - dmg);
            }
            job.setSuccessful(false);
        }
    }

}

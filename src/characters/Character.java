package characters;
import java.util.HashMap;
import java.util.Map;

import tasks.Task;

public class Character {

    private boolean available;
    private Task currentTask;
    
    private String firstName;
    private String lastName;
    private String occupation; 

    // physical stats and mental stats
    private int health;
    private int stamina; //ability to take an action
    private int loyalty; //willingness to do something
    private double learningPotential; //how quickly stat ups happen

    private Map<Skill, Double> skills = new HashMap<>();
    private int deception; //possibly that a person lies
    private double luck; //ability to do something they're not qualified for [also for scavenging]
    
    public double getLearningPotential() {
        return learningPotential;
    }

    public void setLearningPotential(double learningPotential) {
        this.learningPotential = learningPotential;
    }

    public void setLuck(double luck) {
        this.luck = luck;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task current_task) {
        this.currentTask = current_task;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(int loyalty) {
        this.loyalty = loyalty;
    }

    public Map<Skill, Double> getSkills() {
        return skills;
    }
    
    public void setSkills(Map<Skill, Double> skills) {
        this.skills = skills;
    }
    
    public int getDeception() {
        return deception;
    }

    public void setDeception(int deception) {
        this.deception = deception;
    }

    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public double getLearning_potential() {
        return learningPotential;
    }

    public void setLearning_potential(int learning_potential) {
        this.learningPotential = learning_potential;
    }

    public double getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
    
    private void dp(String s) {
        System.out.println(s);
    }

}

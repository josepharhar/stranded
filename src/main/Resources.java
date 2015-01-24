package main;

public class Resources {
    public double scrapMetal;
    public double stationDefenses;
    public double stationIntegrity;
    public double electronicEquipment;
    
    public Resources(int difficulty) {
        scrapMetal = Math.random() * 40 + 20 * difficulty;
        stationDefenses = Math.random() * 40 + 20 * difficulty;
        stationIntegrity = Math.random() * 40 + 20 * difficulty;
        electronicEquipment = Math.random() * 20 + 10 * difficulty;
    }
}

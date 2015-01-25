package main;

import java.util.HashMap;
import java.util.Map;

public class Resources {
    private Map<Resource, Double> map = new HashMap<>();
     
    public Resources() {
        map.put(Resource.ELECTRONICS, 20.0);
        map.put(Resource.FUEL, 20.0);
        map.put(Resource.SCRAP, 20.0);
        map.put(Resource.STATION_DEFENSES, 20.0);
        map.put(Resource.STATION_HEALTH, 20.0);
        map.put(Resource.MORALE, 20.0);
        map.put(Resource.HEALING, 0.0);
    }
    
    public double getResource(Resource resource) {
        return map.get(resource);
    }
    
    public boolean trySubtract(Map<Resource, Double> values) {
        for (Map.Entry<Resource, Double> entry : values.entrySet()) {
            double newVal =  map.get(entry.getKey()) - entry.getValue();
            if (newVal < 0) {
                return false;
            }
        }
        return true;
    }

    public void subtract(Map<Resource, Double> values) {
        for (Map.Entry<Resource, Double> entry : values.entrySet()) {
            double newVal =  map.get(entry.getKey()) - entry.getValue();
            map.put(entry.getKey(), Math.max(newVal, 0));
        }
    }
    
    public void add(Map<Resource, Double> values) {
        for (Map.Entry<Resource, Double> entry : values.entrySet()) {
            map.put(entry.getKey(), map.get(entry.getKey()) + entry.getValue());
        }
    }
}

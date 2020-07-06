package dunzo.coffeemachine.controllers;

import dunzo.coffeemachine.model.Beverage;

import java.util.HashMap;
import java.util.Map;

public class BeverageController {

    private Map<String, Beverage> beverageMap;
    private static BeverageController instance;

    private BeverageController() {
        beverageMap = new HashMap<>();
    }

    public static BeverageController getInstance() {
        if (instance == null) {
            instance = new BeverageController();
        }
        return instance;
    }

    public void addBeverage(Beverage beverage) {
        beverageMap.put(beverage.getName(), beverage);
    }

    public void removeBeverage(Beverage beverage) {
        beverageMap.remove(beverage.getName());
    }

    public Beverage getBeverageByName(String beverageName) {
        return beverageMap.get(beverageName);
    }
}

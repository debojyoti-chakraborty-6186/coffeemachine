package dunzo.coffeemachine.model;

import dunzo.coffeemachine.controllers.IngredientController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VendingUnit {

    private String id;
    private List<Beverage> beverageList;
    private boolean stopUnit;
    private int currentindex;

    public VendingUnit(String id) {
        this.id = id;
        beverageList = new ArrayList<>();
        stopUnit = false;
        this.currentindex = 0;
    }

    public String getId() {
        return id;
    }

    public void setBeverageList(List<Beverage> beverageList) {
        this.beverageList = beverageList;
    }

    public void setStopUnit(boolean stopUnit) {
        this.stopUnit = stopUnit;
    }

    public List<Beverage> getBeverageList() {
        return beverageList;
    }

    public void dispenseBeverage(Beverage beverage) {
        IngredientController ingredientController = IngredientController.getInstance();
        Map<String, Integer> ingredientQtyMap = ingredientController.getIngredientQuantityMap();
        synchronized (ingredientQtyMap) {
            for (Map.Entry<String, Integer> mapItr : beverage.getIngredientQuantityMap().entrySet()) {
                if (ingredientQtyMap.get(mapItr.getKey()) < mapItr.getValue()) {
                    throw new IllegalArgumentException("Required quantity not available");
                }
            }
            System.out.println("Dispensing SUCCESS for " + beverage.getName() + " from vending machine: " + this.id);

            //Reducing quantity
            for (Map.Entry<String, Integer> mapItr : beverage.getIngredientQuantityMap().entrySet()) {
                ingredientQtyMap.put(mapItr.getKey(), ingredientQtyMap.get(mapItr.getKey())-mapItr.getValue());
            }
        }
    }
}

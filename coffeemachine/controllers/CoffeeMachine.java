package dunzo.coffeemachine.controllers;

import dunzo.coffeemachine.model.Beverage;
import dunzo.coffeemachine.model.Ingredient;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CoffeeMachine {

    private VendingUnitController vendingUnitController;
    private IngredientController ingredientController;
    private BeverageController beverageController;

    public CoffeeMachine() {
        vendingUnitController = VendingUnitController.getInstance();
        ingredientController = IngredientController.getInstance();
        beverageController = BeverageController.getInstance();

        initializeIngredientsAndBeverage();
    }

    public VendingUnitController getVendingUnitController() {
        return vendingUnitController;
    }

    public IngredientController getIngredientController() {
        return ingredientController;
    }

    public BeverageController getBeverageController() {
        return beverageController;
    }

    public void initializeIngredientsAndBeverage() {
        Ingredient milk = new Ingredient(generateUUID(), 10, "Milk");
        Ingredient water = new Ingredient(generateUUID(), 10, "Water");
        Ingredient sugar = new Ingredient(generateUUID(), 10, "Sugar");
        Ingredient coffeeBeans = new Ingredient(generateUUID(), 10, "CoffeeBeans");
        ingredientController.addIngredient(milk);
        ingredientController.addIngredientQuantity(milk.getName(), 10);
        ingredientController.addIngredient(water);
        ingredientController.addIngredientQuantity(water.getName(), 10);
        ingredientController.addIngredient(sugar);
        ingredientController.addIngredientQuantity(sugar.getName(), 10);
        ingredientController.addIngredient(coffeeBeans);
        ingredientController.addIngredientQuantity(coffeeBeans.getName(), 5);

        Map<String , Integer> espressoQuatityMap = new HashMap<>();
        espressoQuatityMap.put(coffeeBeans.getName(), 1);
        espressoQuatityMap.put(water.getName(), 1);
        Beverage espresso = new Beverage(generateUUID(), "Espresso", espressoQuatityMap);
        beverageController.addBeverage(espresso);

        Map<String , Integer> latteQuatityMap = new HashMap<>();
        latteQuatityMap.put(milk.getName(), 2);
        latteQuatityMap.put(coffeeBeans.getName(), 1);
        latteQuatityMap.put(water.getName(), 1);
        Beverage latte = new Beverage(generateUUID(), "Latte", latteQuatityMap);
        beverageController.addBeverage(latte);

        Map<String , Integer> cappuccinoQuatityMap = new HashMap<>();
        cappuccinoQuatityMap.put(milk.getName(), 1);
        cappuccinoQuatityMap.put(coffeeBeans.getName(), 1);
        cappuccinoQuatityMap.put(water.getName(), 1);
        Beverage cappuccino = new Beverage(generateUUID(), "Cappuccino", cappuccinoQuatityMap);
        beverageController.addBeverage(cappuccino);
    }

    public void addVendingMachine(int noOfVendingMachine) {
        for (int i=0; i<noOfVendingMachine; i++) {
            vendingUnitController.addVendingUnit("vending_unit_" + i);
        }
    }

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}

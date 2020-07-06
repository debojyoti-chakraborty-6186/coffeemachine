package dunzo.coffeemachine.controllers;

import dunzo.coffeemachine.model.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class IngredientController {

    private Map<String, Ingredient> ingredientMap;
    private Map<String, Integer> ingredientQuantityMap;
    private static IngredientController instance;

    private IngredientController() {
        ingredientMap = new HashMap<>();
        ingredientQuantityMap = new HashMap<>();
    }

    public static IngredientController getInstance() {
        if (instance == null) {
            instance = new IngredientController();
        }
        return instance;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientMap.put(ingredient.getName(), ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredientMap.remove(ingredient.getName());
    }

    public Ingredient getIngredientByName(String ingredientName) {
        return ingredientMap.get(ingredientName);
    }

    public Map<String, Ingredient> getIngredientMap() {
        return ingredientMap;
    }

    public Map<String, Integer> getIngredientQuantityMap() {
        return ingredientQuantityMap;
    }

    public void addIngredientQuantity(String id, int quantity) {
        synchronized (ingredientQuantityMap) {
            if (ingredientQuantityMap.get(id) == null) {
                ingredientQuantityMap.put(id, quantity);
            }
            else {
                ingredientQuantityMap.put(id, ingredientQuantityMap.get(id)+quantity);
            }
        }
    }
}

package dunzo.coffeemachine.model;

import java.util.Map;

public class Beverage {

    private String id;
    private String name;
    private Map<String, Integer> ingredientQuantityMap;

    public Beverage(String id, String name, Map<String, Integer> ingredientQuantityMap) {
        this.id = id;
        this.name = name;
        this.ingredientQuantityMap = ingredientQuantityMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getIngredientQuantityMap() {
        return ingredientQuantityMap;
    }

    public void setIngredientQuantityMap(Map<String, Integer> ingredientQuantityMap) {
        this.ingredientQuantityMap = ingredientQuantityMap;
    }
}

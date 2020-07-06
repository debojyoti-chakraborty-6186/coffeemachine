package dunzo.coffeemachine.model;

public class Ingredient {

    private String id;
    private int minQuantity;
    private String name;

    public Ingredient(String id, int minQuantity, String name) {
        this.id = id;
        this.minQuantity = minQuantity;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

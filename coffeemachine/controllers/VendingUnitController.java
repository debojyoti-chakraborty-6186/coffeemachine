package dunzo.coffeemachine.controllers;

import dunzo.coffeemachine.model.VendingUnit;

import java.util.HashMap;
import java.util.Map;

public class VendingUnitController {

    private Map<String, VendingUnit> vendingUnitMap;
    private static VendingUnitController instance;

    private VendingUnitController() {
        vendingUnitMap = new HashMap<>();
    }

    public static VendingUnitController getInstance() {
        if (instance == null) {
            instance = new  VendingUnitController();
        }
        return instance;
    }

    public void addVendingUnit(String id) {
        vendingUnitMap.put(id, new VendingUnit(id));
    }

    public VendingUnit getVendingUnit(String id) {
        return vendingUnitMap.get(id);
    }

    public Map<String, VendingUnit> getVendingUnitMap() {
        return vendingUnitMap;
    }
}

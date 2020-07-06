package dunzo.coffeemachine;

import dunzo.coffeemachine.controllers.CoffeeMachine;
import dunzo.coffeemachine.model.Beverage;
import dunzo.coffeemachine.model.VendingUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CoffeeMachineImpl {

    public static void main(String args[]) throws InterruptedException {
        CoffeeMachineImpl obj = new CoffeeMachineImpl();

        System.out.println("*** Serial execution with 2 vending machine in round robin fashion ***");
        obj.SerialExecution(2);
        System.out.println("********\n\n\n");

        System.out.println("*** Parellel execution with 3 vending machine, 3 threads working in parallel and COFFEE BEANS quantity runs out ***");
        obj.parallelExecution(3);
        System.out.println("********\n\n\n");

        System.out.println("*** Parellel execution with 3 vending machine, 3 threads working in parallel and COFFEE BEANS quantity ADDED ***");
        obj.parallelExecutionWithIngredientAdd(3);
        System.out.println("********\n\n\n");
    }

    //Simple test of dispensing in round robin manner
    public void SerialExecution(int noOfVendingMachine) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.addVendingMachine(noOfVendingMachine);
        Map<String, VendingUnit> vendingUnitMap = coffeeMachine.getVendingUnitController().getVendingUnitMap();
        List<Beverage> beverageToBeDispensed = new ArrayList<>();
        beverageToBeDispensed.add(coffeeMachine.getBeverageController().getBeverageByName("Espresso"));
        beverageToBeDispensed.add(coffeeMachine.getBeverageController().getBeverageByName("Latte"));
        beverageToBeDispensed.add(coffeeMachine.getBeverageController().getBeverageByName("Cappuccino"));
        for (int i=0; i<beverageToBeDispensed.size(); i++) {
            coffeeMachine.getVendingUnitController().getVendingUnit("vending_unit_" + i%noOfVendingMachine).dispenseBeverage(beverageToBeDispensed.get(i));
        }
        System.out.println("Dispensing done");
    }

    //Parallel execution with 3 vending machine
    public void parallelExecution(int noOfVendingMachine) throws InterruptedException {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.addVendingMachine(noOfVendingMachine);
        Map<String, VendingUnit> vendingUnitMap = coffeeMachine.getVendingUnitController().getVendingUnitMap();

        List<Beverage> beverageVendingMachoneOne = new ArrayList<>();
        beverageVendingMachoneOne.add(coffeeMachine.getBeverageController().getBeverageByName("Espresso"));
        beverageVendingMachoneOne.add(coffeeMachine.getBeverageController().getBeverageByName("Latte"));
        beverageVendingMachoneOne.add(coffeeMachine.getBeverageController().getBeverageByName("Cappuccino"));

        List<Beverage> beverageVendingMachoneTwo = new ArrayList<>();
        beverageVendingMachoneTwo.add(coffeeMachine.getBeverageController().getBeverageByName("Espresso"));
        beverageVendingMachoneTwo.add(coffeeMachine.getBeverageController().getBeverageByName("Espresso"));
        beverageVendingMachoneTwo.add(coffeeMachine.getBeverageController().getBeverageByName("Cappuccino"));


        List<Beverage> beverageVendingMachoneThree = new ArrayList<>();
        beverageVendingMachoneThree.add(coffeeMachine.getBeverageController().getBeverageByName("Cappuccino"));
        beverageVendingMachoneThree.add(coffeeMachine.getBeverageController().getBeverageByName("Cappuccino"));

        Thread t1 = new Thread("Vending_Machine_One"){
            public void run() {
                for (int i=0; i<beverageVendingMachoneOne.size(); i++) {
                    Random random = new Random();
                    int rand = random.nextInt(3);
                    try {
                        coffeeMachine.getVendingUnitController().getVendingUnit("vending_unit_0").dispenseBeverage(beverageVendingMachoneOne.get(i));
                        Thread.sleep(rand*1000);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Dispensing on vending_unit_0 for " + beverageVendingMachoneOne.get(i).getName() + " FAILED because of lack of ingredients");
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Dispensing done for vending_unit_0");
            }
        };
        Thread t2 = new Thread("Vending_Machine_Two"){
            public void run() {
                for (int i=0; i<beverageVendingMachoneTwo.size(); i++) {
                    Random random = new Random();
                    int rand = random.nextInt(3);
                    try {
                        coffeeMachine.getVendingUnitController().getVendingUnit("vending_unit_1").dispenseBeverage(beverageVendingMachoneTwo.get(i));
                        Thread.sleep(rand*1000);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Dispensing on vending_unit_1 for " + beverageVendingMachoneOne.get(i).getName() + " FAILED because of lack of ingredients");
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Dispensing done for vending_unit_1");
            }
        };
        Thread t3 = new Thread("Vending_Machine_Three"){
            public void run() {
                for (int i=0; i<beverageVendingMachoneThree.size(); i++) {
                    Random random = new Random();
                    int rand = random.nextInt(3);
                    try {
                        coffeeMachine.getVendingUnitController().getVendingUnit("vending_unit_2").dispenseBeverage(beverageVendingMachoneThree.get(i));
                        Thread.sleep(rand*1000);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Dispensing on vending_unit_2 for " + beverageVendingMachoneOne.get(i).getName() + " FAILED because of lack of ingredients");
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Dispensing done for vending_unit_2");
            }
        };
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Dispensing done");
    }

    //Parallel execution with 3 vending machine with INGREDIENTS ADD
    public void parallelExecutionWithIngredientAdd(int noOfVendingMachine) throws InterruptedException {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.addVendingMachine(noOfVendingMachine);
        Map<String, VendingUnit> vendingUnitMap = coffeeMachine.getVendingUnitController().getVendingUnitMap();

        List<Beverage> beverageVendingMachoneOne = new ArrayList<>();
        beverageVendingMachoneOne.add(coffeeMachine.getBeverageController().getBeverageByName("Espresso"));
        beverageVendingMachoneOne.add(coffeeMachine.getBeverageController().getBeverageByName("Latte"));
        beverageVendingMachoneOne.add(coffeeMachine.getBeverageController().getBeverageByName("Cappuccino"));

        List<Beverage> beverageVendingMachoneTwo = new ArrayList<>();
        beverageVendingMachoneTwo.add(coffeeMachine.getBeverageController().getBeverageByName("Espresso"));
        beverageVendingMachoneTwo.add(coffeeMachine.getBeverageController().getBeverageByName("Espresso"));
        beverageVendingMachoneTwo.add(coffeeMachine.getBeverageController().getBeverageByName("Cappuccino"));


        List<Beverage> beverageVendingMachoneThree = new ArrayList<>();
        beverageVendingMachoneThree.add(coffeeMachine.getBeverageController().getBeverageByName("Cappuccino"));
        beverageVendingMachoneThree.add(coffeeMachine.getBeverageController().getBeverageByName("Cappuccino"));

        //Add ingredients
        System.out.println("\nADDING COFFEE BEANS with quantity 5\n");
        coffeeMachine.getIngredientController().addIngredientQuantity("CoffeeBeans", 5);

        Thread t1 = new Thread("Vending_Machine_One"){
            public void run() {
                for (int i=0; i<beverageVendingMachoneOne.size(); i++) {
                    Random random = new Random();
                    int rand = random.nextInt(3);
                    try {
                        coffeeMachine.getVendingUnitController().getVendingUnit("vending_unit_0").dispenseBeverage(beverageVendingMachoneOne.get(i));
                        Thread.sleep(rand*1000);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Dispensing on vending_unit_0 for " + beverageVendingMachoneOne.get(i).getName() + " FAILED because of lack of ingredients");
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Dispensing done for vending_unit_0");
            }
        };
        Thread t2 = new Thread("Vending_Machine_Two"){
            public void run() {
                for (int i=0; i<beverageVendingMachoneTwo.size(); i++) {
                    Random random = new Random();
                    int rand = random.nextInt(3);
                    try {
                        coffeeMachine.getVendingUnitController().getVendingUnit("vending_unit_1").dispenseBeverage(beverageVendingMachoneTwo.get(i));
                        Thread.sleep(rand*1000);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Dispensing on vending_unit_1 for " + beverageVendingMachoneOne.get(i).getName() + " FAILED because of lack of ingredients");
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Dispensing done for vending_unit_1");
            }
        };
        Thread t3 = new Thread("Vending_Machine_Three"){
            public void run() {
                for (int i=0; i<beverageVendingMachoneThree.size(); i++) {
                    Random random = new Random();
                    int rand = random.nextInt(3);
                    try {
                        coffeeMachine.getVendingUnitController().getVendingUnit("vending_unit_2").dispenseBeverage(beverageVendingMachoneThree.get(i));
                        Thread.sleep(rand*1000);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Dispensing on vending_unit_2 for " + beverageVendingMachoneOne.get(i).getName() + " FAILED because of lack of ingredients");
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Dispensing done for vending_unit_2");
            }
        };
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Dispensing done");
    }
}

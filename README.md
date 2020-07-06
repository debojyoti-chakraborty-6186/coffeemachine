# coffeemachine

Code structure involves models and their corresponding controllers.

## Model
1) Ingredient
2) Beverage
3) Vending Unit

## Controller
1) Ingredient Controller
2) Beverage Controller
3) Vending Unit Controller

CoffeeMachine class is where data preparation and initialization happens

Vending Unit Controller has "ingredientQuantityMap", where ingredient with their quantity is kept. Whenever add ingredient or remove ingredient(while dispensing) is done, it is synchronized so that multiple thread do not disrupt data consistency

## Test Cases
Initially data is initialized with
10 units of Milk (min quantity: 2 units)
10 units of Water (min quantity: 2 units)
10 units of Sugar (min quantity: 2 units)
5 units of CoffeeBeans (min quantity: 2 units)

1) Simple Test case -> This involves creating 2 vending units and beverages are dispensed in round robin manner. O/P below.

SerialExecution()
```*** Serial execution with 2 vending machine in round robin fashion ***
Dispensing SUCCESS for Espresso from vending machine: vending_unit_0
Dispensing SUCCESS for Latte from vending machine: vending_unit_1
Dispensing SUCCESS for Cappuccino from vending machine: vending_unit_0
Dispensing done
********
```


2) Parallel execution with 3 vending machine -> This involves 3 threads which randomly dispenses beverages from 3 vending unit. In the o/p below, after dispensing few beverages the "coffee beans" ingredient runs out, so dispensing further fails. Also when ingredient quantity (here COFFEE BEANS) is coming below "minQuantity" it shows a WARNING message
`WARNING:: CoffeeBeans quantity is running LOW, PLEASE REFILL`

parallelExecution()
```*** Parellel execution with 3 vending machine, 3 threads working in parallel and COFFEE BEANS quantity runs out ***
Dispensing SUCCESS for Espresso from vending machine: vending_unit_0
Dispensing SUCCESS for Espresso from vending machine: vending_unit_1
Dispensing SUCCESS for Cappuccino from vending machine: vending_unit_2
Dispensing SUCCESS for Espresso from vending machine: vending_unit_1
Dispensing SUCCESS for Cappuccino from vending machine: vending_unit_1
WARNING:: CoffeeBeans quantity is running LOW, PLEASE REFILL
Dispensing done for vending_unit_1
Dispensing SUCCESS for Cappuccino from vending machine: vending_unit_2
WARNING:: CoffeeBeans quantity is running LOW, PLEASE REFILL
Dispensing SUCCESS for Latte from vending machine: vending_unit_0
Dispensing done for vending_unit_2
WARNING:: CoffeeBeans quantity is running LOW, PLEASE REFILL
Dispensing on vending_unit_0 for Cappuccino FAILED because of lack of ingredients
Dispensing done for vending_unit_0
Dispensing done
********
```


3) Parallel execution with 3 vending machine with INGREDIENTS ADD -> This involves 3 threads which randomly dispenses beverages from 3 vending unit. In the o/p below, COFFEE BEANS ingredient has been added so the dispensing of all the beverages succeeds.

parallelExecutionWithIngredientAdd()
```*** Parellel execution with 3 vending machine, 3 threads working in parallel and COFFEE BEANS quantity ADDED ***

ADDING COFFEE BEANS with quantity 5

Dispensing SUCCESS for Espresso from vending machine: vending_unit_0
Dispensing SUCCESS for Latte from vending machine: vending_unit_0
Dispensing SUCCESS for Espresso from vending machine: vending_unit_1
Dispensing SUCCESS for Cappuccino from vending machine: vending_unit_2
Dispensing SUCCESS for Cappuccino from vending machine: vending_unit_0
Dispensing SUCCESS for Espresso from vending machine: vending_unit_1
Dispensing SUCCESS for Cappuccino from vending machine: vending_unit_2
Dispensing done for vending_unit_2
Dispensing done for vending_unit_0
Dispensing SUCCESS for Cappuccino from vending machine: vending_unit_1
WARNING:: CoffeeBeans quantity is running LOW, PLEASE REFILL
Dispensing done for vending_unit_1
Dispensing done
********
```

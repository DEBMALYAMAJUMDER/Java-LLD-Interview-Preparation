package vendingmachine;

import vendingmachine.entity.Product;
import vendingmachine.entity.PurchaseResult;
import vendingmachine.service.VendingMachine;
import vendingmachine.service.impl.VendingMachineImpl;

public class VendingMachineMain {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachineImpl();

        // Load inventory
        machine.addProduct(
                new Product("P1", "Coke", 40d), 5);

        machine.addProduct(
                new Product("P2", "Pepsi", 35d), 2);

        machine.addProduct(
                new Product("P3", "Chips", 20d), 1);

        System.out.println("===== Scenario 1 : Successful Purchase =====");

        machine.selectProduct("P1");

        machine.insertMoney(20d);
        machine.insertMoney(50d);

        PurchaseResult result1 = machine.purchase();

        System.out.println(result1);


        System.out.println("\n===== Scenario 2 : Insufficient Money =====");

        machine.selectProduct("P2");

        machine.insertMoney(10d);

        try {
            machine.purchase();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\n===== Scenario 3 : Cancel Transaction =====");

        machine.selectProduct("P1");

        machine.insertMoney(100d);

        double refund = machine.cancel();

        System.out.println("Refund : " + refund);


        System.out.println("\n===== Scenario 4 : Out Of Stock =====");

        machine.selectProduct("P3");
        machine.insertMoney(20d);
        PurchaseResult res = machine.purchase();
        System.out.println(res);

        try {
            machine.selectProduct("P3");
            machine.insertMoney(20d);
            machine.purchase();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

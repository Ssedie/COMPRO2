package com.zed;

public class Objects1 {
    public static void main(String[] args) {
        Coffee o1 = new Coffee("Double Dutch", "Espresso", "medium", 75.50,"light", "Philippines", true, 5, "Trickle");
        o1.addFlavor("Vanilla");
        o1.calculatePrize("medium");
        o1.discount(10);
        o1.updateStock(5);
        System.out.println(o1.getName() + " " + o1.getType() + " " + o1.getSize());
        System.out.println(o1.explain());
        System.out.println("The total price of your coffee is: " + o1.getPrice());
    }
}

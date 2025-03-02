package com.zed;

public class Objects2 {
    public static void main(String[] args) {
        Coffee o2 = new Coffee("Sunset", "Latte", "large", 86.75, "light", "Spain", false, 10, "French Press");
        o2.calculatePrize("large");
        o2.discount(20);
        o2.updateStock(10);
        o2.addFlavor("Chocolate");
        String power = o2.getName() + " " + o2.getType() + " " + o2.getSize();
        System.out.println(power);
        System.out.println(o2.explain());
        System.out.println("Total: " + o2.getPrice());

    }
}

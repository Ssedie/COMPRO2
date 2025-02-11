public class Coffees {
    public static void main(String[] args) {

        Coffee kape1 = new Coffee("Kopiko", "Americano", "medium", 65, "dark", "Homemade", false, 57, "Hot Brew");
        Coffee kape2 = new Coffee("Kapeblanco", "Espresso", "small", 70, "light", "Italy", false, 35, "Cold Brew");



        kape1.addFlavor("Chocolate");
        kape1.discount(10);
        kape1.updateStock(5);
        System.out.println(kape1.getName() + " " + kape1.getType() + " " + kape1.getSize());
        System.out.println(kape1.explain());
        System.out.println("The total price for your coffee is: € " + kape1.calculatePrize(kape1.getSize()));


        kape2.addFlavor("Cookies and Cream");
        kape2.addFlavor("Double Dutch");
        kape2.discount(10);
        kape2.updateStock(7);
        System.out.println(kape2.getName() + " " + kape2.getType() + " " + kape2.getSize());
        System.out.println(kape2.explain());
        System.out.println("The total price for your coffee is: € " + kape2.calculatePrize(kape2.getSize()));


    }
}

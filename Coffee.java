import java.util.Scanner;

public class Coffee {

    String[] name = {"Espresso", "Latte"};
    String[] type = {"Arabica", "Robusta"};
    String[] size = {"Small", "Medium", "Large"};
    double[] price = {45.50, 60.75, 80.50};
    String[] roastLevel = {"Light", "Medium", "Dark"};
    String origin;
    String[] flavorNotes = {"Chocolate", "Citrus", "Nutty"};
    String[] brewMethod = {"Drip", "French Press", "Espresso"};
    boolean isDecaf;
    boolean stock;

    void choice() {
        Scanner pili = new Scanner(System.in);
        String order;
        String typeOrder;
        String sizeOrder;
        double total = 0;

        for (int i = 0; i < name.length;i++){
            System.out.println(name[i] + " ");
            order = pili.nextLine();
        }

        for (int i = 0; i < type.length;i++){
            System.out.println(type[i] + " ");
            typeOrder = pili.nextLine();
        }

        for (int i = 0; i < size.length;i++){
            System.out.println(size[i] + " ");
            sizeOrder = pili.nextLine();

            if (sizeOrder == "Small") {
                total += price[0];
            }
        }
    }
}

import java.util.Scanner;

public class ngofi {
    public static void main(String[] args) {
        Scanner info = new Scanner(System.in);

            String menu = String.format("""
                    ~~~~ Menu ~~~~
                    1. Espresso - 50.0 PHP
                    2. Latte - 70.0 PHP
                    3. Cappuccino - 65.0 PHP
                    4. Mocha - 80.0 PHP
                    0. Finish Order
                    Choose your coffee (1-4, or 0 to finish):
                    """);
        loop: while (true) {
            System.out.print(menu);

            String coffeeName;
            double price = 0;
            int coffeeChoice = info.nextInt();
            switch (coffeeChoice) {
                case (1):
                    coffeeName = "Espresso";
                    price = 50.0;
                    break;
                case (2):
                    coffeeName = "Latte";
                    price = 70.0;
                    break;
                case (3):
                    coffeeName = "Cappuccino";
                    price = 65.0;
                    break;
                case (4):
                    coffeeName = "Mocha";
                    price = 80.0;
                    break;
                case (0):
                    break loop;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    return;
            }
            System.out.println("Enter quantity: ");
            int quantity = info.nextInt();
        }

    }
}

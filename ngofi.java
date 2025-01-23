import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ngofi {


    static String[] coffeeName = {"Espresso", "Latte", "Cappuccino", "Mocha"};
    static double[] price = {50.0, 70.0, 65.0, 80.0};


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


        int[] quantities = new int[coffeeName.length];


        while (true) {
            System.out.print(menu);
            int coffeeChoice = 0;
            try {
                coffeeChoice = info.nextInt();
            } catch (InputMismatchException e) {
                System.out.print(menu);
                info.nextLine();
                coffeeChoice = info.nextInt();
            }


            if(coffeeChoice == 0){
                break;
            } else if (coffeeChoice < 1 || coffeeChoice > coffeeName.length) {
                System.out.println("Invalid input. Please try again");
                System.out.print(menu);
                info.nextInt();
            }


            System.out.println("Enter quantity: ");
            int quantity = info.nextInt();


            if(quantity <= 0){
                System.out.println("Please enter how many drinks you will order");
                System.out.println("Enter quantity: ");
                info.nextInt();
            }
            quantities[coffeeChoice -1] += quantity;
        }


        double subtotal = 0;
        for (int i = 0; i < coffeeName.length; i++) {
            subtotal += quantities[i] * price[i];
        }
        double vat = subtotal * 0.12;
        double grandTotal = subtotal + vat;
        receipt(coffeeName, price, quantities);

        System.out.printf("Subtotal: %.2f\n", subtotal);
        System.out.printf("VAT (12%%): %.2f\n", vat);
        System.out.printf("Grand Total: %.2f\n", grandTotal);
        System.out.println("-------------------------------");


        saveReceipt(quantities, subtotal, vat, grandTotal);


        System.out.println("Receipt saved to CoffeeReceipt.txt");
    }


    public static void receipt(String[] name,double[] price, int [] quantities){
        String header = String.format("""
               ~~~~~~~~~~~ Receipt ~~~~~~~~~~~
               """);
        String divider = String.format("""
               ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
               """);
        System.out.print(header);
        for (int i = 0; i < coffeeName.length; i++) {
            if (quantities[i] > 0) {
                System.out.printf("%d x %s @ %.2f each = %.2f\n", quantities[i], name[i], price[i], quantities[i] * price[i]);
            }
        }
        System.out.println(divider);
    }
    public static void saveReceipt(int[] quantities, double subtotal, double vat, double grandTotal) {
        try (FileWriter writer = new FileWriter("CoffeeReceipt.txt")) {
            writer.write("~~~~~~~~~~~ Receipt ~~~~~~~~~~~\n");
            for (int i = 0; i < coffeeName.length; i++) {
                if (quantities[i] > 0) {
                    writer.write(String.format("%d x %s @ %.2f each = %.2f\n", quantities[i], coffeeName[i], price[i], quantities[i] * price[i]));
                }
            }
            writer.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            writer.write(String.format("Subtotal: %.2f\n", subtotal));
            writer.write(String.format("VAT (12%%): %.2f\n", vat));
            writer.write(String.format("Grand Total: %.2f\n", grandTotal));
            writer.write("-------------------------------\n");
        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}


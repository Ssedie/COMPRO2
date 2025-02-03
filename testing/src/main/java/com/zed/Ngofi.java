package com.zed;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Ngofi {


    static String[][] coffees =
            {{"Espresso", "50.0"},
            {"Latte", "70.0"},
            {"Cappuccino", "65.0"},
            {"Mocha","80.0"}};


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


        int[] quantities = new int[100];

        System.out.println(coffees.length);

        double subtotal = 0;

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
            } else if (coffeeChoice < 1 || coffeeChoice > coffees.length) {
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



        for (int i = 0; i < coffees.length; i++) {
            for(int j = 0; j < 2;j++) {
                subtotal += quantities[i] * Double.parseDouble(coffees[i][j]);
            }
        }

        double vat = subtotal * 0.12;
        double grandTotal = subtotal + vat;

        receipt(coffees, quantities, subtotal, vat, grandTotal);

        saveReceipt(quantities, subtotal, vat, grandTotal);


        System.out.println("Receipt saved to CoffeeReceipt.txt");
    }


    /**
     *
     * @param coffee
     * @param quantities
     * @param subtotal
     * @param vat
     * @param grandTotal
     */
    public static void receipt(String[][] coffee, int [] quantities, double subtotal, double vat, double grandTotal ){
        String header = String.format("""
               ~~~~~~~~~~~ Receipt ~~~~~~~~~~~
               """);
        String divider = String.format("""
               ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
               """);
        System.out.print(header);
        for (int i = 0; i < coffees.length; i++) {
            if (quantities[i] > 0) {
                System.out.printf("%d x %s @ %.2f each = %.2f\n", quantities[i], coffee[i], coffee[i][i] , quantities[i] * Double.parseDouble(coffee[i][i]));
            }
        }
        System.out.print(divider);
        System.out.printf("Subtotal: %.2f\n", subtotal);
        System.out.printf("VAT (12%%): %.2f\n", vat);
        System.out.printf("Grand Total: %.2f\n", grandTotal);
        System.out.print(divider);
    }

    /**
     * Used to create a txt file for the receipt to be saved
     *
     * @param quantities An array that has the value for the orders
     * @param subtotal A double type variable that has the value of all prices of what was ordered
     * @param vat A double type variable that is used to compute for the grand-total
     * @param grandTotal A double type variable that is the total for quantities added with vat
     */
    public static void saveReceipt(int[] quantities, double subtotal, double vat, double grandTotal) {
        try (FileWriter writer = new FileWriter("CoffeeReceipt.txt")) {
            writer.write("~~~~~~~~~~~ Receipt ~~~~~~~~~~~\n");
            for (int i = 0; i < coffees.length; i++) {
                if (quantities[i] > 0) {
                    writer.write(String.format("%d x %s @ %.2f each = %.2f\n", quantities[i], coffees[i], Double.parseDouble(coffees[i][i]), quantities[i] * Double.parseDouble(coffees[i][i])));
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
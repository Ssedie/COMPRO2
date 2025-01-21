package com.zed;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner info = new Scanner(System.in);

        System.out.print("Hello and welcome!");
        System.out.println();

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }
}
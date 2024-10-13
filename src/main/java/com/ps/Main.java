package com.ps;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static Scanner commandScanner = new Scanner(System.in);
    static Scanner inputScanner = new Scanner(System.in);
    static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        String mainMenuCommand;
        do {
            System.out.println("Hello welcome to Account Ledger CLI Application");
            System.out.println("What would you like to do?");
            System.out.println("D) Add a deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            mainMenuCommand = commandScanner.nextLine().toLowerCase();

            switch (mainMenuCommand) {
                case "d":
                    addDeposit();
                    break;
                case "p":
                    makePayment();
                    break;
                case "l":
                    ledgerMenu();
                    break;
                case "x":
                    System.out.println("You choose to exit");
                    break;
                default:
                    System.out.println("Command not found");

            }

        } while (!mainMenuCommand.equals("x"));


    }

    public static void ledgerMenu() {
        String subMenuCommand;

        do {
            System.out.println("Which entry would you like this?");
            System.out.println("A) All");
            System.out.println("D) Deposit");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            subMenuCommand = commandScanner.nextLine().toLowerCase();
            switch (subMenuCommand) {
                case "a":
//                    allEntries();
                    break;
                case "d":
//                    depositMethod();
                    break;
                case "p":
//                    paymentsMethod();
                    break;
                case "r":
                    reportsMenu();
                    break;
                case "h":
                    System.out.println("Going back to home");
                    break;
                default:
                    System.out.println("Command not found");

            }

        } while (!subMenuCommand.equals("h"));
    }

    public static void reportsMenu() {
        int menu;
        do {
            System.out.println("Welcome to the Reports Screen");
            System.out.println("Please select a report screen to run");
            System.out.println("1) Month to Date");
            System.out.println("2) previous Month");
            System.out.println("3) Year to Month");
            System.out.println("4) previous Years");
            System.out.println("5) Search by vendor");
            System.out.println("0) Back");

            menu = commandScanner.nextInt();
            switch (menu) {
                case 1:
                    //MonthToDate();
                    break;
                case 2:
                    //previousMonth();
                    break;
                case 3:
                    //yearToMonth();
                    break;
                case 4:
                    //previousYears();
                    break;
                case 5:
                    //SearchByVendor();
                    break;
                case 0:
                    System.out.println("Going back...");
                    break;
                default:
                    System.out.println("Command not found");


            }


        } while (menu != 0);
    }

    public static void addDeposit() {
        System.out.println("Enter the Description");
        String description = inputScanner.nextLine();
        System.out.println("Enter the vendor");
        String vendor = inputScanner.nextLine();
        double amount;
        do {
            System.out.println("Please enter the Deposit amount");
            amount = inputScanner.nextDouble();
            if (amount <= 0) {
                System.out.println("Please enter the payment amount");

            }


        } while (amount <= 0);

        Transaction deposit = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
        transactions.add(deposit);
        System.out.println("Deposit added successfully");


    }

    public static void makePayment() {
        System.out.println("Enter the Description");
        String description = inputScanner.nextLine();
        System.out.println("Enter the vendor");
        String vendor = inputScanner.nextLine();
        double amount;
        do {
            System.out.println("Please enter the payment amount");
            amount = inputScanner.nextDouble();
            if (amount <= 0) {
                System.out.println("Enter a positive number");

            }

        } while (amount <= 0);

        Transaction payment = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, -amount);
        transactions.add(payment);

    }


}
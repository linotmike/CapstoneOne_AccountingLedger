package com.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    static Scanner commandScanner = new Scanner(System.in);
    static Scanner inputScanner = new Scanner(System.in);
    static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        loadTransactionFromFile();
        String mainMenuCommand;

        do {
            System.out.println("Hello welcome to Account Ledger CLI Application");
            System.out.println("What would you like to do?");
            System.out.println("D) Add a deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            try {
                mainMenuCommand = commandScanner.nextLine().toLowerCase().trim();
            } catch (Exception e) {
                mainMenuCommand = "x";
            }

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
            System.out.println("Which entry would you like to see?");
            System.out.println("A) All");
            System.out.println("D) Deposit");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            try {
                subMenuCommand = commandScanner.nextLine().toLowerCase().trim();

            } catch (Exception e) {
                subMenuCommand = "h";
            }

            switch (subMenuCommand) {
                case "a":
                    allEntries();
                    break;
                case "d":
                    showDeposit();
                    break;
                case "p":
                    showPayments();
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
        int reportsMenuCommand;
        do {
            System.out.println("Welcome to the Reports Screen");
            System.out.println("Please select a report screen to run");
            System.out.println("1) Month to Date");
            System.out.println("2) previous Month");
            System.out.println("3) Year to Month");
            System.out.println("4) previous Years");
            System.out.println("5) Search by vendor");
            System.out.println("0) Back");
            try {
                reportsMenuCommand = commandScanner.nextInt();

            } catch (InputMismatchException ime) {
                reportsMenuCommand = 0;

            }

            switch (reportsMenuCommand) {
                case 1:
                    monthToDate();
//                    System.out.println("testing");
                    break;
                case 2:
                    previousMonth();
                    break;
                case 3:
                    yearToDate();
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
                    System.out.println("Command not found please try again");


            }


        } while (reportsMenuCommand != 0);
    }

    public static void loadTransactionFromFile() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));
            String firstLine = bufferedReader.readLine();
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                String[] transactionsArr = input.split("\\|");
                if (transactionsArr.length != 5) {
                    System.out.println("Invalid Format " + input);
                    continue;

                }
                try {
                    LocalDate date = LocalDate.parse(transactionsArr[0].trim());
                    LocalTime time = LocalTime.parse(transactionsArr[1].trim());
                    String description = transactionsArr[2];
                    String vendor = transactionsArr[3];
                    double amount = Double.parseDouble(transactionsArr[4]);

                    transactions.add(new Transaction(date, time, description, vendor, amount));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void addDeposit() {
        System.out.println("Enter the Description");
        String description = inputScanner.nextLine();
//        commandScanner.nextLine();
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
        System.out.println("Deposit added successfully " + deposit);


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
        System.out.println("Payment successfully made: " + payment);

    }

    public static void allEntries() {
        if (transactions.isEmpty()) {
            System.out.println("There are no transactions");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);

            }
        }


    }

    public static void showDeposit() {
        if (transactions.isEmpty()) {
            System.out.println("There are no transactions");

        }
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction.getAmount() > 0) {
                System.out.println("Here are the deposits!");
                System.out.print(transaction + "\n");

            }
        }

    }

    public static void showPayments() {
        if (transactions.isEmpty()) {
            System.out.println("There are no transactions");
        }
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction.getAmount() < 0) {
                System.out.println("Here are the payments!");
                System.out.println(transaction);
            }
        }
    }

    public static void monthToDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfMonth = currentDate.withDayOfMonth(1);// first day of the month
        if (transactions.isEmpty()) {
            System.out.println("There are no transactions");
        }
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);

            if ((transaction.getDate().isEqual(startOfMonth) || transaction.getDate().isAfter(startOfMonth)
                    && (transaction.getDate().isEqual(currentDate) || transaction.getDate().isBefore(currentDate)))) {
                System.out.println(transaction);


            }
        }
    }

    public static void previousMonth() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();
        int previousMonth;
        int previousYear = currentYear;

        if (currentMonth == 1) {
            previousMonth = 12;
            previousYear = currentYear - 1;

        } else {
            previousMonth = currentMonth - 1;

        }


        if (transactions.isEmpty()) {
            System.out.println("There are no transaction");

        }
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);

            LocalDate transactionDate = transaction.getDate();

            if (transactionDate.getMonthValue() == previousMonth && transactionDate.getYear() == previousYear) {
                System.out.println(transaction);
            }


        }
    }

    public static void yearToDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfYear = currentDate.withDayOfYear(1);

        if (transactions.isEmpty()) {
            System.out.println("There are no transactions");
            return;

        }
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if ((transaction.getDate().isEqual(startOfYear) || transaction.getDate().isAfter(startOfYear)) &&
                    (transaction.getDate().isEqual(currentDate) || transaction.getDate().isBefore(currentDate))) {
                System.out.println(transaction);
            }


        }


    }

    public static void previousYear() {


    }


//public static void previousMonth() {
//    LocalDate currentDate = LocalDate.now();
//    LocalDate startOfCurrentMonth = currentDate.withDayOfMonth(1);//fisrt day of the month
//    LocalDate startOfPreviousMonth = startOfCurrentMonth.minusMonths(1); //first day of the previous month
//    LocalDate lastDayOfPreviousMonth = startOfPreviousMonth.minusDays(1);// last day of the previous month
//
//
//    if (transactions.isEmpty()) {
//        System.out.println("There are no transaction");
//
//    }
//    for (int i = 0; i < transactions.size(); i++) {
//        Transaction transaction = transactions.get(i);
//
//     if ((transaction.getDate().isEqual(startOfPreviousMonth) || transaction.getDate().isAfter(startOfPreviousMonth) &&
//                    transaction.getDate().isEqual(lastDayOfPreviousMonth) || transaction.getDate().isBefore(lastDayOfPreviousMonth)
//
//            )) {
//                System.out.println(transaction);
//            }
//
//
//
//    }
//}


}
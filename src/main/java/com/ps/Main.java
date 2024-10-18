package com.ps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    static Scanner commandScanner = new Scanner(System.in);
    static Scanner inputScanner = new Scanner(System.in);
    static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        loadTransactionFromFile();
//        loadHtml();
        String mainMenuCommand;

        do {
            System.out.println("Hello welcome to Account Ledger CLI Application\n");
            System.out.println("What would you like to do?\n");
            System.out.println("D) Add a deposit\n");
            System.out.println("P) Make Payment\n");
            System.out.println("L) Ledger\n");
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
            System.out.println("Which entry would you like to see?\n");
            System.out.println("A) All\n");
            System.out.println("D) Deposit\n");
            System.out.println("P) Payments\n");
            System.out.println("R) Reports\n");
            System.out.println("H) Home\n");

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
            System.out.println("Welcome to the Reports Screen\n");
            System.out.println("Please select a report screen to run\n");
            System.out.println("1) Month to Date\n");
            System.out.println("2) previous Month\n");
            System.out.println("3) Year to Date\n");
            System.out.println("4) previous Years\n");
            System.out.println("5) Search by vendor\n");
            System.out.println("6) Custom Search\n");
            System.out.println("0) Back\n");
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
                    previousYear();
                    break;
                case 5:
                    searchByVendor();
                    break;
                case 6:
                    customSearch();
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
                input = input.trim();
                if (input.isEmpty()) {
                    continue;
                }
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
                    String amountArr = transactionsArr[4];
                    double amount = Double.parseDouble(amountArr);


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
//        inputScanner.nextLine();
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
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("hh:mm:ss");

        String formattedDate = date.format(df);
        String formattedTime = time.format(tf);


        Transaction deposit = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
        transactions.add(deposit);
        System.out.println("Deposit added successfully\n " + deposit);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
            bufferedWriter.write(String.format("\n%s|%s|%s|%s|%f",
                    formattedDate,
                    formattedTime,
                    deposit.getDescription(),
                    deposit.getVendor(),
                    deposit.getAmount()
            ));

            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadHtml();


    }

    public static void makePayment() {
        System.out.println("Enter the Description");
        String description = inputScanner.nextLine();
//        inputScanner.nextLine();
//        commandScanner.nextLine();
        commandScanner.nextLine();
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


        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("hh:mm:ss");

        String formattedDate = date.format(df);
        String formattedTime = time.format(tf);
        Transaction payment = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, -amount);
        transactions.add(payment);
        System.out.println("Payment successfully made:\n " + payment);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
            bufferedWriter.write(String.format("\n%s|%s|%s|%s|%f",
                    formattedDate,
                    formattedTime,
                    payment.getDescription(),
                    payment.getVendor(),
                    payment.getAmount()
            ));

            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        loadHtml();
    }

    public static void allEntries() {
        if (transactions.isEmpty()) {
            System.out.println("There are no transactions");
        } else {
            Collections.reverse(transactions);
            for (Transaction transaction : transactions) {
                System.out.println(transaction);

            }
        }
        Collections.reverse(transactions);


    }

    public static void showDeposit() {
        if (transactions.isEmpty()) {
            System.out.println("There are no transactions");
            return;

        }
        Collections.reverse(transactions);
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction.getAmount() > 0) {
                System.out.println("Here are the deposits!");
                System.out.print(transaction + "\n");

            }
        }
        Collections.reverse(transactions);

    }

    public static void showPayments() {
        if (transactions.isEmpty()) {
            System.out.println("There are no transactions");
            return;
        }
        Collections.reverse(transactions);
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction.getAmount() < 0) {
                System.out.println("Here are the payments!");
                System.out.println(transaction);
            }
        }
        Collections.reverse(transactions);
    }

    public static void monthToDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfMonth = currentDate.withDayOfMonth(1);// first day of the month
        if (transactions.isEmpty()) {
            System.out.println("There are no transactions");
            return;
        }
        Collections.reverse(transactions);
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);

            if ((transaction.getDate().isEqual(startOfMonth) || transaction.getDate().isAfter(startOfMonth)
                    && (transaction.getDate().isEqual(currentDate) || transaction.getDate().isBefore(currentDate)))) {
                System.out.println(transaction+ "\n");


            }
        }
        Collections.reverse(transactions);
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
            return;

        }
        Collections.reverse(transactions);
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);

            LocalDate transactionDate = transaction.getDate();

            if (transactionDate.getMonthValue() == previousMonth && transactionDate.getYear() == previousYear) {
                System.out.println(transaction + "\n");
            }


        }
        Collections.reverse(transactions);
    }

    public static void yearToDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfYear = currentDate.withDayOfYear(1);

        if (transactions.isEmpty()) {
            System.out.println("There are no transactions");
            return;

        }
        Collections.reverse(transactions);
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if ((transaction.getDate().isEqual(startOfYear) || transaction.getDate().isAfter(startOfYear)) &&
                    (transaction.getDate().isEqual(currentDate) || transaction.getDate().isBefore(currentDate))) {
                System.out.println(transaction + "\n");
            }


        }
        Collections.reverse(transactions);


    }

    public static void previousYear() {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int previousYear = currentYear - 1;

        if (transactions.isEmpty()) {
            System.out.println("There are no transactions");
            return;

        }
        Collections.reverse(transactions);
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction.getDate().getYear() == previousYear) {
                System.out.print(transaction + "\n");
            }
        }
        Collections.reverse(transactions);


    }

    public static void searchByVendor() {
//        String response = 
        System.out.println("Please enter the name of the vendor");
        commandScanner.nextLine();
        String response = commandScanner.nextLine();
        System.out.println("The name of the vendor: " + response);

        if (response.isEmpty()) {
            System.out.println("There is no vendor by that name");

        }
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction.getVendor().equalsIgnoreCase(response)) {
                System.out.println(transaction + "\n");
            }
        }


    }

    public static void loadHtml() {
//        loadTransactionFromFile();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        StringBuilder html = new StringBuilder();
        for (Transaction transaction : transactions) {
            html.append("<tr>");
            html.append("<td>").append(transaction.getDate()).append("</td>");
            html.append("<td>").append(transaction.getTime().format(timeFormatter)).append("</td>");
            html.append("<td>").append(transaction.getDescription()).append("</td>");
            html.append("<td>").append(transaction.getVendor()).append("</td>");
            if (transaction.getAmount() < 0) {
                html.append("<td style= 'color:red;'>").append(String.format("%.2f", transaction.getAmount())).append("</td>");
            } else {
                html.append("<td>").append(String.format("%.2f", transaction.getAmount())).append("</td>");

            }
            html.append("</tr>\n");
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("web/transactions_template.html"));
            StringBuilder strBuilder = new StringBuilder();
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                strBuilder.append(input).append("\n");
            }
            bufferedReader.close();
//            String finalHtml = String.format(strBuilder.toString(), html.toString());
            String finalHtml = strBuilder.toString().replace("<!-- %s -->", html.toString());


            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("web/transactions.html"));
            bufferedWriter.write(finalHtml);
//            System.out.println(finalHtml);
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void customSearch() {
        LocalDate startDate = null;
        LocalDate endDate = null;
        String description = null;
        String vendor = null;
        Double amount = null;

        System.out.println("Would you like to include start date in your custom search? 1) yes 2) No");
        int response = commandScanner.nextInt();
        commandScanner.nextLine();
        if (response == 1) {
            System.out.println("Enter the start date (yyyy-mm-dd)");
            String startStr = commandScanner.nextLine().trim();
            startDate = LocalDate.parse(startStr);
        }

        System.out.println("Would you like to include end date in your custom search? 1) yes 2) No");
        response = commandScanner.nextInt();
        commandScanner.nextLine();
        if (response == 1) {
            System.out.println("Enter the end date (yyyy-mm-dd)");
            String endStr = commandScanner.nextLine().trim();
            endDate = LocalDate.parse(endStr);
        }

        System.out.println("Would you like to include a description in your search 1) yes 2)No");
        response = commandScanner.nextInt();
        commandScanner.nextLine();
        if (response == 1) {
            System.out.println("Enter the description");
            description = commandScanner.nextLine().trim();
        }

        System.out.println("Would you like to include a vendor in your search 1) yes 2)No");
        response = commandScanner.nextInt();
        commandScanner.nextLine();
        if (response == 1) {
            System.out.println("Enter the name of the vendor");
            vendor = commandScanner.nextLine().trim();
        }

        System.out.println("Would you like to include an amount in your search 1) yes 2)No");
        response = commandScanner.nextInt();
        commandScanner.nextLine();
        if (response == 1) {
            System.out.println("Enter the amount");
            amount = commandScanner.nextDouble();
            commandScanner.nextLine();
        }

        System.out.println("\nSearching based on the information provided\n\n");

        if (startDate != null) {
            System.out.println("Start Date: " + startDate);
        } else {
            System.out.println("Start Date: " + " Not Specified");
        }
        if (endDate != null) {
            System.out.println("End Date: " + endDate);
        } else {
            System.out.println("End Date: " + " Not Specified");
        }
        if (description != null) {
            System.out.println("Description: " + description);
        } else {
            System.out.println("Description: " + " Not Specified");
        }
        if (vendor != null) {
            System.out.println("Vendor: " + vendor);
        } else {
            System.out.println("Vendor: " + " Not Specified");
        }
        if (amount != null) {
            System.out.println("Amount: " + amount);
        } else {
            System.out.println("Amount: " + " Not Specified");
        }

        for (Transaction transaction : transactions) {
            boolean matches = true;

            if (startDate != null && transaction.getDate().isBefore(startDate)) {
                matches = false;
            }
            if (endDate != null && transaction.getDate().isAfter(endDate)) {
                matches = false;
            }
            if (description != null && !transaction.getDescription().equalsIgnoreCase(description)) {
                matches = false;
            }
            if (vendor != null && !transaction.getVendor().equalsIgnoreCase(vendor)) {
                matches = false;
            }
//            if (amount != null && transaction.getAmount() != amount) {
//                matches = false;
//            }
            if (amount != null && Math.abs(transaction.getAmount() - amount) > 0.00001) {
                matches = false;
            }

            if (matches) {
                System.out.println(transaction +"\n");
            }

        }


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
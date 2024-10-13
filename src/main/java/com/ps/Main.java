package com.ps;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;


public class Main {
    static Scanner commandScanner = new Scanner(System.in);
    static Scanner inputScanner = new Scanner(System.in);
    public static void main(String[] args) {
        String mainMenuCommand;
        do{
            System.out.println("Hello welcome to Account Ledger CLI Application");
            System.out.println("What would you like to do?");
            System.out.println("D) Add a deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            mainMenuCommand = commandScanner.nextLine().toLowerCase();

            switch(mainMenuCommand){
                case "d":
//                    addDeposit();
                    break;
                case "p":
//                    makePayment();
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

        }while(!mainMenuCommand.equals("x"));






    }
public static void ledgerMenu(){
        String subMenuCommand;

        do{
            System.out.println("Which entry would you like this?");
            System.out.println("A) All");
            System.out.println("D) Deposit");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            subMenuCommand = commandScanner.nextLine().toLowerCase();
            switch(subMenuCommand){
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
//                    reportsMethod();
                    break;
                case "h":
                    System.out.println("Going back to home");
                    break;
                default:
                    System.out.println("Command not found");

            }

        }while(!subMenuCommand.equals("h"));
}


}
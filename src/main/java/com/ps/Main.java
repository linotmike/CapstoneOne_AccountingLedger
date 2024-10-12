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
//                    ledger();
                    break;
                case "x":
                    System.out.println("You choose to exit");
                default:
                    System.out.println("Command not found");

            }

        }while(!mainMenuCommand.equals("x"));






    }



}
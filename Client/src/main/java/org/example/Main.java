package org.example;

import java.util.Locale;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Application bankingApp = new Application(new APIClient("http://localhost:8080/bank"));
        int selection;
        String textualInput;
        Scanner input = new Scanner(System.in);
        while(true) {
            if (bankingApp.isLoggedIn()) {
                System.out.println("What do you want to do?");
                System.out.println("-------------------------\n");
                System.out.println("1 - Add Money");
                System.out.println("2 - Take Money");
                System.out.println("3 - View balance");
                System.out.println("4 - Transfer Money");
                System.out.println("5 - View ID");
                System.out.println("6 - Log out");
                System.out.println("7 - Quit");
                selection = input.nextInt();
                input.nextLine();
                if (selection == 1) {
                    System.out.println("-------------------------\n");
                    System.out.println("Please enter the amount you want to add: ");
                    input.useLocale(Locale.US); //to make sure that the . is a comma
                    double amount = input.nextDouble();
                    bankingApp.addMoney(amount);
                }
                else if (selection == 2) {
                    System.out.println("------------------------\n");
                    System.out.println("Please enter the amount you want to take: ");
                    input.useLocale(Locale.US);
                    double amount = input.nextDouble();
                    bankingApp.takeMoney(amount);
                }
                else if (selection == 3) {
                    System.out.println("-------------------------\n");
                    bankingApp.checkBalance();
                }
                else if (selection == 4) {
                    System.out.println("-------------------------\n");
                    System.out.println("Who do you want to transfer money to? (id)");
                    long id = input.nextLong();
                    input.nextLine();
                    System.out.println("How much do you want to transfer?");
                    double amount = input.nextDouble();
                    bankingApp.transferMoney(amount, id);
                }
                else if (selection == 5) {
                    System.out.println("-------------------------\n");
                    bankingApp.checkID();
                }
                else if (selection == 6){
                    System.out.println("-------------------------\n");
                    bankingApp.logout();
                }
                else{
                    System.out.println("-------------------------\n");
                    System.exit(0);
                }
            } else {
                System.out.println("What do you want to do?");
                System.out.println("-------------------------\n");
                System.out.println("1 - Log In");
                System.out.println("2 - Create new account");
                System.out.println("3 - Quit");
                selection = input.nextInt();
                input.nextLine();
                if (selection == 1) {
                    System.out.println("-------------------------\n");
                    System.out.println("Please enter your name: ");
                    textualInput = input.nextLine();
                    bankingApp.login(textualInput);
                } else if (selection == 2) {
                    System.out.println("-------------------------\n");
                    System.out.println("Please enter your name: ");
                    textualInput = input.nextLine();
                    bankingApp.createBankAccount(textualInput);
                } else {
                    System.exit(0);
                }
            }
        }
    }
}

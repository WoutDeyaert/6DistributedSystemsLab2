package org.example;

public class Application {

    private BankAccount bankAccount;
    private long currentID;
    private APIClient apiClient;

    public Application(APIClient apiClient) {
        this.apiClient = apiClient;
        currentID = 0;
    }

    public boolean isLoggedIn(){
        return currentID != 0;
    }
    public void createBankAccount(String username) {
        try {
            apiClient.createAccount(username);
            System.out.println("Account created successfully");
        }
        catch(RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    public void login(String username) {
        try{
            bankAccount = apiClient.getAccount(username);
            currentID = bankAccount.getId();
            System.out.println("Logged in successfully");
        }
        catch (RuntimeException e){
            System.err.println(e.getMessage());
        }
    }

    public void logout() {
        bankAccount = null;
        currentID = 0;
        System.out.println("Logged out successfully");
    }

    public void addMoney(double amount){
        if (currentID == 0){
            System.err.println("You need to login first!");
        }
        else{
            try {
                apiClient.addMoney(currentID, amount);
                System.out.println("Money added successfully");
            }
            catch (RuntimeException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public void takeMoney(double amount){
        if (currentID == 0){
            System.err.println("You need to login first!");
        }
        else{
            try {
                apiClient.takeMoney(currentID, amount);
                System.out.println("Money taken successfully");
            }
            catch (RuntimeException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public void transferMoney(double amount,long idto){
        if (currentID == 0){
            System.err.println("You need to login first!");
        }
        else{
            try {
                apiClient.transferMoney(this.currentID, idto, amount);
                System.out.println("Money transfered successfully");
            }
            catch (RuntimeException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public void checkBalance(){
        if (currentID == 0){
            System.err.println("You need to login first!");
        }
        else{
            try{
                bankAccount = apiClient.getAccount(currentID);
                System.out.println("Current balance: " + bankAccount.getBalance());
            }
            catch(RuntimeException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public void checkID(){
        if (currentID == 0){
            System.err.println("You need to login first!");
        }
        else{
            System.out.println("Your ID: " + currentID);
        }
    }
}

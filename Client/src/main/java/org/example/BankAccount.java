package org.example;

public class BankAccount {

    private long id;
    private String owner;
    private float balance;

    public BankAccount(String owner,long id) {
        this.owner = owner;
        this.id = id;
        this.balance = 0;
    }

    public BankAccount() {
    }


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public float getBalance() {
        return balance;
    }

    public void addMoney(float amount) {
        this.balance += amount;
    }

    public void takeMoney(float amount) {
        this.balance -= amount;
    }
}

package dist.lab2.server;

public class BankAccount {

    private long id;
    private String owner;
    private float balance;

    public BankAccount(String owner,long id) {
        this.owner = owner;
        this.id = id;
        this.balance = 0;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

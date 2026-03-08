package dist.lab2.server.Service;

import dist.lab2.server.BankAccount;

public interface BankService {
    BankAccount newAccount(String ownerName);
    long getId(String ownerName);
    BankAccount getBankAccount(long id);
    BankAccount getBankAccount(String name);

    void takeMoney(long id, float amount);
    void addMoney(long id, float amount);
    void transferMoney(long idfrom, long idto, float amount);
}

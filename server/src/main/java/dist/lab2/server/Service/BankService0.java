package dist.lab2.server.Service;

import dist.lab2.server.BankAccount;
import dist.lab2.server.Exceptions.AccountNotFoundException;
import dist.lab2.server.Exceptions.InsufficientBalanceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service //the framework has to know that this is the service
public class BankService0 implements BankService{

    private List<BankAccount> bankAccounts = new ArrayList<>();


    @Override
    public BankAccount getBankAccount(long id) {
        Optional<BankAccount> o = bankAccounts.stream().filter(x -> x.getId() == id).findFirst();
        if (o.isPresent()) {
            return o.get();
        }
        else{
            throw new AccountNotFoundException(id);
        }
    }

    @Override
    public BankAccount getBankAccount(String name) {
        Optional<BankAccount> o = bankAccounts.stream().filter(x -> x.getOwner().equals(name)).findFirst();
        if (o.isPresent()) {
            return o.get();
        }
        else{
            throw new AccountNotFoundException(0);
        }
    }

    @Override
    public void takeMoney(long id, float amount) {
        Optional<BankAccount> o = bankAccounts.stream().filter(x -> x.getId() == id).findFirst();
        if (o.isPresent()) {
            BankAccount bankAccount = o.get();
            if (bankAccount.getBalance() >= amount) {
                bankAccount.takeMoney(amount);
            }
            else{
                throw new InsufficientBalanceException();
            }
        }
        else{
            throw new AccountNotFoundException(id);
        }
    }

    @Override
    public void addMoney(long id, float amount) {
        Optional<BankAccount> o = bankAccounts.stream().filter(x -> x.getId() == id).findFirst();
        if (o.isPresent()) {
            o.get().addMoney(amount);
        }
        else{
            throw new AccountNotFoundException(id);
        }
    }

    @Override
    public void transferMoney(long idfrom, long idto, float amount) {

        Optional<BankAccount> ofrom = bankAccounts.stream().filter(x -> x.getId() == idfrom).findFirst();
        Optional<BankAccount> oto = bankAccounts.stream().filter(x -> x.getId() == idto).findFirst();

        if (ofrom.isPresent()) {
            if (oto.isPresent()) {
                BankAccount bankAccountFrom = ofrom.get();
                BankAccount bankAccountTo = oto.get();
                if (bankAccountFrom.getBalance() >= amount) {
                    bankAccountFrom.takeMoney(amount);
                    bankAccountTo.addMoney(amount);
                }
                else{
                    throw new InsufficientBalanceException();
                }
            }
            else{
                throw new AccountNotFoundException(idto);
            }
        }
        else{
            throw new AccountNotFoundException(idfrom);
        }

    }

    @Override
    public BankAccount newAccount(String ownerName){
        long id = (long) (Math.random()*10000000);
        BankAccount ba = new BankAccount(ownerName, id);
        bankAccounts.add(ba);
        return ba;
    }
}

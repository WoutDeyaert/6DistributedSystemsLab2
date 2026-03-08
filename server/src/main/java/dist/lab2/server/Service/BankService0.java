package dist.lab2.server.Service;

import dist.lab2.server.BankAccount;
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
        return o.orElse(null);
    }

    @Override
    public BankAccount getBankAccount(String name) {
        Optional<BankAccount> o = bankAccounts.stream().filter(x -> x.getOwner().equals(name)).findFirst();
        return o.orElse(null);
    }

    @Override
    public void takeMoney(long id, float amount) {
        Optional<BankAccount> o = bankAccounts.stream().filter(x -> x.getId() == id).findFirst();
        if (o.isPresent()) {
            BankAccount bankAccount = o.get();
            if (bankAccount.getBalance() >= amount) {
                bankAccount.takeMoney(amount);
            }
        }
    }

    @Override
    public void addMoney(long id, float amount) {
        Optional<BankAccount> o = bankAccounts.stream().filter(x -> x.getId() == id).findFirst();
        o.ifPresent(x -> x.addMoney(amount));
    }

    @Override
    public void transferMoney(long idfrom, long idto, float amount) {

        Optional<BankAccount> ofrom = bankAccounts.stream().filter(x -> x.getId() == idfrom).findFirst();
        Optional<BankAccount> oto = bankAccounts.stream().filter(x -> x.getId() == idto).findFirst();

        if (ofrom.isPresent() && oto.isPresent()) {
            BankAccount bankAccountFrom = ofrom.get();
            BankAccount bankAccountTo = oto.get();
            if (bankAccountFrom.getBalance() >= amount) {
                bankAccountFrom.takeMoney(amount);
                bankAccountTo.addMoney(amount);
            }
        }
    }

    @Override
    public BankAccount newAccount(String ownerName){
        long id = (long) (Math.random()*10000000);
        BankAccount ba = new BankAccount(ownerName, id);
        bankAccounts.add(ba);
        return ba;
    }

    @Override
    public long getId(String ownerName) {
        Optional<BankAccount> o = bankAccounts.stream().filter(x -> x.getOwner().equals(ownerName)).findFirst();
        return o.map(BankAccount::getId).orElse(-1L);
    }

}

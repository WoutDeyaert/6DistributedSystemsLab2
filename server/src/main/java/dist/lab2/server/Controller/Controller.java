package dist.lab2.server.Controller;

import dist.lab2.server.BankAccount;
import dist.lab2.server.Service.BankService;
import dist.lab2.server.Service.BankService0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class Controller {

    @Autowired
    private BankService0 bankService;   //injects the banking service automatically

    @GetMapping("/")
    public String home(){
        return "Welcome on the banking application";
    }

    @PostMapping("/createAccount/{name}")
    public BankAccount createAccount(@PathVariable String name){
        return bankService.newAccount(name);
    }

    @GetMapping("/getAccountByID/{id}")
    public BankAccount getAccountById(@PathVariable long id){
        return bankService.getBankAccount(id);
    }

    @GetMapping("/getAccountByName/{name}")
    public BankAccount getAccountByName(@PathVariable String name){
        return bankService.getBankAccount(name);
    }

    @PutMapping("/addMoney/{id}/{amount}")
    public String addMoney(@PathVariable long id, @PathVariable float amount){
        bankService.addMoney(id, amount);
        return "Money added";
    }

    @PutMapping("/takeMoney/{id}/{amount}")
    public String takeMoney(@PathVariable long id, @PathVariable float amount){
        bankService.takeMoney(id, amount);
        return "Money taken";
    }

    @PutMapping("/transferMoney/{idFrom}/{idTo}/{amount}")
    public String transferMoney(@PathVariable long idFrom, @PathVariable long idTo, @PathVariable float amount){
        bankService.transferMoney(idFrom, idTo, amount);
        return "Money transfered";
    }
}
//6705787
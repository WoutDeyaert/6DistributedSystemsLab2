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

    @GetMapping("/getID/{name}")
    public BankAccount getAccount(@PathVariable String name){
        return bankService.getBankAccount(name);
    }

    @PutMapping("/addMoney/{id}/{amount}")
    public String addMoney(@PathVariable long id, @PathVariable float amount){
        bankService.addMoney(id, amount);
        return "Money added";
    }
}
//6705787
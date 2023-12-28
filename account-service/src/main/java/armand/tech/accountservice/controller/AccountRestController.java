package armand.tech.accountservice.controller;

import armand.tech.accountservice.clients.CustomerRestClient;
import armand.tech.accountservice.entity.BankAccount;
import armand.tech.accountservice.model.Customer;
import armand.tech.accountservice.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountRestController {

    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;

    @GetMapping("/accounts")
    public List<BankAccount> accountList(){
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        bankAccountList.forEach(bankAccount -> {
            bankAccount.setCustomer(customerRestClient.findCustomerById(bankAccount.getCustomerId()));
        });

        return bankAccountList;
    }

    @GetMapping("/accounts/{id}")
    public BankAccount bankAccountId(@PathVariable String id){
        BankAccount bankAccount = bankAccountRepository.findById(id).get();
        Customer customer = customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }
}

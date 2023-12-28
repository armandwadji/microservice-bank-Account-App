package armand.tech.accountservice;

import armand.tech.accountservice.clients.CustomerRestClient;
import armand.tech.accountservice.entity.BankAccount;
import armand.tech.accountservice.enums.AccountType;
import armand.tech.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient){
		return args -> {
			customerRestClient.findAllCustomers().forEach(customer -> {

				List<BankAccount> bankAccountList = new ArrayList<>();

				BankAccount bankAccount1 = BankAccount.builder()
						.accountId(UUID.randomUUID().toString())
						.currency("MAD")
						.balance(Math.random() * 80000)
						.type(AccountType.SAVING_ACCOUNT)
						.createdAt(LocalDate.now())
						.customerId(customer.getId())
						.build();

				BankAccount bankAccount2 = BankAccount.builder()
						.accountId(UUID.randomUUID().toString())
						.currency("MAD")
						.balance(Math.random() * 120000)
						.type(AccountType.CURRENT_ACCOUNT)
						.createdAt(LocalDate.now())
						.customerId(customer.getId())
						.build();

				bankAccountList.add(bankAccount1);
				bankAccountList.add(bankAccount2);

				bankAccountRepository.saveAll(bankAccountList);
			});
		};
	}

}

package armand.tech.customerservice;

import armand.tech.customerservice.config.GlobalConfig;
import armand.tech.customerservice.entity.Customer;
import armand.tech.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {
			List<Customer> customerList = new ArrayList<>();
			Customer customer1 = Customer.builder()
					.firstName("Armand")
					.lastName("WADJI")
					.email("princedjiwa@yahoo.com")
					.build();

			Customer customer2 = Customer.builder()
					.firstName("Ophelie")
					.lastName("MEI")
					.email("ophelie.mei@gmail.com")
					.build();

			customerList.add(customer1);
			customerList.add(customer2);

			customerRepository.saveAll(customerList);
		};
	}

}

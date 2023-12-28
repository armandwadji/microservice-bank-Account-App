package armand.tech.accountservice.clients;

import armand.tech.accountservice.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {

    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable Long id);

    @GetMapping("/customers")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultAllCustomers")
    List<Customer> findAllCustomers();

    default Customer getDefaultCustomer(Long id, Exception exception){
        return Customer.builder()
                .id(id)
                .firstName("Not Vailable")
                .lastName("Not vailable")
                .email("Not vailable")
                .build();
    }

    default List<Customer> getDefaultAllCustomers(Exception exception){
        return List.of();
    }
}

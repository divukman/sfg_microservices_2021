package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getById(UUID id) {
        final CustomerDto customerDto = CustomerDto.builder()
                .name("First Customer")
                .id(UUID.randomUUID())
                .build();

        return customerDto;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        return CustomerDto.builder()
                .name("New Customer")
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        log.info("Updating customer id " + customerId);
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        log.info("Deleting customer id " + customerId);
    }

}

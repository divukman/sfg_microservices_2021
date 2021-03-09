package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

}

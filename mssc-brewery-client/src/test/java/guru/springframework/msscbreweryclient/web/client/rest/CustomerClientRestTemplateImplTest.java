package guru.springframework.msscbreweryclient.web.client.rest;

import guru.springframework.msscbreweryclient.web.client.CustomerClient;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerClientRestTemplateImplTest {

    @Autowired
    CustomerClient customerClient;

    @Test
    void getCustomerById() {
        final CustomerDto customerDto = customerClient.getCustomerById(UUID.randomUUID());
        assertNotNull(customerDto);
    }

    @Test
    void createCustomer() {
        final URI uri = customerClient.createCustomer(CustomerDto.builder().name("Customer from test").build());
        assertNotNull(uri);
    }

    @Test
    void deleteCustomer() {
        customerClient.deleteCustomer(UUID.randomUUID());
    }

    @Test
    void updateCustomer() {
        customerClient.updateCustomer(UUID.randomUUID(), CustomerDto.builder().name("Customer from test").build());
    }
}
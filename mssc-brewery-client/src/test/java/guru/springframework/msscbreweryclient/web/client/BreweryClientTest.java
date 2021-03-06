package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.client.rest.BreweryClientImpl;
import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClientImpl breweryClient;

    @Test
    void getBeerById() {
        final BeerDto beerDto = breweryClient.getBeerById(UUID.randomUUID());
        assertNotNull(beerDto);
    }

    @Test
    void saveNewBeer() {
        final URI uri= breweryClient.saveNewBeer(BeerDto.builder().beerName("New Beer").beerStyle("LAGER").build());
        System.out.println(uri);
        assertNotNull(uri);
    }

    @Test
    void updateBeer() {
        final BeerDto beerDto = BeerDto.builder().beerName("New Beer").beerStyle("LAGER").build();
        breweryClient.updateBeer(UUID.randomUUID(), beerDto);
    }

    @Test
    void deleteBeer() {
        breweryClient.deleteBeer(UUID.randomUUID());
    }

    @Test
    void getCustomerById() {
        final CustomerDto customerDto = breweryClient.getCustomerById(UUID.randomUUID());
        assertNotNull(customerDto);
    }

    @Test
    void createCustomer() {
        final URI uri = breweryClient.createCustomer(CustomerDto.builder().name("Customer from test").build());
        assertNotNull(uri);
    }

    @Test
    void deleteCustomer() {
        breweryClient.deleteCustomer(UUID.randomUUID());
    }

    @Test
    void updateCustomer() {
        breweryClient.updateCustomer(UUID.randomUUID(), CustomerDto.builder().name("Customer from test").build());
    }
}
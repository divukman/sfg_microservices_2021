package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;

import java.net.URI;
import java.util.UUID;

public interface BreweryClient {
    CustomerDto getCustomerById(UUID uuid);
    URI createCustomer(CustomerDto customerDto);
    void deleteCustomer(UUID uuid);
    void updateCustomer(UUID uuid, CustomerDto customerDto);

    BeerDto getBeerById(final UUID uuid);
    URI saveNewBeer(final BeerDto beerDto);
    void updateBeer(final UUID uuid, final BeerDto beerDto);
    void deleteBeer(final UUID uuid);
}

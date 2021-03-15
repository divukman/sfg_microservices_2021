package guru.springframework.msscbreweryclient.web.client.rest;

import guru.springframework.msscbreweryclient.web.client.BreweryClient;
import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
@Setter
@Getter
public class BreweryClientImpl implements BreweryClient {
    public static String BEER_PATH_V1 = "/api/v1/beer/";
    public static String CUSTOMER_PATH_V1 = "/api/v1/customer/";

    private String apiHost;

    private final RestTemplate restTemplate;

    public BreweryClientImpl(final RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(final UUID uuid) {
        return restTemplate.getForObject(apiHost+BEER_PATH_V1 + uuid.toString(), BeerDto.class);
    }

    public URI saveNewBeer(final BeerDto beerDto) {
        return restTemplate.postForLocation(apiHost+BEER_PATH_V1, beerDto);
    }

    public void updateBeer(final UUID uuid, final BeerDto beerDto) {
       restTemplate.put(apiHost+BEER_PATH_V1 + uuid.toString(), beerDto);
    }

    public void deleteBeer(final UUID uuid) {
        restTemplate.delete(apiHost+BEER_PATH_V1 + uuid.toString());
    }

    @Override
    public CustomerDto getCustomerById(UUID uuid) {
        return restTemplate.getForObject(apiHost + CUSTOMER_PATH_V1 + uuid.toString(), CustomerDto.class);
    }

    @Override
    public URI createCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(apiHost + CUSTOMER_PATH_V1, customerDto);
    }

    @Override
    public void deleteCustomer(UUID uuid) {
        restTemplate.delete(apiHost + CUSTOMER_PATH_V1 + uuid);
    }

    @Override
    public void updateCustomer(UUID uuid, CustomerDto customerDto) {
        restTemplate.put(apiHost + CUSTOMER_PATH_V1 + uuid, customerDto);
    }
}

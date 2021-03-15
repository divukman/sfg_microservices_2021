package guru.springframework.msscbreweryclient.web.client.rest;

import guru.springframework.msscbreweryclient.web.client.CustomerClient;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@Slf4j
@ConfigurationProperties(value="sfg.brewery", ignoreUnknownFields = false)
public class CustomerClientRestTemplateImpl implements CustomerClient {
    public static final String API_V1_PATH = "/api/v1/customer/";

    private final RestTemplate restTemplate;
    private String apiHost;

    @Autowired
    public CustomerClientRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    @Override
    public CustomerDto getCustomerById(UUID uuid) {
        return restTemplate.getForObject(apiHost + API_V1_PATH + uuid.toString(), CustomerDto.class);
    }

    @Override
    public URI createCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(apiHost + API_V1_PATH, customerDto);
    }

    @Override
    public void deleteCustomer(UUID uuid) {
        restTemplate.delete(apiHost + API_V1_PATH + uuid);
    }

    @Override
    public void updateCustomer(UUID uuid, CustomerDto customerDto) {
        restTemplate.put(apiHost + API_V1_PATH + uuid, customerDto);
    }
}

package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
@Setter
@Getter
public class BreweryClient {
    public static String BEER_PATH_V1 = "/api/v1/beer/";

    private String apiHost;

    private final RestTemplate restTemplate;

    public BreweryClient(final RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(final UUID uuid) {
        return restTemplate.getForObject(apiHost+BEER_PATH_V1 + uuid.toString(), BeerDto.class);
    }
}

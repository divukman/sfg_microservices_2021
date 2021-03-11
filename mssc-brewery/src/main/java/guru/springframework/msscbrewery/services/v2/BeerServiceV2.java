package guru.springframework.msscbrewery.services.v2;


import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;

import java.util.UUID;

public interface BeerServiceV2 {
    BeerDtoV2 getBeerById(UUID beerId);
    BeerDtoV2 createBeer(final BeerDtoV2 beerDto);
    void updateBeer(final UUID beerId, final BeerDtoV2 beerDto);
    void deleteBeer(final UUID beerId);
}

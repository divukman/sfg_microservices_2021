package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);
    BeerDto createBeer(final BeerDto beerDto);
    void updateBeer(final UUID beerId, final BeerDto beerDto);
    void deleteBeer(final UUID beerId);
}

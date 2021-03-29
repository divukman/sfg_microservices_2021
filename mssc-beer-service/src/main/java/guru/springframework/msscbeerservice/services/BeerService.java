package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.web.exception.NotFoundException;
import guru.springframework.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {

    BeerDto findById(UUID beerId) throws NotFoundException;
    BeerDto saveNewBeer(BeerDto beerDto);
    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
    void deleteBeer(UUID beerId);
}

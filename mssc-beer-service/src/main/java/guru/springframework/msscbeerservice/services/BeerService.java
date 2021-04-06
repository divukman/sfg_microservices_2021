package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.web.exception.NotFoundException;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPageList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    BeerPageList listBeers(String beerName, BeerStyleEnum beerStyle, Boolean showQuantityOnHand, PageRequest pageRequest);
    BeerDto findById(UUID beerId, Boolean showQuantityOnHand) throws NotFoundException;
    BeerDto saveNewBeer(BeerDto beerDto);
    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
    void deleteBeer(UUID beerId);
}

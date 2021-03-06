package guru.springframework.msscbeerservice.services;

import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.model.BeerPageList;
import guru.sfg.brewery.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    BeerPageList listBeers(String beerName, BeerStyleEnum beerStyle, Boolean showQuantityOnHand, PageRequest pageRequest);
    BeerDto findById(UUID beerId, Boolean showQuantityOnHand);
    BeerDto saveNewBeer(BeerDto beerDto);
    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
    void deleteBeer(UUID beerId);
    BeerDto findByUpc(String upc, Boolean showQuantityOnHand);
}

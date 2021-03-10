package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDto getBeerById(UUID beerId) {
        final BeerDto beerDto  = BeerDto.builder()
                .id(beerId)
                .beerName("Dummy Beer")
                .beerStyle("Lager")
                .upc(1234L)
                .build();

        return beerDto;
    }

    @Override
    public BeerDto createBeer(BeerDto beerDto) {
        final BeerDto newBeer  = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName(beerDto.getBeerName())
                .beerStyle(beerDto.getBeerStyle())
                .upc(12345L)
                .build();

        return newBeer;
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        //@todo: implement
    }

    @Override
    public void deleteBeer(UUID beerId) {
        log.info("Deleting beer with id " + beerId);
    }

}

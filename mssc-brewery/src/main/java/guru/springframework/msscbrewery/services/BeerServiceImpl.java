package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

}

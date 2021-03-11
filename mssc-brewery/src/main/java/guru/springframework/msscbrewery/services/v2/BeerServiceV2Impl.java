package guru.springframework.msscbrewery.services.v2;

import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import guru.springframework.msscbrewery.web.model.v2.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
    @Override
    public BeerDtoV2 getBeerById(UUID beerId) {
        final BeerDtoV2 beerDto  = BeerDtoV2.builder()
                .id(beerId)
                .beerName("Dummy Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .upc(1234L)
                .build();

        return beerDto;
    }

    @Override
    public BeerDtoV2 createBeer(BeerDtoV2 beerDto) {
        final BeerDtoV2 beerDtov2  = BeerDtoV2.builder()
                .id(UUID.randomUUID())
                .beerName(beerDto.getBeerName())
                .beerStyle(beerDto.getBeerStyle())
                .upc(1234L)
                .build();

        return beerDto;
    }

    @Override
    public void updateBeer(UUID beerId, BeerDtoV2 beerDto) {
        log.info("Updating beer with id " + beerId);
    }

    @Override
    public void deleteBeer(UUID beerId) {
        log.info("Deleting beer with id " + beerId);
    }
}

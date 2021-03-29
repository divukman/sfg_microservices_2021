package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.rapositories.BeerRepository;
import guru.springframework.msscbeerservice.web.exception.NotFoundException;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    final BeerRepository beerRepository;
    final BeerMapper beerMapper;

    @Override
    public BeerDto findById(UUID beerId) {
        final Beer beer = beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException("Beer with ID: " + beerId + " not found."));
        return beerMapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        final Beer savedBeer = beerRepository.save(beerMapper.beerDtoToBeer(beerDto));
        return beerMapper.beerToBeerDto(savedBeer);
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        final Beer beer = beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException("Beer with ID: " + beerId + " not found."));

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(
                beerRepository.save(beer)
        );
    }

    @Override
    public void deleteBeer(UUID beerId) {
        beerRepository.deleteById(beerId);
    }
}

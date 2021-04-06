package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.rapositories.BeerRepository;
import guru.springframework.msscbeerservice.web.exception.NotFoundException;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPageList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    final BeerRepository beerRepository;
    final BeerMapper beerMapper;

    @Cacheable(cacheNames = "beerListCache", condition = "#showQuantityOnHand == false")
    @Override
    public BeerPageList listBeers(String beerName, BeerStyleEnum beerStyle, Boolean showQuantityOnHand, PageRequest pageRequest) {
        BeerPageList beerPageList = null;
        Page<Beer> beerPage = null;

        log.debug("beer service -> list beers called");

        if (!ObjectUtils.isEmpty(beerName) && beerStyle != null) {
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!ObjectUtils.isEmpty(beerName) && beerStyle == null) {
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (ObjectUtils.isEmpty(beerName) && beerStyle != null) {
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        beerPageList = new BeerPageList(
                showQuantityOnHand ?
                        beerPage.getContent().stream().map(beerMapper::beerToBeerDtoWithInventory).collect(Collectors.toList()) :
                        beerPage.getContent().stream().map(beerMapper::beerToBeerDto).collect(Collectors.toList()),
          PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
          beerPage.getTotalElements()
        );

        return beerPageList;
    }

    @Cacheable(cacheNames = "beerCache", key="#beerId", condition="#showQuantityOnHand == false")
    @Override
    public BeerDto findById(UUID beerId, Boolean showQuantityOnHand) {
        final Beer beer = beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException("Beer with ID: " + beerId + " not found."));
        return showQuantityOnHand ? beerMapper.beerToBeerDto(beer) : beerMapper.beerToBeerDto(beer);
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

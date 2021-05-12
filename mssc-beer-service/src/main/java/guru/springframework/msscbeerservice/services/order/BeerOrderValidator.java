package guru.springframework.msscbeerservice.services.order;

import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.brewery.model.BeerOrderLineDto;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.rapositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class BeerOrderValidator {
    private final BeerRepository beerRepository;

    public boolean validateOrder(final BeerOrderDto beerOrderDto) {
        boolean isValid = true;
        log.debug("Validating order id: " + beerOrderDto.getId().toString());

        // For each beer order line check if beer id and upc are valid
        final List<BeerOrderLineDto> lstBeerOrderLines =  beerOrderDto.getBeerOrderLines();
        for (int i = 0; i < lstBeerOrderLines.size(); i++) {
            final BeerOrderLineDto beerOrderLineDto = lstBeerOrderLines.get(i);
            final UUID beerId = beerOrderLineDto.getBeerId();
            final String beerUPC = beerOrderLineDto.getUpc();

            log.debug("Validating beer id: " + beerId + " with UPC: " + beerUPC);
            final Optional<Beer> beerOpt = beerRepository.findById(beerId);
            if (!beerOpt.isPresent() || (!beerOpt.get().getUpc().equalsIgnoreCase(beerOrderLineDto.getUpc()))) {
                isValid = false;
                break;
            }
        }

        log.debug("All beer order lines are valid: " + isValid);
        return isValid;
    }
}

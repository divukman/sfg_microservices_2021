package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.config.JmsConfig;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.events.BrewBeerEvent;
import guru.springframework.msscbeerservice.rapositories.BeerRepository;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewingService {
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {
        final List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            final Integer inventoryQuantityOnHand = beerInventoryService.getOnHandInventory(beer.getId());

            log.debug("----------------");
            log.debug("Brewing Service");
            log.debug("Beer name: " + beer.getBeerName());
            log.debug("Beer min on hand: " + beer.getMinOnHand());
            log.debug("Inventory: " + inventoryQuantityOnHand);
            log.debug("----------------");

            if (beer.getMinOnHand() >= inventoryQuantityOnHand) {
                jmsTemplate.convertAndSend(JmsConfig.QUEUE_BREWING_REQUEST,new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}

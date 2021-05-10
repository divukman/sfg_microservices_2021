package guru.springframework.msscbeerservice.services.brewing;

import guru.springframework.msscbeerservice.config.JmsConfig;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.sfg.brewery.model.events.BrewBeerEvent;
import guru.sfg.brewery.model.events.NewInventoryEvent;
import guru.springframework.msscbeerservice.rapositories.BeerRepository;
import guru.sfg.brewery.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @JmsListener( destination = JmsConfig.QUEUE_BREWING_REQUEST )
    //@Transactional
    public void listen(BrewBeerEvent brewBeerEvent) {
        final BeerDto beerDto = brewBeerEvent.getBeerDto();

        final Beer beer = beerRepository.findById(beerDto.getId()).get();
        final int qtb = beer.getQuantityToBrew();

        // brewing 'logic'
        beerDto.setQuantityOnHand(qtb);
        log.debug("Brewed beer: " + beer.getBeerName() + ", brewed no of beers: " + qtb);

        final NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);
        jmsTemplate.convertAndSend(JmsConfig.QUEUE_NEW_INVENTORY, newInventoryEvent);
    }
}

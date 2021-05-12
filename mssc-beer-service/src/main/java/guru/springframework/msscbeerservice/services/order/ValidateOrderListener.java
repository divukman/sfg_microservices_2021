package guru.springframework.msscbeerservice.services.order;

import guru.sfg.brewery.model.BeerOrderLineDto;
import guru.sfg.brewery.model.events.ValidateOrderRequest;
import guru.sfg.brewery.model.events.ValidateOrderResult;
import guru.springframework.msscbeerservice.config.JmsConfig;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.rapositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValidateOrderListener {
    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.QUEUE_VALIDATE_ORDER)
    public void listen(final ValidateOrderRequest validateOrderRequest) {
        boolean isValid = true;
        log.debug(" *** *** ***");
        log.debug("Beer Service: JMS message: Validate order request for order id: " + validateOrderRequest.getBeerOrderDto().getId().toString());
        log.debug(" *** *** ***");

        // For each beer order line check if beer id and upc are valid
        final List<BeerOrderLineDto> lstBeerOrderLines =  validateOrderRequest.getBeerOrderDto().getBeerOrderLines();
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
        jmsTemplate.convertAndSend(JmsConfig.QUEUE_VALIDATE_ORDER_RESULT, ValidateOrderResult.builder().orderId(validateOrderRequest.getBeerOrderDto().getId()).isValid(isValid).build());
    }
}

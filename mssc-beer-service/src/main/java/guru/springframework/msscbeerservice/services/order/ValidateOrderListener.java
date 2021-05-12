package guru.springframework.msscbeerservice.services.order;

import guru.sfg.brewery.model.events.ValidateOrderRequest;
import guru.sfg.brewery.model.events.ValidateOrderResult;
import guru.springframework.msscbeerservice.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValidateOrderListener {
    private final BeerOrderValidator beerOrderValidator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.QUEUE_VALIDATE_ORDER)
    public void listen(final ValidateOrderRequest validateOrderRequest) {
        log.debug(" *** *** ***");
        log.debug("Beer Service: JMS message: Validate order request for order id: " + validateOrderRequest.getBeerOrderDto().getId().toString());
        log.debug(" *** *** ***");

        boolean isValid = beerOrderValidator.validateOrder(validateOrderRequest.getBeerOrderDto());
        jmsTemplate.convertAndSend(JmsConfig.QUEUE_VALIDATE_ORDER_RESULT, ValidateOrderResult.builder().orderId(validateOrderRequest.getBeerOrderDto().getId()).isValid(isValid).build());
    }
}

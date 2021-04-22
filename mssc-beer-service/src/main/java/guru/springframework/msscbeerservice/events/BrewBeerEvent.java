package guru.springframework.msscbeerservice.events;

import guru.springframework.msscbeerservice.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {
    private static final long serialVersionUID = 2583516500003705306L;

    public BrewBeerEvent(final BeerDto beerDto) {
        super(beerDto);
    }

}

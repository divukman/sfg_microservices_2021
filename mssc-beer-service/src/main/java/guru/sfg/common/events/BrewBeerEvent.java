package guru.sfg.common.events;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.*;

@NoArgsConstructor
@Builder
@Setter
@Getter
public class BrewBeerEvent extends BeerEvent {
    private static final long serialVersionUID = 2583516500003705306L;

    public BrewBeerEvent(final BeerDto beerDto) {
       super(beerDto);
    }

}

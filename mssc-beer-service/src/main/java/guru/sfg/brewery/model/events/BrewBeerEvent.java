package guru.sfg.brewery.model.events;

import guru.sfg.brewery.model.BeerDto;
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

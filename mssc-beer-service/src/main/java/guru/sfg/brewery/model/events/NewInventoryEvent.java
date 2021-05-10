package guru.sfg.brewery.model.events;

import guru.sfg.brewery.model.BeerDto;
import lombok.*;

@NoArgsConstructor
@Builder
@Setter
@Getter
public class NewInventoryEvent extends BeerEvent {
    private static final long serialVersionUID = -2618760829308238039L;

    public NewInventoryEvent(final BeerDto beerDto) {
       super(beerDto);
    }
}

package guru.sfg.common.events;

import guru.springframework.msscbeerservice.web.model.BeerDto;
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

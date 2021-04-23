package guru.sfg.common.events;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeerEvent implements Serializable {
    private static final long serialVersionUID = -5013098052583709758L;

    private BeerDto beerDto;
}

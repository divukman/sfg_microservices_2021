package guru.springframework.msscbeerservice.bootstrap;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.rapositories.BeerRepository;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @deprecated Moved to data.sql init script, this is due to ID being auto-generated GUID
 */
@Profile("localmysql")
@Component
@RequiredArgsConstructor
@Slf4j
public class Bootstrap implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) {
        log.info("Beer service bootstrap...");
        if (beerRepository.count()  == 0) {
            loadBeers();
        }
    }


    private void loadBeers() {
        beerRepository.save(
                Beer.builder()
                        .beerName("Karlovacko")
                        .beerStyle(BeerStyleEnum.ALE)
                        .minOnHand(10)
                        .quantityToBrew(299)
                        .price(BigDecimal.valueOf(11.5))
                        .upc(BEER_1_UPC)
                        .build()
        );

        beerRepository.save(
                Beer.builder()
                        .beerName("Mango Bobs")
                        .beerStyle(BeerStyleEnum.GOSE)
                        .minOnHand(10)
                        .quantityToBrew(299)
                        .price(BigDecimal.valueOf(14.5))
                        .upc(BEER_2_UPC)
                        .build()
        );

        beerRepository.save(
                Beer.builder()
                        .beerName("Galaxy Cat")
                        .beerStyle(BeerStyleEnum.GOSE)
                        .minOnHand(10)
                        .quantityToBrew(299)
                        .price(BigDecimal.valueOf(13.9))
                        .upc(BEER_3_UPC)
                        .build()
        );

        log.info("Bootstrap done.... loaded no of beers:" + beerRepository.count());
    }
}

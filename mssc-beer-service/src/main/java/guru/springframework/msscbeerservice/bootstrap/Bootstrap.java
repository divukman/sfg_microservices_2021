package guru.springframework.msscbeerservice.bootstrap;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.rapositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class Bootstrap implements CommandLineRunner {

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
                        .beerStyle("LAGER")
                        .minOnHand(10)
                        .quantityToBrew(299)
                        .price(BigDecimal.valueOf(11.5))
                        .upc(22334000001L)
                        .build()
        );

        beerRepository.save(
                Beer.builder()
                        .beerName("Mango Bobs")
                        .beerStyle("IPA")
                        .minOnHand(10)
                        .quantityToBrew(299)
                        .price(BigDecimal.valueOf(14.5))
                        .upc(22334000002L)
                        .build()
        );

        beerRepository.save(
                Beer.builder()
                        .beerName("Galaxy Cat")
                        .beerStyle("PALE_ALE")
                        .minOnHand(10)
                        .quantityToBrew(299)
                        .price(BigDecimal.valueOf(13.9))
                        .upc(22334000003L)
                        .build()
        );

        log.info("Bootstrap done.... loaded no of beers:" + beerRepository.count());
    }
}

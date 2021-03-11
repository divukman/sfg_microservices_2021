package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(final @PathVariable("beerId") UUID beerId) {
        final BeerDto beer = BeerDto.builder()
                .beerName("Karlovacko")
                .beerStyle(BeerStyleEnum.ALE)
                .build();

        return new ResponseEntity<>(beer, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody BeerDto beerDto) {
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(final @PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{beerId}")
    public ResponseEntity deleteBeerById(final @PathVariable("beerId") UUID beerId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

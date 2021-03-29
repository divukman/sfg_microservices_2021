package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.services.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(final @PathVariable("beerId") UUID beerId) {
        final BeerDto beer = beerService.findById(beerId);
        return new ResponseEntity<>(beer, HttpStatus.OK);
    }


    @PostMapping // POST - create new beer
    public ResponseEntity handlePost(@Valid @RequestBody BeerDto beerDto){
        final BeerDto savedBeerDTO = beerService.saveNewBeer(beerDto);
        HttpHeaders headers = new HttpHeaders();
        //todo add hostname to url
        headers.add("Location", "/api/v1/beer/" + savedBeerDTO.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(final @PathVariable("beerId") UUID beerId, @RequestBody @Valid BeerDto beerDto) {
        beerService.updateBeer(beerId, beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{beerId}")
    public ResponseEntity deleteBeerById(final @PathVariable("beerId") UUID beerId) {
        beerService.deleteBeer(beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

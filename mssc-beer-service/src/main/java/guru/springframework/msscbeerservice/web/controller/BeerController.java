package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.services.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPageList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping("/api/v1/")
public class BeerController {
    public static final Integer DAFAULT_PAGE_NUMBER = 0;
    public static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerService beerService;


    @GetMapping( value = "beer", produces = { "application/json" })
    public ResponseEntity<BeerPageList> listBeers(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "beerName", required = false) String beerName,
            @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
            @RequestParam(value = "showInventoryOnHand", required = false, defaultValue = "false") Boolean showInventoryOnHand) {

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = BeerController.DAFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 0) {
            pageSize = BeerController.DEFAULT_PAGE_SIZE;
        }

        final BeerPageList beerPageList = beerService.listBeers(beerName, beerStyle, showInventoryOnHand, PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(beerPageList, HttpStatus.OK);
    }

    @GetMapping("beer/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(
            final @PathVariable("beerId") UUID beerId,
            @RequestParam(value = "showInventoryOnHand", required = false, defaultValue = "false") Boolean showInventoryOnHand
    ) {
        final BeerDto beer = beerService.findById(beerId, showInventoryOnHand);
        return new ResponseEntity<>(beer, HttpStatus.OK);
    }

    @GetMapping("beerUpc/{beerUpc}")
    public ResponseEntity<BeerDto> getBeerByUpc(
            final @PathVariable("beerUpc") String beerUpc,
            @RequestParam(value = "showInventoryOnHand", required = false, defaultValue = "false") Boolean showInventoryOnHand
    ) {
        final BeerDto beer = beerService.findByUpc(beerUpc, showInventoryOnHand);
        return new ResponseEntity<>(beer, HttpStatus.OK);
    }



    @PostMapping(value = "beer") // POST - create new beer
    public ResponseEntity handlePost(@Valid @RequestBody BeerDto beerDto){
        final BeerDto savedBeerDTO = beerService.saveNewBeer(beerDto);
        HttpHeaders headers = new HttpHeaders();
        //todo add hostname to url
        headers.add("Location", "/api/v1/beer/" + savedBeerDTO.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("beer/{beerId}")
    public ResponseEntity updateBeerById(final @PathVariable("beerId") UUID beerId, @RequestBody @Valid BeerDto beerDto) {
        beerService.updateBeer(beerId, beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("beer/{beerId}")
    public ResponseEntity deleteBeerById(final @PathVariable("beerId") UUID beerId) {
        beerService.deleteBeer(beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

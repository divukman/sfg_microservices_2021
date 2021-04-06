package guru.springframework.msscbeerservice.services.inventory;

import guru.springframework.msscbeerservice.bootstrap.Bootstrap;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Disabled // just for manual testing
@SpringBootTest
class BeerInventoryServiceRestTemplateTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @Test
    public void testGetCall() {
        final Integer qoh = beerInventoryService.getOnHandInventory(UUID.fromString( "0a818933-087d-47f2-ad83-2f986ed087eb") );

        System.out.println("Q O H: " + qoh);
    }

}
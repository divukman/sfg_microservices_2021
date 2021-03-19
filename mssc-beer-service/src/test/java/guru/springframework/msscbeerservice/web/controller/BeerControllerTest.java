package guru.springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {

        mockMvc.perform(
                get("/api/v1/beer/" + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = BeerDto.builder()
                .beerName("New beer")
                .beerStyle(BeerStyleEnum.ALE)
                .upc(1234l)
                .price(BigDecimal.valueOf(11.22))
                .build();

        String beerJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(
                post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerJson)
        ).andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto beerDto = BeerDto.builder()
                .beerName("New beer")
                .beerStyle(BeerStyleEnum.ALE)
                .upc(1234l)
                .price(BigDecimal.valueOf(11.22))
                .build();

        String beerJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(
                put("/api/v1/beer/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerJson)
        ).andExpect(status().isNoContent());
    }

    @Test
    void deleteBeerById() throws Exception {
        mockMvc.perform(
                delete("/api/v1/beer/" + UUID.randomUUID())
        ).andExpect(status().isNoContent());
    }
}
package guru.springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("localh2")
@SpringBootTest
@AutoConfigureMockMvc
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private UUID savedBeerId;

    private Random random = new Random();

    @BeforeEach
    void saveBeer() throws Exception {
        final Long upc = Math.abs(random.nextLong());

        BeerDto beerDto = BeerDto.builder()
                .beerName("New beer")
                .beerStyle(BeerStyleEnum.ALE)
                .upc(String.valueOf(upc))
                .price(BigDecimal.valueOf(11.22))
                .build();

        String beerJson = objectMapper.writeValueAsString(beerDto);

        final ResultActions resultActions = mockMvc.perform(
                post("/api/v1/beer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerJson)
        ).andExpect(status().isCreated());

        final String response = resultActions.andReturn().getResponse().getHeader("Location");
        final String strId = response.replace("/api/v1/beer/", "");
        savedBeerId = UUID.fromString(strId);
    }

    @Test
    void getBeerById() throws Exception {

        mockMvc.perform(
                get("/api/v1/beer/" + savedBeerId).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        final Long upc = Math.abs(random.nextLong());
        BeerDto beerDto = BeerDto.builder()
                .beerName("New beer")
                .beerStyle(BeerStyleEnum.ALE)
                .upc(String.valueOf(upc))
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
        final Long upc = Math.abs(random.nextLong());
        BeerDto beerDto = BeerDto.builder()
                .beerName("New beer")
                .beerStyle(BeerStyleEnum.ALE)
                .upc(String.valueOf(upc))
                .price(BigDecimal.valueOf(11.22))
                .build();

        String beerJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(
                put("/api/v1/beer/" + savedBeerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerJson)
        ).andExpect(status().isNoContent());
    }

    @Test
    void deleteBeerById() throws Exception {
        mockMvc.perform(
                delete("/api/v1/beer/" + savedBeerId)
        ).andExpect(status().isNoContent());
    }
}
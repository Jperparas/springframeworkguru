package guru.springframework.spring6restmvc.controllers;

import guru.springframework.spring6restmvc.services.BeerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.UUID;


@WebMvcTest(BeerController.class)
class BeerControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    BeerService beerService;

    @Test
    void getBeerbyId() throws Exception {

        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }
}
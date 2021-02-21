package com.price.calculator.v1.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.price.calculator.v1.constants.AppConstants;
import com.price.calculator.v1.dto.ItemCartDTO;
import com.price.calculator.v1.service.CalculationEngineService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PriceControllerTest {

    @MockBean
    CalculationEngineService calculationEngineService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testGetPriceLisForPenguinEarItem() throws Exception {
        float[] prices = new float[50];
        IntStream.rangeClosed(1, 50).forEach(i -> {
            prices[i - 1] = (float) i;
        });

        Mockito.when(calculationEngineService.pricesList(AppConstants.PENGUINEAR))
                .thenReturn(prices);

        RequestBuilder requestBuilderPenguinEarPriceList = MockMvcRequestBuilders.get("/price-calculator/price-list/" + AppConstants.PENGUINEAR);

        MvcResult result = mockMvc.perform(requestBuilderPenguinEarPriceList).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        IntStream.rangeClosed(1, 50).forEach(val -> {
            assertTrue(content.contains(String.valueOf((float) val)));
        });
    }

    @Test
    void testGetPriceLisForHorseShoeItem() throws Exception {
        float[] prices = new float[50];
        IntStream.rangeClosed(1, 50).forEach(i -> {
            prices[i - 1] = (float) i;
        });

        Mockito.when(calculationEngineService.pricesList(AppConstants.HORSESHOE))
                .thenReturn(prices);

        RequestBuilder requestBuilderHorseShoePriceList = MockMvcRequestBuilders.get("/price-calculator/price-list/" + AppConstants.HORSESHOE);

        MvcResult result = mockMvc.perform(requestBuilderHorseShoePriceList).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        IntStream.rangeClosed(1, 50).forEach(val -> {
            assertTrue(content.contains(String.valueOf((float) val)));
        });
    }

    @Test
    void testBuildTotalPriceForItems() throws Exception {
        float mockTotalPrice = 12f;
        int[] itemCounts = {1, 2};
        ItemCartDTO itemDTO=new ItemCartDTO(1,2,1,2);
        Mockito.when(calculationEngineService.buildTotalPriceForCart(Mockito.any(ItemCartDTO.class)))
                .thenReturn(mockTotalPrice);

        RequestBuilder requestBuilderTotalPrice = MockMvcRequestBuilders.post("/price-calculator/price-total/").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemDTO)).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilderTotalPrice).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        Assertions.assertTrue(content.contains(String.valueOf(mockTotalPrice)));
    }


}

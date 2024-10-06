package com.currency.exchange.controller;

import com.currency.exchange.dto.request.ExchangeRateRequest;
import com.currency.exchange.dto.request.Items;
import com.currency.exchange.dto.response.ExchangeRateResponse;
import com.currency.exchange.enums.Category;
import com.currency.exchange.enums.CustomerType;
import com.currency.exchange.service.ExchangeRateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyExchangeController.class)
public class CurrencyExchangeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExchangeRateService exchangeRateService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "user", password = "password")
    public void testCalculate_Success() throws Exception {

        ExchangeRateRequest request = new ExchangeRateRequest();
        request.setBaseCurrency("EUR");
        request.setTargetCurrency("GBP");
        request.setCustomerType(CustomerType.MEMBER);
        request.setCustomerTenureInMonths(1);
        request.setItems(List.of(Items.builder().itemPrice(10.0).itemName("XYZ")
                .category(
                        Category.GROCERIES).quantity(1).build()));
        ExchangeRateResponse response = ExchangeRateResponse.builder().build();
        Mockito.when(exchangeRateService.calculateExchangeRate(request))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(request))
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk());
    }

    @Test
    public void testCalculate_WithInvalidAuth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/calculate")
                        .with(csrf())
                        .with(httpBasic("invalidUser", "invalidPass"))) // Invalid credentials
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testCalculateWithoutAuth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/calculate")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

}

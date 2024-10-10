package com.currency.exchange.service.impl;

import com.currency.exchange.discount.PercentageDiscount;
import com.currency.exchange.discount.DiscountFactory;
import com.currency.exchange.discount.ValueBaseDiscount;
import com.currency.exchange.dto.request.ExchangeRateRequest;
import com.currency.exchange.dto.request.Items;
import com.currency.exchange.dto.request.User;
import com.currency.exchange.dto.response.ExchangeRateResponse;
import com.currency.exchange.enums.Category;
import com.currency.exchange.enums.CustomerType;
import com.currency.exchange.integration.client.ExchangeRateClient;
import com.currency.exchange.service.ExchangeRateService;
import com.currency.exchange.util.UserDetailsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Implementation of ExchangeRateService Interface.
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    /**
     * Logger.
     */
    private final Logger log =
            LoggerFactory.getLogger(ExchangeRateServiceImpl.class);

    /**
     * ExchangeRateClient Object.
     */
    @Autowired
    private ExchangeRateClient exchangeRateClient;

    /**
     * UserDetailsUtil Instance.
     */
    @Autowired
    private UserDetailsUtil userDetailsUtil;

    /**
     * DiscountFactory Object.
     */
    @Autowired
    private DiscountFactory discountFactory;

    /**
     * Value based discount.
     */
    @Autowired
    private ValueBaseDiscount valueBaseDiscount;

    /**
     * To convert the total bill amount in desired currency.
     *
     * @param exchangeRateRequest - Items, base and target currency
     * @return ExchangeRateResponse
     */
    @Override
    public ExchangeRateResponse calculateExchangeRate(
            final ExchangeRateRequest exchangeRateRequest) {
        log.info("Inside calculateExchangeRate method.");
        double totalBillAmount = calculateTotalBill(exchangeRateRequest);
        log.debug("The totalBill amount calculated.");
        ExchangeRateResponse resp =
                getExchangeRateResponse(exchangeRateRequest);
        resp.setTotalBill(resp.getConversionRate() * totalBillAmount);
        return resp;
    }

    /**
     * To get exchange rates from third party API.
     *
     * @param exchangeRateRequest - Request
     * @return ExchangeRateResponse
     */
    @Cacheable(cacheNames = "exchangeRate",
            key = "#baseCurrency.concat('-').concat(#target)")
    private ExchangeRateResponse getExchangeRateResponse(
            final ExchangeRateRequest exchangeRateRequest) {
        return exchangeRateClient.calculateExchangeRate(
                exchangeRateRequest.getBaseCurrency(),
                exchangeRateRequest.getTargetCurrency());
    }

    /**
     * to calculate the total bill amount.
     *
     * @param exchangeRateReq - Request Object.
     * @return double - total bill amount in base currency.
     */
    private double calculateTotalBill(
            final ExchangeRateRequest exchangeRateReq) {
        log.info("Inside calculateTotalBill method: "
                + "Calculating the total Bill amount.");
        double groceriesTotal = 0.0;
        double restTtl = 0.0;
        double finalAmount = 0.0;
        for (Items item : exchangeRateReq.getItems()) {
            if (item.getCategoryId() == (Category.GROCERIES.getId())) {
                groceriesTotal += (item.getItemPrice()
                        *
                        (item.getQuantity() == null ? 1 : item.getQuantity()));
            } else {
                restTtl += (item.getItemPrice()
                        *
                        (item.getQuantity() == null ? 1 : item.getQuantity()));
            }
        }
        if (restTtl != 0) {
            User user = userDetailsUtil.getUserDetails(
                    exchangeRateReq.getUser().getUserId());
            CustomerType customerType = user.getCustomerType();
            boolean isCustomerForMoreThanTwoYears =
                    userDetailsUtil.isCustomerForMoreThanTwoYears(user);

            // Get the appropriate discount based on customer type, tenure, and total amount
            PercentageDiscount discount =
                    discountFactory.getDiscount(customerType,
                            isCustomerForMoreThanTwoYears,
                            restTtl);

            // Apply the discount if there is one else No discount applies
            finalAmount = discount != null ? discount.applyDiscount(restTtl) :
                    restTtl;
        }
        //Applying value based discount after percentage discount.
        return valueBaseDiscount.applyDiscount(finalAmount + groceriesTotal);
    }

    /**
     * To empty the cache.
     */
    @CacheEvict(value = "exchangeRate", allEntries = true)
    @Scheduled(fixedRateString = "${spring.cache.time-to-live}")
    public void emptyCache() {
        log.info("emptying the cache");
    }
}

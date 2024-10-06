package com.currency.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * starter class.
 */
@SpringBootApplication
@EnableFeignClients
@EnableCaching
@EnableScheduling
public class ExchangeApplication {

    /**
     * starter method.
     *
     * @param args - args
     */
    public static void main(final String[] args) {
        SpringApplication.run(ExchangeApplication.class, args);
    }

}

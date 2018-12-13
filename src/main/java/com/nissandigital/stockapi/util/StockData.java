package com.nissandigital.stockapi.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nissandigital.stockapi.rest.Stock;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;

public class StockData {
    public static Object StringToJsonStockData(String body) throws IOException {

        Object global_quote = new ObjectMapper().readValue(body, Object.class);
        System.out.println("greate "+global_quote);
        return null;
    }
}

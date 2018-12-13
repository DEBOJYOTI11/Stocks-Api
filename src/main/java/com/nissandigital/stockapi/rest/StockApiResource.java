package com.nissandigital.stockapi.rest;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.nissandigital.stockapi.util.StockData;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nissandigital.stockapi.util.ApiResources;

import io.swagger.annotations.ApiOperation;

@RestController
public class StockApiResource {

	@Autowired
	private RestTemplate restTemplate;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/")
	public String index() {
		logger.warn("hello world");
		return "hello stock api";
	}

	/**
	 * The api gets global quote of a particular symbol with more details
	 *
	 * @param symbol
	 * @return
	 * @author ndh00159
	 */
	@GetMapping("/global-quote/{symbol}")
	public ResponseEntity<?> globalQuote(@PathVariable String symbol) {

		if (symbol == null)
			return new ResponseEntity<String>("Missing stock symbol", HttpStatus.BAD_REQUEST);

		String globalQuoteApi = ApiResources.globalQuote;
		globalQuoteApi = ApiResources.replace(globalQuoteApi, symbol);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<Stock> response;
		try {
			response = restTemplate.exchange(
					globalQuoteApi,
					HttpMethod.GET,
					entity,
					Stock.class);

			logger.error("Succes  " + globalQuoteApi);

			return new ResponseEntity<Stock>(response.getBody(), HttpStatus.ACCEPTED);

		} catch (Exception e) {

			logger.error("Error occurred while accessing " + globalQuoteApi);

			return new ResponseEntity<String>("Could not get Stock information", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * The api gets global quote of the most commons automotive companioes. the list is hardcoded as of now
	 *
	 * @param
	 * @return
	 * @author ndh00159
	 */
	@GetMapping("/global-quotes/all")
	public ResponseEntity<?> globalQuotesAll() {


		List<String> common_stocks = ApiResources.common_stocks;


		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<Stock> response;

		List<Object> listGlobalQuotes = new ArrayList<>();

		for (String stock_symbol : common_stocks) {

			String globalQuoteApi = ApiResources.replace(ApiResources.globalQuote, stock_symbol);
			logger.info("Called api is " + globalQuoteApi);
			try {
				response = restTemplate.exchange(
						globalQuoteApi,
						HttpMethod.GET,
						entity,
						Stock.class);

				Stock s = response.getBody();

				listGlobalQuotes.add(s.getQuote());
			} catch (Exception e) {
				logger.error("Error occurred for the api " + globalQuoteApi);
//				listGlobalQuotes.add("Wrong symbol "+stock_symbol);
			}

		}
		return new ResponseEntity<List<Object>>(listGlobalQuotes, HttpStatus.ACCEPTED);
	}

	/**
	 * The api gives the time series data for a particular symbol
	 *
	 * @param symbol
	 * @return
	 * @authoor ndh00159
	 */
	@GetMapping("/time-series/{symbol}")
	public ResponseEntity<?> timeSeries(@PathVariable("symbol") String symbol) {

		if (symbol == null)
			return new ResponseEntity<String>("Missing stock symbol", HttpStatus.BAD_REQUEST);


		String timeSeriesApi = ApiResources.timeSeries;
		timeSeriesApi = ApiResources.replace(timeSeriesApi, symbol);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		try {
			HttpEntity<String> response = restTemplate.exchange(
					timeSeriesApi,
					HttpMethod.GET,
					entity,
					String.class);

			return new ResponseEntity<String>(response.getBody(), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.error("Error occurred for time series api " + timeSeriesApi);
			return new ResponseEntity<String>("Could not get Stock information", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	 * The api searches for the stock symbol with a company name
	 *
	 * @param company_name
	 * @return
	 */
	@GetMapping("/search-symbol/{company_name}")
	public ResponseEntity<?> searchSymbol(@PathVariable String company_name) {

		if (company_name == null)
			return new ResponseEntity<String>("Missing stock symbol", HttpStatus.BAD_REQUEST);

		String searchSymbolApi = ApiResources.searchSymbol;
		searchSymbolApi = ApiResources.replace(searchSymbolApi, company_name);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		System.out.println(searchSymbolApi);

		try {

			HttpEntity<String> response = restTemplate.exchange(
					searchSymbolApi,
					HttpMethod.GET,
					entity,
					String.class);

			return new ResponseEntity<String>(response.getBody(), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.error("Error in search symbol api "+searchSymbolApi);
			return new ResponseEntity<String>("Could not get Stock information", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

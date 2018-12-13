package com.nissandigital.stockapi.util;

import java.util.Arrays;
import java.util.List;

public class ApiResources {

	//api for alphavantage provider
//	public static String timeSeries = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=%s&interval=5min&apikey=";
//	public static String globalQuote = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=%s&apikey=";
//	public static String searchSymbol = "https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=%s&apikey=";
//	public static String apiKey = "QYX6S2CD2P9VXFH0";

	public static String timeSeries = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=%s&interval=5min&apikey=QYX6S2CD2P9VXFH0";
	public static String globalQuote = "https://api.iextrading.com/1.0/stock/%s/batch?types=quote,news,chart&range=1m&last=10";
	public static String globalQuoteTypeQuoteOnly = "https://api.iextrading.com/1.0/stock/%s/batch?types=quote&range=1m&last=10";
	public static String searchSymbol = "https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=%s&apikey=QYX6S2CD2P9VXFH0";

	public static List<String> common_stocks = Arrays.asList("F","HMC","HYMTF","TM","TTM");
	
	public static String replace(String api, String symbol) {
		
		api   = String.format(api, symbol);
		return api;
	}
}

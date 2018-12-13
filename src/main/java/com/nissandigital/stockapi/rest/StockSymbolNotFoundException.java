package com.nissandigital.stockapi.rest;

public class StockSymbolNotFoundException extends RuntimeException {

	public StockSymbolNotFoundException(String exception) {
		super(exception);
	}

}

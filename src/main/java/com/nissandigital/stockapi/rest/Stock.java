package com.nissandigital.stockapi.rest;

import java.io.Serializable;

public class Stock implements Serializable {
	public Object getQuote() {
		return quote;
	}

	public Stock() {
	}

	public void setQuote(Object quote) {
		this.quote = quote;
	}

	public Object getNews() {
		return news;
	}

	public void setNews(Object news) {
		this.news = news;
	}

	public Object getChart() {
		return chart;
	}

	public void setChart(Object chart) {
		this.chart = chart;
	}

	private Object quote;
	private Object news;
	private Object chart;


}
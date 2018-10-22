package be.vdab.groenetenen.restclients;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rates {

	@JsonProperty("USD")
	private BigDecimal usd;
	
	public void setUsd(final BigDecimal usd) {
		this.usd = usd;
	}
	
	public BigDecimal getUSD() {
		return usd;
	}
}

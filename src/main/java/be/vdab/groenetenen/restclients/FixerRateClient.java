package be.vdab.groenetenen.restclients;

import java.math.BigDecimal;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import be.vdab.groenetenen.exceptions.UnableToReadRateException;

@Component
public class FixerRateClient implements RateClient {

	private static final Logger LOGGER
	= LoggerFactory.getLogger(FixerRateClient.class);
	
	private final URI fixerURL;
	private final RestTemplate restTemplate;
	
	public FixerRateClient(
			@Value("${fixerRateURL}") final URI fixerURL,
			final RestTemplateBuilder restTemplateBuilder) {
		this.fixerURL = fixerURL;
		this.restTemplate = restTemplateBuilder.build();
	}
	
	@Override
	public BigDecimal getDollarRate() {
		try {
			final USDRate rate = restTemplate.getForObject(
					fixerURL,  USDRate.class);
			
			return rate.getRates().getUSD();
		}
		catch (final RestClientException rce) {
			LOGGER.error("Can't read USD rate", rce);
			
			throw new UnableToReadRateException();
		}
	}

}

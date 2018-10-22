package be.vdab.groenetenen.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import be.vdab.groenetenen.restclients.RateClient;

@Service
public class EuroServiceImpl implements EuroService {

	private final RateClient rateClient;
	
	public EuroServiceImpl(final RateClient rateClient) {
		this.rateClient = rateClient;
	}
	
	@Override
	public BigDecimal toDollar(BigDecimal euro) {
		return euro.multiply(rateClient.getDollarRate())
				.setScale(2, RoundingMode.HALF_UP);
	}

}

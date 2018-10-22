package be.vdab.groenetenen.restclients;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FixerRateClientTest {

	@Autowired
	private FixerRateClient client;
	
	@Test
	public void rateMustBePositive() {
		assertTrue(client.getDollarRate().compareTo(BigDecimal.ZERO) > 0);
	}
}

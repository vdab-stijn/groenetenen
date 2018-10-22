package be.vdab.groenetenen.web;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.groenetenen.exceptions.UnableToReadRateException;
import be.vdab.groenetenen.services.EuroService;

@Controller
@RequestMapping("euro")
public class EuroController {

	private final EuroService euroService;
	
	public EuroController(final EuroService euroService) {
		this.euroService = euroService;
	}
	
	private static final String VIEW_DOLLAR
	= "euro/todollar";
	@GetMapping("{euro}/todollar")
	public ModelAndView toDollar(@PathVariable final BigDecimal euro) {
		final ModelAndView modelAndView = new ModelAndView(VIEW_DOLLAR);
		
		try {
			modelAndView.addObject(
					new EuroDollar(euro, euroService.toDollar(euro)));
		}
		catch (final UnableToReadRateException utrre) {
			modelAndView.addObject("error", "Can't read dollar rate");
		}
		
		return modelAndView;
	}
}

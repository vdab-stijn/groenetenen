package be.vdab.groenetenen.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

	private String greeting() {
		final int hour = LocalDateTime.now().getHour();
		
		if (hour >= 6 && hour < 12)
			return "goodMorning";
		
		if (hour >= 12 && hour < 18)
			return "goodAfternoon";
		
		return "goodNight";
	}
	
	private static final String VIEW_INDEX	= "index";
	@GetMapping
	public ModelAndView index() {
		return new ModelAndView(VIEW_INDEX)
				.addObject("greeting", greeting());
	}
}

package be.vdab.groenetenen.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.groenetenen.entities.Tender;
import be.vdab.groenetenen.services.TenderService;

@Controller
@RequestMapping("tenders")
@SessionAttributes("tender")
public class TenderController {

	private final TenderService tenderService;
	
	public TenderController(final TenderService tenderService) {
		this.tenderService = tenderService;
	}
	
	private static final String VIEW_STEP_1
	= "tenders/step1";
	@GetMapping("add")
	public ModelAndView step1() {
		return new ModelAndView(VIEW_STEP_1)
				.addObject(new Tender());
	}
	
	private static final String VIEW_STEP_2
	= "tenders/step2";
	@PostMapping(value = "add", params="step2")
	public String step1ToStep2(
			@Validated(Tender.Step1.class) final Tender tender,
			final BindingResult bindingResult) {
		return bindingResult.hasErrors() ? VIEW_STEP_1 : VIEW_STEP_2;
	}
	
	private static final String REDIRECT_AFTER_ADD
	= "redirect:/";
	@PostMapping(value = "add", params="save")
	public String create(
			@Validated(Tender.Step2.class) final Tender tender,
			final BindingResult bindingResult,
			final SessionStatus sessionStatus,
			final HttpServletRequest request) {
		if (bindingResult.hasErrors()) return VIEW_STEP_2;
		
		final String tenderURL
		= request.getRequestURL().toString().replace("add", "");
		
		tenderService.create(tender, tenderURL);
		
		sessionStatus.setComplete();
		
		return REDIRECT_AFTER_ADD;
	}
	
	private static final String VIEW_TENDER
	= "tenders/tender";
	@GetMapping("{tender}")
	public ModelAndView read(@PathVariable final Tender tender) {
		return new ModelAndView(VIEW_TENDER)
				.addObject(tender);
	}
}

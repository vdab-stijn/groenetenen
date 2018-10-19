package be.vdab.groenetenen.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.groenetenen.services.BranchService;
import be.vdab.groenetenen.web.forms.FromToPostalCodeForm;

@Controller
@RequestMapping("branches")
public class BranchController {

	private final BranchService branchService;
	
	public BranchController(final BranchService branchService) {
		this.branchService = branchService;
	}
	
	public static final String VIEW_FROM_TO_POSTAL_CODE
	= "branches/fromtopostalcode";
	@GetMapping("fromtopostalcode")
	public ModelAndView showFromToPostalCode() {
		return new ModelAndView(VIEW_FROM_TO_POSTAL_CODE)
				.addObject(new FromToPostalCodeForm());
	}
	
	@GetMapping(params = { "from", "to" })
	public ModelAndView fromToPostalCode(
			@Valid FromToPostalCodeForm form,
			final BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ModelAndView(VIEW_FROM_TO_POSTAL_CODE);
		
		return new ModelAndView(VIEW_FROM_TO_POSTAL_CODE)
				.addObject("branches",
						branchService.findByPostalCode(
								form.getFrom(), form.getTo()));
	}
}

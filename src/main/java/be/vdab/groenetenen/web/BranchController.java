package be.vdab.groenetenen.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.groenetenen.entities.Branch;
import be.vdab.groenetenen.services.BranchService;
import be.vdab.groenetenen.web.forms.FromToPostalCodeForm;

@Controller
@RequestMapping(path = "branches", produces = MediaType.TEXT_HTML_VALUE)
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
	
	public static final String VIEW_BRANCH
	= "branches/branch";
	public static final String REDIRECT_BRANCH_NOT_FOUND
	= "redirect:/";
	@GetMapping("{filiaal}")
	public ModelAndView read(
			@PathVariable Optional<Branch> branch,
			RedirectAttributes redirectAttributes) {
		if (branch.isPresent())
			return new ModelAndView(VIEW_BRANCH)
					.addObject(branch.get());
		
		redirectAttributes.addAttribute("error", "Branch not found");
		
		return new ModelAndView(REDIRECT_BRANCH_NOT_FOUND);
	}
}

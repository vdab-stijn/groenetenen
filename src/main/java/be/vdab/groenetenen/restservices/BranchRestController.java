package be.vdab.groenetenen.restservices;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.vdab.groenetenen.entities.Branch;
import be.vdab.groenetenen.exceptions.BranchHasEmployeesException;
import be.vdab.groenetenen.exceptions.BranchNotFoundException;
import be.vdab.groenetenen.services.BranchService;

@RestController
@RequestMapping("/branches")
@ExposesResourceFor(Branch.class)
public class BranchRestController {

	private final BranchService branchService;
	private final EntityLinks entityLinks;
	
	public BranchRestController(
			final BranchService branchService,
			final EntityLinks entityLinks) {
		this.branchService = branchService;
		this.entityLinks = entityLinks;
	}
	
	@GetMapping
	public BranchesResource findAll() {
		return new BranchesResource(branchService.findAll(), entityLinks);
	}
	
	@PostMapping
	public HttpHeaders create(@RequestBody @Valid final Branch branch) {
		branchService.create(branch);
		
		final HttpHeaders headers = new HttpHeaders();
		final Link link = entityLinks.linkToSingleResource(
				Branch.class, branch.getId());
		
		headers.setLocation(URI.create(link.getHref()));
		
		return headers;
	}
	
	@GetMapping("{branch}")
	public BranchResource read(@PathVariable final Optional<Branch> branch) {
		if (branch.isPresent())
			return new BranchResource(branch.get(), entityLinks);
		
		throw new BranchNotFoundException();
	}
	
	@PutMapping("{id}")
	public void update(@RequestBody @Valid final Branch branch) {
		branchService.update(branch);
	}
	
	@DeleteMapping("{branch}")
	public void delete(@PathVariable final Optional<Branch> branch) {
		if (!branch.isPresent())
			throw new BranchNotFoundException();
		
		branchService.delete(branch.get().getId());
	}

	@ExceptionHandler(BranchNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void branchNotFound() {
		
	}
	
	@ExceptionHandler(BranchHasEmployeesException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public String branchHasEmployees() {
		return "Can't delete: Branch has employees";
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String branchWithWrongProperties(
			final MethodArgumentNotValidException manve) {
		final StringBuilder errors = new StringBuilder();
		
		manve.getBindingResult().getFieldErrors().forEach(error ->
			errors	.append(error.getField()).append(":")
					.append(error.getDefaultMessage()).append('\n'));
		
		errors.deleteCharAt(errors.length() - 1);
		
		return errors.toString();
	}
}

package be.vdab.groenetenen.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PostalCodeValidator
implements ConstraintValidator<PostalCode, Integer> {
	
	private static final int MIN_CODE = 1000;
	private static final int MAX_CODE = 9999;

	@Override
	public void initialize(final PostalCode postalCode) {}
	
	@Override
	public boolean isValid(
			final Integer postalCode,
			final ConstraintValidatorContext context) {
		return postalCode == null || 
				(postalCode >= MIN_CODE && postalCode <= MAX_CODE);
	}

}

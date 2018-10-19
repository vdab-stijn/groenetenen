package be.vdab.groenetenen.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.vdab.groenetenen.web.forms.FromToPostalCodeForm;

public class FromToPostalCodeValidator
implements ConstraintValidator<FromToPostalCode, FromToPostalCodeForm> {

	@Override
	public void initialize(final FromToPostalCode arg0) {}
	
	@Override
	public boolean isValid(
			final FromToPostalCodeForm form,
			final ConstraintValidatorContext context) {
		if (form.getFrom() == null || form.getTo() == null) return true;
		
		return form.getFrom() <= form.getTo();
	}

}

package be.vdab.groenetenen.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
@Constraint(validatedBy = FromToPostalCodeValidator.class)
public @interface FromToPostalCode {
	String message() 
		default "{be.vdab.groenetenen.constraints.FromToPostalCode.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}

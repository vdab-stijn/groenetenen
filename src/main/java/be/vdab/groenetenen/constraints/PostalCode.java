package be.vdab.groenetenen.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Constraint(validatedBy = PostalCodeValidator.class)
public @interface PostalCode {

	String message() default "{be.vdab.groenetenen.constraints.PostalCode.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}

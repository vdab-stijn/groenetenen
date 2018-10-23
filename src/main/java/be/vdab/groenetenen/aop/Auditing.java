package be.vdab.groenetenen.aop;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Auditing {

	private static final Logger LOGGER
	= LoggerFactory.getLogger(Auditing.class);
	
	/*@Before("be.vdab.groenetenen.aop.PointcutExpressions.services()")*/
	@AfterReturning(pointcut
		= "be.vdab.groenetenen.aop.PointcutExpressions.services()",
		returning = "returnValue")
	public void writeAudit(
			final JoinPoint joinPoint, final Object returnValue) {
		final StringBuilder builder
		= new StringBuilder("TIME\t").append(LocalDateTime.now());
		
		final Authentication authentication
		= SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null)
			builder.append("\nUser\t").append(authentication.getName());
		
		builder.append("\nMethod\t")
			.append(joinPoint.getSignature().toLongString());
		
		Arrays.stream(joinPoint.getArgs())
			.forEach(object -> builder.append("\nParameter\t").append(object));
		
		if (returnValue != null) {
			builder.append("\nReturn\t\t");
			
			if (returnValue instanceof Collection)
				builder.append(
						((Collection<?>)returnValue).size()).append(" objects");
			else
				builder.append(returnValue.toString());
		}
		
		LOGGER.info(builder.toString());
	}
}

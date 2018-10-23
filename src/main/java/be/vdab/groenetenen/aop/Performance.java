package be.vdab.groenetenen.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Performance {

	private static final Logger LOGGER
	= LoggerFactory.getLogger(Performance.class);
	
	@Around("be.vdab.groenetenen.aop.PointcutExpressions.services()")
	public Object writePerformance(final ProceedingJoinPoint joinPoint)
	throws Throwable {
		final long before = System.nanoTime();
		
		try {
			return joinPoint.proceed();
		}
		finally {
			final long duration = System.nanoTime() - before;
			
			LOGGER.info("{} took {} nanoseconds",
					joinPoint.getSignature().toLongString(), duration);
		}
	}
}

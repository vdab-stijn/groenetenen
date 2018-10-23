package be.vdab.groenetenen.messaging;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class MessagingConfig {

	@Bean
	public Jaxb2Marshaller marshaller() {
		final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		
		marshaller.setClassesToBeBound(TenderAndTenderURL.class);
		
		return marshaller;
	}
	
	@Bean
	public MarshallingMessageConverter converter(
			final Jaxb2Marshaller marshaller) {
		return new MarshallingMessageConverter(marshaller, marshaller);
	}
	
	@Bean
	public DefaultJmsListenerContainerFactory factory(
			final ConnectionFactory connectionFactory,
			final MarshallingMessageConverter converter) {
		final DefaultJmsListenerContainerFactory factory
		= new DefaultJmsListenerContainerFactory();
		
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(converter);
		
		return factory;
	}
}

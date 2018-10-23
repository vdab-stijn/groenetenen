package be.vdab.groenetenen.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String MANAGER = "manager";
	private static final String HELPDESK = "helpdeskmedewerker";
	private static final String STOCKKEEPER = "magazijnier";
	
	private static final String USERS_BY_USERNAME
	= "SELECT naam AS username, paswoord AS password, actief AS enabled " +
			"FROM gebruikers WHERE naam = ?";
	private static final String AUTHORITIES_BY_USERNAME
	= "SELECT gebruikers.naam AS username, rollen.naam AS authorities " +
			"FROM gebruikers INNER JOIN gebruikersrollen " +
			"ON gebruikers.id = gebruikersrollen.gebruikerid " +
			"INNER JOIN rollen " +
			"ON rollen.id = gebruikersrollen.rolid " +
			"WHERE gebruikers.naam = ?";
	
//	@Bean
//	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//		return new InMemoryUserDetailsManager(
//				User.builder().username("joe").password("{noop}theboss")
//					.authorities(MANAGER).build(),
//				User.builder().username("averell").password("{noop}hungry")
//					.authorities(HELPDESK, STOCKKEEPER).build());
//	}
	
	@Bean
	public JdbcDaoImpl jdbcDaoImpl(final DataSource dataSource) {
		final JdbcDaoImpl impl = new JdbcDaoImpl();
		
		impl.setDataSource(dataSource);
		impl.setUsersByUsernameQuery(USERS_BY_USERNAME);
		impl.setAuthoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME);
		
		return impl;
	}
	
	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring()
			.mvcMatchers("/images/**")
			.mvcMatchers("/css/**")
			.mvcMatchers("/scripts/**");
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage("/login")
			.and().logout()
			.and().authorizeRequests()
			.mvcMatchers("/tenders/add").hasAuthority(MANAGER)
			.mvcMatchers("/employees")
				.hasAnyAuthority(STOCKKEEPER, HELPDESK)
			.mvcMatchers("/", "/branches", "/login").permitAll()
			.mvcMatchers("/**").authenticated();
		
		http.httpBasic();
	}
}

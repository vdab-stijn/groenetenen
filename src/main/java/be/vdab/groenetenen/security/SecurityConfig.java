package be.vdab.groenetenen.security;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String MANAGER = "manager";
	private static final String HELPDESK = "helpdeskmedewerker";
	private static final String STOCKKEEPER = "magazijnier";
	
	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		return new InMemoryUserDetailsManager(
				User.builder().username("joe").password("{noop}theboss")
					.authorities(MANAGER).build(),
				User.builder().username("averell").password("{noop}hungry")
					.authorities(HELPDESK, STOCKKEEPER).build());
	}
	
	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring()
			.mvcMatchers("/images/**")
			.mvcMathers("/css/**")
			.mvcMatchers("/scripts/**");
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.formLogin()
			.and.authorizeRequests()
			.mvcMatchers("/tenders/add").hasAuthority(MANAGER)
			.mvcMatchers("/employees")
				.hasAnyAuthority(STOCKKEEPER, HELPDESK);
	}
}

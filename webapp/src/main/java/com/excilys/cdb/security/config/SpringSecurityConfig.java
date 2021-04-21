package com.excilys.cdb.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser("user")
				.password(passwordEncoder().encode("network")).roles("USER").and().withUser("admin")
				.password(passwordEncoder().encode("network")).roles("ADMIN");

	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

//		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/ListComputer*").hasRole("USER")
//				.antMatchers("/AddComputer", "/EditComputer").hasRole("ADMIN").and().formLogin().loginPage("/login")
//				.defaultSuccessUrl("/ListComputer").failureUrl("/login?error=true").and().logout()
//				.deleteCookies("JSESSIONID").and().rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400).and()
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/**").hasAnyRole("ADMIN", "USER").and()
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/ListComputer")
				.hasAnyRole("USER", "ADMIN").antMatchers("/AddComputer", "/EditComputer").hasRole("ADMIN").and()
				.formLogin().loginPage("/login").defaultSuccessUrl("/ListComputer").failureUrl("/login?error=true")
				.permitAll().and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true)
				.permitAll().and().rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400);

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
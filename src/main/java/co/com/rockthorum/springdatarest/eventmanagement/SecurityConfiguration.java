package co.com.rockthorum.springdatarest.eventmanagement;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder(4)).withUser("jquiroga")
				.password("$2a$04$AmYCt4j0MwW6MZW9w2kF/ecc/ImW53fwzf3LT7wYZQz77VJLr3Tzi").roles("USER").and()
				.withUser("admin").password("$2a$04$ANwkx5hSQwwpcXEAYC50Su/JcwrJdHS5tlxGBqlUT/YuhCx.DBDn6")
				.roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.POST, "/api/events/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/events/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PATCH, "/api/events/**").hasRole("ADMIN").and().csrf().disable();
	}
}

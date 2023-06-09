package org.java.SpringFoto.auth.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfiguration {

	@Bean
	PasswordEncoder passwordEncoder() {
		
//	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    
		return 
			http.csrf(c -> c.disable())
				.authorizeHttpRequests(a -> a
			        .requestMatchers("/users/**").hasAnyAuthority("USER", "ADMIN")
			        .requestMatchers("/admin/**").hasAuthority("ADMIN")
			        .requestMatchers("/**").permitAll()
			).formLogin(f -> f.permitAll()
			).logout(l -> l.logoutSuccessUrl("/")
			).build();
	}
}

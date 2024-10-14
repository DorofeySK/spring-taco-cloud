package tacos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import tacos.repository.IUserRepository;
import tacos.users.User;

@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService(IUserRepository userRepo) {
		return username -> {
			User user = userRepo.findByUsername(username);
			if (user != null) {
				return user;
			}
			throw new UsernameNotFoundException("User\'" + username + "\' not found");
		};
	}
}

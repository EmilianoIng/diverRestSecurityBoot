package com.diver.main.security;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.diver.main.service.SecurityInterface;



@SuppressWarnings("deprecation")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	SecurityInterface service;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		http.authorizeRequests().antMatchers("/login","/diver/logout","/user","/registrationConfirm","/register","/diver/user_login","/diver/user","/diver/register","/diver/loginCustom","diver","/submitted","/loginCustom","/v2/*","http://localhost:8080/swagger-ui/index.html","/swagger-ui/**"/*,"/diver/insertAnagrafica"*/).permitAll()
		.and()
		.formLogin()
		
		 .usernameParameter("username")
         .passwordParameter("password")
		.loginProcessingUrl("/submitted") // ad indicare che le richieste provenienti da tale path saranno prese in gestione direttamente da security ignorando i miei controller
	     // .defaultSuccessUrl("/test",true)
		.successHandler(successHandler())
       // .failureHandler(failureHandler())
	    .permitAll()
	  
		.and()
		
        .httpBasic();
		http.logout(logout -> logout.permitAll()
                .logoutSuccessHandler((request, response, authentication) -> {
                	System.out.println("logged out "+authentication.getName());
                    response.setStatus(HttpServletResponse.SC_OK);
                }));
		 http.cors().and().csrf().disable();
		
	}
	
	
	
	private AuthenticationSuccessHandler successHandler() {
	    return new AuthenticationSuccessHandler() {
	        @Override
	        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
	                HttpServletResponse httpServletResponse, Authentication authentication)
	                throws IOException, ServletException {
	        	httpServletResponse.sendRedirect("/test");
	           // httpServletResponse.getWriter().append("OK");
	            httpServletResponse.setStatus(200);
	        }

		
	    };
	}

	/*private AuthenticationFailureHandler failureHandler() {
	    return new AuthenticationFailureHandler() {
	    	@Override
	        public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
	                HttpServletResponse httpServletResponse, AuthenticationException e)
	                throws IOException, ServletException {
	            httpServletResponse.getWriter().append("Authentication failure");
	            httpServletResponse.setStatus(401);
	        }

				    };
	}*/
	
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	UrlBasedCorsConfigurationSource urlCorsConfigSrc = new
	UrlBasedCorsConfigurationSource();
	 CorsConfiguration configuration = new CorsConfiguration();
	 configuration.setAllowedOrigins(Arrays.asList("*"));
     configuration.setAllowedMethods(Arrays.asList("*"));
     configuration.setAllowedHeaders(Arrays.asList("*"));
	configuration.addExposedHeader("Location");
	 urlCorsConfigSrc.registerCorsConfiguration("/**",
	configuration.applyPermitDefaultValues());//new CorsConfiguration().applyPermitDefaultValues());
	return urlCorsConfigSrc;
	}
	
	
	//decommentare passo in tale metodo  l'interfaccia che estende l'interfaccia UserDetailsService (in realtà la prima è superflua potevo passare direttamente l'interfaccia UserDetailService)
/*	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		
		.userDetailsService(service);
		
		
	}*/
	 @Bean(name="authenticationManager")
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	
	@Bean
	public PasswordEncoder encoder() {
	return new BCryptPasswordEncoder();
	}


}

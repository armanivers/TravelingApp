package com.deap.TravellingApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.deap.TravellingApp.service.MyUserDetailsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	//Allow access to static ressources
    String[] resources = new String[]{
            "/include/**","/css/**","/img/**","/js/**"
    };
    
    String[] staticPages = new String[]{
    		"/forgot-password","/reset","/aboutUs","/contactUs","/news","/jobs","/support","/faq","/user/findDestination","/user/showDestinations","/user/showAvailableHotels/**"
    };
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
	        .antMatchers(resources).permitAll()  
	        .antMatchers(staticPages).permitAll()
	        .antMatchers("/","/login","/register").permitAll()
	        .antMatchers("/provider/**").access("hasRole('PROVIDER')")
	        .antMatchers("/admin/**").access("hasRole('ADMIN')")
	        .antMatchers("/user/**").access("hasRole('USER') or hasRole('ADMIN') or hasRole('PROVIDER')")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/error");
        	
        http.csrf().disable();
    }
    
    //Password hashing/salting
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    	//Parameter (4) = hashing strength, range 4-31
    	//if no number, hasing chooses random one (not good because it isn't consistent)
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }
	
    @Autowired
    MyUserDetailsService userDetailsService;
	
    //Register user and password hashing service
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
 
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
    }
}
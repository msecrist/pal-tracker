package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${SECURITY_FORCE_HTTPS:false}")
    private boolean forceHttps;

   @Override
   public void configure(HttpSecurity http) throws Exception {
       if (forceHttps) {
           http.requiresChannel().anyRequest().requiresSecure();
       }
       http.authorizeRequests().mvcMatchers("/**").hasRole("USER")
               .and().httpBasic().and()
               .csrf().disable();
   }

   @Autowired
    public void configureAuthMgr(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
               .withUser("user").password("password").roles("USER");
   }
}

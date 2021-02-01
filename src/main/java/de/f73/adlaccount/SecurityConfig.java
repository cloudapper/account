package de.f73.adlaccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Value("${SPRING_BASIC_AUTH_USER}")
    private String basicAuthUser;
    @Value("${SPRING_BASIC_AUTH_PW}")
    private String basicAuthPassword;

    @Bean(name = "pwdEncoder")
    public PasswordEncoder getPasswordEncoder() {
        DelegatingPasswordEncoder delPasswordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories
                .createDelegatingPasswordEncoder();
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        delPasswordEncoder.setDefaultPasswordEncoderForMatches(bcryptPasswordEncoder);
        return delPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                // .csrf().disable()
                // .authorizeRequests()
                // .antMatchers(HttpMethod.GET, "/").permitAll()
                // .antMatchers(HttpMethod.POST, "/signup").permitAll()
                // .anyRequest().authenticated()
                // .and()
                // .httpBasic();
                .csrf().disable().cors().and()
                .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/").permitAll().and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/signup").permitAll().anyRequest().authenticated()
                .and().httpBasic();
    }

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth)
    //         throws Exception
    // {
    //     auth.inMemoryAuthentication()
    //             .withUser(basicAuthUser)
    //             .password("{noop}" + basicAuthPassword)
    //             .roles("USER");
    // }
}


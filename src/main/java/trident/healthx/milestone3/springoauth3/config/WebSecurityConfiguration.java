package trident.healthx.milestone3.springoauth3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailService () {
        var userDetailService = new InMemoryUserDetailsManager();

        var u = User.withUsername("Sam").password("tomorrow").authorities("read").build();

        userDetailService.createUser(u);

        return userDetailService;
    }

    @Override
    protected void configure( HttpSecurity http) throws Exception {
      /*  http
                .authorizeRequests(authorize -> {
                    authorize
                            .antMatchers(HttpMethod.POST,"/api/v1/user/**").permitAll()
                            .antMatchers("/h2-console/**").permitAll() //do not use in production!
                            .antMatchers("/", "/webjars/**", "/login", "/resources/**").permitAll()
                            .antMatchers(HttpMethod.GET,"/api/v1/users").permitAll()
                            .antMatchers("/api/v1/users/**").permitAll()
                            .antMatchers("/client").permitAll()
                            .antMatchers("/client/**").permitAll();

                } )
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();*/



        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/user/**")
                .permitAll()
                .antMatchers("/",
                        "/h2-console/**",
                        "/webjars/**",
                        "/login",
                        "/resources/**",
                        "/api/v1/users/**",
                        "/api/v1/client",
                        "/api/v1/client/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .cors()
                .and()
                .csrf()
                .disable();


        //h2 console config
        http.headers().frameOptions().sameOrigin();
    }
    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }




    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManager();
    }



}

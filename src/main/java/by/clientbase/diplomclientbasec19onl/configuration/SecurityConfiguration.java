package by.clientbase.diplomclientbasec19onl.configuration;

import by.clientbase.diplomclientbasec19onl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Denis Smirnov on 14.06.2023
 */


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserService userService;

//    public SecurityConfiguration(UserService userService) {
//        this.userService = userService;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
               .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/user/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/authorization").permitAll()
                .and()
                .logout().permitAll().logoutSuccessUrl("/");


//                .defaultSuccessUrl("/")
//                .usernameParameter("username").passwordParameter("password")
//                .failureUrl("/user/login?failed=true")
//                .and()
//                .logout()
//                .logoutUrl("/user/logout")
//                .logoutSuccessUrl("/user/login")
//                .invalidateHttpSession(true);

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

}

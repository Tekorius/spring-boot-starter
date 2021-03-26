package is.ryt.app.web_core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(50)
public class SwaggerSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/swagger-ui/**", "/swagger-ui.html")
                .antMatchers("/v3/api-docs/**")
            .and()
                .authorizeRequests()
                .anyRequest().permitAll();
    }
}

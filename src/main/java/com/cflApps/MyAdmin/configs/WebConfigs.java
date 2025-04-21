package com.cflApps.MyAdmin.configs;

import com.cflApps.MyAdmin.filters.CorsFilter;
import com.cflApps.MyAdmin.filters.JwtValidationFilter;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigs {

    @Value("${myAdmin.allowed.origin}")
    private String ALLOWED_ORIGINS;

    @Autowired
    private JwtValidationFilter jwtValidationFilter;

	@Bean
    public FilterRegistrationBean<Filter> customCorsFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/myAdmin/api/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
	
	@Bean
    public FilterRegistrationBean<Filter> jwtFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtValidationFilter);
        registrationBean.addUrlPatterns("/myAdmin/api/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica a todas as rotas
                        .allowedOrigins(ALLOWED_ORIGINS) // Libera o frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}

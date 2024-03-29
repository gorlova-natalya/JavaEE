package com.teachmeskills.config;

import com.teachmeskills.interceptor.AuthInterceptor;
import com.teachmeskills.session.AuthContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final AuthContext authContext;
    private static final List<String> ALLOWED_PATHS = List.of("/login", "/reg", "/loginUser", "/registration",
            "/accessDenied", "/api/v1/users", "/api/v1/registration", "/api/v1/auth");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MappedInterceptor(new String[]{"/*"},
                new AuthInterceptor(authContext))).excludePathPatterns(ALLOWED_PATHS);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/reg").setViewName("reg");
        registry.addViewController("/users").setViewName("menu");
        registry.addViewController("/accessDenied").setViewName("accessDenied");
    }

    @Bean
    public IDialect conditionalCommentDialect() {
        return new Java8TimeDialect();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }
}

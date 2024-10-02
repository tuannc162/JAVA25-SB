package com.example.movie_app.config;

import com.example.movie_app.config.interceptor.AuthenticationInterCeptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final AuthenticationInterCeptor authenticationInterCeptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterCeptor)
                .addPathPatterns(
                        "/api/reviews", "/api/reviews/**", "/api/favorites", "/api/favorites/**",
                        "/api/users", "/thong-tin-ca-nhan", "/phim-yeu-thich"
                );
    }
}

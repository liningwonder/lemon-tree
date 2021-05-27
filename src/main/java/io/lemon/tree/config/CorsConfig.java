package io.lemon.tree.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig());
        return new CorsFilter(source);
    }

    private CorsConfiguration corsConfig() {
        //String[] domains = upConfig.getDomain().split(",");
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 请求常用的三种配置，*代表允许所有，也可以自定义属性（比如 header 只能带什么，只能是 post 方式等）
        //corsConfiguration.setAllowedOrigins(Arrays.asList(domains));
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }

}

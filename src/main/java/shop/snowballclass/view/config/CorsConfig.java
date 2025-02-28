package shop.snowballclass.view.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    private static final List<String> ALLOWED_ORIGIN = List.of("http://localhost:3000, http://snowball-class.shop", "https://snowball-class.shop");
    private static final List<String> ALLOWED_ORIGIN_PATTERN = List.of("http://*.snowball-class.shop", "https://*.snowball-class.shop");

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(ALLOWED_ORIGIN.toArray(new String[0]))
                .allowedOriginPatterns(ALLOWED_ORIGIN_PATTERN.toArray(new String[0]))
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("X-Requested-With", "Origin", "Accept", "Content-Type", "Authorization", "X-XSRF-token")
                .allowCredentials(true)
                .maxAge(3600); // preflight 요청 결과 캐시 시간
    }
}

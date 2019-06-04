package cn.gaoyuexiang.spring.practice.cors.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class CORSConfiguration {

    @Bean
    fun corsFilter(): CorsFilter {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:8080")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/hello", configuration)
        return CorsFilter(source)
    }

}
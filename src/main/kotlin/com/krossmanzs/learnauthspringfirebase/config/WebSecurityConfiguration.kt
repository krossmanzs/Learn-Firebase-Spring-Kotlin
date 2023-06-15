package com.krossmanzs.learnauthspringfirebase.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class WebSecurityConfiguration {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests {
                it.anyRequest().authenticated()
            }
        http.oauth2ResourceServer { configurer ->
            configurer.jwt { it.jwkSetUri("https://www.googleapis.com/service_accounts/v1/jwk/securetoken%40system.gserviceaccount.com") }
        }
        return http.build()
    }
}
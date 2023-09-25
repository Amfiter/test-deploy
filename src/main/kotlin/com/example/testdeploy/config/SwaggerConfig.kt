package com.example.testdeploy.config

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Конфигурационный класс для Swagger
 */
@Configuration
class SwaggerConfig {
    @Bean
    fun publicApiDefinition(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
            .group("public")
            .pathsToMatch("/public/api/**")
            .build()
    }
}

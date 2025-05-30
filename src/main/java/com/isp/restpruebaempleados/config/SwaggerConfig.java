package com.isp.restpruebaempleados.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuraci&oacute;n de Swagger para la generaci&oacute;n autom&aacute;tica de la documentaci&oacute;n de la API REST.
 *
 * Esta clase configura y personaliza la documentaci&oacute;n de la API REST utilizando OpenAPI.
 * Define los detalles como el t&iacute;tulo, la descripci&oacute;n y la versi&oacute;n de la API que se reflejar&aacute;n en la documentaci&oacute;n generada.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configura y devuelve una instancia personalizada de OpenAPI para la documentación de la API REST.
     *
     * @return una instancia de {@code OpenAPI} con título, descripción y versión personalizados para la documentación de la API
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("REST API Prueba Empleados")
                .description("API REST para prueba de empleados")
                .version("v1"));
    }
}

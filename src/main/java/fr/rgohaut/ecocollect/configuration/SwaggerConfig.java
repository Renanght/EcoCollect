package fr.rgohaut.ecocollect.configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;





@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI poubelleConnecteeOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Poubelle Connectée API")
                        .description("API pour la gestion des poubelles publiques et des collectes")
                        .version("1.0.0"));
    }
}
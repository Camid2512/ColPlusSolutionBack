package co.edu.unbosque.ColPlusSolution.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API Documentation", version = "1.0", description = "API Documentation for ColPlusSolution"))
public class SwaggerConfig {
}

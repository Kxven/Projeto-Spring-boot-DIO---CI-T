package dio.projeto_dio_spring.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig{
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Title - Minha Rest API")
                        .description("API um exemplo de uso de Spring Boot REST API")
                        .version("1.0")
                        .termsOfService("Termos de uso: Open Source")
                        .license(new License()
                                .name("Licença - Sua Empresa")
                                .url("http://www.seusite.com.br"))
                        .contact(new Contact()
                                .name("Seu nome")
                                .url("http://www.seusite.com.br")
                                .email("voce@seusite.com.br")));
    }
}

package dev.saxo.trading.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

    private final String title = "Test Task: SAXO Bank";
    private final String description =
        """
    We need to place an order using the Saxobank REST API demo via Postman. It is crucial that the Day Risk and Max Risk parameters are functioning correctly.

        1. Create Account:
        •	Endpoint: /api/accounts
        •	Method: POST
        •	Parameters:
        •	Name of Account:\s
        •	SAXO API Key
        •	SAXO Secret Key
        •	Daily Risk USD : 0 for disable, 2000 or any input example per day if it reached system don’t allow to place orders for that day and close open trades all.\s
        •	Max Risk USD: 0 for disable, 10000 or any input example max loss, if it reached system don’t allow to place orders until we edit this input .\s

        2. Place orders:
        •	Place Order:
        •	Endpoint: /api/orders
        •	Method: POST
        •	Parameters: as below JSON format  that we put in tradingview or postman


        {
           "secret":"HhAvbBhEs",
           "symbol":"NVDA.XNAS",
           "BuySell": "buy",
           "OrderType":"Market",
           "PositionSize": 10,\s
           "Strategy": "NVDA-SSL-10m",
           "slPercentpertrade": "0.75"
        }

        """;
    private final String version = "1.0.0";
    private final String url = "https://www.developer.saxo/openapi/learn";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title(title)
                                .description(description)
                                .version(version)
                                .contact(
                                        new Contact()
                                                .name("Alexandru Popa")
                                                .email("alexandruclaudiu.popa@gmail.com")
                                                .url("www.example.com")
                                )
                                .license(
                                        new License()
                                                .name("internal")
                                                .url(url)
                                )

                )
                .externalDocs(
                        new ExternalDocumentation()
                                .description("Find out more about SAXO")
                                .url("https://www.developer.saxo")
                )
                .servers(List.of(
                                new Server()
                                        .url("http://localhost:8080")
                        )
                );
    }
}
package ru.favdemo.restaurantvoting.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@OpenAPIDefinition(
        info = @Info(
                title = "REST API documentation",
                version = "1.0",
                description = """
                        <h3>Voting system for deciding where to have lunch.</h3><br>
                        <p>
                        * 2 types of users: admin and regular users <br>
                        * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price) <br>
                        * Menu changes each day (admins do the updates) <br>
                        * Users can vote for a restaurant they want to have lunch at today <br>
                        * Only one vote counted per user <br>
                        * If user votes again the same day: <br>
                            - If it is before 11:00 we assume that he changed his mind. <br>
                            - If it is after 11:00 then it is too late, vote can't be changed <br> <br>
                        Each restaurant provides a new menu each day.
                        </p><br>
                        <p>
                        <b>Test credentials:</b> <br>
                        - Admin: admin@gmail.com / admin <br>
                        - User: user@gmail.com / password <br>
                        - User-vote1: user1@gmail.com / password1 <br>
                        - User-vote2: user2@gmail.com / password2
                        </p>
                        """,
                contact = @Contact(url = "https://github.com/avf-hub/restaurant-voting", name = "Falkovskiy Andrey", email = "avf-91@yandex.ru")
        ),
        security = @SecurityRequirement(name = "basicAuth")
)
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("REST API")
                .pathsToMatch("/api/**")
                .build();
    }
}

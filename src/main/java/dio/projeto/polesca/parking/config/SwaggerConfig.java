package dio.projeto.polesca.parking.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//http://localhost:8080/swagger-ui.html
@Component
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket docApi() {

        Set<String> returnType = new HashSet<>(Arrays.asList("application/json"));

        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.select()
                .apis(RequestHandlerSelectors.basePackage("dio.projeto.polesca.parking.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(infoApi().build())
                .consumes(returnType)
                .produces(returnType);

        return docket;
    }

    private Contact contact() {
        return new Contact(
                "Wilton Polesca",
                "github/wiltonpolesca",
                "wiltonpolesca@gmail.com");
    }

    private ApiInfoBuilder infoApi() {
        ApiInfoBuilder info = new ApiInfoBuilder();
        info.title("Parking");
        info.description("Final project Quebec Java Digital: Parking");
        info.version("1.0");
        info.license("MIT");
        info.licenseUrl("https://opensource.org/licenses/MIT");
        info.contact(contact());
        return info;
    }
}

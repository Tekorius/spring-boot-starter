package is.ryt.app.web_core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        AppWebCore.class, Jsr310JpaConverters.class
})
@ComponentScan(basePackages = {"is.ryt.app"})
@ConfigurationPropertiesScan("is.ryt.app")
public class AppWebCore {
    // TODO: FIX AUTHORITIES

    public static void main(String[] args) {
        SpringApplication.run(AppWebCore.class, args);
    }
}

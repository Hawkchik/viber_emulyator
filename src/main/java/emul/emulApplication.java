package emul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication

public class emulApplication {

    public static void main(String... args) {
        SpringApplication.run(emulApplication.class, args);
    }

}

package emul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class Responses {

    private static final Logger log = LoggerFactory.getLogger(Responses.class);

    @Bean
    CommandLineRunner initResponses(ViberResponseRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new ViberResponse("123",23)));
            log.info("Preloading " + repository.save(new ViberResponse("1234",25)));
        };
    }
}
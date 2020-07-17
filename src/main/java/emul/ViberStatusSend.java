package emul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.lang.Thread.sleep;

@Service
public class ViberStatusSend {
    private static Logger logger = LoggerFactory.getLogger(ViberStatusSend.class);

    private final RestTemplate template = new RestTemplate();

    public void send(ViberResponse viberResponse) {
        ViberStatus viberStatus = new ViberStatus();

        URI uri = UriComponentsBuilder.fromUriString("http://10.241.0.194:9003/viber_status_emul")
                .build().toUri();

        viberStatus.setMessage_token(viberResponse.getMessage_token());
        viberStatus.setMessage_status(1);

        try {
            sleep(30000);
            ResponseEntity<String> response = template.postForEntity(uri, viberStatus, String.class);

            String body = response.getBody();

        } catch (Throwable e) {
            logger.error("[{}]. error processing mobicont request for {} ms", e);

        }
    }

}

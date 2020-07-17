package emul;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;

@Service
public class ViberSend {
    private static Logger logger = LoggerFactory.getLogger(ViberSend.class);

    private final RestTemplate template = new RestTemplate();

    public void send(ViberResponse viberResponse) {
        ViberStatus viberStatus = new ViberStatus();

        URI uri = UriComponentsBuilder.fromUriString("http://zhqdev0.hints.svzn.net/http-resp-new/resp/200")
                .build().toUri();

        viberStatus.setMessage_token(viberResponse.getMessage_token());
        viberStatus.setStatus(1);

        try {

            ResponseEntity<String> response = template.postForEntity(uri,viberStatus,String.class);

            String body = response.getBody();

        } catch (Throwable e) {
            logger.error("[{}]. error processing mobicont request for {} ms", e);

        }
    }

}

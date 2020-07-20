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

    public void send(ViberResponse viberResponse, String phone) {
        ViberStatus viberStatus = new ViberStatus();

        URI uri = UriComponentsBuilder.fromUriString("http://10.241.0.194:9003/viber_status_emul")
                .build().toUri();
        viberStatus.setMessage_token(viberResponse.getMessage_token());

        //Доставлено + Прочитано 01
        if (phone.matches("\\d{9}01")) {

            try {
                viberStatus.setMessage_status(0);
                ResponseEntity<String> response = template.postForEntity(uri, viberStatus, String.class);

                String body = response.getBody();
                logger.info("Deliver sent");

            } catch (Throwable e) {
                logger.error("[{}]. error processing mobicont request for {} ms", e);
            }
            viberStatus.setMessage_status(1);
            logger.info("Seen send");
        }
// Прочитано 02
        else if (phone.matches("\\d{9}02")) {
            viberStatus.setMessage_status(1);
            logger.info("Seen send");

        }

// Просрочено 03

        else if (phone.matches("\\d{9}03")) {
            viberStatus.setMessage_status(2);
            logger.info("Expired send");

        }

        //Просрочено + Доставлено 04

        else if (phone.matches("\\d{9}04")) {
            try {
                viberStatus.setMessage_status(2);
                ResponseEntity<String> response = template.postForEntity(uri, viberStatus, String.class);

                String body = response.getBody();
                logger.info("Expired sent");

            } catch (Throwable e) {
                logger.error("[{}]. error processing mobicont request for {} ms", e);
            }
            viberStatus.setMessage_status(0);
            logger.info("Delivered send");

        }
        //Просрочено + Прочитано 05

        else if (phone.matches("\\d{9}05")) {
            try {
                viberStatus.setMessage_status(2);
                ResponseEntity<String> response = template.postForEntity(uri, viberStatus, String.class);

                String body = response.getBody();
                logger.info("Expired sent");

            } catch (Throwable e) {
                logger.error("[{}]. error processing mobicont request for {} ms", e);
            }
            viberStatus.setMessage_status(1);
            logger.info("Seen send");

        }

        //Прочитано + просрочено 06
        else if (phone.matches("\\d{9}06")) {
            try {
                viberStatus.setMessage_status(2);
                ResponseEntity<String> response = template.postForEntity(uri, viberStatus, String.class);

                String body = response.getBody();
                logger.info("Expired sent");

            } catch (Throwable e) {
                logger.error("[{}]. error processing mobicont request for {} ms", e);
            }
            viberStatus.setMessage_status(0);
            logger.info("Delivered send");

        }


        //Доставлено + Просрочено 07

        else if (phone.matches("\\d{9}07")) {
            try {
                viberStatus.setMessage_status(0);
                ResponseEntity<String> response = template.postForEntity(uri, viberStatus, String.class);

                String body = response.getBody();
                logger.info("Delivered sent");

            } catch (Throwable e) {
                logger.error("[{}]. error processing mobicont request for {} ms", e);
            }
            viberStatus.setMessage_status(2);
            logger.info("Expired send");

        }
// Subscribe
        else if (phone.matches("\\d{9}08")) {

            viberStatus.setMessage_status(4);
            logger.info("Expired send");

        }
// Unsubscribe
        else if (phone.matches("\\d{9}09")) {

            viberStatus.setMessage_status(5);
            logger.info("Expired send");

        } else viberStatus.setMessage_status(0);


        try {
            sleep(30000);
            ResponseEntity<String> response = template.postForEntity(uri, viberStatus, String.class);

            String body = response.getBody();

        } catch (Throwable e) {
            logger.error("[{}]. error processing mobicont request for {} ms", e);

        }
    }

}

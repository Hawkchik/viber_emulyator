package emul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;

import static java.lang.Thread.sleep;

@Service
public class ViberStatusSend {
    private static Logger logger = LoggerFactory.getLogger(ViberStatusSend.class);

    @Value("${viber.config.url}")
    public String url;

    private final RestTemplate template = new RestTemplate();

    public void send(ViberResponse viberResponse, String phone, Integer serviceId) {
        ViberStatus viberStatus = new ViberStatus();
// Тестовый стенд адрес для пуша статуса
        URI uri = UriComponentsBuilder.fromUriString(url)
                .build().toUri();
        logger.info("Request begin sending");
        viberStatus.setMessage_token(viberResponse.getMessage_token());
        viberStatus.setService_id(serviceId);
        viberStatus.setPhone_number(phone);
        Date date1 = new Date();
        viberStatus.setMessage_time(date1.getTime());

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
            logger.info("Delivered send by default");
        } catch (Throwable e) {
            logger.error("[{}]. error processing mobicont request for {} ms", e);

        }
    }

}

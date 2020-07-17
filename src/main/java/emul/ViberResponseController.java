package emul;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ViberResponseController extends Thread {
    private final ViberResponseRepository repository;
    private static Logger logger = LoggerFactory.getLogger(ViberResponseController.class);

    ViberResponseController(ViberResponseRepository repository) {
        this.repository = repository;
    }


// Aggregate root


    @GetMapping("/viber")
    List<ViberResponse> all() {
        return repository.findAll();  // Вернуть все ответы из бд условной
    }


    @PostMapping("/viber")
    ViberResponse newViberResponse(@RequestBody ViberRequest newViberRequest) {
        ViberResponse viberResponse = new ViberResponse();
        ViberStatusSend viberStatusSend = new ViberStatusSend();
        viberResponse.setSeq(newViberRequest.getSeq());
        viberResponse.setMatching_template_id((int) (Math.random() * (1 + 80000) + 1)); //Случайный шаблон
        viberResponse.setMessage_token(String.valueOf((int) (Math.random() * (1 + 80000) + 1)));//Случайный токен


        try {
            new Thread(() -> viberStatusSend.send(viberResponse)).start();
            logger.info("Begin sending");
            logger.info("Response sent");
            return viberResponse;

        } catch (Exception e) {

            logger.info("Error");
            return viberResponse;
        }


    }


}

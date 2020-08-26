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
        String phone = newViberRequest.getDest();
        Integer serviceId = newViberRequest.getService_id();


        viberResponse.setSeq(newViberRequest.getSeq());
        viberResponse.setMatching_template_id((int) (Math.random() * (1 + 80000) + 1)); //Случайный шаблон   1 - 80000
        viberResponse.setMessage_token(String.valueOf((int) (Math.random() * (1 + 80000) + 1)));//Случайный токен


        try {
            logger.info("Begin working");
            // SRVC_INTERNAL_FAILURE = 1
            if (phone.matches("\\d{9}30")) {
                viberResponse.setStatus(1);
                return viberResponse;
            }
            // SRVC_BAD_SERVICE_ID = 2
            else if (phone.matches("\\d{9}32")) {
                viberResponse.setStatus(2);
                return viberResponse;
            }
            // SRVC_BLOCKED_MESSAGE_TYPE = 4
            else if (phone.matches("\\d{9}33")) {
                viberResponse.setStatus(4);
                return viberResponse;
            }
            // SRVC_BAD_MESSAGE_TYPE = 5
            else if (phone.matches("\\d{9}34")) {
                viberResponse.setStatus(5);
                return viberResponse;
            }
            // SRVC_BAD_PARAMETERS = 6
            else if (phone.matches("\\d{9}35")) {
                viberResponse.setStatus(6);
                return viberResponse;
            }
            // SRVC_TIMEOUT = 7
            else if (phone.matches("\\d{9}36")) {
                viberResponse.setStatus(7);
                return viberResponse;
            }
            // SRVC_USER_BLOCKED = 8
            else if (phone.matches("\\d{9}37")) {
                viberResponse.setStatus(8);
                return viberResponse;
            }
            // SRVC_NOT_VIBER_USER = 9
            else if (phone.matches("\\d{9}38")) {
                viberResponse.setStatus(9);
                return viberResponse;
            }
            // SRVC_NO_SUITABLE_DEVICE = 10
            else if (phone.matches("\\d{9}39")) {
                viberResponse.setStatus(10);
                return viberResponse;
            }
            // SRVC_UNAUTHORIZED_IP = 11
            else if (phone.matches("\\d{9}40")) {
                viberResponse.setStatus(11);
                return viberResponse;
            }
            // SRVC_NOT_PERMITTED = 13
            else if (phone.matches("\\d{9}41")) {
                viberResponse.setStatus(13);
                return viberResponse;
            }
            // SRVC_BILLING_FAILURE = 14
            else if (phone.matches("\\d{9}42")) {
                viberResponse.setStatus(14);
                return viberResponse;
            }
            // SRVC_BAD_LABEL = 18
            else if (phone.matches("\\d{9}43")) {
                viberResponse.setStatus(18);
                return viberResponse;
            }
            // SRVC_INVALID_TTL = 20
            else if (phone.matches("\\d{9}44")) {
                viberResponse.setStatus(20);
                return viberResponse;
            }
            // SRVC_WAIT_FOR_USER_RESPONSE = 21
            else if (phone.matches("\\d{9}45")) {
                viberResponse.setStatus(21);
                return viberResponse;
            } else {
                if (newViberRequest.getType() == 301) {
                    new Thread(() -> viberStatusSend.send(viberResponse, phone, serviceId)).start();
                    logger.info(viberResponse.toString());
                    return viberResponse;
                }
                else if (newViberRequest.getType()==306||newViberRequest.getType()==307){
                    viberResponse.setMatching_template_id(0);
                    viberResponse.setSession_id(newViberRequest.getService_id());
                    new Thread(() -> viberStatusSend.send(viberResponse, phone, serviceId)).start();
                    logger.info(viberResponse.toString());
                    return viberResponse;
                }

                else {
                    viberResponse.setMatching_template_id(0);
                    new Thread(() -> viberStatusSend.send(viberResponse, phone, serviceId)).start();
                    logger.info(viberResponse.toString());
                    return viberResponse;
                }
            }
        } catch (Exception e) {

            logger.info("Error");
            return viberResponse;
        }
    }


}







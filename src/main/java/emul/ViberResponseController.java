package emul;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ViberResponseController {
    private final ViberResponseRepository repository;

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
        ViberSend viberSend = new ViberSend();
        viberResponse.setSeq(newViberRequest.getSeq());
        viberResponse.setMatching_template_id((int) (Math.random() * (1 + 80000) + 1)); //Случайный шаблон
        viberResponse.setMessage_token(String.valueOf((int) (Math.random() * (1 + 80000) + 1)));//Случайный токен

        viberSend.send(viberResponse);
        return viberResponse;
    }


}

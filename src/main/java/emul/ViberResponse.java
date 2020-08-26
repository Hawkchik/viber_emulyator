package emul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.util.UriComponentsBuilder;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Entity
class ViberResponse {

    private static Logger logger = LoggerFactory.getLogger(ViberResponse.class);
    private @Id
    @GeneratedValue
    Integer seq;
    private String message_token;
    private Integer matching_template_id;
    private Integer status = 0;
    private Integer session_id=0;

    public Integer getSession_id() {
        return session_id;
    }

    public void setSession_id(Integer session_id) {
        this.session_id = session_id;
    }

    @Value("${mobicont.http-client.url}")
    private String url;


    ViberResponse() {
    }

    ViberResponse(String message_token, Integer matching_template_id) {
        this.message_token = message_token;
        this.matching_template_id = matching_template_id;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getMessage_token() {
        return message_token;
    }

    public void setMessage_token(String message_token) {
        this.message_token = message_token;
    }

    public Integer getMatching_template_id() {
        return matching_template_id;
    }

    public void setMatching_template_id(Integer matching_template_id) {
        this.matching_template_id = matching_template_id;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



  /*  public void send () {
        WebClient client = WebClient.builder()
                // Указываем базовый URL
                .baseUrl("http://localhost:8081/viber_status_emul")
                // Заголовок Content-Type будет добавляться во все запросы
                .defaultHeader("Content-Type", "application/json")
                .build();
        Mono<ViberResponse> personMono = client.post()
                .body(Mono.just(new ViberResponse("12", 4)), ViberResponse.class)
                // Отправляем запрос
                .retrieve()
                .bodyToMono(ViberResponse.class);

    }
*/


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof ViberResponse))
            return false;
        ViberResponse ViberResponse = (ViberResponse) o;
        return Objects.equals(this.message_token, ViberResponse.message_token) && Objects.equals(this.seq, ViberResponse.seq)
                && Objects.equals(this.matching_template_id, ViberResponse.matching_template_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.seq, this.message_token, this.matching_template_id);
    }

    @Override
    public String toString() {
        return "ViberResponse{" + "seq=" + this.seq + ", message_token='" + this.message_token + '\'' + ", Matching_template_id='" + this.matching_template_id + '\'' + ", service_id='" +'\'' +this.session_id +'}';
    }
}
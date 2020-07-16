package emul;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
class ViberResponse {

    private @Id
    @GeneratedValue
    Integer seq;
    private String message_token;
    private Integer matching_template_id;
    private Integer status = 0;

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
        return "ViberResponse{" + "seq=" + this.seq + ", message_token='" + this.message_token + '\'' + ", Matching_template_id='" + this.matching_template_id + '\'' + '}';
    }
}
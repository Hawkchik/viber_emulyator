package emul;

import java.util.Date;

public class ViberStatus {
    private Integer message_status = 0;
    private String message_token;
    private Integer service_id;
    private String phone_number;
    private Long message_time;

    public Integer getMessage_status() {
        return message_status;
    }

    public void setMessage_status(Integer status) {
        this.message_status = status;
    }

    public String getMessage_token() {
        return message_token;
    }

    public void setMessage_token(String message_token) {
        this.message_token = message_token;
    }

    public Integer getService_id() {
        return service_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Long getMessage_time() {
        return message_time;
    }

    public void setMessage_time(long message_time) {
        this.message_time = message_time;
    }
}

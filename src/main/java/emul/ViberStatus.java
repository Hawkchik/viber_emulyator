package emul;

public class ViberStatus {
    private Integer message_status = 0;
    private String message_token;

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
}

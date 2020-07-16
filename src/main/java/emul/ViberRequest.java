package emul;

public class ViberRequest {
    private Integer service_id;
    private String dest;
    private String tag;
    private Integer seq;
    private Integer type;
    private String label;
    private Integer ttl;
    private String expiry_txt;
    public String getExpiry_txt() {
        return expiry_txt;
    }

    public void setExpiry_txt(String expiry_txt) {
        this.expiry_txt = expiry_txt;
    }

    Message message = new Message();

    public Integer getService_id() {
        return service_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}

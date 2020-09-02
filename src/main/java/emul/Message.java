
package emul;

/**
 * @author Ivan Li
 */
public class Message {

    @Override
    public String toString() {
        return "Message {" + "action=" + this.getAction() + ", caption='" + this.getCaption() + '\'' + ", image='" + this.getImg() + '\'' + ", message='" + this.getTxt() + '\'' + '}';
    }

    private String action;
    private String caption;
    private String txt;
    private String img;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

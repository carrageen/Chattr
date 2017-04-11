package cancerApi;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = -2870173773950597861L;
    boolean isPrivateMessage;
    boolean isSystemMessage;
    public String text;

    public Message(String msg) {
        this.text = msg;
    }

    public Message() {
        this.text = "";
    }

    public String toString() {
        return text;
    }
}
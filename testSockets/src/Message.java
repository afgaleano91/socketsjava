import java.io.Serializable;

// debe implementar Serializable para ser enviado
public class Message implements Serializable{
    private final String text;

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
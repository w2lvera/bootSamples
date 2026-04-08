package w2l.inspired.domain;
public class Message {

    String name;
    String text;

    public Message(){

    }

    public Message(String name, String text) {
        this.name = name;
        this.text = text;
    }

    //@XmlElement
    public String getName() {
        return name;
    }

    //@XmlElement
    public String getText() {
        return text;
    }

}


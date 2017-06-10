package in.zollet.abhilashdas.hellochat.model;

public class Message {

    private String text;
    private String name;
    private String uId;
    private String photoUrl;

    public Message() {
    }

    public Message(String text, String name, String photoUrl,  String uid) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.uId = uid;
    }

    public String getText() {
        return text;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}

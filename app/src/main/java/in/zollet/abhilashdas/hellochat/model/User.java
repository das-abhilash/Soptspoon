package in.zollet.abhilashdas.hellochat.model;

import android.net.Uri;

import org.parceler.Parcel;

/**
 * Created by abhilashdas on 16/05/17.
 */

@Parcel
public class User {

    public User(){}
    private String displayName;
    private String photoUrl;
    private String email;
    private String uId;

    public User(String uid, String displayName, String email, Uri photoUrl) {
        this.uId = uid;
        this.displayName = displayName;
        this.email = email;
        this.photoUrl = String.valueOf(photoUrl);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }
}

package unswbook;

import java.util.Date;

public class User {
    private String username;
    private String password;
    private String email;
    private String gender;
    private String dob;
    private String fullname;
    private String photoUrl;
    private String isBanned;
    private String isActive;

    public User() {

    }

    public User(String username, String password, String email, String gender, String dob,
                String fullname, String photoUrl, String isBanned, String isActive){
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.fullname = fullname;
        this.photoUrl = photoUrl;
        this.isBanned = isBanned;
        this.isActive = isActive;

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }


    public String isBanned() {
        return isBanned;
    }

    public void setBanned(String string) {
        isBanned = string;
    }

    public String isActive() {
        return isActive;
    }

    public void setActive(String active) {
        isActive = active;
    }

}

package com.harjot.govermentadmin;

/**
 * Created by Harjot on 11/29/2017.
 */

public class User {
    String id;
    String name;
    String email;
    String phoneno;

    public User(String id, String name, String email, String phoneno) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneno = phoneno;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneno='" + phoneno + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}

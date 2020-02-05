package com.example.zippy.model;

public class User {
    private String _id;
    private String fname;
    private String lname;
    private String mobile;
    private String email;
    private String username;
    private String description;
    private String userimage;
    private String password;
    private Boolean enabled;

    public User(String _id, String fname, String lname, String mobile, String email, String username, String description, String userimage, String password, Boolean enabled) {
        this._id = _id;
        this.fname = fname;
        this.lname = lname;
        this.mobile = mobile;
        this.email = email;
        this.username = username;
        this.description = description;
        this.userimage = userimage;
        this.password = password;
        this.enabled = enabled;
    }

        public User(String fname, String lname, String mobile, String email, String username, String password) {
        this.fname = fname;
        this.lname = lname;
        this.mobile = mobile;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String mobile, String email, String username, String description, String userimage) {
        this.mobile = mobile;
        this.email = email;
        this.username = username;
        this.description = description;
        this.userimage = userimage;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}

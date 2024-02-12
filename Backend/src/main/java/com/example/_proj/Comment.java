package com.example._proj;


import lombok.Data;

@Data
public class Comment {
    public Comment(String content, String user, String placeName) {
        this.content = content;
        this.user = user;
        this.placeName = placeName;
    }

    private String id;
    private String content;
    private String user;
    private String placeName;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }


    public Comment() {}

}
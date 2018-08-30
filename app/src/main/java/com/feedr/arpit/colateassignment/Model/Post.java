package com.feedr.arpit.colateassignment.Model;

public class Post {

    private String title,time, name, description;
    private Integer profile_image,postImage;
    //required empty constructor
    public Post(){}

    public Post(String title, String time, String name, String description, Integer postImage, Integer profile_image) {
        this.title = title;
        this.time = time;
        this.name = name;
        this.description = description;
        this.postImage = postImage;
        this.profile_image = profile_image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPostImage() {
        return postImage;
    }

    public void setPostImage(Integer postImage) {
        this.postImage = postImage;
    }

    public Integer getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(Integer profile_image) {
        this.profile_image = profile_image;
    }
}

package com.antara.restapitesting.model;

public class User {

    private String id;
    private String createdAt;
    private String first;
    private String avatar;
    private String last;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", first='" + first + '\'' +
                ", avatar='" + avatar + '\'' +
                ", last='" + last + '\'' +
                '}';
    }

    /**
     * No args constructor for use in serialization
     */
    public User() {
    }

    /**
     * @param createdAt
     * @param last
     * @param id
     * @param avatar
     * @param first
     */
    public User(String id, String createdAt, String first, String avatar, String last) {
        super();
        this.id = id;
        this.createdAt = createdAt;
        this.first = first;
        this.avatar = avatar;
        this.last = last;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }


}
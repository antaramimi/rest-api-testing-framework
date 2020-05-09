package com.antara.restapitesting.model;

import java.util.Date;

public class Ticket {

    private String id;
    private String createdAt;
    private User createdBy;
    private String description;

    /**
     * No args constructor for use in serialization
     */
    public Ticket() {
    }

    /**
     * @param createdAt
     * @param createdBy
     * @param description
     * @param id
     */
    public Ticket(String id, String createdAt, User createdBy, String description) {
        super();
        this.id = id;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.description = description;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", createdBy=" + createdBy +
                ", description='" + description + '\'' +
                '}';
    }

    public void setDescription(String description) {
        this.description = description;
    }

}


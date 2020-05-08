package com.antara.restapitesting.model;

import java.util.Date;

public class Ticket {
    int id;
    Date date;

    Ticket(int id, Date date) {
        this.id = id;
        this.date=date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

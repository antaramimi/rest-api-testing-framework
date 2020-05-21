package com.antara.restapitesting.model;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.antara.restapitesting.model.Main.getUsers;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class Main {
    public static final String BASE_URL = "https://5eb538e4de5849001638b305.mockapi.io/api/v1";
    public static final String BASE_URL_TICKET = "https://5eb538e4de5849001638b305.mockapi.io/api/v1";

    public static List<User> getUsers() {
        return get(BASE_URL + "/user").as(new TypeRef<List<User>>() {
        });
    }


    public static List<Ticket> getTickets() {
        return get(BASE_URL_TICKET + "/ticket").as(new TypeRef<List<Ticket>>() {
        });
    }

    public static List<User> getUserWithTicket() {
        List<User> userWithTicket = new ArrayList<>();
        for (Ticket ticket : Main.getTickets()) {
            userWithTicket.add(ticket.getCreatedBy());
        }
        return userWithTicket;
    }


    public static List<User> getUsersWithNoTicket() {
        List<User> usersWithNoTickets = new ArrayList<>();
        Set<User> usersWithTickets = new HashSet<>();
        for (Ticket ticket : getTickets()) {
            usersWithTickets.add(ticket.getCreatedBy());
        }

        return usersWithNoTickets;

    }

    public static List<Ticket> checkTheTicketCreatedOnThatDate(Date d1, Date d2) throws ParseException {
        Set<Date> listTicketOnThatDate = new HashSet<>();
        List<Ticket> TicketCreatedOn = new ArrayList<>();
        SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd");

        d1 = sdfo.parse("2020-05-07");
        d2 = sdfo.parse("2020-05-08");

        Iterator<Ticket> iteratorTicket = Main.getTickets().iterator();

        while (iteratorTicket.hasNext()) {
            Ticket tickets = iteratorTicket.next();
            Date date = sdfo.parse(tickets.getCreatedAt());
            if (date.equals(d1) && date.equals(d2)) {
                return TicketCreatedOn;
            }

        }

        return TicketCreatedOn;

    }
}


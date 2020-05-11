package com.antara.restapitesting.model;

import io.restassured.common.mapper.TypeRef;

import java.util.List;

import static io.restassured.RestAssured.get;

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






}

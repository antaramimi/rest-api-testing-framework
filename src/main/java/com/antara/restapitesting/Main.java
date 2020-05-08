package com.antara.restapitesting;

import com.antara.restapitesting.model.User;
import io.restassured.common.mapper.TypeRef;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.get;

public class Main {
    private static final String BASE_URL = "https://5eb538e4de5849001638b305.mockapi.io/api/v1";

    @Test
    public void getUser() {

        List<User> users = get(BASE_URL + "/user").as(new TypeRef<List<User>>() {});
        System.out.println(users.size());
        for(int i=0;i<users.size();i++){
            System.out.println(users.get(i));
        }
    }


}






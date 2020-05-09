import com.antara.restapitesting.model.Ticket;
import com.antara.restapitesting.model.User;
import io.restassured.common.mapper.TypeRef;
import io.restassured.specification.Argument;
import org.hamcrest.Matcher;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.module.jsv.JsonSchemaValidator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Main {
    private static final String BASE_URL = "https://5eb538e4de5849001638b305.mockapi.io/api/v1";
    private static final String BASE_URL_TICKET = "https://5eb538e4de5849001638b305.mockapi.io/api/v1";
    static List<User> users;
    static List<Ticket> tickets;

    @BeforeClass
    public static void setUp() {
        users = get(BASE_URL + "/user").as(new TypeRef<List<User>>() {
        });
        tickets = get(BASE_URL_TICKET + "/ticket").as(new TypeRef<List<Ticket>>() {
        });
    }


    @Test
    public void getUser() {

        System.out.println(users.size());
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
    }

    @Test
    public void getTicket() {

        System.out.println(tickets.size());
        for (int i = 0; i < tickets.size(); i++) {
            System.out.println(tickets.get(i));
        }
    }

    @Test
    public void verifyTheStatus() {
        given().when().get(BASE_URL + "/user").then().statusCode(200);
    }

    @Test
    public void verifyName() {
        given().when().get(BASE_URL + "/user").then()
                .body(containsString("Malcolm"));
    }


    @Test
    public void verifyUserTicket() {
        Map<String, List<Ticket>> map = new HashMap<>();

        for (Ticket ticket : tickets) {
            List<Ticket> ticketsForUser = map.get(ticket.getCreatedBy().getFirst());
            if (ticketsForUser == null) {
                ticketsForUser = new ArrayList<>();
            }
            ticketsForUser.add(ticket);
            map.put(ticket.getCreatedBy().getFirst(), ticketsForUser);
        }

        assertEquals(2, map.get("Malcolm").size());
        assertEquals(3, map.get("Conrad").size());
        assertEquals(1, map.get("Leonard").size());
    }


    @Test
    public void verifyUserTicket1() {
        int counter = 0;
        Iterator<Ticket> ticketIterator = tickets.iterator();
        while (ticketIterator.hasNext()) {
            Ticket ticket = ticketIterator.next();
            if (ticket.getCreatedBy().getFirst().equals("Malcolm")) {
                counter++;

            }
        }

        assertEquals(counter, 2);

    }


    @Test
    public void verifyDate() {
        given().when().get(BASE_URL_TICKET + "/ticket").then()
                .statusCode(200).assertThat().body("createdAt", everyItem(hasItem(("2020-05-07T19:14:01.255Z"))));

    }

    @Test
    public void verifyHowManyUser() throws ParseException {
        int counter = 0;
        String date = "2020-05-07";
        Iterator<Ticket> ticketIterator = tickets.iterator();
        while (ticketIterator.hasNext()) {
            Ticket ticket = ticketIterator.next();
            if (ticket.getCreatedAt().contains(date)) {
                counter++;

            }
        }

        assertEquals(counter, 2);
    }


}

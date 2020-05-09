import com.antara.restapitesting.model.Ticket;
import com.antara.restapitesting.model.User;
import io.restassured.common.mapper.TypeRef;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
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
        given().when().get(BASE_URL+ "/user").then().statusCode(200);
    }

    @Test
    public void verifyName() {
        given().when().get(BASE_URL+ "/user").then()
                .body(containsString("Malcolm"));
    }



    @Test
    public void verifyUserTicket(){
        Map<String, List<Ticket>> map = new HashMap<>();

        for (Ticket ticket : tickets) {
            List<Ticket> ticketsForUser = map.get(ticket.getCreatedBy().getFirst());
            if (ticketsForUser == null) {
                ticketsForUser = new ArrayList<>();
            }
            ticketsForUser.add(ticket);
            System.out.println(ticket.getCreatedBy().getFirst());
            System.out.println(ticketsForUser);
            map.put(ticket.getCreatedBy().getFirst(), ticketsForUser);
        }

        assertEquals(2, map.get("malcolm").size());


    }


    @Test
    public void verifyUserTicket1(){
        int counter = 0;
        Iterator<Ticket> ticketIterator = tickets.iterator();
        while (ticketIterator.hasNext()) {
            Ticket ticket = ticketIterator.next();
            if (ticket.getCreatedBy().getFirst().equals("Malcolm")) {
                counter++;

            }
        }

        Assert.assertEquals(counter, 2);

    }

}

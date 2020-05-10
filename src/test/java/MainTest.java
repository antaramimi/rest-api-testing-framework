import com.antara.restapitesting.model.Main;
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

import static com.antara.restapitesting.model.Main.*;
import static com.antara.restapitesting.model.Main.getUsers;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class MainTest {


   @Test
    public  void getUser() {


        System.out.println(getUsers().size());
        for (int i = 0; i < getUsers().size(); i++) {
            System.out.println(getUsers().get(i));
        }
    }

    @Test
    public void getTicket() {

        System.out.println(getTickets().size());
        for (int i = 0; i < getTickets().size(); i++) {
            System.out.println(getTickets().get(i));
        }
    }




    @Test
    public void verifyTheStatus() {
        given().when().get(Main.BASE_URL + "/user").then().statusCode(200);
    }

    @Test
    public void verifyName() {
        given().when().get(Main.BASE_URL + "/user").then()
                .body(containsString("Malcolm"));
    }


    @Test
    public void verifyUserTicket() {
        Map<String, List<Ticket>> map = new HashMap<>();

        for (Ticket ticket : Main.getTickets()) {
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
        Iterator<Ticket> ticketIterator = Main.getTickets().iterator();
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
        given().when().get(Main.BASE_URL_TICKET + "/ticket").then()
                .statusCode(200).assertThat().body("createdAt", everyItem(hasItem(("2020-05-07T19:14:01.255Z"))));

    }


    @Test
    public void verifyHowManyTicket() {
        int counter = 0;
        String date = "2020-05-07";
        Iterator<Ticket> ticketIterator = Main.getTickets().iterator();
        while (ticketIterator.hasNext()) {
            Ticket ticket = ticketIterator.next();
            if (ticket.getCreatedAt().contains(date)) {
                counter++;

            }
        }
        assertEquals(counter, 2);
    }
    
    @Test
    public void verifyManyTicket() throws ParseException {
        int counter = 0;
        SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdfo.parse("2020-05-07");
        System.out.println("Date1 : " + sdfo.format(d1));
        Iterator<Ticket> iteratorTicket = Main.getTickets().iterator();

        while (iteratorTicket.hasNext()) {
            Ticket tickets = iteratorTicket.next();
            System.out.println(tickets.getCreatedAt());
            Date date = sdfo.parse(tickets.getCreatedAt());

            if (sdfo.format(date).equals(sdfo.format(d1))) {
                counter++;
            }
        }
        assertEquals(counter, 2);
    }



}

package org.example.testngtests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.pojo.*;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BookingApi {

    @BeforeTest
    public void beforetest(){
        WebDriverManager.chromedriver().clearDriverCache().driverVersion("").setup();
    }

    /**
     API call to get list of Users
     */

    @Test
    public void getListOfUsers(){
        try{
            JsonPath path = given()
                    .when()
                    .get("https://reqres.in/api/users?page=1")
                    .then()
                    .statusCode(200)
                    .extract().body().jsonPath();

            // Extract a single object from the json response
            UserDetails userDetails = path.getObject("", UserDetails.class);
            System.out.println(userDetails.toString());

            // Data is list of objects
            List<Data> dataList = userDetails.getData();
            System.out.println(dataList.get(4));
            GlobalFields.Id = dataList.get(4).getId();
            System.out.println(GlobalFields.Id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * API call to get Single User
     */
    @Test
    public void getSingleUser1(){
        given()
                .when()
                .get("https://reqres.in/api/users?page=1")
                .then()
                .statusCode(200)
                .body("data[4].email", equalTo("charles.morris@reqres.in"));
    }
    @Test(dependsOnMethods = "getListOfUsers")
    public void getSingleUser(){
        JsonPath path = given()
                .when()
                .pathParam("id", GlobalFields.Id)
                .get("https://reqres.in/api/users/{id}")
                .then()
                .statusCode(200)
                .extract().body().jsonPath();

        // Extracting user details
        Data userData = path.getObject("data", Data.class);
        Support support = path.getObject("support", Support.class);

        // Printing the extracted details
        System.out.println(userData.toString());
        System.out.println(support.toString());
    }
    @Test
    public void bookingApiAuth(){
        JSONObject credential = new JSONObject();
        credential.put("username", "admin");
        credential.put("password", "password123");

        GlobalFields.ACCESS_TOKEN = given()
                .header("Content-Type", "application/json")
                .body(credential)
                .when()
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getString("token");
        System.out.println("Access_Token: "+ GlobalFields.ACCESS_TOKEN);
    }
    @Test
    public void getAllBooking(){
        JsonPath jsonPath = given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200)
                .extract().body().jsonPath();
        List<BookingId> bookingIds = jsonPath.getList("", BookingId.class);
        GlobalFields.BOOKING_ID = bookingIds.get(1).getBookingid();
        System.out.println("BookingId: "+ GlobalFields.BOOKING_ID);
    }
    @Test(dependsOnMethods = "getAllBooking")
    public void getBookingById(){
        Booking booking = given()
                .pathParam("bookingid", GlobalFields.BOOKING_ID)
                .when()
                .get("https://restful-booker.herokuapp.com/booking/{bookingid}")
                .then()
                .statusCode(200)
                .extract().body().as(Booking.class);
        System.out.println(booking.toString());
    }
    @Test
    public void createBooking(){
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2023-01-01");
        bookingDates.setCheckout("2024-01-01");

        Booking booking = new Booking();
        booking.setFirstname("Curiosity");
        booking.setLastname("Rover");
        booking.setTotalprice(222);
        booking.setDepositpaid(false);
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Late night snacks");

        BookingResponse bookingResponse = given()
                .header("Content-Type", "application/json")
                .body(booking)
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200)
                .extract().response().as(BookingResponse.class);
        GlobalFields.BOOKING_ID = bookingResponse.getBookingid();
        System.out.println("BookingId: "+ GlobalFields.BOOKING_ID);
    }
    @Test(dependsOnMethods = {"createBooking", "bookingApiAuth"} )
    public void updateBookingById(){
        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2020-01-01");
        bookingdates.setCheckout("2021-01-01");

        Booking bookingUpdate = new Booking();
        bookingUpdate.setFirstname("Perseverance");
        bookingUpdate.setLastname("Rover");
        bookingUpdate.setTotalprice(122);
        bookingUpdate.setDepositpaid(true);
        bookingUpdate.setBookingdates(bookingdates);
        bookingUpdate.setAdditionalneeds("Supper");

        System.out.println(bookingUpdate);

        Booking bookingResponse = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token="+ GlobalFields.ACCESS_TOKEN )
                .pathParam("bookingid", GlobalFields.BOOKING_ID)
                .body(bookingUpdate)
                .when()
                .put("https://restful-booker.herokuapp.com/booking/{bookingid}")
                .then()
                .statusCode(200)
                .extract().body().as(Booking.class);
        Assert.assertEquals(bookingResponse.getAdditionalneeds(), "Supper");
    }
    @Test(dependsOnMethods = {"createBooking", "bookingApiAuth"})
    public void updateFieldByyId(){
        JSONObject lastname = new JSONObject();
        lastname.put("lastname", "Browniss");

        Booking bookingResponse = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token="+ GlobalFields.ACCESS_TOKEN )
                .pathParam("bookingid", GlobalFields.BOOKING_ID)
                .body(lastname)
                .when()
                .patch("https://restful-booker.herokuapp.com/booking/{bookingid}")
                .then()
                .statusCode(200)
                .extract().body().as(Booking.class);
        Assert.assertEquals(bookingResponse.getLastname(), "Browniss", "Not Equal");
        System.out.println(bookingResponse.toString());
    }
    @Test(dependsOnMethods = {"createBooking", "bookingApiAuth"})
    public void deleteById(){
        // Perform delete operation
        Response response= given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token="+ GlobalFields.ACCESS_TOKEN )
                .pathParam("bookingid", GlobalFields.BOOKING_ID)
                .when()
                .delete("https://restful-booker.herokuapp.com/booking/{bookingid}")
                .then()
                .statusCode(201)
                .extract().response();
        Assert.assertEquals(response.asString(), "Created", "Deleted");
    }
}

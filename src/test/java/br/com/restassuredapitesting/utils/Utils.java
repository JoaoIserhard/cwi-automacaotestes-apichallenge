package br.com.restassuredapitesting.utils;

import org.json.simple.JSONObject;

public class Utils {

    public static JSONObject validPayLoadBooking() {
        JSONObject payload = new JSONObject();
        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");


        payload.put("firstname", "Ronaldo");
        payload.put("lastname", "Fenomeno");
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingdates);
        payload.put("additionalneeds", "Breakfast");
        return payload;
    }

    public static JSONObject validPayLoad2Booking() {
        JSONObject payload = new JSONObject();
        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin", "2021-01-01");
        bookingdates.put("checkout", "2021-04-04");


        payload.put("firstname", "Bob");
        payload.put("lastname", "Esponja");
        payload.put("totalprice", 96000);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingdates);
        payload.put("additionalneeds", "Breakfast");
        return payload;
    }

    public static JSONObject extraPayLoadBooking() {
        JSONObject payload = new JSONObject();
        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin", "2021-04-02");
        bookingdates.put("checkout", "2021-04-04");

        payload.put("firstname", "Bob");
        payload.put("lastname", "Esponja");
        payload.put("totalprice", 96000);
        payload.put("depositpaid", true);
        payload.put("pets", true);
        payload.put("bookingdates", bookingdates);
        payload.put("additionalneeds", "Breakfast");
        return payload;
    }

    public static JSONObject invalidPayLoadBooking() {
        JSONObject invalidPayload = new JSONObject();

        invalidPayload.put("firstname", "Bob");
        invalidPayload.put("lastname", "Esponja");
        invalidPayload.put("totalprice", 9010);
        invalidPayload.put("depositpaid", true);
        invalidPayload.put("additionalneeds", "Hamburguer de Siri");
        return invalidPayload;
    }


    public static  String getContractsBasePath(String pack, String contract) {
        return System.getProperty("user.dir")
                + "/src/test/java/br/com/restassuredapitesting/tests/"
                + pack
                + "/contracts/"
                + contract
                + ".json";
    }
}

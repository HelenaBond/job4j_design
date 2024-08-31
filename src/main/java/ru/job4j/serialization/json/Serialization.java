package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

public class Serialization {
    public static void main(String[] args) {
        Destination postOffice = new Destination(
                "+7(924)111-111-11-11",
                123456,
                12.2,
                true,
                new Address("Moscow", "SomeStreet", 1),
                new String[]{"No Animals", "No Freezing"});
        System.out.println(postOffice);

        Gson gson = new GsonBuilder().create();

        String jsonPostOffice = gson.toJson(postOffice);
        System.out.println("From pojo to json:");
        System.out.printf("With gson: %s%n", jsonPostOffice);

        System.out.println("From json to pojo:");
        Destination desPostOffice = gson.fromJson(jsonPostOffice, Destination.class);
        System.out.printf("With gson: %s%n", desPostOffice);

        System.out.printf("Object postOffice equals object desPostOffice : %s%n",
                postOffice.equals(desPostOffice));

        JSONObject jsonObjectAddress = new JSONObject();
        jsonObjectAddress.put("city", "Moscow");
        jsonObjectAddress.put("street", "SomeStreet");
        jsonObjectAddress.put("houseNumber", 1);
        JSONObject jsonObjectOffice = new JSONObject();
        jsonObjectOffice.put("phone", postOffice.getPhone());
        jsonObjectOffice.put("zipCode", postOffice.getZipCode());
        jsonObjectOffice.put("distance", postOffice.getDistance());
        jsonObjectOffice.put("partner", postOffice.isPartner());
        jsonObjectOffice.put("address", jsonObjectAddress);
        jsonObjectOffice.put("permissions", postOffice.getPermissions());

        System.out.printf("From pojo to jsonObject: %s%n", jsonObjectOffice);
        System.out.printf("From pojo to jsonObject: %s%n", new JSONObject(postOffice));
    }
}

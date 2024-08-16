package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        System.out.println(jsonPostOffice);

        Destination desPostOffice = gson.fromJson(jsonPostOffice, Destination.class);
        System.out.println(desPostOffice);

        System.out.printf("Object postOffice equals object desPostOffice : %s%n",
                postOffice.equals(desPostOffice));
    }
}

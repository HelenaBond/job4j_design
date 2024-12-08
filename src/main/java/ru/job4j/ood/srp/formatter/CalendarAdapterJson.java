package ru.job4j.ood.srp.formatter;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Calendar;

public class CalendarAdapterJson implements JsonSerializer<Calendar> {

    private final DateTimeParser<Calendar> dateTimeParser;

    public CalendarAdapterJson(DateTimeParser<Calendar> dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public JsonElement serialize(
            Calendar calendar,
            Type type,
            JsonSerializationContext jsonSerializationContext) {
        String formattedDate = dateTimeParser.parse(calendar);
        return new JsonPrimitive(formattedDate);
    }
}

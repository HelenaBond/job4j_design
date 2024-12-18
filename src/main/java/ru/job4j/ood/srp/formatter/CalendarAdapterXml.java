package ru.job4j.ood.srp.formatter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.Calendar;

public class CalendarAdapterXml extends XmlAdapter<String, Calendar> {

    private final DateTimeParser<Calendar> parser;

    public CalendarAdapterXml(DateTimeParser<Calendar> parser) {
        this.parser = parser;
    }

    @Override
    public Calendar unmarshal(String v) {
        throw new RuntimeException("Реализуйте десериализацию тут.");
    }

    @Override
    public String marshal(Calendar calendar) {
        return parser.parse(calendar);
    }
}

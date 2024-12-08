package ru.job4j.ood.srp.report.engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.CalendarAdapterJson;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class JsonReportEngine implements Report {

    private final Store store;

    public JsonReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        CalendarAdapterJson parser = new CalendarAdapterJson(new ReportDateTimeParser());

        Gson gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(Calendar.class, parser)
                .setPrettyPrinting()
                .create();
        return gson.toJson(employees);
    }
}

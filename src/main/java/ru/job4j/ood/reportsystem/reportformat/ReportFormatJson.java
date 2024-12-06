package ru.job4j.ood.reportsystem.reportformat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.reportsystem.model.EmployeeDto;

import java.util.List;

/**
 * Формат не будет создавать теги для null значений.
 * Поэтому обнулите те значения в EmployeeDto которые вам не нужны в итоговом json.
 * Поряк полей в json соответствует порядку полей в EmployeeDto.
 */
public class ReportFormatJson implements ReportFormat {
    @Override
    public String toReportFormat(List<EmployeeDto> employees) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(employees);
    }
}

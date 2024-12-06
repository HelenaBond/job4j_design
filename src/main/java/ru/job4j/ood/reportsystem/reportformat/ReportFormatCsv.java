package ru.job4j.ood.reportsystem.reportformat;

import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import ru.job4j.ood.reportsystem.model.EmployeeDto;


import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.StringJoiner;

/**
 * Для формирования таблицы в конструктор нужно передать массив строк с именами полей
 * в желаемом порядке. Строки в массиве должны полностью совпадать с именами полей с учётом регистра.
 * Если нужны все поля - перечислите их в массиве.
 */
public class ReportFormatCsv implements ReportFormat {

    private final String[] headers;

    public ReportFormatCsv(String[] headers) {
        this.headers = headers;
    }

    @Override
    public String toReportFormat(List<EmployeeDto> employees) {
        Writer stringWriter = new StringWriter();
        ColumnPositionMappingStrategy<EmployeeDto> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(EmployeeDto.class);
        strategy.setColumnMapping(headers);
        StatefulBeanToCsv<EmployeeDto> beanToCsv = new StatefulBeanToCsvBuilder<EmployeeDto>(stringWriter)
                .withSeparator(';')
                .withMappingStrategy(strategy)
                .withApplyQuotesToAll(false)
                .build();
        try {
            StringJoiner joiner = new StringJoiner("; ", "", System.lineSeparator());
            for (String header : headers) {
                joiner.add(header);
            }
            stringWriter.append(joiner.toString());
            beanToCsv.write(employees);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
            throw new RuntimeException(e);
        }
        return stringWriter.toString();
    }
}

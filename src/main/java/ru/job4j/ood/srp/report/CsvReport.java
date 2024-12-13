package ru.job4j.ood.srp.report;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeDtoCsv;
import ru.job4j.ood.srp.requirement.Requirement;
import ru.job4j.ood.srp.store.Store;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

/**
 * Для формирования таблицы в конструктор нужно передать массив строк с именами полей
 * в желаемом порядке. Строки в массиве должны полностью совпадать с именами полей с учётом регистра.
 * Если нужны все поля - перечислите их в массиве.
 * Библиотека openCsv предлагает расширение кода относительно одного бина с помощью профилей.
 * Для использования которых нужно создавать StatefulBeanToCsv под каждый профиль.
 * Для решения текущей задачи был выбран вариант использования Dto.
 * Этот подход увеличивает потребление памяти в 2 раза, но делает код чище.
 */
public class CsvReport extends AbstractReport {

    private final Requirement requirement;

    public CsvReport(Store store, Requirement requirement) {
        super(store);
        this.requirement = requirement;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Writer stringWriter = new StringWriter();
        ColumnPositionMappingStrategy<EmployeeDtoCsv> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(EmployeeDtoCsv.class);
        strategy.setColumnMapping(requirement.getHeaders());
        StatefulBeanToCsv<EmployeeDtoCsv> beanToCsv = new StatefulBeanToCsvBuilder<EmployeeDtoCsv>(stringWriter)
                .withSeparator(';')
                .withMappingStrategy(strategy)
                .withApplyQuotesToAll(false)
                .build();
        try {
            StringJoiner joiner = new StringJoiner("; ", "", System.lineSeparator());
            for (String header : requirement.getHeaders()) {
                joiner.add(header);
            }
            stringWriter.append(joiner.toString());

            List<Employee> employees = this.getStore().findBy(filter);
            List<EmployeeDtoCsv> readyEmployees = requirement.preparedList(employees);
            beanToCsv.write(readyEmployees);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
            throw new RuntimeException(e);
        }
        return stringWriter.toString();
    }
}

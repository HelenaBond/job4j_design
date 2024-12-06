package ru.job4j.ood.reportsystem.reportformat;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.job4j.ood.reportsystem.model.EmployeeDto;
import ru.job4j.ood.reportsystem.model.EmployeeListWrapper;

import java.io.StringWriter;
import java.util.List;

/**
 * Формат не будет создавать теги для null значений.
 * Поэтому обнулите те значения в EmployeeDto которые вам не нужны в итоговом xml.
 * Поряк полей - фиксированный.
 */
public class ReportFormatXml implements ReportFormat {
    @Override
    public String toReportFormat(List<EmployeeDto> employees) {
        try {
            JAXBContext context = JAXBContext.newInstance(EmployeeListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            EmployeeListWrapper wrapper = new EmployeeListWrapper();
            wrapper.setEmployees(employees);

            StringWriter writer = new StringWriter();
            marshaller.marshal(wrapper, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}

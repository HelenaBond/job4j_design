package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.io.StringWriter;

public class Serialization {
    public static void main(String[] args) throws Exception {
        Product product = new Product(
                false,
                123456789,
                2000.0,
                "washing powder",
                new DeliveryTerms(false, true),
                new String[] {"anionic surfactants", "nonionic surfactants"});

        JAXBContext context = JAXBContext.newInstance(Product.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(product, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Object result = unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}

package ru.job4j.inout.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class MainLibrary {
    public static void main(String[] args) throws Exception {
        Library library = new Library(false, 30, new Book("Pushkin"), "Grigoriev",  new String[] {"Кузнецов", "Петров"});

        JAXBContext context = JAXBContext.newInstance(Library.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(library, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Library result = (Library) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}

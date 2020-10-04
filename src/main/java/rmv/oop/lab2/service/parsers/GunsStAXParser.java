package rmv.oop.lab2.service.parsers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
@Slf4j
public class GunsStAXParser extends XMLParser {
    @Override
    public void parse(File XMLFile) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader;
        try {
            reader = xmlInputFactory.createXMLEventReader(new FileInputStream(XMLFile));
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    String attributeString = null;
                    if (startElement.getAttributes().hasNext()) {
                        attributeString = startElement.getAttributeByName(new QName("id")).getValue();
                    }
                    nextEvent = reader.nextEvent();
                    String string = startElement.getName().getLocalPart();
                    if (nextEvent.isCharacters()) {
                        gunsHandler.setElementValue(nextEvent.asCharacters().getData());
                        gunsHandler.setField(string, attributeString);
                    }
                }
            }
            log.info(gunsHandler.getGuns().getGunList().toString());
        } catch (FileNotFoundException | XMLStreamException e) {
            log.info(e.toString());
        }
    }
}

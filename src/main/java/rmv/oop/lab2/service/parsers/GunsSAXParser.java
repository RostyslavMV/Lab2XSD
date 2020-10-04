package rmv.oop.lab2.service.parsers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class GunsSAXParser extends XMLParser {
    @Override
    public void parse(File XMLFile) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(XMLFile, gunsHandler);
            log.info(gunsHandler.getGuns().getGunList().toString());
        } catch (SAXException | ParserConfigurationException | IOException e) {
            log.error(e.toString());
        }
    }
}

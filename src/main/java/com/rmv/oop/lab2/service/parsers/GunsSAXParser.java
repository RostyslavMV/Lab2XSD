package com.rmv.oop.lab2.service.parsers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import com.rmv.oop.lab2.service.xmlcreator.XMLCreator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class GunsSAXParser extends XMLParser {

    @Autowired
    public GunsSAXParser(XMLCreator xmlCreator) {
        this.xmlCreator = xmlCreator;
    }

    @Override
    public void parse(File XMLFile) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(XMLFile, gunsHandler);
            log.info(gunsHandler.getGuns().getGunList().toString());
            xmlCreator.buildXML(gunsHandler.getGuns().getGunList(),"output\\SAXOutput.xml");
        } catch (SAXException | ParserConfigurationException | IOException e) {
            log.error(e.toString());
        }
    }
}

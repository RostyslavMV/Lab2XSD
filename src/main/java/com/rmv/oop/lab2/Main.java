package com.rmv.oop.lab2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.rmv.oop.lab2.service.parsers.GunsDOMParser;
import com.rmv.oop.lab2.service.parsers.GunsSAXParser;
import com.rmv.oop.lab2.service.parsers.GunsStAXParser;
import com.rmv.oop.lab2.service.validator.XMLValidator;

import java.io.File;
import java.net.URL;

@SpringBootApplication
@Slf4j
public class Main implements CommandLineRunner {

    @Autowired
    private XMLValidator xmlValidator;

    @Autowired
    private GunsSAXParser gunsSAXParser;

    @Autowired
    private GunsStAXParser gunsStAXParser;

    @Autowired
    private GunsDOMParser gunsDOMParser;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        URL urlXML = getClass().getClassLoader().getResource("guns.xml");
        URL urlXSD = getClass().getClassLoader().getResource("guns.xsd");
        File XML = new File(urlXML.getFile());
        File XSD = new File(urlXSD.getFile());
        if(xmlValidator.isValid(XML, XSD)){
            log.info("XML is valid");
        }
        else log.info("XML is not valid");
        gunsSAXParser.parse(XML);
        gunsStAXParser.parse(XML);
        gunsDOMParser.parse(XML);
    }
}

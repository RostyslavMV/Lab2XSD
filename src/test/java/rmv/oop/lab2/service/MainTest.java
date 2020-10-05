package rmv.oop.lab2.service;

import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rmv.oop.lab2.service.parsers.GunsDOMParser;
import rmv.oop.lab2.service.parsers.GunsSAXParser;
import rmv.oop.lab2.service.parsers.GunsStAXParser;
import rmv.oop.lab2.service.validator.XMLValidator;
import rmv.oop.lab2.service.xmlcreator.XMLCreator;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@ExtendWith(SpringExtension.class)
class MainTest {

    @Mock
    private XMLCreator xmlCreator;

    @InjectMocks
    private XMLValidator xmlValidator;

    @InjectMocks
    private GunsSAXParser gunsSAXParser;

    @InjectMocks
    private GunsStAXParser gunsStAXParser;

    @InjectMocks
    private GunsDOMParser gunsDOMParser;

    @org.junit.jupiter.api.Test
    void test(){
        URL urlXML = getClass().getClassLoader().getResource("guns.xml");
        URL urlXSD = getClass().getClassLoader().getResource("guns.xsd");
        File XML = new File(urlXML.getFile());
        File XSD = new File(urlXSD.getFile());
        assert(xmlValidator.isValid(XML, XSD));
        gunsSAXParser.parse(XML);
        gunsStAXParser.parse(XML);
        gunsDOMParser.parse(XML);
        try {
            assert(FileUtils.contentEquals(new File("output\\SAXOutput.xml"),
                    new File("output\\StAXOutput.xml")));
            assert(FileUtils.contentEquals(new File("output\\SAXOutput.xml"),
                    new File("output\\DOMOutput.xml")));
        }
       catch (IOException e){
            e.printStackTrace();
       }
    }
}

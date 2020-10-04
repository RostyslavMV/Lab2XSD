package rmv.oop.lab2.service.validator;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.FileUtils;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class XMLValidator {

    public boolean isValid(File XMLfile, File XSDfile) {
        if (!FileUtils.getExtension(XMLfile.getName()).equals("xml")) {
            throw new IllegalArgumentException("Expected XML file");
        }
        if (!FileUtils.getExtension(XSDfile.getName()).equals("xsd")) {
            throw new IllegalArgumentException("Expected XSD file");
        }
        try {
            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = factory.newSchema(XSDfile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(XMLfile));
        } catch (SAXException | IOException e) {
            log.error("Validation error, reason: " + e.getMessage());
            return false;
        }
        return true;
    }
}

package rmv.oop.lab2.service.parsers;

import java.io.File;

public abstract class XMLParser {
    GunsHandler gunsHandler = new GunsHandler();
    public abstract void parse(File XMLFile);
}

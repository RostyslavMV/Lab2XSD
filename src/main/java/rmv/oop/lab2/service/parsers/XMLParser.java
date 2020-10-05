package rmv.oop.lab2.service.parsers;

import rmv.oop.lab2.service.xmlcreator.XMLCreator;

import java.io.File;

public abstract class XMLParser {
    protected XMLCreator xmlCreator;

    GunsHandler gunsHandler = new GunsHandler();

    public abstract void parse(File XMLFile);
}

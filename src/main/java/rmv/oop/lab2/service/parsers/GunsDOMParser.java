package rmv.oop.lab2.service.parsers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import rmv.oop.lab2.model.jaxb.gen.*;
import rmv.oop.lab2.service.xmlcreator.XMLCreator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigInteger;

@Service
@Slf4j
public class GunsDOMParser extends XMLParser {

    @Autowired
    public GunsDOMParser(XMLCreator xmlCreator) {
        this.xmlCreator = xmlCreator;
    }

    @Override
    public void parse(File XMLFile) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(XMLFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Gun");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                gunsHandler.getGuns().getGunList().add(new Gun());
                gunsHandler.lastestGun().setCharacteristics(new GunCharacteristics());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    gunsHandler.lastestGun().setId(new BigInteger(eElement.getAttribute("id")));
                    gunsHandler.lastestGun().setModel(eElement.getElementsByTagName("model")
                            .item(0)
                            .getTextContent());
                    gunsHandler.lastestGun().setIsHandy(Boolean.parseBoolean(eElement.getElementsByTagName(
                            "isHandy")
                            .item(0)
                            .getTextContent()));
                    gunsHandler.lastestGun().setCountryOfOrigin(Country.valueOf(eElement.getElementsByTagName(
                            "countryOfOrigin")
                            .item(0)
                            .getTextContent()));
                    gunsHandler.lastestGun().setMaterial(Material.valueOf(eElement.getElementsByTagName(
                            "material")
                            .item(0)
                            .getTextContent()));
                    gunsHandler.lastestGun().getCharacteristics().setRangeType(RangeType
                            .valueOf(eElement.getElementsByTagName("rangeType")
                                    .item(0)
                                    .getTextContent()));
                    gunsHandler.lastestGun().getCharacteristics().setRange(Integer
                            .parseInt(eElement.getElementsByTagName("range")
                                    .item(0)
                                    .getTextContent()));
                    gunsHandler.lastestGun().getCharacteristics().setSightingRange(Integer
                            .parseInt(eElement.getElementsByTagName("sightingRange")
                                    .item(0)
                                    .getTextContent()));
                    gunsHandler.lastestGun().getCharacteristics().setHasClip(Boolean
                            .parseBoolean(eElement.getElementsByTagName("hasClip")
                                    .item(0)
                                    .getTextContent()));
                    gunsHandler.lastestGun().getCharacteristics().setHasOptic(Boolean
                            .parseBoolean(eElement.getElementsByTagName("hasOptic")
                                    .item(0)
                                    .getTextContent()));
                }
            }
            log.info(gunsHandler.getGuns().getGunList().toString());
            xmlCreator.buildXML(gunsHandler.getGuns().getGunList(),"output\\DOMOutput.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

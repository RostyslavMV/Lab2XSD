package com.rmv.oop.lab2.service.xmlcreator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.rmv.oop.lab2.model.jaxb.gen.Gun;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class XMLCreator {

    public void buildXML(List<Gun> gunList, String xmlFilePath) {
        try {
            File file = new File(xmlFilePath);
            if (file.createNewFile()) {
                log.info("File created: " + file.getName());
            } else {
                log.info("File " + xmlFilePath + " already exists.");
            }
        } catch (IOException e) {
            log.error(e.toString());
        }

        Collections.sort(gunList);

        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            Element root = document.createElement("Guns");
            document.appendChild(root);

            for (Gun gun : gunList) {
                Element gunElement = document.createElement("Gun");

                root.appendChild(gunElement);

                Attr attr = document.createAttribute("id");
                attr.setValue(gun.getId().toString());
                gunElement.setAttributeNode(attr);

                Element model = document.createElement("model");
                model.appendChild(document.createTextNode(gun.getModel()));
                gunElement.appendChild(model);

                Element isHandy = document.createElement("isHandy");
                isHandy.appendChild(document.createTextNode(String.valueOf(gun.isIsHandy())));
                gunElement.appendChild(isHandy);

                Element countryOfOrigin = document.createElement("countryOfOrigin");
                countryOfOrigin.appendChild(document.createTextNode(gun.getCountryOfOrigin().toString()));
                gunElement.appendChild(countryOfOrigin);

                Element characteristics = document.createElement("characteristics");
                gunElement.appendChild(characteristics);

                Element rangeType = document.createElement("rangeType");
                rangeType.appendChild(document.createTextNode(String.valueOf(gun.getCharacteristics().getRangeType())));
                characteristics.appendChild(rangeType);

                Element range = document.createElement("range");
                range.appendChild(document.createTextNode(String.valueOf(gun.getCharacteristics().getRange())));
                characteristics.appendChild(range);

                Element sightingRange = document.createElement("sightingRange");
                sightingRange.appendChild(document.createTextNode(String.valueOf(gun.getCharacteristics().getSightingRange())));
                characteristics.appendChild(sightingRange);

                Element hasClip = document.createElement("hasClip");
                hasClip.appendChild(document.createTextNode(String.valueOf(gun.getCharacteristics().isHasClip())));
                characteristics.appendChild(hasClip);

                Element hasOptic = document.createElement("hasOptic");
                hasOptic.appendChild(document.createTextNode(String.valueOf(gun.getCharacteristics().isHasOptic())));
                characteristics.appendChild(hasOptic);


                Element material = document.createElement("material");
                material.appendChild(document.createTextNode(gun.getMaterial().toString()));
                gunElement.appendChild(material);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));

            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException | TransformerException e) {
            log.error(e.toString());
        }
    }
}

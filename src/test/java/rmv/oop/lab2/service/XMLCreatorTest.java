package rmv.oop.lab2.service;

import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rmv.oop.lab2.model.jaxb.gen.*;
import rmv.oop.lab2.service.xmlcreator.XMLCreator;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class XMLCreatorTest {

    @Test
    void createXML(){
        XMLCreator xmlCreator = new XMLCreator();
        List<Gun> guns = new ArrayList<>();
        Gun gun1 = new Gun();
        Gun gun2 = new Gun();
        gun1.setCharacteristics(new GunCharacteristics());
        gun2.setCharacteristics(new GunCharacteristics());

        gun1.setModel("Makarov pistol");
        gun1.setId(BigInteger.valueOf(1));
        gun1.setIsHandy(true);
        gun1.setCountryOfOrigin(Country.USSR);
        gun1.setMaterial(Material.STEEL);
        gun1.getCharacteristics().setRangeType(RangeType.CLOSE);
        gun1.getCharacteristics().setRange(50);
        gun1.getCharacteristics().setSightingRange(50);
        gun1.getCharacteristics().setHasClip(true);
        gun1.getCharacteristics().setHasOptic(false);

        gun2.setModel("AK-47");
        gun2.setId(BigInteger.valueOf(2));
        gun2.setIsHandy(false);
        gun2.setCountryOfOrigin(Country.USSR);
        gun2.setMaterial(Material.STEEL);
        gun2.getCharacteristics().setRangeType(RangeType.CLOSE);
        gun2.getCharacteristics().setRange(400);
        gun2.getCharacteristics().setSightingRange(800);
        gun2.getCharacteristics().setHasClip(true);
        gun2.getCharacteristics().setHasOptic(false);

        guns.add(gun1);
        guns.add(gun2);
        xmlCreator.buildXML(guns,"src\\test\\util\\XMLCreatorTest.xml");
        try {
        assert(FileUtils.contentEquals(new File("src\\test\\util\\XMLCreatorTest.xml"),
                new File("src\\test\\util\\Expected.xml")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

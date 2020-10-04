package rmv.oop.lab2.service.parsers;

import lombok.Setter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import rmv.oop.lab2.model.jaxb.gen.*;

import java.math.BigInteger;
import java.util.List;

public class GunsHandler extends DefaultHandler {
    private static final String GUNS = "Guns";
    private static final String GUN = "Gun";
    private static final String MODEL = "model";
    private static final String ISHANDY = "isHandy";
    private static final String COUNTRYOFORIGIN = "countryOfOrigin";
    private static final String CHARACTERISTICS = "characteristics";
    private static final String RANGETYPE = "rangeType";
    private static final String RANGE = "range";
    private static final String SIGHTINGRANGE = "sightingRange";
    private static final String HASCLIP = "hasClip";
    private static final String HASOPTIC = "hasOptic";
    private static final String MATERIAL = "material";

    private Guns guns = new Guns();
    @Setter
    private String elementValue;

    @Override
    public void characters(char[] ch, int start, int length){
        elementValue = new String(ch, start, length);
    }

    @Override
    public void startDocument() {
        guns = new Guns();
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr){
        switch (qName) {
            case GUN:
                Gun gun = new Gun();
                gun.setId(new BigInteger(attr.getValue(0)));
                guns.getGunList().add(gun);
                break;
            case CHARACTERISTICS:
                lastestGun().setCharacteristics(new GunCharacteristics());
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        setField(qName);
    }

    public Gun lastestGun() {
        List<Gun> gunList = guns.getGunList();
        int latestArticleIndex = gunList.size() - 1;
        return gunList.get(latestArticleIndex);
    }

    public Guns getGuns() {
        return guns;
    }

    public void setField(String qName) {
        switch (qName) {
            case MODEL:
                lastestGun().setModel(elementValue);
                break;
            case ISHANDY:
                lastestGun().setIsHandy(Boolean.parseBoolean(elementValue));
                break;
            case COUNTRYOFORIGIN:
                lastestGun().setCountryOfOrigin(Country.valueOf(elementValue));
                break;
            case RANGETYPE:
                lastestGun().getCharacteristics().setRangeType(RangeType.valueOf(elementValue));
                break;
            case RANGE:
                lastestGun().getCharacteristics().setRange(Integer.parseInt(elementValue));
                break;
            case SIGHTINGRANGE:
                lastestGun().getCharacteristics().setSightingRange(Integer.parseInt(elementValue));
                break;
            case HASCLIP:
                lastestGun().getCharacteristics().setHasClip(Boolean.parseBoolean(elementValue));
                break;
            case HASOPTIC:
                lastestGun().getCharacteristics().setHasOptic(Boolean.parseBoolean(elementValue));
                break;
            case MATERIAL:
                lastestGun().setMaterial(Material.valueOf(elementValue));
                break;
        }
    }

    public void setField(String qName, String attribute){
        switch (qName) {
            case GUN:
                Gun gun = new Gun();
                gun.setId(new BigInteger(attribute));
                guns.getGunList().add(gun);
                break;
            case CHARACTERISTICS:
                lastestGun().setCharacteristics(new GunCharacteristics());
                break;
            default:
                setField(qName);
                break;
        }
    }

}

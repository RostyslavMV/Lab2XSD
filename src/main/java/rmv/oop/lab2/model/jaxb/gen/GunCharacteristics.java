//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.05 at 01:43:53 AM EEST 
//


package rmv.oop.lab2.model.jaxb.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GunCharacteristics complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GunCharacteristics"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="rangeType" type="{rmv/oop/lab2/model/jaxb/gen}RangeType"/&gt;
 *         &lt;element name="range" type="{rmv/oop/lab2/model/jaxb/gen}Range"/&gt;
 *         &lt;element name="sightingRange" type="{rmv/oop/lab2/model/jaxb/gen}SightingRange"/&gt;
 *         &lt;element name="hasClip" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="hasOptic" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GunCharacteristics", propOrder = {
    "rangeType",
    "range",
    "sightingRange",
    "hasClip",
    "hasOptic"
})
public class GunCharacteristics {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected RangeType rangeType;
    @XmlSchemaType(name = "integer")
    protected int range;
    @XmlSchemaType(name = "integer")
    protected int sightingRange;
    protected boolean hasClip;
    protected boolean hasOptic;

    /**
     * Gets the value of the rangeType property.
     * 
     * @return
     *     possible object is
     *     {@link RangeType }
     *     
     */
    public RangeType getRangeType() {
        return rangeType;
    }

    /**
     * Sets the value of the rangeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RangeType }
     *     
     */
    public void setRangeType(RangeType value) {
        this.rangeType = value;
    }

    /**
     * Gets the value of the range property.
     * 
     */
    public int getRange() {
        return range;
    }

    /**
     * Sets the value of the range property.
     * 
     */
    public void setRange(int value) {
        this.range = value;
    }

    /**
     * Gets the value of the sightingRange property.
     * 
     */
    public int getSightingRange() {
        return sightingRange;
    }

    /**
     * Sets the value of the sightingRange property.
     * 
     */
    public void setSightingRange(int value) {
        this.sightingRange = value;
    }

    /**
     * Gets the value of the hasClip property.
     * 
     */
    public boolean isHasClip() {
        return hasClip;
    }

    /**
     * Sets the value of the hasClip property.
     * 
     */
    public void setHasClip(boolean value) {
        this.hasClip = value;
    }

    /**
     * Gets the value of the hasOptic property.
     * 
     */
    public boolean isHasOptic() {
        return hasOptic;
    }

    /**
     * Sets the value of the hasOptic property.
     * 
     */
    public void setHasOptic(boolean value) {
        this.hasOptic = value;
    }

}
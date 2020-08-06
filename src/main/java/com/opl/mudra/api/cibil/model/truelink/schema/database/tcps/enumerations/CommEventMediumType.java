
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommEventMediumType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CommEventMediumType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Call"/>
 *     &lt;enumeration value="EMail"/>
 *     &lt;enumeration value="Fax"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="ReplyCard"/>
 *     &lt;enumeration value="VoiceMail"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CommEventMediumType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum CommEventMediumType {

    @XmlEnumValue("Call")
    CALL("Call"),
    @XmlEnumValue("EMail")
    E_MAIL("EMail"),
    @XmlEnumValue("Fax")
    FAX("Fax"),
    @XmlEnumValue("Other")
    OTHER("Other"),
    @XmlEnumValue("ReplyCard")
    REPLY_CARD("ReplyCard"),
    @XmlEnumValue("VoiceMail")
    VOICE_MAIL("VoiceMail");
    private final String value;

    CommEventMediumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CommEventMediumType fromValue(String v) {
        for (CommEventMediumType c: CommEventMediumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

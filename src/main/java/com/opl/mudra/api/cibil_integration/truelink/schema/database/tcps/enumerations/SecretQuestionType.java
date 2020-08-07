
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SecretQuestionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SecretQuestionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FavoriteCar"/>
 *     &lt;enumeration value="FavoriteFilm"/>
 *     &lt;enumeration value="FavoriteFood"/>
 *     &lt;enumeration value="FavoriteGame"/>
 *     &lt;enumeration value="FavoriteShow"/>
 *     &lt;enumeration value="FavoriteTeam"/>
 *     &lt;enumeration value="FriendName"/>
 *     &lt;enumeration value="NoQuestion"/>
 *     &lt;enumeration value="PetName"/>
 *     &lt;enumeration value="SchoolMascot"/>
 *     &lt;enumeration value="TeacherLastName"/>
 *     &lt;enumeration value="FirstCar"/>
 *     &lt;enumeration value="MotherMiddleName"/>
 *     &lt;enumeration value="FatherMiddleName"/>
 *     &lt;enumeration value="BirthCity"/>
 *     &lt;enumeration value="GrandmotherFirstName"/>
 *     &lt;enumeration value="GrandfatherFirstName"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SecretQuestionType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum SecretQuestionType {

    @XmlEnumValue("FavoriteCar")
    FAVORITE_CAR("FavoriteCar"),
    @XmlEnumValue("FavoriteFilm")
    FAVORITE_FILM("FavoriteFilm"),
    @XmlEnumValue("FavoriteFood")
    FAVORITE_FOOD("FavoriteFood"),
    @XmlEnumValue("FavoriteGame")
    FAVORITE_GAME("FavoriteGame"),
    @XmlEnumValue("FavoriteShow")
    FAVORITE_SHOW("FavoriteShow"),
    @XmlEnumValue("FavoriteTeam")
    FAVORITE_TEAM("FavoriteTeam"),
    @XmlEnumValue("FriendName")
    FRIEND_NAME("FriendName"),
    @XmlEnumValue("NoQuestion")
    NO_QUESTION("NoQuestion"),
    @XmlEnumValue("PetName")
    PET_NAME("PetName"),
    @XmlEnumValue("SchoolMascot")
    SCHOOL_MASCOT("SchoolMascot"),
    @XmlEnumValue("TeacherLastName")
    TEACHER_LAST_NAME("TeacherLastName"),
    @XmlEnumValue("FirstCar")
    FIRST_CAR("FirstCar"),
    @XmlEnumValue("MotherMiddleName")
    MOTHER_MIDDLE_NAME("MotherMiddleName"),
    @XmlEnumValue("FatherMiddleName")
    FATHER_MIDDLE_NAME("FatherMiddleName"),
    @XmlEnumValue("BirthCity")
    BIRTH_CITY("BirthCity"),
    @XmlEnumValue("GrandmotherFirstName")
    GRANDMOTHER_FIRST_NAME("GrandmotherFirstName"),
    @XmlEnumValue("GrandfatherFirstName")
    GRANDFATHER_FIRST_NAME("GrandfatherFirstName");
    private final String value;

    SecretQuestionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SecretQuestionType fromValue(String v) {
        for (SecretQuestionType c: SecretQuestionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

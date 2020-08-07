package com.opl.mudra.api.oneform.enums;

public enum PropertyTypeLAP
{
	COMMERCIAL(1,"Commercial","Commercial"),
	RESIDENTIAL(2,"Residential","Residential"),
	INDUSTRIAL(3,"Industrial","Industrial"),
	PLOT(4,"Plot","Plot");


private final Integer id;
private final String value;
private final String description;
PropertyTypeLAP(Integer id, String value, String description) {
this.id = id;
this.value = value;
this.description = description;
}

public Integer getId() {
return id;
}

public String getValue() {
return value;
}

public String getDescription() {
return description;
}
public static PropertyTypeLAP getById(Integer id) {
switch (id) {
case 1:
	return COMMERCIAL;
case 2:
	return RESIDENTIAL;
case 3:
	return INDUSTRIAL;
case 4:
	return PLOT;
default:
	return null;
}
}
public static PropertyTypeLAP[] getAll() {
	return PropertyTypeLAP.values();

}
}

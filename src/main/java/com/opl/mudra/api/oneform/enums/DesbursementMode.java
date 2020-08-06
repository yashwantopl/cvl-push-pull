package com.opl.mudra.api.oneform.enums;

/**
 * @author Sanket
 *
 */
public enum DesbursementMode {

	CHEQUE(1, "Cheque", "Cheque"), NEFT(2,"NEFT", "NEFT"),  RTGS(3, " RTGS", " RTGS");

 private final Integer id;
 private final String value;
 private final String description;
 

 DesbursementMode(Integer id, String value, String description) {
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
 
 


 public static DesbursementMode getById(Integer id) {
  switch (id) {
  case 1:
   return CHEQUE;
  case 2:
   return DesbursementMode.NEFT;
  case 3:
   return RTGS;
  default:
   return null;
  }
 }

 public static DesbursementMode[] getAll() {
  return DesbursementMode.values();

 }
}
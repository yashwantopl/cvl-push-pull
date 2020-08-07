package com.opl.mudra.api.oneform.enums;

/**
 * @author Sanket
 *
 */
public enum Denomination {

 LAKHS(1, "Lakhs", "Lakhs",100000l), MILLIONS(2, "Millions", "Millions",1000000l), CRORES(3, "Crores", "Crores",10000000l), BILLIONS(4,
   "Billions", "Billions",100000000l), ABSOLUTE(5, "Absolute", "Absolute",1l);

 private final Integer id;
 private final String value;
 private final String description;
 private final Long digit;

 Denomination(Integer id, String value, String description,Long digit) {
  this.id = id;
  this.value = value;
  this.description = description;
  this.digit = digit;
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
 
 

 public Long getDigit() {
  return digit;
 }

 public static Denomination getById(Integer id) {
  switch (id) {
  case 1:
   return LAKHS;
  case 2:
   return MILLIONS;
  case 3:
   return CRORES;
  case 4:
   return BILLIONS;
  case 5:
   return ABSOLUTE;
  default:
   return null;
  }
 }

 public static Denomination[] getAll() {
  return Denomination.values();

 }
}
package com.opl.mudra.api.analyzer.model.yodlee.register;

public class User {

private String loginName;
private String password;
private String email;
private Name name;
private Address address;
private Preferences preferences;
private Session session;
public String getLoginName() {
	return loginName;
}
public void setLoginName(String loginName) {
	this.loginName = loginName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Name getName() {
	return name;
}
public void setName(Name name) {
	this.name = name;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}
public Preferences getPreferences() {
	return preferences;
}
public void setPreferences(Preferences preferences) {
	this.preferences = preferences;
}
public Session getSession() {
	return session;
}
public void setSession(Session session) {
	this.session = session;
}


}

package com.opl.mudra.api.analyzer.model.yodlee;

public class YodleeEncryptRequest {
	
	private String keyAlias;
	
	private String keyAsPemString;

	public String getKeyAlias() {
		return keyAlias;
	}

	public void setKeyAlias(String keyAlias) {
		this.keyAlias = keyAlias;
	}

	public String getKeyAsPemString() {
		return keyAsPemString;
	}

	public void setKeyAsPemString(String keyAsPemString) {
		this.keyAsPemString = keyAsPemString;
	}

	@Override
	public String toString() {
		return "YodleeEncryptRequest [keyAlias=" + keyAlias + ", keyAsPemString=" + keyAsPemString + "]";
	}

	public String toJson() {
		return "{'keyAlias':" + keyAlias + ",'keyAsPemString':" + keyAsPemString +"}";
	}

	public YodleeEncryptRequest(String keyAlias, String keyAsPemString) {
		super();
		this.keyAlias = keyAlias;
		this.keyAsPemString = keyAsPemString;
	}

	public YodleeEncryptRequest() {
		super();
	}
}

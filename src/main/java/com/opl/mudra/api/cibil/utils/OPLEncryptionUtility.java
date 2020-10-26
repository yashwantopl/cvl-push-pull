package com.opl.mudra.api.cibil.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.opl.mudra.api.cibil_integration.EncryptionDetail;


public class OPLEncryptionUtility {

	/*private static String bankUatPublickey = "MIIGHzCCBAegAwIBAgIJAJmyv+ECCUtnMA0GCSqGSIb3DQEBCwUAMIGlMQswCQYD\r\n"
			+ "VQQGEwJpbjEPMA0GA1UECAwGZ3VqcmF0MRIwEAYDVQQHDAlhaG1lZGFiYWQxFzAV\r\n"
			+ "BgNVBAoMDm9ubGluZXBzYmxvYW5zMRIwEAYDVQQLDAlkZXZlbG9wZXIxFzAVBgNV\r\n"
			+ "BAMMDm9ubGluZXBzYmxvYW5zMSswKQYJKoZIhvcNAQkBFhxhbmtpLmd1cHRhQG9u\r\n"
			+ "bGluZXBzYmxvYW4uY29tMB4XDTIwMDQxNzEwMDcwM1oXDTMwMDQxNTEwMDcwM1ow\r\n"
			+ "gaUxCzAJBgNVBAYTAmluMQ8wDQYDVQQIDAZndWpyYXQxEjAQBgNVBAcMCWFobWVk\r\n"
			+ "YWJhZDEXMBUGA1UECgwOb25saW5lcHNibG9hbnMxEjAQBgNVBAsMCWRldmVsb3Bl\r\n"
			+ "cjEXMBUGA1UEAwwOb25saW5lcHNibG9hbnMxKzApBgkqhkiG9w0BCQEWHGFua2ku\r\n"
			+ "Z3VwdGFAb25saW5lcHNibG9hbi5jb20wggIiMA0GCSqGSIb3DQEBAQUAA4ICDwAw\r\n"
			+ "ggIKAoICAQC6JCxvtIw95QqKNVbEbu3xdTUPWEjHcaBFRliNeKzjBy1JhmF98kqm\r\n"
			+ "jsIK7KdlaSxJ6jrEDJlpTkC/kgIQa4Dj0KluAmEYXRF9XtGfDeeARl3T6vDI+4dW\r\n"
			+ "672koI0ODTuAaRpStCfKCnS6uhuvpIle1Yh2p5TYgPm6u4/CGgULcHV/5jY/oBFI\r\n"
			+ "tdhneCn3PfW69Iv/zLOa+UDKuLcRWtROSyNeSl0ii9N88vPVyDUpByrsFCVakkvH\r\n"
			+ "zr3qsH1otTiYruzTkdE0bLuRlWvXpkpUov1XKfBJHN+Il9E1ocbcHV2obF0qd5bc\r\n"
			+ "e+QXUh8+5WOhfhRh5OmqW4jPSS0DXWPbQNpUJnKtAmAJ+xeoTK0gz0cDOIGwNnwL\r\n"
			+ "X4A/RD8O5KhTcOelpfL/N2fr1rD9W3tU9llqXk+ez0GO7fXrWVArei2W2KKP6flM\r\n"
			+ "OQcIhc/ZkgshLeMGER8wf+hTiTeAz08Kx0MyirDYMS3QfHDqHlkmNhMbJmtr95KY\r\n"
			+ "v0FtvADpNE+MQcEL9qkQufbSzLRhWB6QiDGabz/0ZvYGyhQfNHO33IpG0fHBI66u\r\n"
			+ "FM5XVK+jYfg8r2TK77xWaYf4JOEDhPOSyWV3JZd9Dbcmu7gd6zqPrJ5rONUx5abs\r\n"
			+ "oOQZpXEfUpqpDYOwND5LV165NRVPiBZfoY9ZAYbAG2ULJ+/U9u6GhwIDAQABo1Aw\r\n"
			+ "TjAdBgNVHQ4EFgQUdWohQrxIy6plnvCM7iLEH/73r6wwHwYDVR0jBBgwFoAUdWoh\r\n"
			+ "QrxIy6plnvCM7iLEH/73r6wwDAYDVR0TBAUwAwEB/zANBgkqhkiG9w0BAQsFAAOC\r\n"
			+ "AgEAoWkc4HMZoyx1hHwVSTTeAEq1TvceQShrjIE/dSkbOeMMLD51zumrLc5qBD3/\r\n"
			+ "i4NWvqd/CdEBeucr2XibMaeY7TILM6RIKdXpmnKZNC4mUfc9dE6lr3smV//CZ3dd\r\n"
			+ "MWhWG+EhnHwYeqnPWYw1oK0IT+FylCttArL5xP8uoZ9Ff5OvqTtt8ArlfFJNFYnj\r\n"
			+ "7lmv0CTKxKoMVx8RoB7uh/LRBXybKSAVn7a8JFtpQdZKK/KY93Jby5Wz14I0gRun\r\n"
			+ "uVKoH0sw3n4bQtsQW5aIPrBheXfIu2Um3gsxHnNTCmdKFowmhuyMKZwm5Un1Yh/B\r\n"
			+ "UWPtCueKqYM+Wzixcy2+Xn5+60D4HtK9z5e2t3cn0HY+Hl5oGdhRQdTWEuEMXWNZ\r\n"
			+ "SYk8ZG0xY7+9qu5XSrhghaoFJIw/hF1X2HFksyzjI0GjDMH5AeCmEPFIKFdaM84x\r\n"
			+ "hoFGy4egkouVlBgFfag14ycEGe9JqYmAr5aWv2kviCqkOUmqw6BqVWsDuIPYIybt\r\n"
			+ "1l5z/piHGj0ZndbB1bE8Yb/F/lPvaGZ8TthoqxSd7qAi7AzvpOeokGA8oSamBOPi\r\n"
			+ "FQzqs3lW2XwDbDySEQCN8J53bktClXjrML55E8TVins2Oyxv4kHwfPbAJA7kuJny\r\n"
			+ "mrx1o9AOmKoPiyhQlixkNnM4WnHExmYGrMHOlIiKkWHtAUU=";

	private static  String bankUatPrivateKey = "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\r\n"
			+ "5Yn5NvCHvgxTJWKAqhXquPOoI+wDjQ==";

	private static String oplQaPublickey = "MIIGMzCCBBugAwIBAgIJAP8i9EfvB71GMA0GCSqGSIb3DQEBCwUAMIGvMQswCQYD\r\n"
			+ "VQQGEwJpbjEPMA0GA1UECAwGZ3VqcmF0MRIwEAYDVQQHDAlhaG1lZGFiYWQxGzAZ\r\n"
			+ "BgNVBAoMEm9ubGluZXBzYmxvYW5zLmNvbTESMBAGA1UECwwJZGV2ZWxvcGVyMRsw\r\n"
			+ "GQYDVQQDDBJvbmxpbmVwc2Jsb2Fucy5jb20xLTArBgkqhkiG9w0BCQEWHmFua2l0\r\n"
			+ "Lmd1cHRhQG9ubGluZXBzYmxvYW5zLmNvbTAeFw0yMDA0MTcxMDM4MzhaFw0zMDA0\r\n"
			+ "MTUxMDM4MzhaMIGvMQswCQYDVQQGEwJpbjEPMA0GA1UECAwGZ3VqcmF0MRIwEAYD\r\n"
			+ "VQQHDAlhaG1lZGFiYWQxGzAZBgNVBAoMEm9ubGluZXBzYmxvYW5zLmNvbTESMBAG\r\n"
			+ "A1UECwwJZGV2ZWxvcGVyMRswGQYDVQQDDBJvbmxpbmVwc2Jsb2Fucy5jb20xLTAr\r\n"
			+ "BgkqhkiG9w0BCQEWHmFua2l0Lmd1cHRhQG9ubGluZXBzYmxvYW5zLmNvbTCCAiIw\r\n"
			+ "DQYJKoZIhvcNAQEBBQADggIPADCCAgoCggIBALUGfWsAlo2Ccgi0390vYjmuEDhA\r\n"
			+ "dJGPtss2nk98wa3HFexJrIrxCHhsC/cdoyPvFgemqA4c1BhD+fdGHt4zCIUy7gWF\r\n"
			+ "Q50KVcYvlP7Nqme1YS56ldyJgxqUNuhG4/9vy8N0A69adZ11PslYhtq9zER12BkY\r\n"
			+ "YY9L9Nw5AaEpTpla9en/hSY3VwVYOwpeO+EYeiVvJOVVbUQJEs2i3wqdSo/AqT2W\r\n"
			+ "KWNbfruAo4PUgDROlf8et1v5sKcxS/U8hOkKTNSaHuLKQui4IFmHBwCUjx9Rv/un\r\n"
			+ "Gubn59p+0lx+ujtoOgb0VrJetj4y57S+cpOW+/OFjak4WqKflOhtNxmTsi1HBnTw\r\n"
			+ "WH3hqoOc1fqsltG+CRmjJzUOrtIX2prpaZH773JZsnULHedSTFNmclR++YnSOhdo\r\n"
			+ "pREKhNIxskNoRjCtHa3PzVKm/vbQyyjBla7hy+QK9zLRLRgss7WR1QRkbSJ2kjxX\r\n"
			+ "MM+7Tv7U4yC3cxmhc0yjvItRHhVpT5jVf5K0oMB6tyD3SJFb9IJ+1g2rtPINEVFU\r\n"
			+ "dH7tnx76SQg4m7A+oJ68NW/r8uEGA8/bXzFDiFxq5neTs3WlR9sM/usjO+Pj2bFJ\r\n"
			+ "tx/disQ/BS830Opdj50/mnGZ+A3leOmHfFGo1DvSqz1Ajs1clcaprMeN7sKJJ4S+\r\n"
			+ "mlq+dbVT4WD2MRafAgMBAAGjUDBOMB0GA1UdDgQWBBQ6Mi6aMuVymBZ068s+44Zb\r\n"
			+ "xEBlBDAfBgNVHSMEGDAWgBQ6Mi6aMuVymBZ068s+44ZbxEBlBDAMBgNVHRMEBTAD\r\n"
			+ "AQH/MA0GCSqGSIb3DQEBCwUAA4ICAQBccWYRVBhmzCWyOfQ44ih/uTiYcfjxe0H9\r\n"
			+ "FKhZoHj91jaGFH17GQgRT4SVujFxrA+tbA+fh95CBYnQjSHOTLDdFyUdHFLooTHO\r\n"
			+ "lbyFAg7oeM/u/fZLLbDTarnp+nKH6rEwdOExv40NzRozJCT8IzG2U5lwf5btgAia\r\n"
			+ "XLBMsbCKWLygHB/rarMcw4OMbq80JQVmgKyeiMC9iAVOs4dkXDB/QhhewXz/7D5c\r\n"
			+ "pa4rB5vF7dkl6NOgsYeJhtlODBOJiX8ngW9dyX2sOerW3DK7jhrU07L24xQV3vVU\r\n"
			+ "C24KLtjqQcReUOgi09LBhplvND5lBp7GFIUmrFWi8CWdMGL3S/knbq1YlxO5x5hY\r\n"
			+ "QufPYxX173inxBy2iTp88eEGO9bmpyt3udkif3hU+SdTsKt3AHLZ2vhw/KvEQorF\r\n"
			+ "YjxzciZQvgRQcgOBTDqJyJsMNVmi79Bm1VI9j+HvsjrvsCXxYacMJ0Nupzxq3OMc\r\n"
			+ "yjDrGNgRAv+lymZApAEapDfxooy37rRPDCvZMho0LmZ2jvKP+vOzpq1sEcjp26I3\r\n"
			+ "/989BcLUEAti9fXwq8AJl7eaYuctuwQVHVUdl2metPSeSkafp4FrNW+CufeNGnbm\r\n"
			+ "jMXV8DbUugNtJIfXTzgCYbOXhpcwKzXyzuC71TzENTv0NI3wdJSTyA7p5qUsxOkA\r\n" + "aS+3IjKz9A==";

	private static  String oplQaPrivatekey = "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\r\n"
			+ "uRYQ0FgM0Ni1v6m9EypzcMMbS8LY2Pu8";*/

	private static byte[] encryptRequestBodyAES256(byte[] plaintext, byte[] key, byte[] IV) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{
		// Get Cipher Instance
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

		// Create SecretKeySpec
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

		// Create IvParameterSpec
		IvParameterSpec ivSpec = new IvParameterSpec(IV);

		// Initialize Cipher for ENCRYPT_MODE
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

		// Perform Encryption
		byte[] cipherText = cipher.doFinal(plaintext);

		return cipherText;
	}

	// public  String decrypt (byte[] cipherText, SecretKey key,byte[] IV)
	// throws Exception
	private static String decryptRequestBody(byte[] cipherText, byte[] key, byte[] IV) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException  {
		// Get Cipher Instance
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

		// Create SecretKeySpec
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

		// Create IvParameterSpec
		IvParameterSpec ivSpec = new IvParameterSpec(IV);

		// Initialize Cipher for DECRYPT_MODE

		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

		// Perform Decryption
		byte[] decryptedText = cipher.doFinal(cipherText);

		return new String(decryptedText);
	}

	private static String signSHA256RSA(String input, String strPk) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException  {
		// Remove markers and new line characters in private key
		String realPK = strPk.replaceAll("-----END PRIVATE KEY-----", "").replaceAll("-----BEGIN PRIVATE KEY-----", "")
				.replaceAll("\n", "");

		// byte[] b1 = Base64.getDecoder().decode(realPK);
		byte[] b1 = DatatypeConverter.parseBase64Binary(realPK);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);
		KeyFactory kf = KeyFactory.getInstance("RSA");

		Signature privateSignature = Signature.getInstance("SHA256withRSA");
		privateSignature.initSign(kf.generatePrivate(spec));
		byte[] bytes = input.getBytes("UTF-8");
		// System.out.println("data before signed : " + input);
		privateSignature.update(bytes);
		byte[] s = privateSignature.sign();
//		input = Base64.getEncoder().encodeToString(bytes);
//		System.out.println("data After signed : " + input);

		return Base64.getEncoder().encodeToString(s);
	}

	private static PublicKey getPublicKey(String publickeyCert) {
		PublicKey publicKey = null;
		try {
			CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
			BufferedReader br = new BufferedReader(new StringReader(publickeyCert));
			String line = null;
			StringBuilder keyBuffer = new StringBuilder();
			while ((line = br.readLine()) != null) {
				if (!line.startsWith("-")) {
					keyBuffer.append(line);
				}
			}
			Certificate certificate = certificateFactory
					.generateCertificate(new ByteArrayInputStream(Base64.getDecoder().decode(keyBuffer.toString())));
			publicKey = certificate.getPublicKey();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return publicKey;
	}

	private static String decSignSHA256RSA(String encryptionRequestBody, String digitalSignature, String strPk) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, SignatureException {
		String k = strPk.replaceAll("\n", "");
		Signature privateSignature = Signature.getInstance("SHA256withRSA");

		privateSignature.initVerify(getPublicKey(k));

		byte[] bytes = encryptionRequestBody.getBytes("UTF-8");

		// byte[] bytes = Base64.getDecoder().decode(data);
		privateSignature.update(bytes);
		// System.out.println("varify : " + privateSignature.verify(Base64.getDecoder().decode(digitalSignature)));

		return encryptionRequestBody;
	}

	/*static String plainText = "{\"applicationId\": 10007729, \"userName\": \"cw\", \"pass\": \"pwd@4mbc\", \"userId\": 63961, \"id\": null, \"clientId\": null, \"pan\": null, \"orgId\": 16, \"dpdDays\": null, \"provider\": null, \"businessTypeId\": null, \"coApplicantId\": null, \"xmlResponseExternal\": null, \"dataInput\": null, \"isNbfcUser\": false, \"loanApplicationCreatedDate\": null, \"applicantRequest\": {\"id\": 15958, \"applicationId\": null, \"panNo\": \"AAGFV5271N\", \"constitutionId\": 5, \"organisationName\": \"V M SHAH   CO\", \"firstAddress\": {\"city\": \"Bhavnagar\", \"countryId\": null, \"landMark\": \"WAGHAWADI\", \"pincode\": \"364001\", \"premiseNumber\": \"plot 103\", \"stateId\": 12, \"cityId\": 821, \"streetName\": \"WAGHAWADI ROAD\", \"stateCode\": null, \"state\": \"Gujarat\", \"district\": null, \"subDistrict\": null, \"village\": null, \"districtMappingId\": null }, \"orgId\": null, \"xmlResponseExternal\": null, \"loanApplicationCreatedDate\": 1593161906000, \"directorBackgroundDetailRequestsList\": [{\"id\": 33084, \"address\": {\"city\": \"Bhavnagar\", \"countryId\": null, \"landMark\": \" MIRA PARK BHILWADA CIRCLE\", \"pincode\": \"364001\", \"premiseNumber\": \"PLOT NO 548\", \"stateId\": 12, \"cityId\": 821, \"streetName\": \"NEW MANEKWADI\", \"stateCode\": null, \"state\": \"Gujarat\", \"district\": null, \"subDistrict\": null, \"village\": null, \"districtMappingId\": null }, \"applicationId\": null, \"salutationId\": null, \"panNo\": \"AZJPB1058R\", \"directorsName\": \"HATIM ASGARALI BATTIWALA\", \"isActive\": true, \"stateCode\": \"11\", \"dob\": 706492800000, \"dobString\": null, \"mobile\": \"9798797798\", \"gender\": 2, \"firstName\": \"HATIM\", \"lastName\": \"BATTIWALA\", \"middleName\": \"ASGARALI\", \"title\": null, \"email\": null, \"xmlResponseExternal\": null, \"nbfcUser\": false }, {\"id\": 33085, \"address\": {\"city\": \"BHAVNAGAR \", \"countryId\": null, \"landMark\": \"RADHAAPARTMENT\", \"pincode\": \"364001\", \"premiseNumber\": \"Plot 103\", \"stateId\": 12, \"cityId\": 47577, \"streetName\": \"RADHAAPARTMENT\", \"stateCode\": null, \"state\": \"Gujarat\", \"district\": null, \"subDistrict\": null, \"village\": null, \"districtMappingId\": null }, \"applicationId\": null, \"salutationId\": null, \"panNo\": \"AMKPM4281B\", \"directorsName\": \"REEMA JINAND SHAH\", \"isActive\": true, \"stateCode\": \"11\", \"dob\": 566611200000, \"dobString\": null, \"mobile\": \"7987977797\", \"gender\": 3, \"firstName\": \"REEMA\", \"lastName\": \"SHAH\", \"middleName\": \"JINAND\", \"title\": null, \"email\": null, \"xmlResponseExternal\": null, \"nbfcUser\": false } ], \"incorporationDate\": null, \"industryType\": null, \"landlineNo\": null, \"nbfcUser\": false }, \"isLoansEnable\": true }";

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, SignatureException, IOException {

		EncryptionDetail encryptionDetail = encrypt(plainText, oplQaPrivatekey, bankUatPublickey);
		
		String jsonString = MultipleJSONObjectHelper.getStringfromObject(encryptionDetail);
		
		System.out.println("jsonString ====> "+jsonString);
		
		String encodedString = Base64.getEncoder().encodeToString(jsonString.getBytes());
		
		System.out.println("Base64 encodedString ====> "+encodedString);
		
		String a = decrypt(encodedString, bankUatPrivateKey, oplQaPublickey);
		System.err.println("===> output : " + a);
	}*/

	/**
	 * This method is used to encrypt the string , passed to it using a public key
	 * provided
	 *
	 * @param plaintext To Encrypt : Text to encrypt
	 * @return :encrypted string
	 */
	private static String getEncryptHeader(String data, String publicKetCrt) {
		try {
			byte[] plaintext = data.getBytes();
			PublicKey key = getPublicKey(publicKetCrt);
			// readPublicKey("D:\\cw-fork\\service-payment-collection\\service-payment-collection\\template\\QA_ICICI_PAYCOLL-API-TEST-0.1
			// (1).crt");

			// PublicKey key =
			// readPublicKey("/apps/services/service-payment-collection/template/QA_ICICI_PAYCOLL-API-TEST-0.1
			// (1).crt");
			Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPPADDING");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptedByte = cipher.doFinal(plaintext);
			String encodedString = Base64.getEncoder().encodeToString(encryptedByte);
			return encodedString;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private static String decryptHeader(String data, String privatekey) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException  {

		byte[] encryptedData = Base64.getDecoder().decode(data);
		PrivateKey privateKey = getPrivatekey(privatekey);
		Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPPADDING");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] encryptedByte = cipher.doFinal(encryptedData);

		return new String(encryptedByte);
	}

	private static PrivateKey getPrivatekey(String publicKey) {

		PrivateKey privateKey = null;
		KeyFactory keyFactory = null;

		byte[] encoded = DatatypeConverter.parseBase64Binary(publicKey);
		// byte[] decode = Base64.getDecoder().decode(base64PrivateKey);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
		try {
			keyFactory = KeyFactory.getInstance("RSA");
			privateKey = keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return privateKey;
	}

	private static byte[] getEncoded() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		// keyGenerator.init(256);
		keyGenerator.init(256);
		// Generate Key
		SecretKey key = keyGenerator.generateKey();
		//System.out.println("Original Text  : " + plainText);
		byte[] encoded = key.getEncoded();
		//String keys = Base64.getEncoder().encodeToString(encoded);
		//System.out.println("Original Key : " + keys);
		return encoded;
	}

	public static EncryptionDetail encrypt(String plainText, String oplPrivateKey, String bankPublickey) throws NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException  {
		EncryptionDetail encryptionDetail = new EncryptionDetail();


		// Start Ecnryption
		// 1 . Plain Text
		byte[] encoded = getEncoded();
		byte[] iv = getIVFromAESKey(encoded);

		// 2) Encrypt the plain request with above AES 256 encryption key.
		byte[] encrypt = encryptRequestBodyAES256(plainText.getBytes(), encoded, iv);
		String encryptionRequestBody = Base64.getEncoder().encodeToString(encrypt);

		// 3) SHA256-RSA algorithm has to be used to sign the plain request payload
		// (Digital Signature)with the help of consuming server's Private Key.
		String digitalSignature = signSHA256RSA(encryptionRequestBody, oplPrivateKey);
//		
		// 4) RSA algorithm has to be used to encrypt the above AES 256 encryption key
		// with the help of Destination server's Public Key.
		// EncryptionUtil ec = new EncryptionUtil();
		// String haderKey = encrypt(keys, oplQaPublickey);
		String keys = Base64.getEncoder().encodeToString(encoded);
		String haderKey = getEncryptHeader(keys, bankPublickey);
		// System.out.println("req header key : " + haderKey);
		encryptionDetail.setHeaderKey(haderKey);
		encryptionDetail.setDigitalSignature(digitalSignature);
		encryptionDetail.setEncryptionRequestBody(encryptionRequestBody);
		// End Ecnryption
		
		// System.out.println("encryptionDetail ====> "+encryptionDetail);
		
		return encryptionDetail;
	}

	/*private static byte[] getIV() {
		// Generating IV.
		byte[] IV = new byte[16];
		SecureRandom random = new SecureRandom();
		random.nextBytes(IV);
		return IV;
	}*/
	
	private static byte[] getIVFromAESKey(byte[] encoded) {
		// Generating IV by SBI team given logic
		return Arrays.copyOfRange(encoded, 0, 16);
	}

	public static String decrypt(String requestBody, String oplPrivateKey, String bankPublicKey) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, SignatureException, InvalidAlgorithmParameterException, IOException {
		
		byte[] decodedBytes = Base64.getDecoder().decode(requestBody);
		String decodedString = new String(decodedBytes);
		
		EncryptionDetail encryptionDetail = MultipleJSONObjectHelper.getObjectFromString(decodedString, EncryptionDetail.class);
		
		String haderKey = encryptionDetail.getHeaderKey();
		String digitalSignature = encryptionDetail.getDigitalSignature(); 
		String encryptionRequestBody = encryptionDetail.getEncryptionRequestBody();
		
		// Start Decryption
		// 4) RSA algorithm has to be used to decrypt the above AES 256 decryption key  with the help of Destination server's private Key.
		String decHeadr = decryptHeader(haderKey, oplPrivateKey);
		// System.out.println("header key : " + decHeadr);
		byte[] aesKey = Base64.getDecoder().decode(decHeadr);
		byte[] iv = getIVFromAESKey(aesKey);

		// 3) SHA256-RSA algorithm has to be used to sign the plain request payload (Digital Signature)with the help of consuming server's Private Key.
		String decrypt = decSignSHA256RSA(encryptionRequestBody, digitalSignature, bankPublicKey);
		//System.out.println("Rec body : " + decrypt);

		// 2) Encrypt the plain request with above AES 256 encryption key.
		return decryptRequestBody(Base64.getDecoder().decode(decrypt), aesKey, iv);
	}

}


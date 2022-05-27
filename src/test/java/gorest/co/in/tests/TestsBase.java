package gorest.co.in.tests;

import gorest.co.in.tests.controller.Controllers;
import gorest.co.in.tests.controller.LoggerControllerImpl;
import org.junit.runner.JUnitCore;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class TestsBase extends IOException {

	protected static String SUFFIX_GET_LIST_OF_USERS;
	protected static String PATH_TO_HTTPS_SERVER;

	protected static String BEARER_TOKEN = "Bearer 53832 ...."; //secret

	protected static Boolean debugMode = false;
	protected Controllers controllers = new Controllers(new LoggerControllerImpl(debugMode));

	public static void main(String[] args) throws Exception {
		PATH_TO_HTTPS_SERVER = getNodeValue("domain");
		SUFFIX_GET_LIST_OF_USERS = getNodeValue("endpoint");

		BEARER_TOKEN = tokenDecrypt();

		JUnitCore junit = new JUnitCore();
		junit.run(Tests.class);
	}

	protected static String tokenDecrypt() throws Exception {
		byte[] secretKey = getNodeValue("secretKey").getBytes(); // 24 bytes
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "TripleDES");
		byte[] iv = getNodeValue("iVector").getBytes(); // 8 bytes
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		byte[] encryptedMessageBytes = Base64.getDecoder().decode(getNodeValue("token"));
		Cipher decryptCipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
		decryptCipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
		byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
		return new String(decryptedMessageBytes, StandardCharsets.UTF_8);
	}

	//setup from env.xml file
	protected static String getNodeValue(String paramName) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(new File("env.xml"));
		doc.getDocumentElement().normalize();
		return doc.getElementsByTagName(paramName).item(0).getTextContent();
	}
}
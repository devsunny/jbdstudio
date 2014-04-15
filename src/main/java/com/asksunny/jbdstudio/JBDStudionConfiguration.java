package com.asksunny.jbdstudio;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.Security;
import java.util.HashMap;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class JBDStudionConfiguration extends HashMap<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String PROP_SSL_KEY_FILE = "jbdstudio.ssl.keyStore";
	public final static String PROP_SSL_KEY_PASSWORD = "jbdstudio.ssl.keyStorePassword";
	public final static String PROP_SSL_CERT_PASSWORD = "jbdstudio.ssl.certPassword";

	public final static String PROP_SSL_TRUST_FILE = "jbdstudio.ssl.trustStore";
	public final static String PROP_SSL_TRUST_PASSWORD = "jbdstudio.ssl.trustStorePassword";

	public final static String SYS_PROP_SSL_KEY_FILE = "javax.net.ssl.keyStore";
	public final static String SYS_PROP_SSL_KEY_PASSWORD = "javax.net.ssl.keyStorePassword";
	public final static String SYS_PROP_SSL_TRUST_FILE = "javax.net.ssl.trustStore";
	public final static String SYS_PROP_SSL_TRUST_PASSWORD = "javax.net.ssl.trustStorePassword";
	private static final String PROTOCOL = "TLS";

	public String getOption(String... keys) {
		String optVal = null;
		for (String key : keys) {
			optVal = get(key);
			if (optVal != null)
				break;
		}
		return optVal;
	}

	public SSLContext getSSLContext() {
		SSLContext sslContext = null;

		try {
			String algorithm = Security
					.getProperty("ssl.KeyManagerFactory.algorithm");
			if (algorithm == null) {
				algorithm = "SunX509";
			}
			
			KeyManager[]  kms = null;

			String keyFile = getOption(PROP_SSL_KEY_FILE, SYS_PROP_SSL_KEY_FILE);
			if (keyFile != null) {
				KeyStore ks = KeyStore.getInstance("JKS");
				String keyPassword = getOption(PROP_SSL_KEY_PASSWORD,
						SYS_PROP_SSL_KEY_PASSWORD);
				FileInputStream fin = new FileInputStream(keyFile);
				ks.load(fin, keyPassword.toCharArray());
				fin.close();
				String certPass = getOption(PROP_SSL_CERT_PASSWORD);
				
				KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
				kmf.init(ks, certPass.toCharArray());
			}

			TrustManager[] tms = null;
			
			String trustFile = getOption(PROP_SSL_TRUST_FILE,
					SYS_PROP_SSL_KEY_FILE);			
			if (trustFile != null) {
				KeyStore ts = KeyStore.getInstance("JKS");
				String trustPassword = getOption(PROP_SSL_TRUST_PASSWORD,
						SYS_PROP_SSL_TRUST_PASSWORD);
				FileInputStream fin = new FileInputStream(trustFile);
				ts.load(fin, trustPassword.toCharArray());
				fin.close();
				TrustManagerFactory tmf = TrustManagerFactory.getInstance(
						algorithm, "SunJSSE");
				tmf.init(ts);
				tms = tmf.getTrustManagers();
			}
			sslContext = SSLContext.getInstance(PROTOCOL);
			sslContext.init(kms, tms, null);
		} catch (Exception e) {
			throw new Error("Failed to initialize the server-side SSLContext", 	e);
		}

		return sslContext;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}

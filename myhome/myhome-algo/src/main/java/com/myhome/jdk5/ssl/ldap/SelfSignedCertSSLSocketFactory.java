package com.myhome.jdk5.ssl.ldap;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * How to accept self-signed certificates for JNDI/LDAP connections?
 * 
 * 
 * 
 */
public class SelfSignedCertSSLSocketFactory extends SSLSocketFactory {
	private SSLSocketFactory socketFactory;

	public SelfSignedCertSSLSocketFactory() {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[] { new DummyTrustmanager() },
					new SecureRandom());
			socketFactory = ctx.getSocketFactory();
		} catch (Exception ex) {
			ex.printStackTrace(System.err); /* handle exception */
		}
	}

	public static SocketFactory getDefault() {
		return new SelfSignedCertSSLSocketFactory();
	}

	@Override
	public String[] getDefaultCipherSuites() {
		return socketFactory.getDefaultCipherSuites();
	}

	@Override
	public String[] getSupportedCipherSuites() {
		return socketFactory.getSupportedCipherSuites();
	}

	@Override
	public Socket createSocket(Socket socket, String string, int i, boolean bln)
			throws IOException {
		return socketFactory.createSocket(socket, string, i, bln);
	}

	@Override
	public Socket createSocket(String string, int i) throws IOException,
			UnknownHostException {
		return socketFactory.createSocket(string, i);
	}

	@Override
	public Socket createSocket(String string, int i, InetAddress ia, int i1)
			throws IOException, UnknownHostException {
		return socketFactory.createSocket(string, i, ia, i1);
	}

	@Override
	public Socket createSocket(InetAddress ia, int i) throws IOException {
		return socketFactory.createSocket(ia, i);
	}

	@Override
	public Socket createSocket(InetAddress ia, int i, InetAddress ia1, int i1)
			throws IOException {
		return socketFactory.createSocket(ia, i, ia1, i1);
	}

	public static void main(String[] args) {

		Hashtable env = new Hashtable(11);
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldaps://ldapserver:636/");
		env.put(Context.SECURITY_PROTOCOL, "ssl");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "myUser");
		env.put(Context.SECURITY_CREDENTIALS, "myPassword");
		env.put("java.naming.ldap.factory.socket",
				"com.mycompagny.brt.test.ldap.SelfSignedCertSSLSocketFactory");
		try {
			LdapContext ctx = new InitialLdapContext(env, null);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public class DummyTrustmanager implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] xcs, String string)
				throws CertificateException {
			// do nothing
		}

		public void checkServerTrusted(X509Certificate[] xcs, String string)
				throws CertificateException {
			// do nothing
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new java.security.cert.X509Certificate[0];
		}
	}
}

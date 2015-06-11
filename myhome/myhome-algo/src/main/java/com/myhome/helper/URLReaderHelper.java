package com.myhome.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class URLReaderHelper {

	/** The log. */
	
	public static String read(String urlAsString, String proxyHost,
			int proxyPort) {
		
		
//		System.setProperty("http.proxyHost", "PROXY_ADDRESS");
//		System.setProperty("http.proxyPort", "PORT_NUMBER");
//		System.setProperty("http.proxyUser", "USERNAME");
//		System.setProperty("http.proxyPassword", "PASSWORD");
//		URL url = new URL("http://mysite.com");
//		HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
		
		StringBuilder content = new StringBuilder();
		
		//Set parameters
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, 10000);
		HttpConnectionParams.setSoTimeout(httpParameters, 10000);

		if (proxyHost != null && proxyHost.trim().length() > 0) {
			HttpHost proxy = new HttpHost(proxyHost, proxyPort);
			httpParameters.setParameter(
					ConnRoutePNames.DEFAULT_PROXY, proxy);
		}
		
		DefaultHttpClient httpclient = new DefaultHttpClient(httpParameters);
		try {

			httpclient = (DefaultHttpClient) WebClientDevWrapper
					.wrapClient(httpclient);

			HttpGet httpget = new HttpGet(urlAsString);
			System.out.println("executing request" + httpget.getRequestLine());

			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				BufferedReader in = null;

				in = new BufferedReader(new InputStreamReader(
						entity.getContent()));
				String decodedString;
				while ((decodedString = in.readLine()) != null) {
					if (decodedString != null) {
						content.append(decodedString);
					}
				}

				System.out.println(" ==> response " + response.getStatusLine()
						+ ",  content = " + content.toString());

				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(
							"site.html"));
					out.write(content.toString());
					out.close();
				} catch (IOException e) {
					System.out.println(e);
				}

			}
			EntityUtils.consume(entity);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
		return content.toString();
	}

	public void test(String url) {
		long token = System.currentTimeMillis();

		// System.setProperty("sun.security.ssl.allowUnsafeRenegotiation",
		// "true");

		DefaultHttpClient dhc = new DefaultHttpClient();

		SSLContext sc;
		try {
			sc = SSLContext.getInstance("TLS");

			X509TrustManager tm = new X509TrustManager() {

				public void checkClientTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			sc.init(null, new TrustManager[] { tm }, null);

			SSLSocketFactory ssf = new SSLSocketFactory(sc);
			ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			// SSLSocketFactory ssf = new SSLSocketFactory(keyStore,
			// KEYSTORE_PASS, null);
			Scheme s = new Scheme("https", ssf, 443);
			ClientConnectionManager ccm = dhc.getConnectionManager();
			ccm.getSchemeRegistry().register(s);

			HttpGet hp = new HttpGet(url);

			HttpResponse hr = dhc.execute(hp);

			System.out.println("sendMessage() " + token + " response -> "
					+ hr.getStatusLine());
			System.out.println("sendMessage() " + token + " response -> "
					+ hr.getEntity().getContent().toString());

			ccm.shutdown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

//		 URLReaderHelper
//		 .read("http://10.3.0.23:7101/stportalWeb/appmanager/stportal/desktop",
//		 "proxy-fr-croissy.dem.com", 8080);
//		
//		 URLReaderHelper
//		 .read("https://easyit.dem.com/MRcgi/MRhomepage.pl",
//		 "proxy-fr-croissy.dem.com", 8080);
//		//

		if (args.length == 1) {
			read(args[0], null, -1);
		} else if (args.length == 2) {
			URLReaderHelper.read(args[0], args[1], 8080);
		} else if (args.length == 3) {

			if (args[2] != null && args[2].trim().length() > 0) {
				try {
					int port = Integer.valueOf(args[2]);
					URLReaderHelper.read(args[0], args[1], port);
				} catch (NumberFormatException e) {
					System.out.println("Error=> Bad port");
				}
			}
		} else {
			System.out
					.println("Please enter url, proxy host, proxy port (default 8080) as arguments");
			System.out.println("Example: ");
			System.out
					.println("java -jar testurl.jar https://www.aim.dem.com");
			System.out
					.println("java -jar testurl.jar https://www.aim.dem.com proxy-frfdssy.dem.com 8080");

		}

	}
}

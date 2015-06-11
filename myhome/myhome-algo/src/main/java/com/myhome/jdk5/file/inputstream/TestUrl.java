 
package com.myhome.jdk5.file.inputstream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;

public class TestUrl {

    public static void main(String[] args) {
        URL yahooURL;
        try {
            yahooURL = new URL("http://www.yahoo.com");

            SocketAddress sockaddr = new InetSocketAddress(
                    "wpad.ds/wpad.dat", 8080);

            Proxy p = new Proxy(Proxy.Type.HTTP, sockaddr);
            URLConnection yahooConnection = yahooURL.openConnection(p);
            System.out.println(yahooConnection);
            System.out.println(yahooURL.openStream());

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        String url = "http://www.google.fr/";

        String line;
        String delivery_line = "";
        boolean sms_sent_success;
        BufferedReader in;

        try {
            in = new BufferedReader(new InputStreamReader(new URL(url)
                    .openStream()));

            do {
                try {
                    line = in.readLine();
                    if (line != null) {
                        delivery_line += line + "\n";
                        if (line.indexOf("success") != -1) {
                            sms_sent_success = true;
                            line = null;
                        }
                    }
                } catch (Exception e3) {
                    System.out.println("Error reading from response site:"
                            + e3);
                    line = null;
                }
            } while (line != null);

        } catch (Exception e2) {
            System.out
                    .println("Unable to open inputstream to response url:"
                            + e2);
        }

        try {
            // The file may or may not exist
            URL myUrl = new URL(url);
            System.out.println("The url is" + url);

            InputStream is = myUrl.openStream();

            byte[] binary = InputstreamToByteArray
                    .convertInputstreamToByteArray(is);

            // Set resource
            System.out.println(binary.toString());

        }

        catch (MalformedURLException e) {
            System.out.println("Don't worry,exception has been caught"
                    + e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage() + " " + e.getCause());

        }
    }

}

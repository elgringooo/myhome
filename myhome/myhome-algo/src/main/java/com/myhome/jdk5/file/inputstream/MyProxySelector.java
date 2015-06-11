/* 
 * @(#)MyProxySelector.java
 *
 * Copyright (c) 2008 DCN SA. All rights reserved.
 * DCN PROPRIETARY/CONFIDENTIAL.  Use is subject to license terms.
 *
 * This file, together  with  its accompanying  software product  and
 * documentation, is  protected by the  intellectual  property rights
 * in  France  and  other  countries, any  applicable  copyrights  or
 * patent rights, and international treaty provisions. No part may be
 * reproduced  in  any  form  by  any  mean  without   prior  written
 * authorization of DCN.
 */
package com.myhome.jdk5.file.inputstream;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;

public class MyProxySelector extends ProxySelector {
    ProxySelector defsel = null;
    MyProxySelector(ProxySelector def) {
        defsel = def;
    }
    
    public java.util.List<Proxy> select(URI uri) {
        if (uri == null) {
            throw new IllegalArgumentException("URI can't be null.");
        }
        String protocol = uri.getScheme();
        if ("http".equalsIgnoreCase(protocol) ||
            "https".equalsIgnoreCase(protocol)) {
            ArrayList<Proxy> l = new ArrayList<Proxy>();
            // Populate the ArrayList with proxies
            return l;
        }
        if (defsel != null) {
            return defsel.select(uri);
        } else {
            ArrayList<Proxy> l = new ArrayList<Proxy>();
            l.add(Proxy.NO_PROXY);
            return l;
        }
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
        if (uri == null || sa == null || ioe == null) {
            throw new IllegalArgumentException("Arguments can't be null.");
        }
//        InnerProxy p = proxies.get(sa); 
//        if (p != null) {
//            if (p.failed() >= 3)
//                proxies.remove(sa);
//        } else {
//            if (defsel != null)
//                defsel.connectFailed(uri, sa, ioe);
//        }
        
    }
    public static void main(String[] args) {
   
    }
}
package com.myhome.jdk5.ssl.ldap;
/**
 * searchssl.java
 * 5 July 2001
 * December 2004 - noted that ldaps url now supported on JSDK 1.5.0
 * Sample JNDI application to perform a search for against the Active Directory
 * over SSL (port 636)
 */
 
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
 
 
public class Searchssl
{
	public static void main (String[] args)
	{
	
		Hashtable env = new Hashtable();
		
		String adminName = "CN=Administrator,CN=Users,DC=ANTIPODES,DC=COM";
		String adminPassword = "XXXXXXX";
		String ldapURL = "ldap://mydc.antipodes.com:636";
 
		//Access the keystore, this is where the Root CA public key cert was installed
		//Could also do this via the command line option java -Djavax.net.ssl.trustStore....
		//No need to specifiy the keystore password for read operations
		String keystore = "/usr/java/j2sdk1.4.0/jre/lib/security/cacerts";
		System.setProperty("javax.net.ssl.trustStore",keystore);
		
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		
		//set security credentials
		env.put(Context.SECURITY_AUTHENTICATION,"simple");
		env.put(Context.SECURITY_PRINCIPAL,adminName);
		env.put(Context.SECURITY_CREDENTIALS,adminPassword);
 
		//specify use of ssl
		env.put(Context.SECURITY_PROTOCOL,"ssl");
				
		//connect to my domain controller
		env.put(Context.PROVIDER_URL,ldapURL);
		try {
 
			// Create the initial directory context
			DirContext ctx = new InitialLdapContext(env,null);
		
			//Create the search controls 		
			SearchControls searchCtls = new SearchControls();
		
			//Specify the attributes to return
			String returnedAtts[]={"sn","givenName","mail"};
			searchCtls.setReturningAttributes(returnedAtts);
		
			//Specify the search scope
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
 
			//specify the LDAP search filter
			String searchFilter = "(&(objectClass=user)(mail=*))";
 
			//Specify the Base for the search
			String searchBase = "DC=ANTIPODES,DC=COM";
 
			//initialize counter to total the results
			int totalResults = 0;
 
 
			// Search for objects using the filter
			NamingEnumeration answer = ctx.search(searchBase, searchFilter, searchCtls);
 
			//Loop through the search results
			while (answer.hasMoreElements()) {
		    		SearchResult sr = (SearchResult)answer.next();
 
				totalResults++;
 
				System.out.println(">>>" + sr.getName());
 
				// Print out some of the attributes, catch the exception if the attributes have no values
				Attributes attrs = sr.getAttributes();
				if (attrs != null) {
					try {
					System.out.println("   surname: " + attrs.get("sn").get());
					System.out.println("   firstname: " + attrs.get("givenName").get());
					System.out.println("   mail: " + attrs.get("mail").get());
 
					} 
					catch (NullPointerException e)	{
					System.out.println("Errors listing attributes: " + e);
					}
				}
 
			}
 
 			System.out.println("Total results: " + totalResults);
			ctx.close();
 
		} 
		catch (NamingException e) {
			System.err.println("Problem searching directory: " + e);
		}
	}
}
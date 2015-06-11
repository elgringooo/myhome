 
package com.myhome.jdk5.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class TestLDAP {

    static void printAttrs(Attributes attrs) {
        if (attrs == null) {
            System.out.println("No attributes");
        } else {
            /* Print each attribute */
            try {
                for (NamingEnumeration ae = attrs.getAll(); ae.hasMore();) {
                    Attribute attr = (Attribute) ae.next();
                    System.out.print("attribute: " + attr.getID());

                    /* print each value */
                    for (NamingEnumeration e = attr.getAll(); e.hasMore(); System.out
                            .println(" => value: " + e.next()))
                        ;
                }
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://itemvm-56346a:389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL,
                "CN=Administrateur, CN=Users, DC=mycompagny, DC=local");
        env.put(Context.SECURITY_CREDENTIALS, "motdepasse");

        DirContext dirContext;

        try {
            dirContext = new InitialDirContext(env);
            System.out.println("LDAP connected");

            // Recherche dans le LDAP
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration resultat = dirContext.search(
                    "CN=Users,DC=mycompany,DC=local", "(cn=test)",
                    searchControls);
            while (resultat.hasMore()) {
                SearchResult sr = (SearchResult) resultat.next();
                System.out.println("Description : "
                        + sr.getAttributes().get("cn").get() + ", "
                        + sr.getAttributes().get("displayName").get());

            }

            // Modification
            String dn = "CN=test, CN=Users,DC=mycompagny,DC=local";
            printAttrs(dirContext.getAttributes(dn));
            // Save orginal attributes
            Attributes orig = dirContext.getAttributes(dn,
                    new String[] { "displayName" });

            // Prepare new attributes
            Attributes attributes = new BasicAttributes(true);
            Attribute attribut = new BasicAttribute("displayName", "test utilisateur");
            attributes.put(attribut);
            dirContext.modifyAttributes(dn, DirContext.REPLACE_ATTRIBUTE,
                    attributes);
            // Display
            printAttrs(dirContext.getAttributes(dn));

        

            dirContext.close();
        } catch (NamingException e) {
            System.err.println("Erreur lors de l'acces au serveur LDAP"
                    + e);
            e.printStackTrace();

        }
    }
}
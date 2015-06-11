/**
 * 
 */
package com.myhome.service;

import java.util.Collection;

import com.myhome.domain.Contact;

/**
 * @author acogoluegnes
 *
 */
public interface IContactService {

	Collection<Contact> select();
	
	Contact get(Long id);
	
	Contact add(Contact contact);
	
	Contact update(Contact contact);
	
}

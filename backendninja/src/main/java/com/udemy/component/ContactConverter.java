package com.udemy.component;

import org.springframework.stereotype.Component;

import com.udemy.entity.Contact;
import com.udemy.model.ContactModel;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactConverter.
 */
@Component("contactConverter")
public class ContactConverter {
	
	
	/**
	 * Convert contact model to contact.
	 *
	 * @param contactModel
	 *            the contact model
	 * @return the contact
	 */
	public Contact convertContactModelToContact(ContactModel contactModel){
		Contact contact = new Contact();
		contact.setId(contactModel.getId());
		contact.setFirstname(contactModel.getFirstname());
		contact.setLastname(contactModel.getLastname());
		contact.setTelephone(contactModel.getTelephone());
		contact.setCity(contactModel.getCity());
		return contact;
	}
	
	/**
	 * Convert contact to contact model.
	 *
	 * @param contact
	 *            the contact
	 * @return the contact model
	 */
	public ContactModel convertContactToContactModel(Contact contact){
		ContactModel contactModel = new ContactModel();
		if(contact!=null){
			contactModel.setId(contact.getId());
			contactModel.setFirstname(contact.getFirstname());
			contactModel.setLastname(contact.getLastname());
			contactModel.setTelephone(contact.getTelephone());
			contactModel.setCity(contact.getCity());
		}
		return contactModel;
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.Users.home;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import models.Contact;
import models.User;
import play.data.validation.Required;
import play.mvc.Controller;

/**
 *
 * @author nagashayanaramamurthy
 */
public class Contacts extends Controller{
    
    public static void contactForm(){
        render("@addContactForm");
    }
    
    public static void addContact(@Required String name, @Required String bday_date, @Required String reminder_before_bday) throws ParseException {
        if (Application.checkUserStatus()) {
            long id = Long.parseLong(session.get("loggedinuser"));

            User user = User.findById(id);
            if (user != null) {
                System.out.println("adding contact" + name + reminder_before_bday + bday_date);
                // add some dummy contacts
                user.addContact(name, bday_date, Integer.parseInt(reminder_before_bday));
            }
        }
        Users.home();

    }
    
    public static void editContactForm(@Required String contact_id) throws ParseException {
        if (Application.checkUserStatus()) {
            long id = Long.parseLong(session.get("loggedinuser"));
            String name = session.get("loggedinusername");

            User user = User.findById(id);
            if (user != null) {
                System.out.println(user.id);
                List<Contact> contacts = user.getContacts();

                Contact edit_contact = Contact.findById(Long.parseLong(contact_id));
                System.out.println(edit_contact.getBday_date());
                System.out.println(edit_contact.name);
                if (edit_contact != null) {
                    flash.put("edit_contact_id", edit_contact.id);
                    flash.put("edit_contact_name", edit_contact.name);
                    flash.put("edit_contact_bday_date", edit_contact.getBday_date());
                    flash.put("edit_contact_email_before_bday_hours", edit_contact.email_before_bday_hours);
                }
                render("@editContactForm", name);
            }

        }
        Users.home();
    }
    
    public static void updateContact(@Required String contact_id, @Required String contact_name, @Required String bday_date, @Required String reminder_before_bday) throws ParseException {
        if (Application.checkUserStatus()) {
            long id = Long.parseLong(session.get("loggedinuser"));
            String name = session.get("loggedinusername");
            User user = User.findById(id);
            if (user != null) {
                try{
                    Contact.updateContact(contact_id, contact_name, bday_date, reminder_before_bday);
                }
                catch(Exception e){
                   System.out.println("caught excpetion" + e.getStackTrace().toString());
                   render("@editContactForm",name);
                }
                
            }

        }
        Users.home();
    }
    
     public static void deleteContact(@Required String contact_id) throws ParseException {
        if (Application.checkUserStatus()) {
            long id = Long.parseLong(session.get("loggedinuser"));
            Contact.remove(id, Long.parseLong(contact_id));

        }

        Users.home();
    }
}

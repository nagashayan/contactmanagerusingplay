/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import AutoJobs.CheckNextTask;
import java.text.ParseException;
import java.util.List;
import models.Contact;
import models.User;
import play.data.validation.Required;
import play.mvc.Before;
import play.mvc.Controller;

/**
 *
 * @author nagashayanaramamurthy
 */
public class Contacts extends Controller {

    @Before
    static void checkAuthentification() {
        if (!session.contains("loggedinuser")) {
            Application.login();
        }
    }

    public static void contactForm() {
        render("@addContactForm");
    }

    public static void addContact(@Required String name, @Required String bday_date, @Required String reminder_before_bday) throws ParseException {

        long userid = Long.parseLong(session.get("loggedinuser"));

        User user = User.findById(userid);
        if (user != null) {
            user.addContact(name, bday_date, Integer.parseInt(reminder_before_bday));
            new CheckNextTask().now();
        }
        Users.home();

    }

    public static void editContactForm(@Required String contact_id) throws ParseException {

        long contactid = Long.parseLong(session.get("loggedinuser"));
        String name = session.get("loggedinusername");

        User user = User.findById(contactid);
        if (user != null) {
            System.out.println(user.id);
            List<Contact> contacts = user.getContacts();

            Contact edit_contact = Contact.findById(Long.parseLong(contact_id));
            System.out.println(edit_contact.getBday_date());
            System.out.println(edit_contact.name);
            if (edit_contact != null) {
                flash.put("edit_contact_id", edit_contact.id);
                flash.put("edit_contact_name", edit_contact.name);
                flash.put("edit_contact_bday_date", edit_contact.getFormatBday_date());
                flash.put("edit_contact_email_before_bday_hours", edit_contact.email_before_bday_hours);
            }
            render("@editContactForm", name);
        }

        Users.home();
    }

    public static void updateContact(@Required String contact_id, @Required String contact_name, @Required String bday_date, @Required String reminder_before_bday) throws ParseException {

        long userid = Long.parseLong(session.get("loggedinuser"));
        String name = session.get("loggedinusername");
        User user = User.findById(userid);
        if (user != null) {
            try {
                Contact.updateContact(contact_id, contact_name, bday_date, reminder_before_bday);
                new CheckNextTask().now();
            } catch (Exception e) {
                System.out.println("caught excpetion" + e.getStackTrace().toString());
                render("@editContactForm", name);
            }

        }

        Users.home();
    }

    public static void deleteContact(@Required String contact_id) throws ParseException {

        long contactid = Long.parseLong(session.get("loggedinuser"));
        Contact.remove(contactid, Long.parseLong(contact_id));
        new CheckNextTask().now();

        Users.home();
    }
}

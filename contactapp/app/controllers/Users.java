/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import models.Contact;
import models.User;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.Scope.Session;

/**
 *
 * @author nagashayanaramamurthy
 */
public class Users extends Controller {

    public static void home() {
        try {
            if (session.contains("loggedinuser")) {
                long id = Long.parseLong(session.get("loggedinuser"));

                User user = User.findById(id);
                if (user != null) {
                    System.out.println(user.id);
                    String name = user.email;
                    List<Contact> contacts = user.getContacts();
                    render(name, contacts);
                }

            }

            flash.error("Please check your email or password and try again");
            session.clear();
            Application.login();
        } catch (Exception e) {
            System.out.println("caught excpetion" + e);
            flash.error("Please check your email or password and try again");
            session.clear();
            Application.login();

        }

    }

    public static void addContact(@Required String name, @Required Date bday_date, @Required String reminder_before_bday) throws ParseException {
        if(Application.checkUserStatus()){
            long id = Long.parseLong(session.get("loggedinuser"));

            User user = User.findById(id);
            if (user != null) {

                // add some dummy contacts
                user.addContact(name, bday_date, Integer.parseInt(reminder_before_bday));
            }
        }
        home();
        
    }
    
    public static void deleteContact(@Required String contact_id) throws ParseException {
        if(Application.checkUserStatus()){
            long id = Long.parseLong(session.get("loggedinuser"));
            Contact.remove(id,Long.parseLong( contact_id));
            
        }
        
        home();
    }

}

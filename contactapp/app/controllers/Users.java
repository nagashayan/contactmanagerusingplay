/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.text.ParseException;
import java.sql.Date;
import java.util.List;
import javax.naming.AuthenticationException;
import models.Contact;
import models.User;
import play.data.validation.Required;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Scope.Session;

/**
 *
 * @author nagashayanaramamurthy
 */
public class Users extends Controller {
    
    @Before
    static void checkAuthentification() {
        if (!session.contains("loggedinuser")) {
            System.out.println("loggedin1");
            Application.login();
        }
    }
    
    public static void home() {
        try {
            
                long id = Long.parseLong(session.get("loggedinuser"));
                String name = session.get("loggedinusername");
                User user = User.findById(id);
                if (user != null) {
                    System.out.println(user.id);
                    List<Contact> contacts = user.getContacts();
                    render(name, contacts);
                }

            throw new AuthenticationException("Please login");
        } catch (Exception e) {
            System.out.println("caught excpetion" + e);
            flash.error(e.toString());
            session.clear();
            Application.login();

        }

    }

}

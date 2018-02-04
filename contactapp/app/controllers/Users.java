/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import javax.naming.AuthenticationException;
import models.Contact;
import models.User;
import play.mvc.Before;
import play.mvc.Controller;

/**
 *
 * @author nagashayanaramamurthy
 */
public class Users extends Controller {
    
    @Before
    static void checkAuthentification() {
        if (!session.contains("loggedinuser")) {
            Application.login();
        }
    }
    
    public static void home() {
        try {
            
                long userid = Long.parseLong(session.get("loggedinuser"));
                String name = session.get("loggedinusername");
                User user = User.findById(userid);
                if (user != null) {
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

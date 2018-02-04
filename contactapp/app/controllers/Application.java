package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import play.data.validation.Required;

public class Application extends Controller {

    public static void index() {
        if (!checkUserStatus()) {
            render();
        } else {
            Users.home();
        }
    }

    public static void login() {
        if (!checkUserStatus()) {
            render();
        } else {
            Users.home();
        }
    }

    public static void validate(String email, String password) {
        User user = User.getUser(email);
        if (user == null || !user.password.equals(password)) {

            flash.error("Please check your email or password and try again");
            login();
        } else {
            flash("name", user.email);
            session.put("loggedinuser", user.id);
            Users.home();
        }
    }

    public static void logout() {
        session.clear();
        index();
    }

    public static void register() {
        if (!checkUserStatus()) {
            render();
        } else {
            Users.home();
        }
    }

    public static void registerUser(@Required String email, @Required String password) {
        if (validation.hasErrors()) {
            for (play.data.validation.Error error : validation.errors()) {
                System.out.println(error.message());
            }
            params.flash();
            flash.error("Oops! Error in the form");
            render("@register");
        }
        try {

            User user = new User(email, password);
            user.save();
            render("@afterregistration");
        } catch (Exception e) {
            System.out.print(e);
            flash.error("Something went wrong, registration is not complete");
            render("@register");
        }

    }

    private static boolean checkUserStatus() {
        if (session.contains("loggedinuser")) {
            System.out.println("loggedin1");

            return true;
        } else {
            return false;

        }
    }

}

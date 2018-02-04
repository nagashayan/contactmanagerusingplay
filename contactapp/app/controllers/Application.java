package controllers;

import play.mvc.*;
import models.*;
import play.data.validation.Required;

public class Application extends Controller {

    @Before(unless = {"logout"})
    static void checkAuthentification() {
        if (session.contains("loggedinuser")) {
            Users.home();
        }
    }

    public static void index() {
        render();
    }

    public static void login() {
        render();
    }

    public static void validate(String email, String password) {
        User user = User.getUser(email);
        if (user == null || !user.password.equals(password)) {

            flash.error("Please check your email or password and try again");
            login();
        } else {
            flash("name", user.email);
            session.put("loggedinuser", user.id);
            session.put("loggedinusername", user.email);
            Users.home();
        }
    }

    public static void logout() {
        session.clear();
        index();
    }

    public static void register() {
        render();
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

}

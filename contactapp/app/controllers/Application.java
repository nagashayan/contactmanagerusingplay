package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        
        render();
    }
    
    public static void home() {
        String name = flash.get("name");
        render(name);
    }
    
    public static void login() {
        render();
    }
    
    public static void validate(String email, String password) {
        if(email.equals("naga@gmail.com") && password.equals("naga")){
            flash("name",email);
            home();
        }
        flash.error("Please check your email or password and try again");
        index();
    }
    
    public static void logout() {
        index();
    }
    
    public static void register() {
        render();
    }
    
    public static void registerUser() {
        render("@afterregistration");
    }

}
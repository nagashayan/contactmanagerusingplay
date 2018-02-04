package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import play.data.validation.Required;

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
    
    public static void registerUser(@Required String email, @Required String password) {
        if (validation.hasErrors()) {
            System.out.println("has errors"+password);
            for(play.data.validation.Error error : validation.errors()) {
             System.out.println(error.message());
         }
            params.flash();
            flash.error("Oops! Error in the form");
            render("@register");
        }
        try {
            
            User user = new User(email,password);
            user.save();
            render("@afterregistration");
        }
        catch (Exception e){
            flash.error("Something went wrong, registration is not complete");
            render("@register");
        }
        
    }

}
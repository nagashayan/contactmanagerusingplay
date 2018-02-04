package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        String error = "";
        if(!error.equals("")){
            error= "User or Password not found";
        }
        render(error);
    }
    
    public static void home() {
        String name = "naga";
        render(name);
    }
    
    public static void login() {
        render();
    }
    
    public static void validate(String email, String password) {
        if(email.equals("naga") && password.equals("naga")){
            home();
        }
        String error = "loginerror";
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
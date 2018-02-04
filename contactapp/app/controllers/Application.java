package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        String name = "naga";
        render(name);
    }
    
    public static void login() {
        render("@index");
    }
    
    public static void logout() {
        render();
    }
    
    public static void register() {
        render();
    }
    
    public static void registerUser() {
        render("@afterregistration");
    }

}
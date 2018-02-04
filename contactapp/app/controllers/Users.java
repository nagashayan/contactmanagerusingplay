/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Scope.Session;

/**
 *
 * @author nagashayanaramamurthy
 */
public class Users extends Controller{
    
    public static void home() {
        String name = flash.get("name");
        
        try{
            if(session.contains("loggedinuser")){
                long id =  Long.parseLong(session.get("loggedinuser"));
            
                User user = User.findById(id);
                if(user != null){
                    name = user.email;
                    render(name);
                }
            
            }
            flash.error("Please check your email or password and try again");
            Application.login();
            
        }
        catch(Exception e){
            System.out.println("caught excpetion"+e);
            flash.error("Please check your email or password and try again");
            Application.login();
            
        }
        
        
        
    }

}

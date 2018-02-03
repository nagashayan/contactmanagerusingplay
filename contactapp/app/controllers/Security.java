/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author nagashayanaramamurthy
 */
public class Security extends Secure.Security {
    
    static boolean authenticate(String username, String password) {
        return username.equals("naga") && password.equals("naga");
    }
}

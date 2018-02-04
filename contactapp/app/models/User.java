/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import javax.persistence.Entity;
import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 *
 * @author nagashayanaramamurthy
 */
@Entity
public class User extends Model {

    @Email
    @Required
    public String email;
    
    @Required
    public String password;
    
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public static User getUser(String email){
        return find("email", email).first();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
    
    @OneToMany(
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
    public List<Contact> contacts = new ArrayList<Contact>();
    
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public static User getUser(String email){
        return find("email", email).first();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   

    public void addContact(String name, String bday, int email_before_bday_hours) throws ParseException {
        Contact contact = new Contact(name,bday,email_before_bday_hours);
        this.getContacts().add(contact);
        
        
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

}

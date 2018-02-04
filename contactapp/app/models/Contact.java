/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import javax.persistence.Entity;
import play.data.validation.Required;
import play.db.jpa.Model;
/**
 *
 * @author nagashayanaramamurthy
 */
@Entity
public class Contact extends Model{

    @Required
    public String name;
    
    @Required
    public Date bday_date;
    
    @Required
    public int email_before_bday_hours;
    
    public Contact(String name, String bday_date, int email_before_bday_hours) throws ParseException{

        this.name = name;
        //string to date format
        this.setBday_date(bday_date);
        this.email_before_bday_hours = email_before_bday_hours;
        
    }
    
    public static void updateContact(String contact_id, String contact_name, String bday_date, String reminder_before_bday) throws ParseException {
        Contact edit_contact = Contact.findById(Long.parseLong(contact_id));
        if(edit_contact != null){
            edit_contact.setName(contact_name);
            edit_contact.setBday_date(bday_date);
            edit_contact.setEmail_before_bday_hours(reminder_before_bday);
            edit_contact.save();
            
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmail_before_bday_hours() {
        return email_before_bday_hours;
    }

    public void setEmail_before_bday_hours(String email_before_bday_hours) {
        System.out.println("parsing"+email_before_bday_hours);
        this.email_before_bday_hours = Integer.parseInt(email_before_bday_hours);
        System.out.println(this.email_before_bday_hours);
    }
    
    public static void remove(long user_id,long contact_id) throws ParseException{
        Contact contact = Contact.findById(contact_id);
        User user = User.findById(user_id);
        user.removeContact(contact);
    }

    public String getBday_date() {
        DateFormat target_bday_date = new SimpleDateFormat("yyyy-MM-dd");
        String format_bday_date = target_bday_date.format(this.bday_date); 
        return format_bday_date;
    }

    public void setBday_date(String bday_date) throws ParseException {
        DateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date format_bday_date = dateFormat.parse(bday_date);
        this.bday_date = new java.sql.Date(format_bday_date.getTime());
        
    }

}

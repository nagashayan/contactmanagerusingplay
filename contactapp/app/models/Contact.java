/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    public Date bday_timestamp;
    
    @Required
    public int email_before_bday_hours;
    
    public Contact(String name, String bday_timestamp, int email_before_bday_hours) throws ParseException{
        System.out.print(name+bday_timestamp);
        System.out.print(email_before_bday_hours);
        this.name = name;
        DateFormat dateFormat  = new SimpleDateFormat("MM/dd/yyyy");
        Date newdate = dateFormat.parse(bday_timestamp);
        this.bday_timestamp = newdate;
        this.email_before_bday_hours = email_before_bday_hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBday_timestamp() {
        return bday_timestamp;
    }

    public void setBday_timestamp(Date bday_timestamp) {
        this.bday_timestamp = bday_timestamp;
    }

    public int getEmail_before_bday_hours() {
        return email_before_bday_hours;
    }

    public void setEmail_before_bday_hours(int email_before_bday_hours) {
        this.email_before_bday_hours = email_before_bday_hours;
    }

}

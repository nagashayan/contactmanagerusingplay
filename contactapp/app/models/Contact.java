/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
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
    public Date bday_timestamp;
    
    @Required
    public Date email_before_bday_hours;
}

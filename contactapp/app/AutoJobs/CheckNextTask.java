/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoJobs;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import models.Contact;
import play.jobs.*;
 
/**
 *
 * @author nagashayanaramamurthy
 */
/** Fire at 12pm (noon) every day **/ 
@On("0 0 12 * * ?")
public class CheckNextTask extends Job {
    
    public void doJob() throws ParseException {
        // execute some application logic here ...
        System.out.println("check for next earliest job");
        Long next_task = Contact.getNextTask();
        Contact contact = Contact.findById(next_task);
        System.out.println("contact id"+contact.id);
        System.out.println("contact name"+contact.name);
        System.out.println("scheduling job on");
        
        System.out.println("contact name"+contact.getBday_date());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(contact.getBday_date());
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        System.out.println("contact name"+timestamp);
        Timestamp nextTask = new Timestamp(timestamp.getTime() - contact.email_before_bday_hours * (60 * 60 * 1000));
        System.out.println("nexttask="+nextTask);
        
        
    }
    
}
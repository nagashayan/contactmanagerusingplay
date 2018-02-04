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
        Timestamp nextTask = Contact.getNextScheduleHour(contact);
        System.out.println("nexttask="+nextTask);
        
        
    }
    
}
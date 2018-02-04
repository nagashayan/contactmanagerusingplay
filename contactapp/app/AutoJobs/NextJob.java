/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoJobs;

import play.Logger;
import play.jobs.Job;
import play.jobs.On;

/**
 *
 * @author nagashayanaramamurthy
 */
/** Fire at 12pm (noon) every day **/ 
@On("0 0 12 * * ?")
public class NextJob extends Job {
    
    public void doJob() {
        Logger.info("Maintenance job ...");
    }
    
}

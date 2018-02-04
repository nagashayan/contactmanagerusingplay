import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    
    @Test
    public void UserCreationTest() throws ParseException {
        
        User user = new User("naga@gmail.com","pwd");
        assertEquals(user.getEmail(),"naga@gmail.com");
        assertEquals(user.getPassword(),"pwd");
    }
    
    @Test
    public void UserContactAddTest() throws ParseException {
        
        User user = new User("naga@gmail.com","pwd");
        user.addContact("naga", "2018-22-02", 10);
        
        //check if added propertly 
        Contact contact = user.getContacts().get(0);
        assertEquals(contact.name,"naga");
        assertEquals(contact.email_before_bday_hours,10);
    }

}

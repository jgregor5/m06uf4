package test;

import commander.IManager;
import commander.ManagerFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author julian
 */
public class HelloManagerTest {
    
    private static final Logger LOGGER = Logger.getLogger(HelloManagerTest.class.getName());
    
    public static void main(String[] args) {
        
        try (IManager mgr = ManagerFactory.getManager(null)) {
            
            JSONObject command = new JSONObject().
                put("command", "hello").
                put("name", "Julian");
        
            LOGGER.log(Level.INFO, "query:{0}", command.toString(4));        
            JSONObject result = mgr.execute(command);        
            LOGGER.log(Level.INFO, "result:{0}", result.toString(4));
            
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
}

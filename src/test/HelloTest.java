package test;

import commander.ComponentManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author julian
 */
public class HelloTest {
    
    private static final Logger LOGGER = Logger.getLogger(HelloTest.class.getName());
    
    public static void main(String[] args) {
        
        ComponentManager mgr = ComponentManager.getInstance();
        
        JSONObject command = new JSONObject().
                put("command", "hello").
                put("name", "Julian");
        
        LOGGER.log(Level.INFO, "query:{0}", command.toString(4));        
        JSONObject response = mgr.execute(command);        
        LOGGER.log(Level.INFO, "result:{0}", response.toString(4));
    }
}

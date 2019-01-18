
package test;

import client.CommanderClient;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author julian
 */
public class MultClientTest {
    
    private static final Logger LOGGER = Logger.getLogger(MultClientTest.class.getName());
    
    public static void main(String[] args) {
        
        CommanderClient commander = new CommanderClient("localhost");
        commander.connect(9000);
        
        JSONObject command = new JSONObject().
                put("command", "multiplicacio").
                put("op1", 5).
                put("op2", 5);
        
        JSONObject result = commander.execute(command);        
        LOGGER.log(Level.INFO, "result:{0}", result.toString(4));
        
        commander.disconnect();
    }
 
}


package test;

import client.CommanderClient;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author julian
 */
public class InfoClientTest {
    
    private static final Logger LOGGER = Logger.getLogger(InfoClientTest.class.getName());
    
    public static void main(String[] args) {
        
        CommanderClient commander = new CommanderClient("146.255.96.104");
        commander.connect(9000);
        
        JSONObject response = commander.execute(new JSONObject().put("command", "info.components"));  
        LOGGER.log(Level.INFO, "response:{0}", response.toString(4));
        
        commander.disconnect();
    }
}

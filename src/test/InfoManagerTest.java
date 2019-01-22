
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
public class InfoManagerTest {
    
    private static final Logger LOGGER = Logger.getLogger(InfoManagerTest.class.getName());
    
    public static void main(String[] args) {
        
        try (IManager mgr = ManagerFactory.getManager("146.255.96.104")) {
        
            JSONObject response = mgr.execute(
                    new JSONObject().put("command", "info.components"));  
            LOGGER.log(Level.INFO, "response:{0}", response.toString(4));
        
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
}


package test;

import commander.ComponentManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author julian
 */
public class StorageTest {
    
    private static final Logger LOGGER = Logger.getLogger(StorageTest.class.getName());
    
    public static void main(String[] args) {
        
        try (ComponentManager mgr = ComponentManager.getInstance()) {
            createAndIncrement(mgr);
            loadAndDelete(mgr);
        }
    }
    
    public static void loadAndDelete(ComponentManager mgr) {
        
        JSONObject command = new JSONObject().
                put("command", "storage.load").
                put("key", "test.counter");
        
        
        LOGGER.log(Level.INFO, "query:{0}", command.toString(4));
        JSONObject result = mgr.execute(command);
        LOGGER.log(Level.INFO, "result:{0}", result.toString(4));
        
        command = new JSONObject().
                put("command", "storage.delete").
                put("key", "test.counter");
        
        LOGGER.log(Level.INFO, "query:{0}", command.toString(4));
        result = mgr.execute(command);
        LOGGER.log(Level.INFO, "result:{0}", result.toString(4));
        
    }
    
    public static void createAndIncrement(ComponentManager mgr) {
        
        JSONObject initialData = new JSONObject().put("counter", 0);
        
        JSONObject command = new JSONObject().
                put("command", "storage.loadorsave").
                put("key", "test.counter").
                put("data", initialData);
        
        LOGGER.log(Level.INFO, "query:{0}", command.toString(4));
        JSONObject result = mgr.execute(command);
        LOGGER.log(Level.INFO, "result:{0}", result.toString(4));
        
        if (result.getBoolean("success")) {
            JSONObject data = result.getJSONObject("data");
            data.put("counter", data.getInt("counter") + 1);
            
            command = new JSONObject().
                    put("command", "storage.save").
                    put("key", "test.counter").
                    put("data", data);
            
            LOGGER.log(Level.INFO, "query:{0}", command.toString(4));
            result = mgr.execute(command);
            LOGGER.log(Level.INFO, "result:{0}", result.toString(4));
        }
    }
}

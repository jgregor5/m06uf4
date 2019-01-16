package test;

import commander.ComponentManager;
import org.json.JSONObject;

/**
 *
 * @author julian
 */
public class HelloTest {
    
    public static void main(String[] args) {
        
        JSONObject command = new JSONObject().
                put("command", "hello").
                put("name", "Julian");
        
        JSONObject response = ComponentManager.getInstance().execute(command);
        
        System.out.println(response.toString(4));
    }
}

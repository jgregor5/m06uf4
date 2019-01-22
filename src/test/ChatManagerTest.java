
package test;

import commander.IEventListener;
import commander.IManager;
import commander.ManagerFactory;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author julian
 */
public class ChatManagerTest implements IEventListener {
    
    private static final Logger LOGGER = Logger.getLogger(ChatManagerTest.class.getName());

    @Override
    public void handleEvent(JSONObject jsono) {
        
        if (jsono.getString("source").equals("chat")) {
            LOGGER.log(Level.INFO, "{0}: {1}", new Object[]{
                jsono.getString("from"), jsono.getString("message")});
        }
    }
    
    public void test() {
        
        try (IManager mgr = ManagerFactory.getManager("146.255.96.104")) {
            
            mgr.registerListener(this);
            
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("> ");
                String nextLine = sc.nextLine();
                if (nextLine.isEmpty()) {
                   break;
                }

                JSONObject response = mgr.execute(new JSONObject().
                    put("command", "chat.send").
                    put("from", "julian").
                    put("message", nextLine));

                if (!response.getBoolean("success")) {
                    LOGGER.log(Level.SEVERE, "bad response {0}", response);
                }
            }
                        
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        
        new ChatManagerTest().test();
    }

}

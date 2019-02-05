
package test;

import client.CommanderClient;
import commander.IEventListener;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author julian
 */
public class ChatTest implements IEventListener {
    
    private static final Logger LOGGER = Logger.getLogger(ChatTest.class.getName());

    @Override
    public void handleEvent(JSONObject jsono) {
        
        if (jsono.getString("source").equals("chat")) {
            LOGGER.log(Level.INFO, "{0}: {1}", new Object[]{
                jsono.getString("from"), jsono.getString("message")});
        }
    }
    
    public void test() {
        
        CommanderClient commander = new CommanderClient("146.255.96.104");
        commander.registerListener(this);
        commander.connect(9000);
        commander.listen(9001);
        
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String nextLine = sc.nextLine();
            if (nextLine.isEmpty()) {
               break;
            }
            
            JSONObject response = commander.execute(new JSONObject().
                put("command", "chat.send").
                put("from", "julian").
                put("message", nextLine));
            
            if (!response.getBoolean("success")) {
                LOGGER.log(Level.SEVERE, "bad response {0}", response);
            }
        }
        
        commander.disconnect();
        commander.unlisten();
    }
    
    public static void main(String[] args) {
        
        new ChatTest().test();
    }

}

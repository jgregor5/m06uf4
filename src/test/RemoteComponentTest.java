
package test;

import commander.IComponent;
import commander.IEventListener;
import commander.IManager;
import commander.ManagerFactory;
import implement.MyTwitterComponent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author julian
 */
public class RemoteComponentTest implements IEventListener {
    
    private static final Logger LOGGER = Logger.getLogger(RemoteComponentTest.class.getName());
    
    private IComponent component;
    private boolean event = false;
    
    public RemoteComponentTest(IComponent component) {        
        this.component = component;               
    }
        
    @Override
    public void handleEvent(JSONObject jo) {        
        // send the events coming from 9001 to my component
        ((IEventListener) this.component).handleEvent(jo);
        
        LOGGER.log(Level.INFO, "event received: {0}", jo.toString());
        this.event = true;
    }

    // TESTS
    
    public void test1() {
        
        JSONObject command = new JSONObject().put("command", "mytwitter.command");            
        JSONObject result = this.component.execute(command);
        LOGGER.log(Level.INFO, result.toString(4));
        
        // wait for event, some time
        sleep(15000);            
    }
    
    public void test2() {
        
        // wait until a tweet is received
        while (!event) {
            sleep(1000);
        }
        
        LOGGER.log(Level.INFO, "it happened!");
    }
    
    private static void sleep(long millis) {        
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            LOGGER.log(Level.INFO, "interrupted!");
        }
    }
    
    public static void main(String[] args) {        
        
        // create component (only for testing)
        MyTwitterComponent comp = new MyTwitterComponent();
        
        try (IManager mgr = ManagerFactory.getManager("146.255.96.104")) {    
            
            // init
            comp.setManager(mgr);            
            RemoteComponentTest test = new RemoteComponentTest(comp);                
            mgr.registerListener(test);

            // tests
            //test.test1();
            test.test2();

            // done
            mgr.unregisterListener(test);
            
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "test failure", ex);
        }        
    }
}

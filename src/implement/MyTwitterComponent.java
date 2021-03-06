package implement;

import commander.IComponent;
import commander.IEventListener;
import commander.IEventSource;
import commander.IInitManager;
import commander.IManager;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

/**
 *
 * @author julian
 */
public class MyTwitterComponent implements IComponent, 
        IEventListener, IEventSource, IInitManager {
    
    private final Set<IEventListener> listeners;
    private IManager mgr;
    
    public MyTwitterComponent() {
        this.listeners = new HashSet<>();
    }
    
    @Override
    public void setManager(IManager mgr) {
        this.mgr = mgr;
    }

    @Override
    public String getName() {
        return "mytwitter";
    }

    @Override
    public String[] getCommands() {
        return new String[]{"mytwitter.command"};
    }

    @Override
    public JSONObject execute(JSONObject json) {

        JSONObject event = new JSONObject().
                put("source", "mytwitter").
                put("type", "executed");
        
        sendEvent(event);
        
        // you could use this.mgr to call another component
        
        return new JSONObject().
                put("success", true).
                put("message", "command executed!");
    }

    @Override
    public void handleEvent(JSONObject jo) {
        if (jo.getString("source").equals("twitter")) {
            // tratarlo
        }
    }

    @Override
    public void registerListener(IEventListener il) {
        this.listeners.add(il);
    }

    @Override
    public void unregisterListener(IEventListener il) {
        this.listeners.remove(il);
    }
    
    private void sendEvent(JSONObject jo) {
        
        for (IEventListener listener: this.listeners) {
            listener.handleEvent(jo);
        }
    }

}

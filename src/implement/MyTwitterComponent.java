package implement;

import commander.IComponent;
import commander.IEventListener;
import commander.IEventSource;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

/**
 *
 * @author julian
 */
public class MyTwitterComponent implements IComponent, 
        IEventListener, IEventSource {
    
    private final Set<IEventListener> listeners;
    
    public MyTwitterComponent() {
        this.listeners = new HashSet<>();
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
        
        return new JSONObject().
                put("message", "hello, " + json.get("name") + "!");
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

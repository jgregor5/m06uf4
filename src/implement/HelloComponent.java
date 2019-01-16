package implement;

import commander.ComponentManager;
import commander.IComponent;
import org.json.JSONObject;

/**
 *
 * @author julian
 */
public class HelloComponent implements IComponent {

    @Override
    public String getName() {
        return "hello";
    }

    @Override
    public String[] getCommands() {
        return new String[]{"hello"};
    }

    @Override
    public JSONObject execute(JSONObject json) {        
        return new JSONObject().
                put("message", "hello, " + json.get("name") + "!");
    }
}

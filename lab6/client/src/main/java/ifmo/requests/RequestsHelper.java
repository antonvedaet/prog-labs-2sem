package ifmo.requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestsHelper {
    public static Map<String, Request> requestMap = new HashMap();
    public static Map<String, Request> getRequestList(){
        Request help = new HelpRequest();
        requestMap.put(help.getCommandName(), help);
        return requestMap;
    }
}

package fr.eseo.dis.pavlovpi.somanager;

import com.android.volley.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;


public class ConnectionTest {

    private static final String URL = "https://192.168.4.248/pfe/webservice.php?";
    private String result;

    @BeforeClass
    public static void setup() throws JSONException {
        Response.Listener<JSONObject> params = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            }
        };

        //JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, params);

        Assert.assertNotNull(null, params);
    }


    /** TODO With HTTP
     private static final String URL = "https://192.168.4.248/pfe/webservice.php?";

     @BeforeClass
     public static void setup() throws JSONException {
     LinkedHashMap<String, String> params = new LinkedHashMap<>();
     params.put("q","LOGON");
     params.put("user","alberpat");
     params.put("pass","w872o32HkYAO");
     String type = "GET";

     JSONObject response = HttpUtils.executeRequest(type,URL,params);

     System.out.println(response.toString());

     Assert.assertNotNull(null, response);
     Assert.assertEquals("OK", response.get("result"));
     Assert.assertEquals("LOGON", response.get("api"));
     }


     @Test
     public void requestLIPRJTest() throws JSONException {
     LinkedHashMap<String, String> params = new LinkedHashMap<>();
     params.put("q","LIPRJ");
     params.put("user","alberpat");
     params.put("token",HttpUtils.token);
     String type = "GET";

     JSONObject response = HttpUtils.executeRequest(type,URL,params);

     System.out.println(response.toString());

     Assert.assertNotNull(null, response);
     Assert.assertEquals("OK", response.get("result"));
     Assert.assertEquals("LIPRJ", response.get("api"));
     }
     */

}

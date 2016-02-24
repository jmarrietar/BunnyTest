package com.virchgrave;

import java.util.TreeMap;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import org.json.JSONException;
import org.json.JSONObject;
import javax.ws.rs.core.MediaType;

@Path("service")
public class serviceResource {

    @Context
    private UriInfo context;

    public serviceResource() {
    }

    @GET
    @Produces("text/html")
    public String getHtml() {
        //TODO return proper representation object
        return "<h1>BunnyIncR<h1>";
    }

    @PUT
    @Consumes("text/html")
    public void putHtml(String content) {
    }

    public static boolean isPalindrome(String numero) {
        StringBuffer NumeroBuffer = new StringBuffer(numero);
        String reverse = NumeroBuffer.reverse().toString();
        if (numero.equals(reverse)) {
            return true;
        }
        return false;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public String postHandler(String content) throws JSONException {

        JSONObject JoseObject = new JSONObject(content);
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectRespuesta = new JSONObject();

        
        TreeMap palindromos = new TreeMap();
        
        int cicle = 0;
        int count = 0;
        int inicio = Integer.parseInt(JoseObject.getString("x"));
        int fin = Integer.parseInt(JoseObject.getString("y"));
        for (int i = inicio; i <= fin; i++) {
            cicle++;
            if (isPalindrome(String.valueOf(i))) {                    // check if number is palindrome 
                String binario = Integer.toString(i, 2);
                if (isPalindrome(binario)) {                          // check if binary of number is palindrome 
                    palindromos.put(Integer.toString(i), binario);    
                    count++;
                }
            }
        }

        jsonObjectRespuesta.put("Palindromos", palindromos);
        jsonObjectRespuesta.put("Numero Palindromos", count);
        jsonObjectRespuesta.put("Numero Ciclos", cicle);

        /* */
        
        return jsonObjectRespuesta+ "\n";
        
    }

}

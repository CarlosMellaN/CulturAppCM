package lt.a96.httpcarlosmellan.culturappcm;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Casa on 01-03-2017.
 */

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://192.168.1.158/culturappcm/login.php";
    private Map<String, String> params;

    public LoginRequest (String nombreusuario, String contrasena, Response.Listener<String> listener) {
    super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nombreusuario", nombreusuario);
        params.put("contrasena", contrasena);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
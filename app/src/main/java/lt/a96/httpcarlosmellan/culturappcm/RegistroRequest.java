package lt.a96.httpcarlosmellan.culturappcm;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Casa on 01-03-2017.
 */

public class RegistroRequest  extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://192.168.1.158:8080/culturappcm/registrousuarios.php";
    private Map<String, String> params;

    public RegistroRequest (String email, String nombre, String nombreusuario, String contrasena, String genero, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("nombre", nombre);
        params.put("nombreusuario", nombreusuario);
        params.put("contrasena", contrasena);
        params.put("genero", genero);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

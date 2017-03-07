package lt.a96.httpcarlosmellan.culturappcm;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Casa on 05-03-2017.
 */

public class EventosRequest  extends StringRequest {
    private static final String STRING_REQUEST_URL = "http://192.168.1.158/culturappcm/ver_eventos.php";
    private Map<String, String> params;

    public EventosRequest (String Nombre, String Descripcion, String Localizacion, Response.Listener<String> listener) {
        super(Method.POST, STRING_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Nombre", Nombre);
        params.put("Descripcion", Descripcion);
        params.put("Localizacion", Localizacion);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

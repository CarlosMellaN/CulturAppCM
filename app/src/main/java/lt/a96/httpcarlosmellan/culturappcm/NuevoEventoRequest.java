package lt.a96.httpcarlosmellan.culturappcm;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Casa on 28-04-2017.
 */

public class NuevoEventoRequest extends StringRequest {
    private static final String NUEVOEVENTO_REQUEST_URL = "http://192.168.1.158/culturappcm/crear_evento.php";
    private Map<String, String> params;

    public NuevoEventoRequest(String Nombre, String Descripcion, String FechaInicio, String FechaFin, String Localizacion, String HoraInicio, String HoraFin, Response.Listener<String> listener) {
        super(Method.POST, NUEVOEVENTO_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Nombre", Nombre);
        params.put("Descripcion", Descripcion);
        params.put("FechaInicio", FechaInicio);
        params.put("FechaFin", FechaFin);
        params.put("Localizacion", Localizacion);
        params.put("HoraInicio", HoraInicio);
        params.put("HoraFin", HoraFin);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

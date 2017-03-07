package lt.a96.httpcarlosmellan.culturappcm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

public class Eventos extends AppCompatActivity {
    SearchView searchView;
    TextView tv_evento, tv_descripcion, tv_localizacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        //searchView = (SearchView)findViewById(R.id.searchView);
        tv_evento = (TextView)findViewById(R.id.tv_evento);
        tv_descripcion = (TextView)findViewById(R.id.tv_descripcion);
        tv_localizacion = (TextView)findViewById(R.id.tv_localizacion);

        Intent intent = getIntent();
        String Nombre = intent.getStringExtra("Nombre");
        String Descripcion = intent.getStringExtra("Descripcion");
        String FechaInicio = intent.getStringExtra("FechaInicio");
        String FechaFin = intent.getStringExtra("FechaFin");
        String Localizacion = intent.getStringExtra("Localizacion");
        String HoraInicio = intent.getStringExtra("HoraInicio");
        String HoraFin = intent.getStringExtra("HoraFin");
        String Estado = intent.getStringExtra("Estado");


        //String mensaje = "Bienvenido a tu perfil " + nombreusuario;
        //et_mensaje.setText(mensaje);
        tv_evento.setText(Nombre);
        tv_descripcion.setText(Descripcion);
        tv_localizacion.setText(Localizacion);
    }

}

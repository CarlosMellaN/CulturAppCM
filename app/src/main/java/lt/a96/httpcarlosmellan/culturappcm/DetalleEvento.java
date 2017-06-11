package lt.a96.httpcarlosmellan.culturappcm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;


public class DetalleEvento extends AppCompatActivity {
    TextView tv_evnombre, tv_evdescripcion, tv_evlocalizacion, tv_evhorainicio, tv_evhorafin, tv_evfechainicio, tv_evfechafin, tv_texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);

        tv_evnombre = (TextView)findViewById(R.id.tv_evnombre);
        tv_evdescripcion = (TextView)findViewById(R.id.tv_evdescripcion);
        tv_evlocalizacion = (TextView)findViewById(R.id.tv_evlocalizacion);
        tv_evhorainicio= (TextView)findViewById(R.id.tv_evhorainicio);
        tv_evhorafin= (TextView)findViewById(R.id.tv_evhorafin);
        tv_evfechainicio = (TextView)findViewById(R.id.tv_evfechainicio);
        tv_evfechafin = (TextView)findViewById(R.id.tv_evfechafin);
        tv_texto = (TextView)findViewById(R.id.tv_texto);



        Intent intent = getIntent();
        //String IdEvento = intent.getStringExtra("IdEvento");
        String Nombre = intent.getStringExtra("Nombre");
        String Descripcion = intent.getStringExtra("Descripcion");
        String Localizacion = intent.getStringExtra("Localizacion");
        String FechaInicio = intent.getStringExtra("FechaInicio");
        String FechaFin = intent.getStringExtra("FechaFin");
        String HoraInicio = intent.getStringExtra("HoraInicio");
        String HoraFin = intent.getStringExtra("HoraFin");
        String Texto = intent.getStringExtra("texto");

        tv_evnombre.setText(Nombre);
        tv_evdescripcion.setText(Descripcion);
        tv_evlocalizacion.setText(Localizacion);
        tv_evfechainicio.setText(FechaInicio);
        tv_evfechafin.setText(FechaFin);
        tv_evhorainicio.setText(HoraInicio);
        tv_evhorafin.setText(HoraFin);
        tv_texto.setText(Texto);

    }


}

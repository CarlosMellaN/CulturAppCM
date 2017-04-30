package lt.a96.httpcarlosmellan.culturappcm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Perfil extends AppCompatActivity {
    TextView et_nombreusuario, et_mail, et_nombre, et_genero, et_mensaje;
    Button btn_eventos, btn_crearevento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        et_nombreusuario = (TextView) findViewById(R.id.et_nombreusuario);
        et_mail = (TextView) findViewById(R.id.et_mail);
        et_nombre = (TextView) findViewById(R.id.et_nombre);
        et_genero = (TextView) findViewById(R.id.et_genero);
        et_mensaje = (TextView) findViewById(R.id.et_mensaje);
        btn_eventos = (Button)findViewById(R.id.btn_eventos);
        btn_crearevento = (Button)findViewById(R.id.btn_crearevento);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String nombre = intent.getStringExtra("nombre");
        String nombreusuario = intent.getStringExtra("nombreusuario");
        String genero = intent.getStringExtra("genero");

        String mensaje = "Bienvenido a tu perfil " + nombreusuario;
        et_mensaje.setText(mensaje);
        et_nombreusuario.setText(nombreusuario);
        et_mail.setText(email);
        et_nombre.setText(nombre);
        et_genero.setText(genero);

        btn_eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Perfil.this, Eventos.class);
                startActivity(intent);

            }
        });

        btn_crearevento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Perfil.this, CrearEvento.class);
                startActivity(intent);

            }
        });

    }

    public void onBackPressed (){
        Intent intent = new Intent(Perfil.this, MainActivity.class);
        //para que al volver atras no vuelva la login y salga de la app
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

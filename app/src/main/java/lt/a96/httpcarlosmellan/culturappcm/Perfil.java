package lt.a96.httpcarlosmellan.culturappcm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Perfil extends AppCompatActivity {
    TextView et_nombreusuario, et_mail, et_nombre, et_genero, et_mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        et_nombreusuario = (TextView) findViewById(R.id.et_nombreusuario);
        et_mail = (TextView) findViewById(R.id.et_mail);
        et_nombre = (TextView) findViewById(R.id.et_nombre);
        et_genero = (TextView) findViewById(R.id.et_genero);
        et_mensaje = (TextView) findViewById(R.id.et_mensaje);

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
    }
}

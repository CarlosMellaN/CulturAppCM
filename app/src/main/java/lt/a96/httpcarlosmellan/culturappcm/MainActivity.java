package lt.a96.httpcarlosmellan.culturappcm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView tv_registarusuario;
    Button btn_iniciarsesion, btn_eventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_eventos=(Button)findViewById(R.id.btn_eventos);
        btn_iniciarsesion=(Button)findViewById(R.id.btn_iniciarsesion);
        tv_registarusuario=(TextView)findViewById(R.id.tv_registarusuario);

        tv_registarusuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrarUsuarios.class);
                startActivity(intent);
            }
        });

        btn_iniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        btn_eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, Eventos.class);
                startActivity(intent);

            }
        });




    }
}
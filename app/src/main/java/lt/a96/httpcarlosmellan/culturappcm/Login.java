package lt.a96.httpcarlosmellan.culturappcm;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    EditText et_usuario, et_password;
    TextView tv_registarusuario;
    Button btn_entrar, btn_cancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_usuario = (EditText)findViewById(R.id.et_usuario);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_entrar = (Button) findViewById(R.id.btn_entrar);
        btn_cancelar = (Button) findViewById(R.id.btn_cancelar);
        tv_registarusuario = (TextView) findViewById(R.id.tv_registarusuario);
        tv_registarusuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this, RegistrarUsuarios.class);
                Login.this.startActivity(registerIntent);
            }
        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nombreusuario = et_usuario.getText().toString();
                final String contrasena = et_password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        //alerta para mostrar el resultado del response
                        /*AlertDialog.Builder builder2 = new AlertDialog.Builder(Login.this);
                        builder2.setMessage("entra al responselistener")
                                .setNegativeButton(response, null)
                                .create()
                                .show();*/
                        //fin alerta
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                String email = jsonResponse.getString("email");
                                String genero = jsonResponse.getString("genero");
                                String nombre = jsonResponse.getString("nombre");

                                Intent intent = new Intent(Login.this, Perfil.class);
                                intent.putExtra("email", email);
                                intent.putExtra("nombre", nombre);
                                intent.putExtra("nombreusuario", nombreusuario);
                                intent.putExtra("genero", genero);

                                Login.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage("Inicio de sesion fallido")
                                        .setNegativeButton("Reintentar", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(nombreusuario, contrasena, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(loginRequest);

            }
        });
    }
}

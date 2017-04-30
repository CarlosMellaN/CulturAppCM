package lt.a96.httpcarlosmellan.culturappcm;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegistrarUsuarios extends AppCompatActivity {

    EditText txt_email, txt_nombre, txt_usuario, txt_password, txt_pass2;
    Spinner sp_genero;
    Button btn_crearusuario, btn_cancelar, btn_crearcongoogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_usuarios);
        txt_email = (EditText)findViewById(R.id.txt_email);
        txt_nombre = (EditText)findViewById(R.id.txt_nombre);
        txt_usuario = (EditText)findViewById(R.id.txt_usuario);
        txt_password = (EditText)findViewById(R.id.txt_password);
        txt_pass2 = (EditText)findViewById(R.id.txt_pass2);
        sp_genero = (Spinner)findViewById(R.id.sp_genero);
        btn_cancelar =(Button)findViewById(R.id.btn_cancelar);
        btn_crearusuario =(Button)findViewById(R.id.btn_crearusuario);
        btn_crearcongoogle =(Button)findViewById(R.id.btn_crearcongoogle);

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrarUsuarios.this,MainActivity.class);
                startActivity(intent);
            }
        });

        // TODO: hacer que no se puedan crear usuarios con el mismo email
        btn_crearusuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = txt_email.getText().toString();
                final String nombre = txt_nombre.getText().toString();
                final String usuario = txt_usuario.getText().toString();
                final String contrasena = txt_password.getText().toString();
                final String contrasena2 = txt_pass2.getText().toString();
                final String genero = sp_genero.getSelectedItem().toString();
                //final int edad = Integer.parseInt(txt_edad.getText().toString());



                if (TextUtils.isEmpty(usuario) || TextUtils.isEmpty(contrasena) || TextUtils.isEmpty(contrasena2)
                        || TextUtils.isEmpty(nombre)  || TextUtils.isEmpty(email)) {
                    Toast.makeText(RegistrarUsuarios.this, "Debe llenar todo los campos", Toast.LENGTH_SHORT).show();

                } else if (!contrasena.equals(contrasena2)) {
                    /*Toast cont = Toast.makeText(Registro.this, "Contraseñas no coinciden", Toast.LENGTH_SHORT);
                    cont.show();*/
                    txt_password.setError("Contraseñas no coinciden");
                    txt_pass2.setError("Contraseñas no coinciden");

                }else if (!Verficacionemail(email)) {
                    Toast cont = Toast.makeText(RegistrarUsuarios.this, "Debe ser un email valido", Toast.LENGTH_SHORT);
                    cont.show();

                }else{
                    Response.Listener<String> responseListener = new Response.Listener<String>(){

                        @Override
                        public void onResponse(String response) {
                            //alerta que muestra el resultado del response
                        /*AlertDialog.Builder builder2 = new AlertDialog.Builder(RegistrarUsuarios.this);
                        builder2.setMessage("entra al responselistener")
                                .setNegativeButton(response, null)
                                .create()
                                .show();*/
                            //fin alerta
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if(success){
                                    Toast.makeText(RegistrarUsuarios.this,"Usuario creado con exito",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(RegistrarUsuarios.this, Login.class);
                                    RegistrarUsuarios.this.startActivity(intent);
                                }else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarUsuarios.this);
                                    builder.setMessage("Registro Fallido")
                                            .setNegativeButton("Reintentar", null)
                                            .create()
                                            .show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    RegistroRequest registroRequest = new RegistroRequest(email, nombre, usuario, contrasena, genero, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegistrarUsuarios.this);
                    queue.add(registroRequest);
                }
            }
        });

        setSpinnerGenero();
    }

    public void setSpinnerGenero(){
        final  String[] datos = new String[]{"Femenino","Masculino"};
        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                datos);
        sp_genero.setAdapter(arrayAdapter);
    }

    private boolean Verficacionemail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}

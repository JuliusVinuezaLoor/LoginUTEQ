package com.example.loginuteq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginuteq.WebServices.Asynchtask;
import com.example.loginuteq.WebServices.WebService;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void clickLogin(View v){
        Map<String, String> datos = new HashMap<String, String>();
        EditText txtusuario = findViewById(R.id.txtusuario);
        EditText txtclave = findViewById(R.id.txtclave);
        WebService ws= new WebService(
                "https://revistas.uteq.edu.ec/ws/login.php?usr="
                        + txtusuario.getText().toString()
                        + "&pass=" + txtclave.getText().toString(),
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }
    @Override
    public void processFinish(String result) throws JSONException {
        ///Validacion de autenticacion
        if(result.equals("Login Correcto!")){
            enviar(result);
        }else {
            Toast.makeText(this, "Error de autenticación", Toast.LENGTH_SHORT).show();
        }

    }

    private void enviar(String info) {
        // Crear un Intent para abrir la otra actividad
        Intent intent = new Intent(this, MainActivity2.class);

        // Agregar la información como un extra en el Intent
        intent.putExtra("RESULTADO_EXTRA", info);

        // Iniciar la otra actividad
        startActivity(intent);
    }
}

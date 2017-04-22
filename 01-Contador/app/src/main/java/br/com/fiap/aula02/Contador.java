package br.com.fiap.aula02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Contador extends AppCompatActivity {

    TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        txtResultado.setText("0");
    }

    public void somar(View v){
        int  num =  Integer.parseInt(txtResultado.getText().toString());
        num++;
        txtResultado.setText(String.valueOf(num));
    }
    public void subtrair(View v){
        int  num =  Integer.parseInt(txtResultado.getText().toString());
        num--;
        txtResultado.setText(String.valueOf(num));

    }

    public void zerar(View v){
        int  num =  Integer.parseInt(txtResultado.getText().toString());
        num = 0;
        txtResultado.setText(String.valueOf(num));

    }
}

package br.com.fiap.notas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                chamarLogin();
            }
        },3000);
    }

    private void chamarLogin(){
        Intent toLogin = new Intent(this, LoginActivity.class);
        startActivity(toLogin);
        finish();
    }
}

package br.com.fiap.notas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.fiap.notas.util.ArquivoDB;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail,edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
    }


    /*primeiro, o metodo checa se há algo compativel com um endereco de email, e se o cam
    po de senha está preenchido.Tudo isso pelo método validarLogin.depois, caso o método ValidarLogin
    retorne true, ele puxa o usuario e senha do nosso SharedPreference e os compara com os dos campos
    */
    public void logar(View v){
        if(validarLogin(edtEmail.getText().toString(),edtSenha.getText().toString())) {
          //Pega o usiario e senha do SP
            ArquivoDB db = new ArquivoDB();
            String usuario = db.retornaValor(this,"dados","usuario");
            String senha = db.retornaValor(this,"dados","senha");

            //compara os valores do SharedPreference com os editados na Tabela
            if(usuario.equals(edtEmail.getText().toString()) && senha.equals(edtSenha.getText().toString())) {
                Intent toNotasCard = new Intent(this, NotasCardActivity.class);
                startActivity(toNotasCard);
            }else{
                Toast.makeText(this,"Usuario ou senha incorretos!",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,getText(R.string.msgPreenchmento_NOk),Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validarLogin(String email,String senha){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() &&!TextUtils.isEmpty(senha);
    }

    public void cadastrar(View v){
        Intent toCadastraLogin = new Intent(this, CadastraLoginActivity.class);
        startActivity(toCadastraLogin);
    }

    @Override
    protected void onResume() {
        edtSenha.setText("");
        super.onResume();
    }
}

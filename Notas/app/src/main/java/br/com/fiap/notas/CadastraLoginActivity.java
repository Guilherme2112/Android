package br.com.fiap.notas;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.HashMap;

import br.com.fiap.notas.util.ArquivoDB;

public class CadastraLoginActivity extends AppCompatActivity {

    private ArquivoDB arquivoDB;
    private HashMap<String, String> mapDados;
    private EditText etNome, etSobrenome, etNascimento, etEmail, etSenha;
    private RadioGroup rgSexo;
    private final String ARQ = "dados.txt";
    private final String SP = "dados";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_login);
        arquivoDB = new ArquivoDB();
        mapDados = new HashMap<>();

        etNome = (EditText) findViewById(R.id.edtNome);
        etSobrenome = (EditText) findViewById(R.id.edtSobrenome);
        etNascimento = (EditText) findViewById(R.id.edtNascimento);
        etEmail = (EditText) findViewById(R.id.edtEmail);
        etSenha = (EditText) findViewById(R.id.edtSenha);
        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);
    }
    //valida informações do formulário, e preenche o HashMap
    private boolean capturaDados() {
        String nome,sobrenome,nascimento,email,senha,sexo;
        boolean dadosOk = false;
        nome = etNome.getText().toString();
        sobrenome = etSobrenome.getText().toString();
        nascimento = etNascimento.getText().toString();
        email = etEmail.getText().toString();
        senha = etSenha.getText().toString();
        //radiobutton - pega o id do btn selecionado no radioGroup
        int sexoId = rgSexo.getCheckedRadioButtonId();
        RadioButton rbSexo = (RadioButton) findViewById(sexoId);
        //usa a blibioteca do android para validar email (android.utils.patterns)
        if(Patterns.EMAIL_ADDRESS.matcher
                (email).matches() &&
                !TextUtils.isEmpty(senha) &&
                !TextUtils.isEmpty(nome) &&
                !TextUtils.isEmpty(sobrenome) &&
                !TextUtils.isEmpty(nascimento) &&
                (sexoId != -1))
        {
            dadosOk = true;
            //pega o texto do RadioButton
            sexo = rbSexo.getText().toString();

            mapDados.put("usuario",email);
            mapDados.put("senha",senha);
            mapDados.put("nome",nome);
            mapDados.put("sobrenome",sobrenome);
            mapDados.put("nascimento",nascimento);
            mapDados.put("sexo",sexo);
        }else{
            //exibe mensagem de erro
            //getText busca um elemento(ID) e retorna o seu texto.
            Toast.makeText(this, getText(R.string.msgPreenchmento_NOk),Toast.LENGTH_SHORT).show();
        }

        return dadosOk;
    }

    public void gravarChaves(View v) {
       if(capturaDados()){
           arquivoDB.gravarChaves(this,SP,mapDados);
           Toast.makeText(this, getString(R.string.msgSucesso),Toast.LENGTH_SHORT).show();
       }
    }



    public void excluirChaves(View v) {
        if(capturaDados()){
            arquivoDB.excluirChaves(this,SP,mapDados);
            Toast.makeText(this,getText(R.string.msgSucesso),Toast.LENGTH_SHORT).show();
        }
    }

    public boolean verificarChaves(View v) {
        //List<String> chaves = Arrays.asList("email","senha");
        if (arquivoDB.verificarChave(this, SP, "usuario") && arquivoDB.verificarChave(this, SP, "senha")) {
            Toast.makeText(this, getString(R.string.dados_ok), Toast.LENGTH_LONG).show();
            return true;
        }else {
            Toast.makeText(this, getString(R.string.dados_nok), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void carregarChaves(View v){
        if(verificarChaves(v)){
            String secsu = arquivoDB.retornaValor(this,SP,"sexo");
            etNome.setText(arquivoDB.retornaValor(this,SP,"nome"));
            etSobrenome.setText(arquivoDB.retornaValor(this,SP,"sobrenome"));
            etNascimento.setText(arquivoDB.retornaValor(this,SP,"nascimento"));
            etEmail.setText(arquivoDB.retornaValor(this,SP,"usuario"));
            etSenha.setText(arquivoDB.retornaValor(this,SP,"senha"));
           //marca o RadioButton correspondente ao secsu

            if(secsu.equals("Feminino")){
                rgSexo.check(R.id.rbFeminino);
            }else{
                rgSexo.check(R.id.rbMasculino);
            }
        }
    }

    public void gravarArquivo(View v) {
        if(capturaDados()){
            try{
                arquivoDB.gravarArquivo(this,ARQ,mapDados.toString());
                Toast.makeText(this,getText(R.string.msgSucesso),Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void lerArquivo(View v) {
       String txt = "vazio";
        try{
            txt = arquivoDB.lerArquivo(this,ARQ);
        }catch (Exception e){
            e.printStackTrace();
        }
        Toast.makeText(this,txt,Toast.LENGTH_SHORT).show();
    }

    public void excluirArquivo(View v) {
        try {
            arquivoDB.excluirArquivo(this, ARQ);
            Toast.makeText(this, getString(R.string.arquivo_excluido), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void voltar(View v){
        finish();
    }
}


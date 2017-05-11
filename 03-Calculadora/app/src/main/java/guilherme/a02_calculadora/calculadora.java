package guilherme.a02_calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class calculadora extends AppCompatActivity {
    String txtANterior;
    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            txtANterior = s.toString();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int contador = s.length() - s.toString().replace(",", "").length();
            if (contador > 1 && val1.getEditableText() == s) {
                val1.setText(txtANterior);
                val1.setSelection(val1.getText().length());
            } else if (contador > 1) {
                val2.setText(txtANterior);
                val2.setSelection(val1.getText().length());
            }
        }
    };
    EditText val1 = null;
    EditText val2 = null;
    TextView txtRes = null;
    double num1 = 0;
    double num2 = 0;
    Double resultado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        val1 =(EditText) findViewById(R.id.val1);
        val2 = (EditText) findViewById(R.id.val2);
        txtRes = (TextView) findViewById(R.id.txtResultado);
        val1.addTextChangedListener(watcher);
        val2.addTextChangedListener(watcher);
    }


    public void soma(View v){
        try{
         num1 = Double.parseDouble(val1.getText().toString());
         num2 = Double.parseDouble(val2.getText().toString());
            resultado = num1+num2;
        txtRes.setText(String.valueOf(resultado));
        }catch (Exception e){
            Toast.makeText(this,"Digite números válidos",Toast.LENGTH_SHORT).show();
        }
    }
    public void sub(View v){
        try{
        num1 = Double.parseDouble(val1.getText().toString());
        num2 = Double.parseDouble(val2.getText().toString());
            resultado = num1-num2;
            txtRes.setText(String.valueOf(resultado));
        }catch (Exception e){
            Toast.makeText(this,"Digite números válidos",Toast.LENGTH_SHORT).show();
        }
    }
    public void vezes(View v){
        try{
        num1 = Double.parseDouble(val1.getText().toString());
        num2 = Double.parseDouble(val2.getText().toString());
            resultado = num1*num2;
            txtRes.setText(String.valueOf(resultado));
        }catch (Exception e){
            Toast.makeText(this,"Digite números válidos",Toast.LENGTH_SHORT).show();
        }
    }
    public void div(View v){
        try{
        num1 = Double.parseDouble(val1.getText().toString());
        num2 = Double.parseDouble(val2.getText().toString());
            resultado = num1/num2;
            txtRes.setText(String.valueOf(resultado));
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Digite números válidos",Toast.LENGTH_SHORT).show();
        }
    }
    public void limpar(View v){
        txtRes.setText("0");
    }
}

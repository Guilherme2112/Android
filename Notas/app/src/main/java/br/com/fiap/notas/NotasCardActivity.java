package br.com.fiap.notas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.fiap.notas.entity.CloudantResponse;
import br.com.fiap.notas.entity.Row;
import br.com.fiap.notas.util.CloudantRequestInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotasCardActivity extends AppCompatActivity {

    private ArrayList<Row> rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_card);
        carregaNotas();
    }

    private void carregaNotas(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://5d503886-4eec-4a78-82ca-008bc64c5d2c-bluemix.cloudant.com/fiap-notas/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CloudantRequestInterface api =
                retrofit.create(CloudantRequestInterface.class);

        api.getAllDocs().enqueue(new Callback<CloudantResponse>() {
            @Override
            public void onResponse(Call<CloudantResponse> call,
                                   Response<CloudantResponse> response) {

                CloudantResponse cloudantResponse = response.body();
                rows = new ArrayList<>(Arrays.asList(cloudantResponse.getRows()));

                for(Row item : rows){
                    Log.i("Nota: ", item.getDoc().toString());
                }


            }

            @Override
            public void onFailure(Call<CloudantResponse> call, Throwable t) {

            }
        });


    }

    public void voltar(View v){
        finish();
    }
}

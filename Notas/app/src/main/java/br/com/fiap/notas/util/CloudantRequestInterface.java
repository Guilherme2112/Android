package br.com.fiap.notas.util;

import br.com.fiap.notas.entity.CloudantResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by logonpf on 27/04/2017.
 Como organização, o Retrofit organiza todas as rotas
 da sua API dentro de uma interface
 */



public interface CloudantRequestInterface {

    @GET("_all_docs?include_docs=true")
    Call<CloudantResponse> getAllDocs();


}

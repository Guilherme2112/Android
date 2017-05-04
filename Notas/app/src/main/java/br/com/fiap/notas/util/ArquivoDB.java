package br.com.fiap.notas.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by logonpf on 30/03/2017.
 */

public class ArquivoDB {

    private SharedPreferences pref;

    // Método que gravará as chaves de um HashMap em um SharedPreferences
    public void gravarChaves(Context context, String prefName,
                             HashMap<String, String> map){
        pref = context.getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor e = pref.edit();
        //Para cada linha do meu HashMap, gravo chave e valor no SharedPreferences
        for(Map.Entry<String, String> entry : map.entrySet()){
            e.putString(entry.getKey(), entry.getValue());
        }
        e.commit();
    }// fim do gravarChaves

    // Método que excluirá as chaves de um HashMap em um SharedPreferences
    public void excluirChaves(Context context, String prefName,
                             HashMap<String, String> map){
        pref = context.getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor e = pref.edit();
        //Para cada linha do meu HashMap, excluo a chave e valor no SharedPreferences
        for(Map.Entry<String, String> entry : map.entrySet()){
            e.remove(entry.getKey());
        }
        e.commit();
    }

    // Método que retorna um valor mediante a uma preferência e uma chave como parâmetros
    public String retornaValor(Context context, String prefName, String key){
        pref = context.getSharedPreferences(prefName,MODE_PRIVATE);
        return pref.getString(key, null);
    }

    //Método que retorna se uma chave existe ou não
    public boolean verificarChave(Context context, String prefName, String key){
        pref = context.getSharedPreferences(prefName, MODE_PRIVATE);
        return pref.contains(key);
    }

    //############## Tratamento de Arquivos

    public void gravarArquivo(Context context, String arqName, String value ) throws FileNotFoundException {
        FileOutputStream fos = context.openFileOutput(arqName, MODE_PRIVATE);
        try {
            fos.write(value.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String lerArquivo(Context context, String arqName) throws FileNotFoundException {
        FileInputStream fis = context.openFileInput(arqName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String txt = null;
        try {
            txt = br.readLine();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return txt;
    }

    public boolean excluirArquivo(Context context, String arqName){
       try {
           return context.deleteFile(arqName);
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
    }



}

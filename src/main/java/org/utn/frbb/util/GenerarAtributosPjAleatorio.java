package org.utn.frbb.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.utn.frbb.model.Raza;

import java.io.IOException;
import java.util.Random;

public class GenerarAtributosPjAleatorio{


    public static Raza asignarRazaAleatorio(){

        Random r = new Random();
        int nro = r.nextInt(3);
        Raza ra = null;
        switch (nro){
            case 0: ra = Raza.humano;
                break;
            case 1: ra = Raza.elfo;
                break;
            case 2: ra = Raza.orco;
                break;
        }
        return ra;
    };
    public static String generarNombreAleatorio(){
        //Eligo un numero random entre 1 y 836 (cantidad maxima de personajes de la api)
        Random random = new Random();
        int indice = random.nextInt(836);
        String nombre = "sin Nombre";

        //Invocacion a la api
        OkHttpClient client = new OkHttpClient();

        String apiUrl = "https://rickandmortyapi.com/api/character/" + indice;

        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            JSONObject jsonObject = new JSONObject(responseData);

            // Acceder al valor del campo "name"
            String name = jsonObject.getString("name");
            nombre = name;

            //System.out.println(responseData);
        } catch (IOException e) {
            //En caso de que el servicio de timeout se de el nombre será "sin nombre" concatenado a un numero aleatorio
            nombre = nombre + indice;
        }

        return nombre;
    };

}

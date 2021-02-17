package com.example.tpfragments.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tpfragments.MainActivity;
import com.example.tpfragments.R;
import com.example.tpfragments.adapters.PeliculasListAdapter;
import com.example.tpfragments.bases.FragmentBase;
import com.example.tpfragments.clases.PeliculaExtensa;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
public class FragmentListado extends FragmentBase {
    View layoutRoot = null;

    public FragmentListado() {
        // Required empty public constructor
    }

    ListView lvDatosBusq;
    Button btnBuscar;
    EditText edPalabraBuscar;
    TextView tvError;
    ArrayList<PeliculaExtensa> ArrayRespuesta;
    String Palabrabuscar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(layoutRoot==null){
            layoutRoot = inflater.inflate(R.layout.fragment_listado,container,false);
        }
        ObtenerReferencias();
        SetearListeners();
        setActivityTitle("Listado");
        return layoutRoot;
    }
    private void CargarDatos() {

        PeliculasListAdapter adapter;
        adapter = new PeliculasListAdapter(getContainerActivity(), R.layout.item_persona, ArrayRespuesta);
        lvDatosBusq.setAdapter(adapter);
        setActivityTitleWithNumber("Listado", ArrayRespuesta.size());
    }

    private void SetearListeners() {
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Palabrabuscar = edPalabraBuscar.getText().toString();
                if(Palabrabuscar.length() != 0) {
                    FragmentListado.TraerResultadoBusqueda Resultado =new FragmentListado.TraerResultadoBusqueda();
                    Resultado.execute();
                }
                else {
                    tvError.setText("Ingrese una palabra de busqueda");
                }
            }
        });
    }


    private void ObtenerReferencias() {
        lvDatosBusq = (ListView) layoutRoot.findViewById(R.id.lvDatosBusqueda);
        btnBuscar = layoutRoot.findViewById(R.id.btnBuscar);
        edPalabraBuscar = layoutRoot.findViewById(R.id.edPalabraBusca);
        tvError = layoutRoot.findViewById(R.id.tvError);
    }
    private class TraerResultadoBusqueda extends AsyncTask<Void,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            HttpURLConnection miConexion = null;
            URL strAPIUrl;
// Estoy en el Background Thread.
            BufferedReader responseReader;
            String responseLine, strResultado = "";
            StringBuilder sbResponse;
            try {
                strAPIUrl = new URL("https://omdbapi.com/?apikey=2432d6a9&s=" + Palabrabuscar );
                miConexion = (HttpURLConnection) strAPIUrl.openConnection();
                miConexion.setRequestMethod("GET");
                if (miConexion.getResponseCode() == 200) {
                    responseReader = new BufferedReader(new InputStreamReader(miConexion.getInputStream()));
                    sbResponse = new StringBuilder();
                    while ((responseLine = responseReader.readLine()) != null) {
                        sbResponse.append(responseLine);
                    }
                    responseReader.close();
                    strResultado = sbResponse.toString();
                } else {

                }
            } catch (Exception e) {
                Log.e("CINDY ", e.getMessage());
            } finally {
                if (miConexion != null){
                    miConexion.disconnect();
                }
            }
            return strResultado;
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);
            PasarJSON(resultado);
        }

        private void PasarJSON(String resultado) {
            JsonObject JSONrespuesta, JSONCategoria;
            JsonArray categoriasJSONArr;
            ArrayRespuesta = new ArrayList<>();

            JSONrespuesta = JsonParser.parseString(resultado).getAsJsonObject();

            categoriasJSONArr = JSONrespuesta.getAsJsonArray("Search");

            for(int i =0; i < categoriasJSONArr.size(); i++){
                JSONCategoria = categoriasJSONArr.get(i).getAsJsonObject();
                String Title  = JSONCategoria.get("Title").getAsString();
                String Year = JSONCategoria.get("Year").getAsString();
                String imdbID = JSONCategoria.get("imdbID").getAsString();
                String Poster = JSONCategoria.get("Poster").getAsString();
                PeliculaExtensa rbObjeto = new PeliculaExtensa(Title, Year, imdbID, Poster);
                ArrayRespuesta.add(rbObjeto);
            }
            CargarDatos();
        }
    }

}
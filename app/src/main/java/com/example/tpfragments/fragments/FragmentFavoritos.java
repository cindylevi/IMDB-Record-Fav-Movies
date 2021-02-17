package com.example.tpfragments.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tpfragments.R;
import com.example.tpfragments.adapters.PeliculasListAdapter;
import com.example.tpfragments.bases.FragmentBase;
import com.example.tpfragments.clases.PeliculaExtensa;

import java.util.ArrayList;

public class FragmentFavoritos extends FragmentBase {
    View layoutRoot = null;

    public FragmentFavoritos() {
        // Required empty public constructor
    }

    ListView lvFavoritos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (layoutRoot == null) {
            layoutRoot = inflater.inflate(R.layout.fragment_favoritos, container, false);
        }
        ObtenerReferencias();
        CargarDatos();
        setActivityTitleWithNumber("Peliculas Favoritas", getContainerActivity().DevuelveFavoritos().size());
        return layoutRoot;
    }

    private void CargarDatos() {
        PeliculasListAdapter adapter;
        adapter = new PeliculasListAdapter(getContainerActivity(), R.layout.item_persona, getContainerActivity().DevuelveFavoritos());
        lvFavoritos.setAdapter(adapter);
    }


    private void ObtenerReferencias() {
        lvFavoritos = (ListView) layoutRoot.findViewById(R.id.lvFavoritas);
    }
}
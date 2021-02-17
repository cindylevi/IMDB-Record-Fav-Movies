package com.example.tpfragments.fragments;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.tpfragments.R;
import com.example.tpfragments.adapters.PeliculasListAdapter;
import com.example.tpfragments.bases.FragmentBase;

public class navBar extends FragmentBase {
    View layoutRoot = null;

    public navBar() {
        // Required empty public constructor
    }

    ImageButton ibFavs, ibListado, ibAcercaDe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (layoutRoot == null) {
            layoutRoot = inflater.inflate(R.layout.fragment_nav_bar, container, false);
        }
        ObtenerReferencias();
        SetearListeners();
        ibAcercaDe.setColorFilter(Color.argb(100, 0, 0, 0));
        ibFavs.setColorFilter(Color.argb(100, 0, 0, 0));
        ibListado.setColorFilter(Color.argb(100, 232, 212, 39));
        return layoutRoot;
    }


    private void SetearListeners() {
        ibListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContainerActivity().showListado();
                ibListado.setColorFilter(Color.argb(100, 232, 212, 39));
                ibAcercaDe.setColorFilter(Color.argb(100, 0, 0, 0));
                ibFavs.setColorFilter(Color.argb(100, 0, 0, 0));
            }
        });
        ibFavs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContainerActivity().showFavoritos();
                ibFavs.setColorFilter(Color.argb(100, 232, 212, 39));
                ibAcercaDe.setColorFilter(Color.argb(100, 0, 0, 0));
                ibListado.setColorFilter(Color.argb(100, 0, 0, 0));
            }
        });
        ibAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContainerActivity().showAcercaDe();
                ibAcercaDe.setColorFilter(Color.argb(100, 232, 212, 39));
                ibListado.setColorFilter(Color.argb(100, 0, 0, 0));
                ibFavs.setColorFilter(Color.argb(100, 0, 0, 0));
            }
        });
    }


    private void ObtenerReferencias() {
        ibFavs = (ImageButton) layoutRoot.findViewById(R.id.ibFavs);
        ibListado = (ImageButton) layoutRoot.findViewById(R.id.ibListado);
        ibAcercaDe = (ImageButton) layoutRoot.findViewById(R.id.ibAcercaDe);
    }
}
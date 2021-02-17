package com.example.tpfragments.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tpfragments.R;
import com.example.tpfragments.adapters.PeliculasListAdapter;
import com.example.tpfragments.bases.FragmentBase;

public class FragmentAcercaDe extends FragmentBase {
    View layoutRoot = null;

    public FragmentAcercaDe() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (layoutRoot == null) {
            layoutRoot = inflater.inflate(R.layout.fragment_acerca_de, container, false);
        }
       setActivityTitle("Acerca de ");
        return layoutRoot;
    }
}
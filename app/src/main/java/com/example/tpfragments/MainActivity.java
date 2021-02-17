package com.example.tpfragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.tpfragments.bases.BaseActivity;
import com.example.tpfragments.clases.PeliculaExtensa;
import com.example.tpfragments.fragments.FragmentAcercaDe;
import com.example.tpfragments.fragments.FragmentFavoritos;
import com.example.tpfragments.fragments.FragmentListado;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    FragmentListado fragmentListado;
    FragmentFavoritos fragFavs;
    FragmentAcercaDe fragmentAcercaDe;
    ArrayList<PeliculaExtensa> PeliculasFavs = new ArrayList<PeliculaExtensa>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createFragments();
        showInitialFragment();

    }
    private void createFragments(){
        fragmentListado = new FragmentListado();
        fragFavs = new FragmentFavoritos();
        fragmentAcercaDe = new FragmentAcercaDe();
    }
    private void showInitialFragment(){
        goToFragmentWithReplace(R.id.fragContenedor, fragmentListado, false);
    }
    public void showListado(){
        goToFragmentWithReplace(R.id.fragContenedor, fragmentListado, true);
    }
    public void showFavoritos(){
        goToFragmentWithReplace(R.id.fragContenedor, fragFavs, true);
    }
    public void showAcercaDe(){
        goToFragmentWithReplace(R.id.fragContenedor, fragmentAcercaDe, true);
    }
    public Boolean ExistePelicula(PeliculaExtensa peliculaBuscar){
        Boolean blnReturnValue = false;
        int intIndex;
        intIndex = indexOfPelicula(peliculaBuscar);
        if(intIndex>=0){
            blnReturnValue = true;
        }
        return blnReturnValue;
    }
    public void  EliminarPeliculaFav(PeliculaExtensa peliculaFav){
        int IndexDato = indexOfPelicula(peliculaFav);
        PeliculasFavs.remove(IndexDato);
    }
    private int indexOfPelicula(PeliculaExtensa peliculaBuscar){
        int intReturnValue = -1;
        int ItemCount;
        int intCurrentIndex = 0;

        if(peliculaBuscar != null){
            ItemCount = PeliculasFavs.size();
            if(ItemCount> 0){
                while (intReturnValue==-1 && intCurrentIndex < ItemCount){
                    if(peliculaBuscar.get_imdbID().equals(PeliculasFavs.get(intCurrentIndex).get_imdbID())){
                        intReturnValue = intCurrentIndex;
                    }
                    else{
                        intCurrentIndex++;
                    }
                }
            }
        }
        return intReturnValue;
    }
    public void  AgregarPeliculaFavorita(PeliculaExtensa peliculaFav){
        PeliculasFavs.add(peliculaFav);
    }
    public ArrayList<PeliculaExtensa>  DevuelveFavoritos(){
        return PeliculasFavs;
    }
}
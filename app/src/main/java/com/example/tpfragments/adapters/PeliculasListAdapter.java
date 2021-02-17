package com.example.tpfragments.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.tpfragments.MainActivity;
import com.example.tpfragments.R;
import com.example.tpfragments.clases.PeliculaExtensa;
import com.example.tpfragments.fragments.FragmentListado;

import java.util.ArrayList;
import java.util.List;

public class PeliculasListAdapter extends ArrayAdapter<PeliculaExtensa> {
    private ArrayList<PeliculaExtensa> peliculasList;
    private int resourceLayout;
    private Context context;

    public PeliculasListAdapter(Context context, int resource, ArrayList<PeliculaExtensa> datosArray) {
        super(context, resource, datosArray);
        this.context  = context;
        this.resourceLayout = resource;
        this.peliculasList   = datosArray;
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PeliculaExtensa     dato;
        TextView    tvYear ;
        Boolean blnExisteLaPeli;
        TextView    tvTitle;
        ImageView   ivPoster;
        CheckBox chkFavs;

        View layoutInterno = convertView;

        if (layoutInterno == null) {
            LayoutInflater vi;
            vi              = LayoutInflater.from(this.context);
            layoutInterno   = vi.inflate(resourceLayout, null);
        }

        dato = getItem(position);

        if (dato != null) {

            // Obtengo la Rerferencia
            ivPoster = (ImageView) layoutInterno.findViewById(R.id.ivPoster);
            tvTitle  = (TextView) layoutInterno.findViewById(R.id.tvTitle);
            tvYear      = (TextView) layoutInterno.findViewById(R.id.tvYear);
            chkFavs = (CheckBox) layoutInterno.findViewById(R.id.chboxFavs);

            // Seteo las propiedades
            tvTitle.setText(dato.get_Title());
            tvYear.setText(dato.get_Year());
            mostrarFoto(dato.get_Poster(), ivPoster);
            chkFavs.setTag(dato);
            blnExisteLaPeli = getContainerActivity().ExistePelicula(dato);
            if(blnExisteLaPeli){
                chkFavs.setChecked(blnExisteLaPeli);
            }
            chkFavs.setOnClickListener(chkFavs_Click);
        }

        return layoutInterno;
    }

    private View.OnClickListener chkFavs_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CheckBox currentChackBox = (CheckBox)view;
            PeliculaExtensa dato = (PeliculaExtensa) currentChackBox.getTag();
            Boolean blnExisteLaPeli;

            blnExisteLaPeli = getContainerActivity().ExistePelicula(dato);
            if(currentChackBox.isChecked()){
                getContainerActivity().AgregarPeliculaFavorita(dato);
                Toast.makeText(context, "Se agrego " + dato.get_Title(), Toast.LENGTH_SHORT);
            }
            else{
                getContainerActivity().EliminarPeliculaFav(dato);
                Toast.makeText(context, "Se elimino " + dato.get_Title(), Toast.LENGTH_SHORT);
            }
        }
    };
    private void mostrarFoto(String posterPelicula, ImageView ivPoster){
        Glide.with(this.context)
                .load(posterPelicula)
                .apply(new RequestOptions()
                        .centerCrop()
                        .dontAnimate()
                        .dontTransform().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .thumbnail(.5f)
                .into(ivPoster);
    }
    public MainActivity getContainerActivity(){
        return (MainActivity)context;
    }
}

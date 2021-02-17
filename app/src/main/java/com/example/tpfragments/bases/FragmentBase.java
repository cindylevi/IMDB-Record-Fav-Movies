package com.example.tpfragments.bases;

import androidx.fragment.app.Fragment;

import com.example.tpfragments.MainActivity;

public class FragmentBase extends Fragment {
    public MainActivity getContainerActivity(){
        return (MainActivity)getActivity();
    }
    public void setActivityTitle(String strTitle){
        getContainerActivity().setTitle(strTitle.toUpperCase());
    }
    public void setActivityTitleWithNumber(String strTitle, Integer number){
        getContainerActivity().setTitle(strTitle.toUpperCase()+" ("+number.toString()+")");
    }
}
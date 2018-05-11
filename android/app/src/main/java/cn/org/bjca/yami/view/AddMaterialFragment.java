package cn.org.bjca.yami.view;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.org.bjca.yami.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddMaterialFragment extends Fragment {


    public AddMaterialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_material, container, false);
    }

}

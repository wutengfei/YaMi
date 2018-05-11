package cn.org.bjca.yami.view;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.org.bjca.yami.R;

/**
 * 套餐Fragment
 */
public class SetMealFragment extends Fragment {


    public SetMealFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setmeal, container, false);
    }

}

package cn.org.bjca.yami.view;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.org.bjca.yami.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {


    public ContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);

    }

}

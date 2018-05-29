package cn.org.bjca.yami.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.org.bjca.yami.R;


public class ItemFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View contextView = inflater.inflate(R.layout.vpager_prediction, container, false);
        TextView mTextView = (TextView) contextView.findViewById(R.id.textview);

        //获取Activity传递过来的参数  
        Bundle mBundle = getArguments();
        String title = mBundle.getString("arg");
        mTextView.setText(title);

        return contextView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}  
package cn.org.bjca.yami.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.org.bjca.yami.R;
import cn.org.bjca.yami.SureActivity;

/**
 * 套餐Fragment
 */
public class SetMealFragment extends Fragment implements View.OnClickListener {

    DragFrameLayout m_dragFrameLayout;
    Context mContext;

    public SetMealFragment() {
    }

    @SuppressLint("ValidFragment")
    public SetMealFragment(Context context) {
        mContext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setmeal, container, false);
        m_dragFrameLayout = (DragFrameLayout) view.findViewById(R.id.df_setMeal);

        TextView setMeal = view.findViewById(R.id.tv_setMeal);
        TextView addMaterial = view.findViewById(R.id.tv_addMaterial);
        Button sure = view.findViewById(R.id.btn_sure);
        sure.setOnClickListener(this);
        m_dragFrameLayout.addDragChildView(setMeal);
        m_dragFrameLayout.addDragChildView(addMaterial);
        return view;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(mContext, SureActivity.class));
    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//    }
}

package cn.org.bjca.yami.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.org.bjca.yami.R;
import cn.org.bjca.yami.bean.PredictionBean;


public class ItemFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contextView = inflater.inflate(R.layout.vpager_prediction, container,
                false);
        TextView mTitle = (TextView) contextView.findViewById(R.id.tv_title);
        TextView setMeal1 = (TextView) contextView.findViewById(R.id.tv_setMeal1);
        TextView setMeal2 = (TextView) contextView.findViewById(R.id.tv_setMeal2);
        TextView setMeal3 = (TextView) contextView.findViewById(R.id.tv_setMeal3);
        TextView setMeal4 = (TextView) contextView.findViewById(R.id.tv_setMeal4);
        TextView setMeal5 = (TextView) contextView.findViewById(R.id.tv_setMeal5);
        TextView addMaterial1 = (TextView) contextView.findViewById(R.id.tv_addMaterial1);
        TextView addMaterial2 = (TextView) contextView.findViewById(R.id.tv_addMaterial2);
        TextView addMaterial3 = (TextView) contextView.findViewById(R.id.tv_addMaterial3);
        TextView addMaterial4 = (TextView) contextView.findViewById(R.id.tv_addMaterial4);

        //获取Activity传递过来的参数
        Bundle mBundle = getArguments();
        PredictionBean.Food foods = (PredictionBean.Food) mBundle.getSerializable("food");
        if (foods != null) {//填充数据
            mTitle.setText(foods.getPeopleName());
            setMeal1.setText(foods.getSetMeal1());
            setMeal2.setText(foods.getSetMeal2());
            setMeal3.setText(foods.getSetMeal3());
            setMeal4.setText(foods.getSetMeal4());
            setMeal5.setText(foods.getSetMeal5());
            addMaterial1.setText(foods.getAddMaterial1());
            addMaterial2.setText(foods.getAddMaterial2());
            addMaterial3.setText(foods.getAddMaterial3());
            addMaterial4.setText(foods.getAddMaterial4());
        }
        return contextView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}  
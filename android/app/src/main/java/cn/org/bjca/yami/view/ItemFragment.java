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
        String foods = mBundle.getString("food");

        if (foods != null) {
            String[] food = foods.split("，");
            mTitle.setText(food[0]);
            setMeal1.setText(food[1]);
            setMeal2.setText(food[2]);
            setMeal3.setText(food[3]);
            setMeal4.setText(food[4]);
            setMeal5.setText(food[5]);
            addMaterial1.setText(food[6]);
            addMaterial2.setText(food[7]);
            addMaterial3.setText(food[8]);
            addMaterial4.setText(food[9]);

        }
        return contextView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}  
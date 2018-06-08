package cn.org.bjca.yami.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import cn.org.bjca.yami.OrderActivity;
import cn.org.bjca.yami.R;
import cn.org.bjca.yami.SureActivity;

/**
 * 套餐Fragment
 */
public class SetMealFragment extends Fragment implements View.OnClickListener {

    DragFrameLayout m_dragFrameLayout;
    Context mContext;
    private TextView setMeal1;//套餐1
    private TextView setMeal2;//套餐2
    private TextView setMeal3;//套餐3
    private TextView setMeal4;//套餐4
    private TextView setMeal5;//套餐5
    private View select;//捣蛋一下
    private TextView setMeal_yes;//已点套餐
    private TextView addMaterial_yes;//已点加料
    private View sure;//确定
    private String[] setMeals = new String[5];
    private String[] addMaterials = new String[4];
    public SetMealFragment() {
    }

    @SuppressLint("ValidFragment")
    public SetMealFragment(Context context) {
        mContext = context;
        setMeals[0] = "套餐一";
        setMeals[1] = "套餐二";
        setMeals[2] = "套餐三";
        setMeals[3] = "套餐四";
        setMeals[4] = "套餐五";
        addMaterials[0] = "加料一";
        addMaterials[1] = "加料二";
        addMaterials[2] = "加料三";
        addMaterials[3] = "加料四";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setmeal, container, false);
        m_dragFrameLayout = (DragFrameLayout) view.findViewById(R.id.df_setMeal);

        setMeal_yes = view.findViewById(R.id.tv_setMeal_yes);
        addMaterial_yes = view.findViewById(R.id.tv_addMaterial_yes);
        setMeal1 = view.findViewById(R.id.tv_setMeal_1);
        setMeal2 = view.findViewById(R.id.tv_setMeal_2);
        setMeal3 = view.findViewById(R.id.tv_setMeal_3);
        setMeal4 = view.findViewById(R.id.tv_setMeal_4);
        setMeal5 = view.findViewById(R.id.tv_setMeal_5);
        select = view.findViewById(R.id.btn_select);
        sure = view.findViewById(R.id.btn_sure);

        //设置悬浮
        m_dragFrameLayout.addDragChildView(setMeal_yes);
        m_dragFrameLayout.addDragChildView(addMaterial_yes);

        setMeal1.setOnClickListener(this);
        setMeal2.setOnClickListener(this);
        setMeal3.setOnClickListener(this);
        setMeal4.setOnClickListener(this);
        setMeal5.setOnClickListener(this);
        select.setOnClickListener(this);
        sure.setOnClickListener(this);

        //恢复选择过的状态
        if (OrderActivity.STATUS_SETMEAL != 0) {
            select.setVisibility(View.INVISIBLE);
            setMeal_yes.setVisibility(View.VISIBLE);
            if (OrderActivity.STATUS_SETMEAL == 1) {
                setMeal_yes.setText(setMeals[0]);
                setMeal1.setBackgroundColor((getResources().getColor(R.color.chose)));
            }
            if (OrderActivity.STATUS_SETMEAL == 2) {
                setMeal_yes.setText(setMeals[1]);
                setMeal2.setBackgroundColor((getResources().getColor(R.color.chose)));
            }
            if (OrderActivity.STATUS_SETMEAL == 3) {
                setMeal_yes.setText(setMeals[2]);
                setMeal3.setBackgroundColor((getResources().getColor(R.color.chose)));
            }
            if (OrderActivity.STATUS_SETMEAL == 4) {
                setMeal_yes.setText(setMeals[3]);
                setMeal4.setBackgroundColor((getResources().getColor(R.color.chose)));
            }
            if (OrderActivity.STATUS_SETMEAL == 5) {
                setMeal_yes.setText(setMeals[4]);
                setMeal5.setBackgroundColor((getResources().getColor(R.color.chose)));
            }
        }
        if (OrderActivity.STATUS_ADDMATERIAL != 0) {
            select.setVisibility(View.INVISIBLE);
            addMaterial_yes.setVisibility(View.VISIBLE);
            if (OrderActivity.STATUS_ADDMATERIAL == 1) {
                addMaterial_yes.setText("加料一");
            }
            if (OrderActivity.STATUS_ADDMATERIAL == 2) {
                addMaterial_yes.setText("加料二");
            }
            if (OrderActivity.STATUS_ADDMATERIAL == 3) {
                addMaterial_yes.setText("加料三");
            }
            if (OrderActivity.STATUS_ADDMATERIAL == 4) {
                addMaterial_yes.setText("加料四");
            }

        }

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_setMeal_1://套餐
                setMeal1.setBackgroundColor((getResources().getColor(R.color.chose)));
                setMeal2.setBackgroundColor((getResources().getColor(R.color.normal)));
                setMeal3.setBackgroundColor((getResources().getColor(R.color.normal)));
                setMeal4.setBackgroundColor((getResources().getColor(R.color.normal)));
                setMeal5.setBackgroundColor((getResources().getColor(R.color.normal)));
                select.setVisibility(View.INVISIBLE);
                setMeal_yes.setVisibility(View.VISIBLE);
                setMeal_yes.setText(setMeals[0]);
                OrderActivity.STATUS_SETMEAL = 1;
                break;
            case R.id.tv_setMeal_2://套餐
                setMeal1.setBackgroundColor((getResources().getColor(R.color.normal)));
                setMeal2.setBackgroundColor((getResources().getColor(R.color.chose)));
                setMeal3.setBackgroundColor((getResources().getColor(R.color.normal)));
                setMeal4.setBackgroundColor((getResources().getColor(R.color.normal)));
                setMeal5.setBackgroundColor((getResources().getColor(R.color.normal)));
                select.setVisibility(View.INVISIBLE);
                setMeal_yes.setVisibility(View.VISIBLE);
                setMeal_yes.setText(setMeals[1]);
                OrderActivity.STATUS_SETMEAL = 2;
                break;
            case R.id.tv_setMeal_3://套餐
                setMeal1.setBackgroundColor((getResources().getColor(R.color.normal)));
                setMeal2.setBackgroundColor((getResources().getColor(R.color.normal)));
                setMeal3.setBackgroundColor((getResources().getColor(R.color.chose)));
                setMeal4.setBackgroundColor((getResources().getColor(R.color.normal)));
                setMeal5.setBackgroundColor((getResources().getColor(R.color.normal)));
                select.setVisibility(View.INVISIBLE);
                setMeal_yes.setVisibility(View.VISIBLE);
                setMeal_yes.setText(setMeals[2]);
                OrderActivity.STATUS_SETMEAL = 3;
                break;
            case R.id.tv_setMeal_4://套餐
                setMeal1.setBackgroundColor((getResources().getColor(R.color.normal)));
                setMeal2.setBackgroundColor((getResources().getColor(R.color.normal)));
                setMeal3.setBackgroundColor((getResources().getColor(R.color.normal)));
                setMeal4.setBackgroundColor((getResources().getColor(R.color.chose)));
                setMeal5.setBackgroundColor((getResources().getColor(R.color.normal)));
                select.setVisibility(View.INVISIBLE);
                setMeal_yes.setVisibility(View.VISIBLE);
                setMeal_yes.setText(setMeals[3]);
                OrderActivity.STATUS_SETMEAL = 4;
                break;
            case R.id.tv_setMeal_5://套餐
                setMeal1.setBackgroundColor((getResources().getColor(R.color.normal)));
                setMeal2.setBackgroundColor((getResources().getColor(R.color.normal)));
                setMeal3.setBackgroundColor((getResources().getColor(R.color.normal)));
                setMeal4.setBackgroundColor((getResources().getColor(R.color.normal)));
                setMeal5.setBackgroundColor((getResources().getColor(R.color.chose)));
                select.setVisibility(View.INVISIBLE);
                setMeal_yes.setVisibility(View.VISIBLE);
                setMeal_yes.setText(setMeals[4]);
                OrderActivity.STATUS_SETMEAL = 5;
                break;

            case R.id.btn_sure://确定
                Intent intent = new Intent(mContext, SureActivity.class);
                intent.putExtra("setMeal", OrderActivity.STATUS_SETMEAL);
                intent.putExtra("addMaterial", OrderActivity.STATUS_ADDMATERIAL);
                startActivity(intent);
                break;
            case R.id.btn_select://捣蛋一下
                Random r = new Random();
                int a = r.nextInt(5) + 1;//产生1-5的随机数。nextInt(5)是产生0-4的随机数
                int b = r.nextInt(4) + 1;//产生1-4的随机数。
                OrderActivity.STATUS_SETMEAL = a;
                OrderActivity.STATUS_ADDMATERIAL = b;

                select.setVisibility(View.INVISIBLE);//捣蛋一下隐藏

                if (OrderActivity.STATUS_SETMEAL == 1) {//随机选中一个套餐
                    setMeal1.setBackgroundColor((getResources().getColor(R.color.chose)));
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText(setMeals[0]);
                } else if (OrderActivity.STATUS_SETMEAL == 2) {
                    setMeal2.setBackgroundColor((getResources().getColor(R.color.chose)));
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText(setMeals[1]);
                } else if (OrderActivity.STATUS_SETMEAL == 3) {
                    setMeal3.setBackgroundColor((getResources().getColor(R.color.chose)));
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText(setMeals[2]);
                } else if (OrderActivity.STATUS_SETMEAL == 4) {
                    setMeal4.setBackgroundColor((getResources().getColor(R.color.chose)));
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText(setMeals[3]);
                } else if (OrderActivity.STATUS_SETMEAL == 5) {
                    setMeal5.setBackgroundColor((getResources().getColor(R.color.chose)));
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText(setMeals[4]);
                }

                if (OrderActivity.STATUS_ADDMATERIAL == 1) {//随机选中一个加料
                    addMaterial_yes.setVisibility(View.VISIBLE);
                    addMaterial_yes.setText( addMaterials[0]);
                } else if (OrderActivity.STATUS_ADDMATERIAL == 2) {
                    addMaterial_yes.setVisibility(View.VISIBLE);
                    addMaterial_yes.setText( addMaterials[1]);
                } else if (OrderActivity.STATUS_ADDMATERIAL == 3) {
                    addMaterial_yes.setVisibility(View.VISIBLE);
                    addMaterial_yes.setText( addMaterials[2]);
                } else if (OrderActivity.STATUS_ADDMATERIAL == 4) {
                    addMaterial_yes.setVisibility(View.VISIBLE);
                    addMaterial_yes.setText( addMaterials[3]);
                }
        }
    }
}

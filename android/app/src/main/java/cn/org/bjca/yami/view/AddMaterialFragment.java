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
import android.widget.TextView;

import java.util.Random;

import cn.org.bjca.yami.OrderActivity;
import cn.org.bjca.yami.R;
import cn.org.bjca.yami.SureActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddMaterialFragment extends Fragment implements View.OnClickListener {

    DragFrameLayout m_dragFrameLayout;
    Context mContext;
    private TextView setMeal_yes;
    private TextView addMaterial_yes;
    private View addMaterial1;
    private View addMaterial2;
    private View addMaterial3;
    private View addMaterial4;
    private Button sure;
    private View select;
    private String[] setMeals = new String[5];
    private String[] addMaterials = new String[4];

    public AddMaterialFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public AddMaterialFragment(Context context) {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_material, container, false);

        m_dragFrameLayout = (DragFrameLayout) view.findViewById(R.id.df_addMaterial);
        setMeal_yes = view.findViewById(R.id.tv_setMeal_yes);
        addMaterial_yes = view.findViewById(R.id.tv_addMaterial_yes);
        select = view.findViewById(R.id.btn_select);
        addMaterial1 = view.findViewById(R.id.tv_addMaterial1);
        addMaterial2 = view.findViewById(R.id.tv_addMaterial2);
        addMaterial3 = view.findViewById(R.id.tv_addMaterial3);
        addMaterial4 = view.findViewById(R.id.tv_addMaterial4);
        sure = view.findViewById(R.id.btn_sure);

        //设置悬浮
        m_dragFrameLayout.addDragChildView(setMeal_yes);
        m_dragFrameLayout.addDragChildView(addMaterial_yes);

        sure.setOnClickListener(this);
        select.setOnClickListener(this);
        addMaterial1.setOnClickListener(this);
        addMaterial2.setOnClickListener(this);
        addMaterial3.setOnClickListener(this);
        addMaterial4.setOnClickListener(this);

        //恢复选择过的状态
        if (OrderActivity.STATUS_SETMEAL != 0) {
            select.setVisibility(View.INVISIBLE);
            setMeal_yes.setVisibility(View.VISIBLE);
            if (OrderActivity.STATUS_SETMEAL == 1) {
                setMeal_yes.setText("套餐一");
            }
            if (OrderActivity.STATUS_SETMEAL == 2) {
                setMeal_yes.setText("套餐二");
            }
            if (OrderActivity.STATUS_SETMEAL == 3) {
                setMeal_yes.setText("套餐三");
            }
            if (OrderActivity.STATUS_SETMEAL == 4) {
                setMeal_yes.setText("套餐四");
            }
            if (OrderActivity.STATUS_SETMEAL == 5) {
                setMeal_yes.setText("套餐五");
            }
        }
        if (OrderActivity.STATUS_ADDMATERIAL != 0) {
            select.setVisibility(View.INVISIBLE);
            addMaterial_yes.setVisibility(View.VISIBLE);
            if (OrderActivity.STATUS_ADDMATERIAL == 1) {
                addMaterial_yes.setText("加料一");
                addMaterial1.setBackgroundColor((getResources().getColor(R.color.chose)));
            }
            if (OrderActivity.STATUS_ADDMATERIAL == 2) {
                addMaterial_yes.setText("加料二");
                addMaterial2.setBackgroundColor((getResources().getColor(R.color.chose)));
            }
            if (OrderActivity.STATUS_ADDMATERIAL == 3) {
                addMaterial_yes.setText("加料三");
                addMaterial3.setBackgroundColor((getResources().getColor(R.color.chose)));
            }
            if (OrderActivity.STATUS_ADDMATERIAL == 4) {
                addMaterial_yes.setText("加料四");
                addMaterial4.setBackgroundColor((getResources().getColor(R.color.chose)));
            }

        }
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_addMaterial1://套餐
                addMaterial1.setBackgroundColor((getResources().getColor(R.color.chose)));
                addMaterial2.setBackgroundColor((getResources().getColor(R.color.normal)));
                addMaterial3.setBackgroundColor((getResources().getColor(R.color.normal)));
                addMaterial4.setBackgroundColor((getResources().getColor(R.color.normal)));
                select.setVisibility(View.INVISIBLE);
                addMaterial_yes.setVisibility(View.VISIBLE);
                addMaterial_yes.setText(addMaterials[0]);
                OrderActivity.STATUS_ADDMATERIAL = 1;
                break;
            case R.id.tv_addMaterial2://套餐
                addMaterial1.setBackgroundColor((getResources().getColor(R.color.normal)));
                addMaterial2.setBackgroundColor((getResources().getColor(R.color.chose)));
                addMaterial3.setBackgroundColor((getResources().getColor(R.color.normal)));
                addMaterial4.setBackgroundColor((getResources().getColor(R.color.normal)));
                select.setVisibility(View.INVISIBLE);
                addMaterial_yes.setVisibility(View.VISIBLE);
                addMaterial_yes.setText(addMaterials[1]);
                OrderActivity.STATUS_ADDMATERIAL = 2;
                break;
            case R.id.tv_addMaterial3://套餐
                addMaterial1.setBackgroundColor((getResources().getColor(R.color.normal)));
                addMaterial2.setBackgroundColor((getResources().getColor(R.color.normal)));
                addMaterial3.setBackgroundColor((getResources().getColor(R.color.chose)));
                addMaterial4.setBackgroundColor((getResources().getColor(R.color.normal)));
                select.setVisibility(View.INVISIBLE);
                addMaterial_yes.setVisibility(View.VISIBLE);
                addMaterial_yes.setText(addMaterials[2]);
                OrderActivity.STATUS_ADDMATERIAL = 3;
                break;
            case R.id.tv_addMaterial4://套餐
                addMaterial1.setBackgroundColor((getResources().getColor(R.color.normal)));
                addMaterial2.setBackgroundColor((getResources().getColor(R.color.normal)));
                addMaterial3.setBackgroundColor((getResources().getColor(R.color.normal)));
                addMaterial4.setBackgroundColor((getResources().getColor(R.color.chose)));
                select.setVisibility(View.INVISIBLE);
                addMaterial_yes.setVisibility(View.VISIBLE);
                addMaterial_yes.setText(addMaterials[3]);
                OrderActivity.STATUS_ADDMATERIAL = 4;
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
                int b = r.nextInt(4) + 1;
                OrderActivity.STATUS_SETMEAL = a;
                OrderActivity.STATUS_ADDMATERIAL = b;

                select.setVisibility(View.INVISIBLE);//捣蛋一下隐藏

                if (OrderActivity.STATUS_ADDMATERIAL == 1) {//随机选中一个加料
                    addMaterial1.setBackgroundColor((getResources().getColor(R.color.chose)));
                    addMaterial_yes.setVisibility(View.VISIBLE);
                    addMaterial_yes.setText(addMaterials[0]);
                } else if (OrderActivity.STATUS_ADDMATERIAL == 2) {
                    addMaterial2.setBackgroundColor((getResources().getColor(R.color.chose)));
                    addMaterial_yes.setVisibility(View.VISIBLE);
                    addMaterial_yes.setText(addMaterials[1]);
                } else if (OrderActivity.STATUS_ADDMATERIAL == 3) {
                    addMaterial3.setBackgroundColor((getResources().getColor(R.color.chose)));
                    addMaterial_yes.setVisibility(View.VISIBLE);
                    addMaterial_yes.setText(addMaterials[2]);
                } else if (OrderActivity.STATUS_ADDMATERIAL == 4) {
                    addMaterial4.setBackgroundColor((getResources().getColor(R.color.chose)));
                    addMaterial_yes.setVisibility(View.VISIBLE);
                    addMaterial_yes.setText(addMaterials[3]);
                }

                if (OrderActivity.STATUS_SETMEAL == 1) {//随机选中一个套餐
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText( setMeals[0]);
                } else if (OrderActivity.STATUS_SETMEAL == 2) {
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText( setMeals[1]);
                } else if (OrderActivity.STATUS_SETMEAL == 3) {
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText( setMeals[2]);
                } else if (OrderActivity.STATUS_SETMEAL == 4) {
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText( setMeals[3]);
                }else if (OrderActivity.STATUS_SETMEAL == 5) {
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText( setMeals[4]);
                }
        }
    }
}

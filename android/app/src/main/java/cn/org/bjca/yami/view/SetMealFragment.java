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
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.Random;

import cn.org.bjca.yami.OrderActivity;
import cn.org.bjca.yami.R;
import cn.org.bjca.yami.SureActivity;
import cn.org.bjca.yami.bean.FoodBean;
import cn.org.bjca.yami.utils.GlobalPara;

import static cn.org.bjca.yami.OrderActivity.STATUS_ADDMATERIAL;
import static cn.org.bjca.yami.OrderActivity.STATUS_SETMEAL;

/**
 * 套餐Fragment
 */

public class SetMealFragment extends Fragment implements View.OnClickListener {

    Context mContext;
    private TextView setMeal1;//套餐1
    private TextView setMeal2;//套餐2
    private TextView setMeal3;//套餐3
    private TextView setMeal4;//套餐4
    private TextView setMeal5;//套餐5
    private View select;//捣蛋一下
    private DragView setMeal_yes;//已点套餐
    private DragView addMaterial_yes;//已点加料
    private View sure;//确定
    private String[] setMeals = new String[5];//套餐详情
    private String[] addMaterials = new String[4];//加料详情

    private ArrayList<FoodBean.SetMeal> setMeal;
    private ArrayList<FoodBean.AddMaterial> addMaterial;

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

        setMeal_yes = view.findViewById(R.id.tv_setMeal_yes);
        addMaterial_yes = view.findViewById(R.id.tv_addMaterial_yes);
        setMeal1 = view.findViewById(R.id.tv_setMeal_1);
        setMeal2 = view.findViewById(R.id.tv_setMeal_2);
        setMeal3 = view.findViewById(R.id.tv_setMeal_3);
        setMeal4 = view.findViewById(R.id.tv_setMeal_4);
        setMeal5 = view.findViewById(R.id.tv_setMeal_5);
        select = view.findViewById(R.id.btn_select);
        sure = view.findViewById(R.id.btn_sure);

        setMeal1.setOnClickListener(this);
        setMeal2.setOnClickListener(this);
        setMeal3.setOnClickListener(this);
        setMeal4.setOnClickListener(this);
        setMeal5.setOnClickListener(this);
        select.setOnClickListener(this);
        sure.setOnClickListener(this);

        getDataFromServer();//从服务器获取信息
        return view;
    }

    private void getDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, GlobalPara.ORDER_URL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = (String) responseInfo.result;
                        parseData(result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        error.printStackTrace();

                    }
                });

    }

    private void parseData(String result) {
        Gson gson = new Gson();
        FoodBean foodBean = gson.fromJson(result, FoodBean.class);
        setMeal = foodBean.getSetMeal();
        addMaterial = foodBean.getAddMaterial();
        for (int i = 0; i < setMeal.size(); i++) {
            setMeals[i] = setMeal.get(i).getSetMealHead() + "\n" + setMeal.get(i).getSetMealBody();
        }
        for (int i = 0; i < addMaterial.size(); i++) {
            addMaterials[i] = addMaterial.get(i).getAddMaterialHead() + "\n"
                    + addMaterial.get(i).getAddMaterialBody();
        }
        setMeal1.setText(setMeals[0]);
        setMeal2.setText(setMeals[1]);
        setMeal3.setText(setMeals[2]);
        setMeal4.setText(setMeals[3]);
        setMeal5.setText(setMeals[4]);

        onStart();
    }

    @Override
    public void onStart() { //恢复选择过的状态
        super.onStart();

        if (STATUS_SETMEAL != 0) {//套餐状态不为0说明已经选择了套餐，将已选套餐设置为显示并设置颜色、文字
            select.setVisibility(View.INVISIBLE);
            setMeal_yes.setVisibility(View.VISIBLE);
            if (STATUS_SETMEAL == 1) {
                setMeal_yes.setText(setMeals[0]);
                setMeal1.setBackgroundColor((getResources().getColor(R.color.chose)));
            }
            if (STATUS_SETMEAL == 2) {
                setMeal_yes.setText(setMeals[1]);
                setMeal2.setBackgroundColor((getResources().getColor(R.color.chose)));
            }
            if (STATUS_SETMEAL == 3) {
                setMeal_yes.setText(setMeals[2]);
                setMeal3.setBackgroundColor((getResources().getColor(R.color.chose)));
            }
            if (STATUS_SETMEAL == 4) {
                setMeal_yes.setText(setMeals[3]);
                setMeal4.setBackgroundColor((getResources().getColor(R.color.chose)));
            }
            if (STATUS_SETMEAL == 5) {
                setMeal_yes.setText(setMeals[4]);
                setMeal5.setBackgroundColor((getResources().getColor(R.color.chose)));
            }
        } else {//套餐状态为零时，恢复原始状态
            setMeal_yes.setVisibility(View.INVISIBLE);
            setMeal1.setBackgroundColor((getResources().getColor(R.color.normal)));
            setMeal2.setBackgroundColor((getResources().getColor(R.color.normal)));
            setMeal3.setBackgroundColor((getResources().getColor(R.color.normal)));
            setMeal4.setBackgroundColor((getResources().getColor(R.color.normal)));
            setMeal5.setBackgroundColor((getResources().getColor(R.color.normal)));
            if (STATUS_ADDMATERIAL == 0) select.setVisibility(View.VISIBLE);
        }

        if (OrderActivity.STATUS_ADDMATERIAL != 0) {//加料状态不为零时，显示加料信息
            select.setVisibility(View.INVISIBLE);
            addMaterial_yes.setVisibility(View.VISIBLE);
            if (OrderActivity.STATUS_ADDMATERIAL == 1) {
                addMaterial_yes.setText(addMaterials[0]);
            }
            if (OrderActivity.STATUS_ADDMATERIAL == 2) {
                addMaterial_yes.setText(addMaterials[1]);
            }
            if (OrderActivity.STATUS_ADDMATERIAL == 3) {
                addMaterial_yes.setText(addMaterials[2]);
            }
            if (OrderActivity.STATUS_ADDMATERIAL == 4) {
                addMaterial_yes.setText(addMaterials[3]);
            }

        } else {
            addMaterial_yes.setVisibility(View.INVISIBLE);
        }
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
                STATUS_SETMEAL = 1;
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
                STATUS_SETMEAL = 2;
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
                STATUS_SETMEAL = 3;
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
                STATUS_SETMEAL = 4;
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
                STATUS_SETMEAL = 5;
                break;

            case R.id.btn_sure://确定
                if (STATUS_SETMEAL == 0 && STATUS_ADDMATERIAL == 0)
                    Toast.makeText(mContext, "请至少选择一个套餐", Toast.LENGTH_SHORT).show();
                else if (STATUS_SETMEAL == 0)
                    Toast.makeText(mContext, "您还未选择套餐", Toast.LENGTH_SHORT).show();
                else {
                    jumpSureActivity();
                }
                break;
            case R.id.btn_select://捣蛋一下
                Random r = new Random();
                int a = r.nextInt(5) + 1;//产生1-5的随机数。nextInt(5)是产生0-4的随机数
                int b = r.nextInt(4) + 1;//产生1-4的随机数。
                STATUS_SETMEAL = a;
                OrderActivity.STATUS_ADDMATERIAL = b;

                select.setVisibility(View.INVISIBLE);//捣蛋一下隐藏

                if (STATUS_SETMEAL == 1) {//随机选中一个套餐
                    setMeal1.setBackgroundColor((getResources().getColor(R.color.chose)));
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText(setMeals[0]);
                } else if (STATUS_SETMEAL == 2) {
                    setMeal2.setBackgroundColor((getResources().getColor(R.color.chose)));
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText(setMeals[1]);
                } else if (STATUS_SETMEAL == 3) {
                    setMeal3.setBackgroundColor((getResources().getColor(R.color.chose)));
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText(setMeals[2]);
                } else if (STATUS_SETMEAL == 4) {
                    setMeal4.setBackgroundColor((getResources().getColor(R.color.chose)));
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText(setMeals[3]);
                } else if (STATUS_SETMEAL == 5) {
                    setMeal5.setBackgroundColor((getResources().getColor(R.color.chose)));
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText(setMeals[4]);
                }

                if (OrderActivity.STATUS_ADDMATERIAL == 1) {//随机选中一个加料
                    addMaterial_yes.setVisibility(View.VISIBLE);
                    addMaterial_yes.setText(addMaterials[0]);
                } else if (OrderActivity.STATUS_ADDMATERIAL == 2) {
                    addMaterial_yes.setVisibility(View.VISIBLE);
                    addMaterial_yes.setText(addMaterials[1]);
                } else if (OrderActivity.STATUS_ADDMATERIAL == 3) {
                    addMaterial_yes.setVisibility(View.VISIBLE);
                    addMaterial_yes.setText(addMaterials[2]);
                } else if (OrderActivity.STATUS_ADDMATERIAL == 4) {
                    addMaterial_yes.setVisibility(View.VISIBLE);
                    addMaterial_yes.setText(addMaterials[3]);
                }
        }


    }

    private void jumpSureActivity() {
        Intent intent = new Intent(mContext, SureActivity.class);

        if (STATUS_SETMEAL == 1) intent.putExtra("setMeal", setMeals[0]);
        else if (STATUS_SETMEAL == 2) intent.putExtra("setMeal", setMeals[1]);
        else if (STATUS_SETMEAL == 3) intent.putExtra("setMeal", setMeals[2]);
        else if (STATUS_SETMEAL == 4) intent.putExtra("setMeal", setMeals[3]);
        else if (STATUS_SETMEAL == 5) intent.putExtra("setMeal", setMeals[4]);

        if (STATUS_ADDMATERIAL == 1) intent.putExtra("addMaterial", addMaterials[0]);
        else if (STATUS_ADDMATERIAL == 2) intent.putExtra("addMaterial", addMaterials[1]);
        else if (STATUS_ADDMATERIAL == 3) intent.putExtra("addMaterial", addMaterials[2]);
        else if (STATUS_ADDMATERIAL == 4) intent.putExtra("addMaterial", addMaterials[3]);
        startActivity(intent);
    }
}

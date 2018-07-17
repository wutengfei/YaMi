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
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.Random;

import cn.org.bjca.yami.activity.order.OrderActivity;
import cn.org.bjca.yami.R;
import cn.org.bjca.yami.activity.order.SureActivity;
import cn.org.bjca.yami.bean.FoodBean;
import cn.org.bjca.yami.utils.GlobalPara;

import static cn.org.bjca.yami.activity.order.OrderActivity.STATUS_ADDMATERIAL;
import static cn.org.bjca.yami.activity.order.OrderActivity.STATUS_SETMEAL;

public class AddMaterialFragment extends Fragment implements View.OnClickListener {

    Context mContext;
    private DragView setMeal_yes;//已点套餐
    private DragView addMaterial_yes;//已点加料
    private TextView addMaterial1;
    private TextView addMaterial2;
    private TextView addMaterial3;
    private TextView addMaterial4;
    private Button sure;
    private View select;
    private String[] setMeals = new String[5];
    private String[] addMaterials = new String[4];

    private ArrayList<FoodBean.SetMeal> setMeal;
    private ArrayList<FoodBean.AddMaterial> addMaterial;

    public AddMaterialFragment() {
    }

    @SuppressLint("ValidFragment")
    public AddMaterialFragment(Context context) {
        mContext = context;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_material, container, false);

        setMeal_yes = view.findViewById(R.id.tv_setMeal_yes2);
        addMaterial_yes = view.findViewById(R.id.tv_addMaterial_yes2);
        select = view.findViewById(R.id.btn_select);
        addMaterial1 = view.findViewById(R.id.tv_addMaterial1);
        addMaterial2 = view.findViewById(R.id.tv_addMaterial2);
        addMaterial3 = view.findViewById(R.id.tv_addMaterial3);
        addMaterial4 = view.findViewById(R.id.tv_addMaterial4);
        sure = view.findViewById(R.id.btn_sure);

        sure.setOnClickListener(this);
        select.setOnClickListener(this);
        addMaterial1.setOnClickListener(this);
        addMaterial2.setOnClickListener(this);
        addMaterial3.setOnClickListener(this);
        addMaterial4.setOnClickListener(this);

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
        addMaterial1.setText(addMaterials[0]);
        addMaterial2.setText(addMaterials[1]);
        addMaterial3.setText(addMaterials[2]);
        addMaterial4.setText(addMaterials[3]);

        onStart();
    }

    @Override
    public void onStart() {//恢复选择过的状态
        super.onStart();
        if (OrderActivity.STATUS_SETMEAL != 0) {//套餐状态不为0说明已经选择了套餐，将已选套餐设置为显示并设置颜色、文字
            select.setVisibility(View.INVISIBLE);
            setMeal_yes.setVisibility(View.VISIBLE);
            if (OrderActivity.STATUS_SETMEAL == 1) {
                setMeal_yes.setText(setMeals[0]);
            }
            if (OrderActivity.STATUS_SETMEAL == 2) {
                setMeal_yes.setText(setMeals[1]);
            }
            if (OrderActivity.STATUS_SETMEAL == 3) {
                setMeal_yes.setText(setMeals[2]);
            }
            if (OrderActivity.STATUS_SETMEAL == 4) {
                setMeal_yes.setText(setMeals[3]);
            }
            if (OrderActivity.STATUS_SETMEAL == 5) {
                setMeal_yes.setText(setMeals[4]);
            }
        } else {//套餐状态为零时，恢复原始状态
            setMeal_yes.setVisibility(View.INVISIBLE);
        }
        if (OrderActivity.STATUS_ADDMATERIAL != 0) {//加料状态不为零时，显示加料信息
            select.setVisibility(View.INVISIBLE);
            addMaterial_yes.setVisibility(View.VISIBLE);
            if (OrderActivity.STATUS_ADDMATERIAL == 1) {
                addMaterial_yes.setText(addMaterials[0]);
                addMaterial1.setBackgroundColor((getResources().getColor(R.color.chose)));
            }
            if (OrderActivity.STATUS_ADDMATERIAL == 2) {
                addMaterial_yes.setText(addMaterials[1]);
                addMaterial2.setBackgroundColor((getResources().getColor(R.color.chose)));
            }
            if (OrderActivity.STATUS_ADDMATERIAL == 3) {
                addMaterial_yes.setText(addMaterials[2]);
                addMaterial3.setBackgroundColor((getResources().getColor(R.color.chose)));
            }
            if (OrderActivity.STATUS_ADDMATERIAL == 4) {
                addMaterial_yes.setText(addMaterials[3]);
                addMaterial4.setBackgroundColor((getResources().getColor(R.color.chose)));
            }

        } else {//加料状态为零时，恢复原始状态
            addMaterial_yes.setVisibility(View.INVISIBLE);
            addMaterial1.setBackgroundColor((getResources().getColor(R.color.normal)));
            addMaterial2.setBackgroundColor((getResources().getColor(R.color.normal)));
            addMaterial3.setBackgroundColor((getResources().getColor(R.color.normal)));
            addMaterial4.setBackgroundColor((getResources().getColor(R.color.normal)));
            if (STATUS_SETMEAL == 0) select.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_addMaterial1://加料1
                addMaterial1.setBackgroundColor((getResources().getColor(R.color.chose)));
                addMaterial2.setBackgroundColor((getResources().getColor(R.color.normal)));
                addMaterial3.setBackgroundColor((getResources().getColor(R.color.normal)));
                addMaterial4.setBackgroundColor((getResources().getColor(R.color.normal)));
                select.setVisibility(View.INVISIBLE);
                addMaterial_yes.setVisibility(View.VISIBLE);
                addMaterial_yes.setText(addMaterials[0]);
                OrderActivity.STATUS_ADDMATERIAL = 1;
                break;
            case R.id.tv_addMaterial2://加料2
                addMaterial1.setBackgroundColor((getResources().getColor(R.color.normal)));
                addMaterial2.setBackgroundColor((getResources().getColor(R.color.chose)));
                addMaterial3.setBackgroundColor((getResources().getColor(R.color.normal)));
                addMaterial4.setBackgroundColor((getResources().getColor(R.color.normal)));
                select.setVisibility(View.INVISIBLE);
                addMaterial_yes.setVisibility(View.VISIBLE);
                addMaterial_yes.setText(addMaterials[1]);
                OrderActivity.STATUS_ADDMATERIAL = 2;
                break;
            case R.id.tv_addMaterial3://加料3
                addMaterial1.setBackgroundColor((getResources().getColor(R.color.normal)));
                addMaterial2.setBackgroundColor((getResources().getColor(R.color.normal)));
                addMaterial3.setBackgroundColor((getResources().getColor(R.color.chose)));
                addMaterial4.setBackgroundColor((getResources().getColor(R.color.normal)));
                select.setVisibility(View.INVISIBLE);
                addMaterial_yes.setVisibility(View.VISIBLE);
                addMaterial_yes.setText(addMaterials[2]);
                OrderActivity.STATUS_ADDMATERIAL = 3;
                break;
            case R.id.tv_addMaterial4://加料4
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
                    setMeal_yes.setText(setMeals[0]);
                } else if (OrderActivity.STATUS_SETMEAL == 2) {
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText(setMeals[1]);
                } else if (OrderActivity.STATUS_SETMEAL == 3) {
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText(setMeals[2]);
                } else if (OrderActivity.STATUS_SETMEAL == 4) {
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText(setMeals[3]);
                } else if (OrderActivity.STATUS_SETMEAL == 5) {
                    setMeal_yes.setVisibility(View.VISIBLE);
                    setMeal_yes.setText(setMeals[4]);
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

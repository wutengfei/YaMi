package cn.org.bjca.yami;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.org.bjca.yami.view.AddMaterialFragment;
import cn.org.bjca.yami.view.CustomToolBar;
import cn.org.bjca.yami.view.SetMealFragment;

/**
 * 点餐页
 */
public class OrderActivity extends AppCompatActivity implements View.OnClickListener {
    private SetMealFragment setMealFragment;
    private AddMaterialFragment addMaterialFragment;
    CustomToolBar toolbar;
    public static int STATUS_SETMEAL = 0;
    public static int STATUS_ADDMATERIAL = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getSupportActionBar().hide();//隐藏标题栏

        //标题栏设置
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setMainTitle("今日美味");
        toolbar.setMainTitleLeftText("返回");
        toolbar.setMainTitleRightText("预告");
        TextView prediction = findViewById(R.id.lt_main_title_right);
        TextView back = findViewById(R.id.lt_main_title_left);
        prediction.setOnClickListener(this);
        back.setOnClickListener(this);

        setDefaultFragment(); // 设置默认的Fragment
        TextView setMeal = findViewById(R.id.tv_setMeal_yes);
        TextView addMaterial = findViewById(R.id.tv_addMaterial_yes);
        // Button sure=findViewById(R.id.sure);
        setMeal.setOnClickListener(this);
        addMaterial.setOnClickListener(this);
        //   sure.setOnClickListener(this);
    }

    private void setDefaultFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_order, new SetMealFragment(this));
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        switch (v.getId()) {
            case R.id.tv_setMeal_yes://套餐
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();
                if (setMealFragment == null) {
                    setMealFragment = new SetMealFragment(this);
                }

                transaction.replace(R.id.fragment_order, setMealFragment);
                transaction.commit();
                break;
            case R.id.tv_addMaterial_yes://加料
                // 开启Fragment事务
                FragmentTransaction transaction2 = fm.beginTransaction();

                if (addMaterialFragment == null) {//加料
                    addMaterialFragment = new AddMaterialFragment(this);
                }

                transaction2.replace(R.id.fragment_order, addMaterialFragment);
                transaction2.commit();



                break;
            case R.id.lt_main_title_left://返回
                finish();
                break;
            case R.id.lt_main_title_right://预告
                startActivity(new Intent(this, PredictionActivity.class));
                break;
        }

    }
}

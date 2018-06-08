package cn.org.bjca.yami;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.org.bjca.yami.view.CustomToolBar;

public class SureActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure);
        getSupportActionBar().hide();//隐藏标题栏

        //标题栏设置
        CustomToolBar toolbar = findViewById(R.id.tool_bar);
        toolbar.setMainTitle("即刻确认");
        toolbar.setMainTitleLeftText("返回");
        toolbar.setMainTitleRightText("");
        TextView back = findViewById(R.id.lt_main_title_left);
        back.setOnClickListener(this);

        Button button = findViewById(R.id.btn_finish);
        button.setOnClickListener(this);
        TextView setMealName = findViewById(R.id.tv_setMealName);
        TextView addMaterialName = findViewById(R.id.tv_addMaterialName);

     //得到套餐和加料的选择信息
        Intent intent = getIntent();
        int setMeal = intent.getIntExtra("setMeal", 0);
        int addMaterial = intent.getIntExtra("addMaterial", 0);

        if (setMeal == 1) {
            setMealName.setText("套餐1");
        } else if (setMeal == 2) {
            setMealName.setText("套餐2");
        }else if (setMeal == 3) {
            setMealName.setText("套餐3");
        }else if (setMeal == 4) {
            setMealName.setText("套餐4");
        }else if (setMeal == 5) {
            setMealName.setText("套餐5");
        }
        if (addMaterial == 1) {
            addMaterialName.setText("加料1");
        } else if (addMaterial == 2) {
            addMaterialName.setText("加料2");
        }else if (addMaterial == 3) {
            addMaterialName.setText("加料3");
        }else if (addMaterial == 4) {
            addMaterialName.setText("加料4");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lt_main_title_left:
                finish();
                break;
            case R.id.btn_finish:
                //TODO 网络交互


                OrderActivity.STATUS_ADDMATERIAL = 0;
                OrderActivity.STATUS_SETMEAL = 0;
                Toast.makeText(this, "订餐成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

    }
}

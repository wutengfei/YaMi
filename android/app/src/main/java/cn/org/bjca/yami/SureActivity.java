package cn.org.bjca.yami;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.org.bjca.yami.view.CustomToolBar;

public class SureActivity extends AppCompatActivity implements View.OnClickListener{

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
    }

    @Override
    public void onClick(View view) {
       finish();
    }
}

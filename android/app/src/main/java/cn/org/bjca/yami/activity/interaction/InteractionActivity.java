package cn.org.bjca.yami.activity.interaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import cn.org.bjca.yami.R;
import cn.org.bjca.yami.SysApplication;
import cn.org.bjca.yami.view.CustomToolBar;

/**
 * 互动模块
 */
public class InteractionActivity extends AppCompatActivity implements  View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题栏
        setContentView(R.layout.activity_interaction);
        getSupportActionBar().hide();//隐藏标题栏
        SysApplication.getInstance().addActivity(this);

       initView();
    }

    private void initView() {
        //标题栏设置
        CustomToolBar toolbar = findViewById(R.id.tool_bar);
        toolbar.setMainTitle("互动");
        toolbar.setMainTitleLeftText("返回");
        toolbar.setMainTitleRightText("");
        TextView back = findViewById(R.id.lt_main_title_left);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}

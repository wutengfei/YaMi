package cn.org.bjca.yami.activity.manage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.org.bjca.yami.R;
import cn.org.bjca.yami.SysApplication;

/**
 * 管理模块
 */
public class ManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        SysApplication.getInstance().addActivity(this);
    }
}

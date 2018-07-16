package cn.org.bjca.yami;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import cn.org.bjca.yami.utils.GlobalPara;
import cn.org.bjca.yami.view.CustomToolBar;

public class SureActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;//姓名
    private EditText cipher;//部门口令

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure);
        SysApplication.getInstance().addActivity(this);
        getSupportActionBar().hide();//隐藏标题栏


        name = findViewById(R.id.et_name);
        cipher = findViewById(R.id.et_cipher);

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
        } else if (setMeal == 3) {
            setMealName.setText("套餐3");
        } else if (setMeal == 4) {
            setMealName.setText("套餐4");
        } else if (setMeal == 5) {
            setMealName.setText("套餐5");
        }
        if (addMaterial == 1) {
            addMaterialName.setText("加料1");
        } else if (addMaterial == 2) {
            addMaterialName.setText("加料2");
        } else if (addMaterial == 3) {
            addMaterialName.setText("加料3");
        } else if (addMaterial == 4) {
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
                finished();
                break;

        }

    }

    private void finished() {
        //获取姓名和暗号
        String username = name.getText().toString().trim();
        String userCipher = cipher.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(userCipher))
            Toast.makeText(this, "请输入完整姓名和暗号", Toast.LENGTH_SHORT).show();
        else {
            HttpUtils utils = new HttpUtils();
            JSONObject sureJson = new JSONObject();
            try {
                sureJson.put("username", username);
                sureJson.put("userCipher", userCipher);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            RequestParams params = new RequestParams();
            params.addBodyParameter("sure_json", sureJson.toString());
            utils.send(HttpRequest.HttpMethod.POST, GlobalPara.SURE_URL, params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            OrderActivity.STATUS_ADDMATERIAL = 0;//清零点餐信息
                            OrderActivity.STATUS_SETMEAL = 0;
                            Toast.makeText(SureActivity.this, "订餐成功", Toast.LENGTH_SHORT).show();
                            finish();

                        }

                        @Override
                        public void onFailure(HttpException error, String msg) {
                            error.printStackTrace();
                            // Toast.makeText(SureActivity.this, "订餐失败，网络错误", Toast.LENGTH_SHORT).show();
                            //TODO  以下等连通服务器后删除
                            OrderActivity.STATUS_ADDMATERIAL = 0;//清零点餐信息
                            OrderActivity.STATUS_SETMEAL = 0;
                            Toast.makeText(SureActivity.this, "订餐成功", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    });

        }
    }

}

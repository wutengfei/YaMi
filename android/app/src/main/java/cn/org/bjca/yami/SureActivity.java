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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.org.bjca.yami.utils.GlobalPara;
import cn.org.bjca.yami.view.CustomToolBar;

public class SureActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;//姓名
    private EditText cipher;//部门口令
    private TextView remainTime;//剩余时间
    private CustomToolBar toolbar;//标题栏
    TextView back;//返回
    TextView setMealName;//套餐名
    TextView addMaterialName;//加料名
    Button sure;//完成

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure);
        SysApplication.getInstance().addActivity(this);
        getSupportActionBar().hide();//隐藏标题栏


        name = findViewById(R.id.et_name);
        cipher = findViewById(R.id.et_cipher);
        remainTime = findViewById(R.id.tv_time);
        toolbar = findViewById(R.id.tool_bar);
        back = findViewById(R.id.lt_main_title_left);
        sure = findViewById(R.id.btn_finish);
        setMealName = findViewById(R.id.tv_setMealName);
        addMaterialName = findViewById(R.id.tv_addMaterialName);

        toolbar.setMainTitle("即刻确认");
        toolbar.setMainTitleLeftText("返回");
        toolbar.setMainTitleRightText("");

        back.setOnClickListener(this);
        sure.setOnClickListener(this);
        remainTime();//显示剩余时间


        //得到套餐和加料的选择信息
        Intent intent = getIntent();
        String setMeal = intent.getStringExtra("setMeal");
        String addMaterial = intent.getStringExtra("addMaterial");

        setMealName.setText(setMeal);
        addMaterialName.setText(addMaterial);
    }

    /**
     * 剩余时间
     */
    private void remainTime() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, GlobalPara.SURE_URL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = (String) responseInfo.result;
                        countDown(result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        error.printStackTrace();
                        countDown("2030-01-01 19:00:00");//倒计时

                    }
                });
    }


    /**
     * 倒计时
     *
     * @param str 服务器传来的截止时间
     */
    private void countDown(String str) {

        String end_time = str;//自己设定

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start_time = formatter.format(currentTime);

        long time = getLongtime(end_time) - getLongtime(start_time);

        long days = time / (1000 * 60 * 60 * 24);
        long hours = (time - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (time - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
        long second = (time - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)
                - minutes * (1000 * 60)) / 1000;

        System.out.println("时间间隔 " + hours + "小时" + minutes + "分钟" + second + "秒");
        remainTime.setText("距离点餐截止还剩 " + hours + ":" + minutes + ":" + second);
    }

    /**
     * 将时间转换成毫秒
     *
     * @param str 字符串时间
     * @return 毫秒
     */
    public static long getLongtime(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long millionSeconds = 0;//毫秒
        try {
            millionSeconds = sdf.parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return millionSeconds;
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

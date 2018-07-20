package cn.org.bjca.yami.activity.order;

import android.gesture.Prediction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.viewpagerindicator.TitlePageIndicator;

import java.util.ArrayList;

import cn.org.bjca.yami.R;
import cn.org.bjca.yami.SysApplication;
import cn.org.bjca.yami.adapter.TabPageIndicatorAdapter;
import cn.org.bjca.yami.bean.PredictionBean;
import cn.org.bjca.yami.utils.GlobalPara;
import cn.org.bjca.yami.view.CustomToolBar;

/**
 * 预告页
 */
public class PredictionActivity extends FragmentActivity implements
        View.OnClickListener, ViewPager.OnPageChangeListener {

    private ViewPager pager;
    private int currentPosition = 0;//当前页面
    private ArrayList<PredictionBean.Food> foods;//从服务端获取的食品信息
    private TitlePageIndicator indicator;//指示器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题栏
        setContentView(R.layout.activity_prediction);
        SysApplication.getInstance().addActivity(this);
        initView();
        falseData();//假数据
        //TODO 暂时使用假数据
        //   getDataFromServer();//解析服务端传来的数据
    }

    private void initView() {
        //标题栏设置
        CustomToolBar toolbar = findViewById(R.id.tool_bar);
        toolbar.setMainTitle("美味预告");
        toolbar.setMainTitleLeftText("返回");
        toolbar.setMainTitleRightText("");
        TextView back = findViewById(R.id.lt_main_title_left);
        back.setOnClickListener(this);

        pager = (ViewPager) findViewById(R.id.pager);
        indicator = (TitlePageIndicator) findViewById(R.id.indicator);

        indicator.setOnPageChangeListener(this);//指示器设置监听
    }

    //TODO 假数据，回头删
    private void falseData() {
        PredictionBean preBean = new PredictionBean();
        PredictionBean.Food food1 = preBean.new Food("10001", "周一", "赵老师", "套餐1", "套餐2",
                "套餐3", "套餐4", "套餐5", "加料1", "加料2", "加料3", "加料4");
        PredictionBean.Food food2 = preBean.new Food("10002", "周二", "钱老师", "套餐1", "套餐2",
                "套餐3", "套餐4", "套餐5", "加料1", "加料2", "加料3", "加料4");
        PredictionBean.Food food3 = preBean.new Food("10003", "周三", "孙老师", "套餐1", "套餐2",
                "套餐3", "套餐4", "套餐5", "加料1", "加料2", "加料3", "加料4");
        PredictionBean.Food food4 = preBean.new Food("10004", "周四", "李老师", "套餐1", "套餐2",
                "套餐3", "套餐4", "套餐5", "加料1", "加料2", "加料3", "加料4");
        PredictionBean.Food food5 = preBean.new Food("10005", "周五", "周老师", "套餐1", "套餐2",
                "套餐3", "套餐4", "套餐5", "加料1", "加料2", "加料3", "加料4");
        foods = new ArrayList<PredictionBean.Food>();
        foods.add(food1);
        foods.add(food2);
        foods.add(food3);
        foods.add(food4);
        foods.add(food5);

        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getSupportFragmentManager(), foods);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1);
        indicator.setViewPager(pager);
    }

    private void getDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, GlobalPara.PREDICTION_URL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = (String) responseInfo.result;
                        parseData(result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        error.printStackTrace();
                        setContentView(R.layout.activity_error);
                    }
                });

    }

    protected void parseData(String result) {
        Gson gson = new Gson();
        PredictionBean data = gson.fromJson(result, PredictionBean.class);

        foods = data.getFoods();

        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getSupportFragmentManager(), foods);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1);
        indicator.setViewPager(pager);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //若viewpager滑动未停止，直接返回
        if (state != ViewPager.SCROLL_STATE_IDLE) return;
        //若当前为第一张，设置页面为倒数第二张
        if (currentPosition == 0) {
            pager.setCurrentItem(foods.size() - 2, false);
        } else if (currentPosition == foods.size() - 1) {
            // 若当前为倒数第一张，设置页面为第二张
            pager.setCurrentItem(1, false);
        }
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}

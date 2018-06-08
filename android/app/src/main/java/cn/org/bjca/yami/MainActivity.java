package cn.org.bjca.yami;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import cn.org.bjca.yami.adapter.MyPagerAdapter;
import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PagerContainer;

/**
 * Created by 吴腾飞 on 2018/5/10.
 * 主页，滑动页面
 */
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    ViewPager pager;
    private int currentPosition = 0;//当前页面

    private ArrayList<Integer> data = new ArrayList<>();//存放图片地址

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();//隐藏标题栏
        //添加图片
        data.add(R.drawable.left);
        data.add(R.drawable.center);
        data.add(R.drawable.right);

        PagerContainer container = (PagerContainer) findViewById(R.id.pager_container);
        pager = container.getViewPager();

        pager.setAdapter(new MyPagerAdapter(data, pager, this));
        pager.setClipChildren(true);
        pager.setOffscreenPageLimit(15);
        pager.addOnPageChangeListener(this);
        pager.setCurrentItem(1);

        boolean showTransformer = true;//true显示缩放效果，false不显示
        if (showTransformer) {

            new CoverFlow.Builder()//设置缩放效果
                    .with(pager)
                    .scale(0.3f)
                    .pagerMargin(-30)
                    .spaceSize(0f)
                    .build();

        } else {
            pager.setPageMargin(30);
        }
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
//        //若viewpager滑动未停止，直接返回
//        if (state != ViewPager.SCROLL_STATE_IDLE) return;
//        //若当前为第一张，设置页面为倒数第二张
//        if (currentPosition == 0) {
//            pager.setCurrentItem(data.size() - 2, false);
//        } else if (currentPosition == data.size() - 1) {
//            // 若当前为倒数第一张，设置页面为第二张
//            pager.setCurrentItem(1, false);
//        }

    }

    public void enter(View view) {
        if (currentPosition == 1) {//定餐模块
            startActivity(new Intent(this, OrderActivity.class));
        } else if (currentPosition == 2) {//管理模块
            startActivity(new Intent(this, ManageActivity.class));
        } else if (currentPosition == 0) {//交互模块
            startActivity(new Intent(this, InteractionActivity.class));
        }
    }

}

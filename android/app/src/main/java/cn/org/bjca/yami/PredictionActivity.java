package cn.org.bjca.yami;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.TabPageIndicator;


import cn.org.bjca.yami.view.CustomToolBar;
import cn.org.bjca.yami.view.ItemFragment;

/**
 * 预告页
 */
public class PredictionActivity extends FragmentActivity implements
        View.OnClickListener {
    /**
     * Tab标题
     */
    private static final String[] TITLE = new String[]{"周一", "周二", "周三", "周四",
            "周五"};
    private ViewPager mViewPager;
    private TabPageIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);
        // getSupportActionBar().hide();//隐藏标题栏
        initView();

    }

    @Override
    public void onClick(View view) {
        finish();
    }


    private void initView() {
        //标题栏设置
        CustomToolBar toolbar = findViewById(R.id.tool_bar);
        toolbar.setMainTitle("美味预告");
        toolbar.setMainTitleLeftText("返回");
        toolbar.setMainTitleRightText("");
        TextView back = findViewById(R.id.lt_main_title_left);
        back.setOnClickListener(this);

        //ViewPager的adapter
        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.vp_menu_detail);
        pager.setAdapter(adapter);

        //实例化TabPageIndicator然后设置ViewPager与之关联
        final TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        //如果我们要对ViewPager设置监听，用indicator设置就行了
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                Toast.makeText(getApplicationContext(), TITLE[arg0], Toast.LENGTH_SHORT).show();
                // indicator.setCurrentItem(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }

    /**
     * ViewPager适配器
     *
     * @author len
     */
    class TabPageIndicatorAdapter extends FragmentPagerAdapter {
        public TabPageIndicatorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //新建一个Fragment来展示ViewPager item的内容，并传递参数
            Fragment fragment = new ItemFragment();
            Bundle args = new Bundle();
            args.putString("arg", TITLE[position]);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position % TITLE.length];
        }

        @Override
        public int getCount() {
            return TITLE.length;
        }
    }

}

package cn.org.bjca.yami;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PageItemClickListener;
import me.crosswall.lib.coverflow.core.PagerContainer;

/**
 * Created by 吴腾飞 on 2018/5/10.
 * 主页，滑动页面
 */
public class MainActivity extends AppCompatActivity {

    ViewPager pager;


    private ArrayList<Integer> data = new ArrayList<>();//存放图片地址

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data.add(R.drawable.left);
        data.add(R.drawable.center);
        data.add(R.drawable.right);
        PagerContainer container = (PagerContainer) findViewById(R.id.pager_container);
        pager = container.getViewPager();

        pager.setAdapter(new MyPagerAdapter(data, pager));
        pager.setClipChildren(true);
        pager.setOffscreenPageLimit(15);

        container.setPageItemClickListener(new PageItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "position:" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        boolean showTransformer = getIntent().getBooleanExtra("showTransformer",
                false);


        if (showTransformer) {

            new CoverFlow.Builder()
                    .with(pager)
                    .scale(0.3f)
                    .pagerMargin(getResources().getDimensionPixelSize(R.dimen.pager_margin))
                    .spaceSize(0f)
                    .build();

        } else {
            pager.setPageMargin(30);
        }
    }


    private class MyPagerAdapter<T> extends PagerAdapter implements
            ViewPager.OnPageChangeListener {

        private int currentPosition = 0;//当前页面
        protected ArrayList<View> views;
        private ViewPager mPager;

        public MyPagerAdapter(ArrayList<T> datas, ViewPager viewPager) {

            views = new ArrayList<>();
//        如果数据大于一条
            if (datas.size() > 1) {
//            添加最后一页到第一页
                datas.add(0, datas.get(datas.size() - 1));
//            添加第一页(经过上行的添加已经是第二页了)到最后一页
                datas.add(datas.get(1));
            }
//            for (T data:datas) {
//                views.add(getItemView(data,datas.get()));
//            }
            for (int i = 0; i < datas.size(); i++) {
                views.add(getItemView(datas.get(i)));
            }

            mPager = viewPager;
            viewPager.setAdapter(this);
            viewPager.addOnPageChangeListener(this);
            viewPager.setCurrentItem(1000, false);
        }

        protected View getItemView(T data) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_cover,
                    null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_cover);
            imageView.setImageDrawable(getResources().getDrawable((Integer) data));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {

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
                mPager.setCurrentItem(views.size() - 2, false);

            } else if (currentPosition == views.size() - 1) {
                // 若当前为倒数第一张，设置页面为第二张
                mPager.setCurrentItem(1, false);
            }

        }
    }
    public void enter(View view) {
        startActivity(new Intent(this,OrderActivity.class));
    }

}

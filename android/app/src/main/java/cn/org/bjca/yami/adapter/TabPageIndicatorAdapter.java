package cn.org.bjca.yami.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.org.bjca.yami.bean.PredictionBean;
import cn.org.bjca.yami.view.ItemFragment;

//预告页的adapter
public class TabPageIndicatorAdapter extends FragmentPagerAdapter {
    protected ArrayList<Fragment> views;//存放pager的每个条目
    private String[] weekday;//周几，指示器的标题
    private ArrayList<PredictionBean.Food> oldFoods;

    public TabPageIndicatorAdapter(FragmentManager fm, ArrayList<PredictionBean.Food> foods) {
        super(fm);
        oldFoods = foods;

        views = new ArrayList<>();

        if (foods.size() > 1) {  // 如果数据大于一条
            foods.add(0, foods.get(foods.size() - 1));// 添加最后一页到第一页
            foods.add(foods.get(1)); //添加第一页(经过上行的添加已经是第二页了)到最后一页
        }

        //把所有的条目加到view集合中
        for (int i = 0; i < foods.size(); i++) {
            views.add(getItem(i));
        }

        weekday = new String[foods.size()];
        for (int i = 0; i < foods.size(); i++) {
            weekday[i] = foods.get(i).getWeekday();
        }

    }

    @Override
    public Fragment getItem(int position) {
        //新建一个Fragment来展示ViewPager item的内容，并传递参数
        Fragment fragment = new ItemFragment();
        Bundle args = new Bundle();

        if (oldFoods != null) {
            args.putSerializable("food", oldFoods.get(position % 5));
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return weekday[position % weekday.length];//防止越界
    }

    @Override
    public int getCount() {
        return oldFoods.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return super.isViewFromObject(view, object);
    }
}

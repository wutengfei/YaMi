package cn.org.bjca.yami.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import cn.org.bjca.yami.R;

//主页的adapter
public class MyPagerAdapter extends PagerAdapter {
    Context mContext;

    protected ArrayList<View> views;

    public MyPagerAdapter(ArrayList<Integer> datas, ViewPager viewPager, Context context) {
        mContext = context;
        views = new ArrayList<>();

        // 如果数据大于一条
        if (datas.size() > 1) {
            // 添加最后一页到第一页
            datas.add(0, datas.get(datas.size() - 1));
            //添加第一页(经过上行的添加已经是第二页了)到最后一页
            datas.add(datas.get(1));
        }

        for (int i = 0; i < datas.size(); i++) {
            views.add(getItemView(datas.get(i)));
        }
    }

    protected View getItemView(Integer data) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cover,
                null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_cover);
        imageView.setImageDrawable(mContext.getResources().getDrawable((Integer) data));
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
}
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

public  class MyPagerAdapter extends PagerAdapter {
Context mContext;

        protected ArrayList<View> views;

        public MyPagerAdapter(ArrayList<Integer> datas, ViewPager viewPager, Context context) {
            mContext=context;
            views = new ArrayList<>();
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
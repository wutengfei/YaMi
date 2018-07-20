package cn.org.bjca.yami.activity.order;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.org.bjca.yami.R;
import cn.org.bjca.yami.SysApplication;
import cn.org.bjca.yami.view.AddMaterialFragment;
import cn.org.bjca.yami.view.CustomToolBar;
import cn.org.bjca.yami.view.DragView;
import cn.org.bjca.yami.view.SetMealFragment;

/**
 * 点餐页
 */
public class OrderActivity extends AppCompatActivity implements View.OnClickListener {
    private SetMealFragment setMealFragment;
    private AddMaterialFragment addMaterialFragment;
    CustomToolBar toolbar;
    public static int STATUS_SETMEAL = 0;//0:未选择，1-5:套餐1-5
    public static int STATUS_ADDMATERIAL = 0;//0：未选择，1-5：加料1-4
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        SysApplication.getInstance().addActivity(this);
        getSupportActionBar().hide();//隐藏标题栏

        //标题栏设置
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setMainTitle("今日美味");
        toolbar.setMainTitleLeftText("返回");
        toolbar.setMainTitleRightText("预告");
        TextView prediction = findViewById(R.id.lt_main_title_right);
        TextView back = findViewById(R.id.lt_main_title_left);
        prediction.setOnClickListener(this);
        back.setOnClickListener(this);

        setDefaultFragment(); // 设置默认的Fragment
        TextView setMeal = findViewById(R.id.tv_setMeal);
        TextView addMaterial = findViewById(R.id.tv_addMaterial);
        TextView select = findViewById(R.id.tv_select);

        setMeal.setOnClickListener(this);
        addMaterial.setOnClickListener(this);
        select.setOnClickListener(this);

    }

    private void setDefaultFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_order, new SetMealFragment(this));
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        switch (v.getId()) {
            case R.id.tv_setMeal://套餐
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();
                if (setMealFragment == null) {
                    setMealFragment = new SetMealFragment(this);
                }

                transaction.replace(R.id.fragment_order, setMealFragment);
                transaction.commit();
                break;
            case R.id.tv_addMaterial://加料
                // 开启Fragment事务
                FragmentTransaction transaction2 = fm.beginTransaction();

                if (addMaterialFragment == null) {//加料
                    addMaterialFragment = new AddMaterialFragment(this);
                }

                transaction2.replace(R.id.fragment_order, addMaterialFragment);
                transaction2.commit();

                break;
            case R.id.tv_select://已选
                startAnimation();
                break;
            case R.id.lt_main_title_left://返回
                finish();
                break;
            case R.id.lt_main_title_right://预告
                startActivity(new Intent(this, PredictionActivity.class));
                break;
        }

    }

    private void startAnimation() {
        final DragView dragView_setMeal = findViewById(R.id.tv_setMeal_yes);
        final DragView dragView_addMate = findViewById(R.id.tv_addMaterial_yes);
        final DragView dragView_setMeal2 = findViewById(R.id.tv_setMeal_yes2);
        final DragView dragView_addMate2 = findViewById(R.id.tv_addMaterial_yes2);

        Animation translateAnimation = new TranslateAnimation( //X轴初始位置
                //X轴移动的开始位置
                Animation.RELATIVE_TO_SELF, 0.0f,//相对于自己移动,移动的百分比
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_SELF, -0.5f);
        translateAnimation.setDuration(500);//动画持续的时间为0.5s
        translateAnimation.setFillEnabled(true);//使其可以填充效果从而不回到原地
        translateAnimation.setFillAfter(true);//不回到起始位置

        //判断当前Fragment是哪个Fragment,并启动动画
        currentFragment = getFragmentManager().findFragmentById(R.id.fragment_order);
        if (currentFragment != null && currentFragment instanceof SetMealFragment) {
            if (STATUS_SETMEAL != 0)
                dragView_setMeal.startAnimation(translateAnimation);
            if (STATUS_ADDMATERIAL != 0)
                dragView_addMate.startAnimation(translateAnimation);
        } else if (currentFragment != null && currentFragment instanceof AddMaterialFragment) {
            if (STATUS_ADDMATERIAL != 0)
                dragView_addMate2.startAnimation(translateAnimation);
            if (STATUS_SETMEAL != 0)
                dragView_setMeal2.startAnimation(translateAnimation);
        }

        // 设置动画监听
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            // 动画执行结束,改变view的位置（TranslateAnimation只负责实现位移动画效果，并不改变view的位置）
            @Override
            public void onAnimationEnd(Animation animation) {
                if (currentFragment != null && currentFragment instanceof SetMealFragment) {
                    int width = dragView_setMeal.getWidth();
                    int height = dragView_setMeal.getHeight();

                    int left_setMeal = dragView_setMeal.getLeft();
                    int top_setMeal = dragView_setMeal.getTop();
                    dragView_setMeal.clearAnimation();
                    dragView_setMeal.layout(left_setMeal, top_setMeal - height / 2,
                            left_setMeal + width, top_setMeal - height / 2 + height);

                    int left_addMate = dragView_addMate.getLeft();
                    int top_addMate = dragView_addMate.getTop();
                    dragView_addMate.clearAnimation();
                    dragView_addMate.layout(left_addMate, top_addMate - height / 2,
                            left_addMate + width, top_addMate - height / 2 + height);
                } else if (currentFragment != null && currentFragment instanceof AddMaterialFragment) {
                    int width = dragView_setMeal2.getWidth();
                    int height = dragView_setMeal2.getHeight();

                    int left_setMeal2 = dragView_setMeal2.getLeft();
                    int top_setMeal2 = dragView_setMeal2.getTop();
                    dragView_setMeal2.clearAnimation();
                    dragView_setMeal2.layout(left_setMeal2, top_setMeal2 - height / 2,
                            left_setMeal2 + width, top_setMeal2 - height / 2 + height);

                    int left_addMate2 = dragView_addMate2.getLeft();
                    int top_addMate2 = dragView_addMate2.getTop();
                    dragView_addMate2.clearAnimation();
                    dragView_addMate2.layout(left_addMate2, top_addMate2 - height / 2,
                            left_addMate2 + width, top_addMate2 - height / 2 + height);
                }
            }
        });
    }


}


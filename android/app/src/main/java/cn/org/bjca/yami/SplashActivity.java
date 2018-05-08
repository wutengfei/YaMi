package cn.org.bjca.yami;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.*;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class SplashActivity extends Activity {


    RelativeLayout relativeLayout;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout_splash);
        imageView = (ImageView) findViewById(R.id.iv_splash);
       // startAnim();//动画包含渐变，旋转，缩放
        startAnimation();//动画包含渐变
    }

    private void startAnim() {

        // 动画集合
        AnimationSet set = new AnimationSet(false);

        // 旋转动画
        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotate.setDuration(1000);// 动画时间
        rotate.setFillAfter(true);// 保持动画状态

        // 缩放动画
        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scale.setDuration(1000);// 动画时间
        scale.setFillAfter(true);// 保持动画状态

        // 渐变动画
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(2000);// 动画时间
        alpha.setFillAfter(true);// 保持动画状态

        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);

        // 设置动画监听
        set.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            // 动画执行结束
            @Override
            public void onAnimationEnd(Animation animation) {
                jumpNextPage();
            }
        });

        relativeLayout.startAnimation(set);
    }

    private void startAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.1f);
        alphaAnimation.setDuration(1000);
        imageView.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(
                new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        jumpNextPage();
                    }
                });
    }

    /**
     * 跳转下一个页面
     */

    private void jumpNextPage() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}

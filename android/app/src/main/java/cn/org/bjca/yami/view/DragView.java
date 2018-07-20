package cn.org.bjca.yami.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import cn.org.bjca.yami.R;
import cn.org.bjca.yami.utils.ScreenUtil;

import static cn.org.bjca.yami.activity.order.OrderActivity.STATUS_ADDMATERIAL;
import static cn.org.bjca.yami.activity.order.OrderActivity.STATUS_SETMEAL;

public class DragView extends TextView {

    private int width;//View的宽度
    private int height;//view的高度
    private int screenWidth;
    private int screenHeight;
    private Context context;

    //是否拖动
    private boolean isDrag = false;
    public static int viewTopY;//view上边的Y坐标
    public static int viewLeftX;//view左边的X坐标

    public boolean isDrag() {
        return isDrag;
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        screenWidth = ScreenUtil.getScreenWidth(context);
        screenHeight = ScreenUtil.getScreenHeight(context) - getStatusBarHeight();
        //控件顶部的Y点
        viewTopY = ScreenUtil.getScreenViewTopHeight(this);
        //控件左侧的X点
        viewLeftX = ScreenUtil.getScreenViewLeftHeight(this);
    }

    public int getStatusBarHeight() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        return getResources().getDimensionPixelSize(resourceId);
    }


    private float downX;
    private float downY;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int l, r, t, b;
        if (this.isEnabled()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.e("kid", "ACTION_DOWN");
                    isDrag = false;
                    downX = event.getX();
                    downY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.e("kid", "ACTION_MOVE");
                    final float xDistance = event.getX() - downX;
                    final float yDistance = event.getY() - downY;
                    //int l,r,t,b;
                    //当水平或者垂直滑动距离大于10,才算拖动事件
                    if (Math.abs(xDistance) > 10 || Math.abs(yDistance) > 10) {
                        Log.e("kid", "Drag");
                        isDrag = true;
                        l = (int) (getLeft() + xDistance);
                        r = l + width;
                        t = (int) (getTop() + yDistance);
                        b = t + height;
                        //不划出边界判断,此处应按照项目实际情况,因为本项目需求移动的位置是手机全屏,
                        // 所以才能这么写,如果是固定区域,要得到父控件的宽高位置后再做处理
                        if (l < 0) {
                            l = 0;
                            r = l + width;
                        } else if (r > screenWidth - width) {
                            r = screenWidth - width;
                            l = r - width;
                        }
                        if (t < 0) {
                            t = 0;
                            b = t + height;
                        } else if (b > screenHeight) {
                            b = screenHeight;
                            t = b - height;
                        }

                        this.layout(l, t, r, b);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    Log.e("kid", "ACTION_UP");
                    setPressed(false);

                    int[] location = new int[2];
                    this.getLocationOnScreen(location);
                    int viewX = location[0];
                    int viewY = location[1];

                    int screenHeight = ScreenUtil.getScreenHeight(context);
                    Log.i("location", "X:" + viewX + "---Y:" + viewY + "---screenH:" + screenHeight);
                    if (viewY < 2 * screenHeight / 3) {//当view拖动到屏幕的1/3以上就消失
                        startAnimation();//消失动画
                        if (this.getId() == R.id.tv_setMeal_yes || this.getId() == R.id.tv_setMeal_yes2)
                            STATUS_SETMEAL = 0;//清空已点套餐的状态
                        if (this.getId() == R.id.tv_addMaterial_yes || this.getId() == R.id.tv_addMaterial_yes2)
                            STATUS_ADDMATERIAL = 0;//清空已点加料的状态
                    }
                    this.destroyDrawingCache();

                    break;
                case MotionEvent.ACTION_CANCEL:
                    Log.e("kid", "ACTION_CANCEL");

                    setPressed(false);
                    break;
            }
            return true;
        }
        return false;
    }

    private void startAnimation() {
        // 动画集合
        AnimationSet set = new AnimationSet(false);
        // 缩放动画
        ScaleAnimation scale = new ScaleAnimation(1, 1.2f, 1, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(1000);// 动画时间
        scale.setFillAfter(true);// 保持动画状态

        // 渐变动画
        AlphaAnimation alpha = new AlphaAnimation(1, 0);
        alpha.setDuration(1000);// 动画时间
        alpha.setFillAfter(true);// 保持动画状态

        set.addAnimation(scale);
        set.addAnimation(alpha);

        this.startAnimation(set);
        this.setVisibility(GONE);
    }

    @Override
    public void getLocationOnScreen(int[] outLocation) {
        super.getLocationOnScreen(outLocation);

    }
}
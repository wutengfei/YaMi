package cn.org.bjca.yami;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.org.bjca.yami.view.AddMaterialFragment;
import cn.org.bjca.yami.view.SetMealFragment;

/**
 * 点餐页
 */
public class OrderActivity extends AppCompatActivity  implements View.OnClickListener{
    private SetMealFragment setMealFragment;
    private AddMaterialFragment addMaterialFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        TextView setMeal=findViewById(R.id.tv_setMeal);
        TextView addMaterial=findViewById(R.id.tv_addMaterial);
        setMeal.setOnClickListener(this);
        addMaterial.setOnClickListener(this);
        // 设置默认的Fragment
        setDefaultFragment();
    }


    private void setDefaultFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        setMealFragment = new SetMealFragment();
        transaction.replace(R.id.fragment_order, setMealFragment);
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.tv_setMeal:
                if (setMealFragment == null) {
                    setMealFragment = new SetMealFragment();
                }
                // 使用当前Fragment的布局替代content的控件
                transaction.replace(R.id.fragment_order, setMealFragment);
                break;
            case R.id.tv_addMaterial:
                if (addMaterialFragment == null) {
                    addMaterialFragment = new AddMaterialFragment();
                }
                transaction.replace(R.id.fragment_order, addMaterialFragment);
                break;
        }
        //添加返回栈,按back键可以返回上一个碎片
      //  transaction.addToBackStack(null);
        // 事务提交
        transaction.commit();
    }
}

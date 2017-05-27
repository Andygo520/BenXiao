package com.cnmar.benxiao.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cnmar.benxiao.R;
import com.cnmar.benxiao.fragment.HomeFragment;
import com.cnmar.benxiao.fragment.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private HomeFragment homeFragment;
    private ProfileFragment profileFragment;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rb4)
    RadioButton rb4;
    @BindView(R.id.rg)
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        //      默认选中首页
        setTabSelection(1);
    }

    public void init(){
        rb1.setVisibility(View.VISIBLE);
        rb2.setVisibility(View.VISIBLE);
        rb1.setText("首页");
        rb2.setText("我的资料");
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        setTabSelection(1);
                        break;
                    case R.id.rb2:
                        setTabSelection(2);
                        break;
                }
            }
        });
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (homeFragment == null && fragment instanceof HomeFragment) {
            homeFragment = (HomeFragment) fragment;
        } else if (profileFragment == null && fragment instanceof ProfileFragment) {
            profileFragment = (ProfileFragment) fragment;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);//将任务置于后台
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。1表示主页，2表示我的资料
     */
    private void setTabSelection(int index) {
        // 开启一个Fragment事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragment(transaction);
        switch (index) {
            // 点击主页tab
            case 1:
                rb1.setChecked(true);
                rb1.setBackgroundColor(getResources().getColor(R.color.colorBase));
                rb2.setBackgroundColor(getResources().getColor(R.color.color_white));
//                动态设置单选按钮文本上下左右的图片（不需要的地方设置为0）
                Drawable drawable1 = getResources().getDrawable(R.drawable.home_selected);
                Drawable drawable2 = getResources().getDrawable(R.drawable.profile);
                drawable1.setBounds(0, 15, 70, 85);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
                drawable2.setBounds(0, 15, 70, 85);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
                rb1.setCompoundDrawables(null, drawable1, null, null);//只放上边
                rb2.setCompoundDrawables(null, drawable2, null, null);//只放上边
//                rb2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.profile,0,0);
                if (homeFragment == null) {
                    // 如果HomeFragment为空，则创建一个并添加到界面上
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.content, homeFragment);
                } else {
                    // 如果HomeFragment不为空，则直接将它显示出来
                    transaction.show(homeFragment);
                }
                break;
            // 点击我的资料tab
            case 2:
                rb2.setChecked(true);
                rb1.setBackgroundColor(getResources().getColor(R.color.color_white));
                rb2.setBackgroundColor(getResources().getColor(R.color.colorBase));
//                动态设置单选按钮文本上下左右的图片（不需要的地方设置为0）
                Drawable drawable3 = getResources().getDrawable(R.drawable.home);
                Drawable drawable4 = getResources().getDrawable(R.drawable.profile_selected);
                drawable3.setBounds(0, 15, 70, 85);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
                drawable4.setBounds(0, 15, 70, 85);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
                rb1.setCompoundDrawables(null, drawable3, null, null);//只放上边
                rb2.setCompoundDrawables(null, drawable4, null, null);//只放上边
                if (profileFragment == null) {
                    // 如果profileFragment为空，则创建一个并添加到界面上
                    profileFragment = new ProfileFragment();
                    transaction.add(R.id.content, profileFragment);
                } else {
                    // 如果friendFragment不为空，则直接将它显示出来
                    transaction.show(profileFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (profileFragment != null) {
            transaction.hide(profileFragment);
        }
    }

}

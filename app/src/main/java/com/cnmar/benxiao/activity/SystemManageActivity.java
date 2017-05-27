package com.cnmar.benxiao.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cnmar.benxiao.R;
import com.cnmar.benxiao.fragment.MaterialInOrderFragment;
import com.cnmar.benxiao.fragment.SystemUserFragment;
import com.cnmar.benxiao.utils.ACache;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SystemManageActivity extends AppCompatActivity {
//    private inOrderFragment inOrderFragment;
    private SystemUserFragment systemUserFragment;
    private MaterialInOrderFragment inOrderFragment;
    @BindView(R.id.left_arrow)
    LinearLayout leftArrow;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rg)
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_manage);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        ArrayList<String> secondMenu = new ArrayList<>();
//        根据登录页获得的一级菜单与二级菜单的关系，设置默认选中项
        secondMenu = (ArrayList<String>) ACache.get(SystemManageActivity.this).getAsObject(getString(R.string.HOME_XTGL));
        if (secondMenu.contains(getString(R.string.rbUser))) {
            rb1.setVisibility(View.VISIBLE);
            rb1.setText(R.string.rbUser);
            setTabSelection(1);
        }
        if (secondMenu.contains(getString(R.string.rbLog))) {
            rb2.setVisibility(View.VISIBLE);
            rb2.setText(R.string.rbLog);
            if (!secondMenu.contains(getString(R.string.rbUser)))
                setTabSelection(2);
        }

        title.setText(R.string.HOME_XTGL);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
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

    @OnClick({R.id.left_arrow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_arrow:
                startActivity(new Intent(SystemManageActivity.this, MainActivity.class));
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(SystemManageActivity.this, MainActivity.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (inOrderFragment == null && fragment instanceof MaterialInOrderFragment) {
            inOrderFragment = (MaterialInOrderFragment) fragment;
        } else if (systemUserFragment == null && fragment instanceof SystemUserFragment) {
            systemUserFragment = (SystemUserFragment) fragment;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示用户管理，1表示操作日志
     */
    private void setTabSelection(int index) {
        // 开启一个Fragment事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragment(transaction);
        switch (index) {
            case 1:
                rb1.setChecked(true);
                rb1.setBackgroundColor(getResources().getColor(R.color.colorBase));
                rb2.setBackgroundColor(getResources().getColor(R.color.color_white));
                //                动态设置单选按钮文本上下左右的图片
                Drawable drawable1 = getResources().getDrawable(R.drawable.user_manage_selected);
                Drawable drawable2 = getResources().getDrawable(R.drawable.log);
                drawable1.setBounds(0, 15, 70, 85);//第一0是距左边距离，第二15是距上边距离，长宽为70
                drawable2.setBounds(0, 15, 70, 85);//第一0是距左边距离，第二15是距上边距离，长宽为70
                rb1.setCompoundDrawables(null, drawable1, null, null);//只放上边
                rb2.setCompoundDrawables(null, drawable2, null, null);//只放上边
                if (systemUserFragment == null) {
                    systemUserFragment = new SystemUserFragment();
                    transaction.add(R.id.content, systemUserFragment);
                } else {
                    transaction.show(systemUserFragment);
                }
                break;
            case 2:
                rb2.setChecked(true);
                rb1.setBackgroundColor(getResources().getColor(R.color.color_white));
                rb2.setBackgroundColor(getResources().getColor(R.color.colorBase));
                //                动态设置单选按钮文本上下左右的图片
                Drawable drawable3 = getResources().getDrawable(R.drawable.user_manage);
                Drawable drawable4 = getResources().getDrawable(R.drawable.log_selected);
                drawable3.setBounds(0, 15, 70, 85);//第一0是距左边距离，第二15是距上边距离，长宽为70
                drawable4.setBounds(0, 15, 70, 85);//第一0是距左边距离，第二15是距上边距离，长宽为70
                rb1.setCompoundDrawables(null, drawable3, null, null);//只放上边
                rb2.setCompoundDrawables(null, drawable4, null, null);//只放上边
                if (inOrderFragment == null) {
                    inOrderFragment = new MaterialInOrderFragment();
                    transaction.add(R.id.content, inOrderFragment);
                } else {
                    transaction.show(inOrderFragment);
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
        if (inOrderFragment != null) {
            transaction.hide(inOrderFragment);
        }
        if (systemUserFragment != null) {
            transaction.hide(systemUserFragment);
        }
    }
}

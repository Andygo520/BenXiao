package com.cnmar.benxiao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.cnmar.benxiao.R;
import com.cnmar.benxiao._Url;
import com.cnmar.benxiao.retrofit.Api;
import com.cnmar.benxiao.retrofit.RxHelper;
import com.cnmar.benxiao.retrofit.RxSubscriber;
import com.cnmar.benxiao.utils.ACache;
import com.cnmar.benxiao.utils.CheckNetwork;
import com.cnmar.benxiao.utils.SPHelper;
import com.cnmar.benxiao.utils.ToastUtil;
import com.cnmar.benxiao.utils.UniversalHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import component.system.model.SystemAppMenu;
import component.system.model.SystemRole;
import component.system.model.SystemUser;

public class LoginActivity extends AppCompatActivity {
    private ArrayList<Integer> roleId;//角色id列表
    private ArrayList<String> menu;//app的一级菜单列表
    private ArrayList<String> secondMenu;//app的二级菜单列表
    private Map<String, List<String>> menuMap;
    private ACache cache;
    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.chkPassword)
    CheckBox chkPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        roleId = new ArrayList<>();
        menu = new ArrayList<>();
        secondMenu = new ArrayList<>();
//      存储角色id以及一级二级菜单信息
        cache = ACache.get(LoginActivity.this);
        boolean auto_login = SPHelper.getBoolean(LoginActivity.this, "auto_login", false);
        String name = SPHelper.getString(LoginActivity.this, "name", "");
//        如果用户没有勾选自动登录，下次登陆的时候默认勾选，并自动显示用户名
        if (!auto_login && !name.equals("")) {
            chkPassword.setChecked(true);
            etUsername.setText(name);
            etUsername.setSelection(name.length());//光标移到字符串末尾
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

    @OnClick(R.id.btnLogin)
    public void onClick() {
        final String name = etUsername.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        //       检查网络连接
        if (!CheckNetwork.isNetworkConnected(this)) {
            Toast.makeText(this, R.string.check_network, Toast.LENGTH_SHORT).show();
            return;
        }
        if (name.equals("") && password.equals("")) {
            Toast.makeText(this, R.string.input_account_and_password, Toast.LENGTH_SHORT).show();
            return;
        }
        if (name.equals("")) {
            Toast.makeText(this, R.string.input_account, Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.equals("")) {
            Toast.makeText(this, R.string.input_password, Toast.LENGTH_SHORT).show();
            return;
        }
        String token = UniversalHelper.getToken(_Url.LOGIN);
        Api.getDefault().login(name, password, token)
                .compose(RxHelper.<SystemUser>handleResult())
                .subscribe(new RxSubscriber<SystemUser>(LoginActivity.this) {
                    @Override
                    protected void _onNext(SystemUser systemUser) {
//                      登陆成功就保存用户名，只有勾选了自动登录才保存密码
                        SPHelper.putString(LoginActivity.this, "name", name);
                        if (chkPassword.isChecked()) {
                            SPHelper.putString(LoginActivity.this, "password", password);
                            SPHelper.putBoolean(LoginActivity.this, "auto_login", true);
                        } else {
                            SPHelper.putBoolean(LoginActivity.this, "auto_login", false);
                        }

//                      存储用户id，多处需要使用到
                        int userId = systemUser.getId();
                        SPHelper.putInt(LoginActivity.this, "userId", userId);
//                      获取app一级菜单列表
                        List<SystemAppMenu> appMenus = systemUser.getAppMenus();
//                      获取用户角色列表
                        List<SystemRole> roles = systemUser.getRoles();
                        for (SystemRole role : roles) {
                            int id = role.getId();
                            roleId.add(id);
                        }
                        for (SystemAppMenu appMenu : appMenus) {
                            String menuName = appMenu.getName();
                            menu.add(menuName);
//                         获取app二级菜单列表
                            List<SystemAppMenu> subMenus = appMenu.getSubList();
                            for (SystemAppMenu subMenu : subMenus) {
                                String subMenuName = subMenu.getName();
                                secondMenu.add(subMenuName);
                            }
//                        存放app一级菜单名跟二级菜单列表的对应关系
                            cache.put(menuName, secondMenu);
                        }
//                        存放用户id、一级菜单列表
                        cache.put("roleId", roleId);
                        cache.put("menu", menu);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtil.showToast(LoginActivity.this, message);
                    }
                });
    }
}

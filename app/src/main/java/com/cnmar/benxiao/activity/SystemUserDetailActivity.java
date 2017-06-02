package com.cnmar.benxiao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cnmar.benxiao.R;
import com.cnmar.benxiao._Url;
import com.cnmar.benxiao.retrofit.Api;
import com.cnmar.benxiao.retrofit.RxHelper;
import com.cnmar.benxiao.retrofit.RxSubscriber;
import com.cnmar.benxiao.utils.ToastUtil;
import com.cnmar.benxiao.utils.UniversalHelper;
import com.cnmar.benxiao.utils.UrlHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import component.system.model.SystemUser;


public class SystemUserDetailActivity extends AppCompatActivity {
    private int id;//用户ID
    @BindView(R.id.left_arrow)
    LinearLayout leftArrow;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tvAccount)
    TextView tvAccount;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvGender)
    TextView tvGender;
    @BindView(R.id.tvBirthday)
    TextView tvBirthday;
    @BindView(R.id.tvState)
    TextView tvState;
    @BindView(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_user_detail);
        ButterKnife.bind(this);

        title.setText(R.string.user_detail);
        id = getIntent().getIntExtra("ID", 0);
        getUserInfoFromNet();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void getUserInfoFromNet() {
        String token = UniversalHelper.getToken(_Url.SYSTEM_USER);
        Api.getDefault().systemUserDetail(id, token)
                .compose(RxHelper.<SystemUser>handleResult())
                .subscribe(new RxSubscriber<SystemUser>(SystemUserDetailActivity.this) {
                    @Override
                    protected void _onNext(SystemUser user) {
                        tvAccount.setText(user.getUsername());
                        tvName.setText(user.getName());
                        tvGender.setText(user.getGender() == null ? "" : user.getGender());
                        tvBirthday.setText(user.getBirthday() == null ? "" : UniversalHelper.getDateString(user.getBirthday(), "yyyy-MM-dd"));
                        tvState.setText(user.getIsEnableVo().getValue());  //账号状态
//                               ImgId>0代表用户设置了图像
                        if (user.getImgId() > 0) {
//                              获取图片的路径，路径=绝对路径+相对路径
                            String path = UrlHelper.URL_IMAGE + user.getImg().getPath();
                            Glide.with(SystemUserDetailActivity.this)
                                    .load(path)
                                    .into(image);
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtil.showToast(SystemUserDetailActivity.this, message);
                    }
                });
    }

    @OnClick(R.id.left_arrow)
    public void onClick() {
        finish();
    }
}

package com.cnmar.benxiao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cnmar.benxiao.R;
import com.cnmar.benxiao._Url;
import com.cnmar.benxiao.activity.LoginActivity;
import com.cnmar.benxiao.retrofit.Api;
import com.cnmar.benxiao.retrofit.RxHelper;
import com.cnmar.benxiao.retrofit.RxSubscriber;
import com.cnmar.benxiao.utils.ACache;
import com.cnmar.benxiao.utils.SPHelper;
import com.cnmar.benxiao.utils.ToastUtil;
import com.cnmar.benxiao.utils.UniversalHelper;
import com.cnmar.benxiao.utils.UrlHelper;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import component.system.model.SystemUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.account)
    TextView account;
    @BindView(R.id.department)
    TextView department;
    @BindView(R.id.duty)
    TextView duty;
    @BindView(R.id.position)
    TextView position;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.birthday)
    TextView birthday;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.logoff)
    Button btnLogOff;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        getProfileFromNet();
        return view;
    }

    public void getProfileFromNet() {
        int userId = SPHelper.getInt(getActivity(), "userId");
        String token = UniversalHelper.getToken(_Url.PROFILE);
        Api.getDefault().profile(userId, token)
                .compose(RxHelper.<SystemUser>handleResult())
                .subscribe(new RxSubscriber<SystemUser>(getActivity()) {
                    @Override
                    protected void _onNext(SystemUser systemUser) {
                        account.setText(systemUser.getUsername());
                        username.setText(systemUser.getName());
                        sex.setText(systemUser.getGenderVo().getValue());
                        if (systemUser.getBirthday() != null) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String day = sdf.format(systemUser.getBirthday());
                            birthday.setText(day);
                        } else
                            birthday.setText("");

                        department.setText(systemUser.getDept() == null ? "" : systemUser.getDept().getName());
                        position.setText(systemUser.getJob() == null ? "" : systemUser.getJob().getName());
                        duty.setText(systemUser.getDuty() == null ? "" : systemUser.getDuty().getName());
                        mobile.setText(systemUser.getPhone() == null ? "" : systemUser.getPhone());
                        if (systemUser.getImgId() > 0) {
//                     获取图片的路径，路径=绝对路径+相对路径
                            String path = UrlHelper.URL_IMAGE + systemUser.getImg().getPath();
                            Glide.with(getActivity())
                                    .load(path)
                                    .into(image);
                        } else
                            image.setImageResource(R.mipmap.user_icon);
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtil.showToast(getActivity(),message);
                    }

                    @Override
                    protected boolean showDialog() {
                        return false;
                    }
                });
    }

    @OnClick(R.id.logoff)
    public void onClick() {
//       注销账号的时候，清除用户数据
        SPHelper.putBoolean(getActivity(), "auto_login", false);
        ACache.get(getActivity()).clear();

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}

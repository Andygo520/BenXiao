package com.cnmar.benxiao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnmar.benxiao.R;
import com.cnmar.benxiao._Url;
import com.cnmar.benxiao.retrofit.Api;
import com.cnmar.benxiao.retrofit.RxHelper;
import com.cnmar.benxiao.retrofit.RxSubscriber;
import com.cnmar.benxiao.utils.ToastUtil;
import com.cnmar.benxiao.utils.UniversalHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import component.supply.model.Supply;

public class SupplyManageDetailActivity extends AppCompatActivity {
    private int id;  //供应商Id
    @BindView(R.id.left_arrow)
    LinearLayout leftArrow;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv11)
    TextView tv11;
    @BindView(R.id.tv12)
    TextView tv12;
    @BindView(R.id.tv21)
    TextView tv21;
    @BindView(R.id.tv22)
    TextView tv22;
    @BindView(R.id.tv31)
    TextView tv31;
    @BindView(R.id.tv41)
    TextView tv41;
    @BindView(R.id.tv42)
    TextView tv42;
    @BindView(R.id.tv51)
    TextView tv51;
    @BindView(R.id.tv52)
    TextView tv52;
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_manage_detail);
        ButterKnife.bind(this);

        title.setText(R.string.supply_detail);
        id = getIntent().getIntExtra("ID", 0);
        getSupplyInfoFromNet();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void getSupplyInfoFromNet() {
        String token= UniversalHelper.getToken(_Url.SUPPLY_LIST);
        Api.getDefault().supplyDetail(id,token)
                .compose(RxHelper.<Supply>handleResult())
                .subscribe(new RxSubscriber<Supply>(SupplyManageDetailActivity.this) {
                    @Override
                    protected void _onNext(Supply supply) {
                        tv11.setText(supply.getCode());
                        tv12.setText(supply.getName());
                        tv21.setText(supply.getTel());
                        tv22.setText(supply.getFax());
                        tv31.setText(supply.getAddress());
                        tv41.setText(supply.getContact());
                        tv42.setText(supply.getJob());
                        tv51.setText(supply.getPhone());
                        tv52.setText(supply.getEmail());
                        String introduction = supply.getIntro();
//                      loadData出现中文乱码，用loadDataWithBaseURL解决问题
//                        webView.loadData(introduction,"text/html","UTF-8");
                        webView.loadDataWithBaseURL("file://", introduction, "text/html", "UTF-8", "about:blank");
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtil.showToast(SupplyManageDetailActivity.this,message);
                    }
                });
    }

    @OnClick(R.id.left_arrow)
    public void onClick() {
        finish();
    }
}

package com.cnmar.benxiao.utils;

import android.content.Context;

import com.cnmar.benxiao.R;
import com.cnmar.benxiao.widget.BottomView;
import com.cnmar.benxiao.widget.CustomDialog;
import com.cnmar.benxiao.widget.HeadView;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Administrator on 2016/9/6.
 */
public class UniversalHelper {
    private static CustomDialog dialog = null;

    public static String getToken(String url) {
        String token = url.replaceAll("http://192.168.1.112:8092/", "");
        if (token.indexOf("/") == 0) {
            token = token.substring(1, token.length());
        }
        if (token.indexOf("/") != -1) {
            token = token.substring(0, token.indexOf("/"));
        }
        if (token.indexOf("?") != -1) {
            token = token.substring(0, token.indexOf("?"));
        }
        try {
            token=md5Encode(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public static String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 显示进度条
     *
     * @param pContext 上下文
     */
    public static void showProgressDialog(Context pContext) {
        dialog = new CustomDialog(pContext, R.style.CustomDialog);
        dialog.show();
    }

    /**
     * 取消进度条
     */
    public static void dismissProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    /**
     * 处理刷新的初始化
     */
    public static void initRefresh(Context context, TwinklingRefreshLayout refreshLayout) {
//        设置刷新头部
        HeadView headerView = new HeadView(context);
        refreshLayout.setHeaderView(headerView);
//        设置刷新尾部
        BottomView loadingView = new BottomView(context);
        refreshLayout.setBottomView(loadingView);
        refreshLayout.setAutoLoadMore(true);//在底部越界的时候自动切换到加载更多模式
    }

    /**
     * 从Date中得到字符串数据
     */
    public static String getDateString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateString = sdf.format(date);
        return dateString;
    }

}

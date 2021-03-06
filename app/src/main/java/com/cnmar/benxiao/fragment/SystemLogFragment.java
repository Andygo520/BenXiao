package com.cnmar.benxiao.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.cnmar.benxiao.R;
import com.cnmar.benxiao._Url;
import com.cnmar.benxiao.retrofit.Api;
import com.cnmar.benxiao.retrofit.BaseModel;
import com.cnmar.benxiao.retrofit.RxHelper;
import com.cnmar.benxiao.retrofit.RxSubscriber;
import com.cnmar.benxiao.utils.ToastUtil;
import com.cnmar.benxiao.utils.UniversalHelper;
import com.cnmar.benxiao.widget.MyListView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import component.system.model.SystemLog;
import rx.functions.Action1;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemLogFragment extends Fragment {
    @BindView(R.id.etSearchInput)
    EditText etSearchInput;
    @BindView(R.id.ivDelete)
    ImageView ivDelete;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.listView)
    MyListView listView;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    @BindView(R.id.tvFinishLoadMore)
    TextView tvFinishLoadMore;

    private int page = 1;    //    page代表显示的是第几页内容，从1开始
    private int total; // 总页数
    private int num = 1; // 第几页
    private int count; // 数据总条数
    private BillAdapter myAdapter;
    //    用来存放从后台取出的数据列表，作为adapter的数据源
    private List<SystemLog> data = new ArrayList<>();

    public SystemLogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.refresh_frame, container, false);
        ButterKnife.bind(this, view);
        init();
        getLogListFromNet("", 1);
        return view;
    }

    public void init() {
        tv1.setText(R.string.log_name);
        tv2.setText(R.string.log_action);
        tv3.setText(R.string.log_info);
        tv4.setText(R.string.log_time);
        etSearchInput.setHint(R.string.search_by_name);

        UniversalHelper.initRefresh(getActivity(), refreshLayout);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                      下拉刷新默认显示第一页（10条）内容
                        page = 1;
                        getLogListFromNet("", page);
                        refreshLayout.finishRefreshing();
                        //                        设置文本提示框不可见
                        tvFinishLoadMore.setVisibility(View.GONE);
                    }
                }, 400);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
//                          当page等于总页数的时候，提示“加载完成”，不能继续上拉加载更多
                        if (page == total) {
                            getLogListFromNet("", page);
                            // 结束上拉刷新...
                            refreshLayout.finishLoadmore();
//                        只在这种情况显示文本提示框
                            tvFinishLoadMore.setVisibility(View.VISIBLE);
                            return;
                        }
                        getLogListFromNet("", page);
                        // 结束上拉刷新...
                        refreshLayout.finishLoadmore();
                    }
                }, 400);
            }
        });
        etSearchInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    String input = etSearchInput.getText().toString().trim();
                    if (input.equals("")) {
                        Toast.makeText(getActivity(), R.string.before_search_please_input, Toast.LENGTH_SHORT).show();
                    } else {
                        getLogListFromNet(input, 1);
                        page = 1;
//                        设置文本提示框不可见
                        tvFinishLoadMore.setVisibility(View.GONE);
                    }
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }

        });
        etSearchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    ivDelete.setVisibility(View.GONE);
                    getLogListFromNet("", 1);
                    page = 1;
                    //                        设置文本提示框不可见
                    tvFinishLoadMore.setVisibility(View.GONE);
                } else {
                    ivDelete.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void getLogListFromNet(String query, int page) {
        String token = UniversalHelper.getToken(_Url.SYSTEM_LOG);
        Api.getDefault().systemLog(query, page, token)
                .doOnNext(new Action1<BaseModel<ArrayList<SystemLog>>>() {
                    @Override
                    public void call(BaseModel<ArrayList<SystemLog>> response) {
                        count = response.getPage().getCount();
                        total = response.getPage().getTotal();
                        num = response.getPage().getNum();
                    }
                })
                .compose(RxHelper.<ArrayList<SystemLog>>handleResult())
                .subscribe(new RxSubscriber<ArrayList<SystemLog>>(getActivity()) {
                    @Override
                    protected void _onNext(ArrayList<SystemLog> systemLogs) {
//      数据小于10条或者当前页为最后一页就设置不能上拉加载更多
                        if (count <= 10 || num == total)
                            refreshLayout.setEnableLoadmore(false);
                        else
                            refreshLayout.setEnableLoadmore(true);
//      当前是第一页的时候，直接显示list内容；当显示更多页的时候，将后面页的list数据加到data中
                        if (num == 1) {
                            data = systemLogs;
                            myAdapter = new BillAdapter(data, getActivity());
                            listView.setAdapter(myAdapter);
                        } else {
                            data.addAll(systemLogs);
//                            myAdapter.notifyDataSetChanged();
                            myAdapter = new BillAdapter(data, getActivity());
                            listView.setAdapter(myAdapter);
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtil.showToast(getActivity(), message);
                    }

                    @Override
                    protected boolean showDialog() {
                        return false;
                    }
                });
    }

    @OnClick({R.id.ivDelete, R.id.llSearch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivDelete:
                etSearchInput.setText("");
                break;
            case R.id.llSearch:
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                }
                String input = etSearchInput.getText().toString().trim();
                if (input.equals("")) {
                    Toast.makeText(getActivity(), R.string.before_search_please_input, Toast.LENGTH_SHORT).show();
                    return;
                }
                getLogListFromNet(input, 1);
                page = 1;
//                        设置文本提示框不可见
                tvFinishLoadMore.setVisibility(View.GONE);
                break;
        }
    }

    class BillAdapter extends BaseAdapter {
        private Context context;
        private List<SystemLog> list = null;

        public BillAdapter(List<SystemLog> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.table_list_item, parent, false);
                holder = new ViewHolder(convertView);
//                偶数行背景设为灰色
                if (position % 2 == 0)
                    holder.tableRow.setBackgroundColor(getResources().getColor(R.color.color_light_grey));
                convertView.setTag(holder);
            } else
                holder = (ViewHolder) convertView.getTag();

            holder.column1.setText(list.get(position).getName());
            holder.column2.setText(list.get(position).getLogOperateVo().getValue() + list.get(position).getLogModuleVo().getValue());
            holder.column3.setText(list.get(position).getContent());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            holder.column4.setText(sdf.format(list.get(position).getCtime()));
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.column1)
            TextView column1;
            @BindView(R.id.column2)
            TextView column2;
            @BindView(R.id.column3)
            TextView column3;
            @BindView(R.id.column4)
            TextView column4;
            @BindView(R.id.table_row)
            TableRow tableRow;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}

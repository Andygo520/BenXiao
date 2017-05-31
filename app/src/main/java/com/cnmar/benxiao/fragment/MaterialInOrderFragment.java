package com.cnmar.benxiao.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import component.material.model.MaterialInOrder;
import component.material.vo.InOrderStatusVo;
import rx.functions.Action1;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaterialInOrderFragment extends Fragment {
    @BindView(R.id.etSearchInput)
    EditText etSearchInput;
    @BindView(R.id.ivDelete)
    ImageView ivDelete;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.tvLine)
    TextView tvLine;
    @BindView(R.id.spinner)
    Spinner spinner;
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
    private int page = 1;    //    page代表显示的是第几页内容，从1开始
    private int total; // 总页数
    private int num = 1; // 第几页
    private int count; // 数据总条数
    private BillAdapter myAdapter;
    //   数组用来存放所有入库单状态
    String[] status = {"所有状态", "待打印", "待入库", "已入库", "未全部入库", "待检验", "检验不合格"};
    //    map用来将入库单状态跟InOrderStatusVo里面的状态关联
    private Map<String, Object> map = new HashMap<>();
    //    用来存放从后台取出的数据列表，作为adapter的数据源
    private List<MaterialInOrder> data = new ArrayList<>();

    public MaterialInOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.refresh_frame, container, false);
        ButterKnife.bind(this, view);

//      在map中存入状态与Vo的对应关系，“所有状态”存入空字符
        map.put(status[0], "");
        map.put(status[1], InOrderStatusVo.pre_print.getKey());
        map.put(status[2], InOrderStatusVo.pre_in_stock.getKey());
        map.put(status[3], InOrderStatusVo.in_stock.getKey());
        map.put(status[4], InOrderStatusVo.not_all.getKey());
        map.put(status[5], InOrderStatusVo.pre_test.getKey());
        map.put(status[6], InOrderStatusVo.test_fail.getKey());
        init();
        getInOrderListFromNet("", "", 1);
        return view;
    }

    public void init() {
        tv1.setText(R.string.material_in_order_no);
        tv2.setText(R.string.material_in_order_supply_no);
        tv3.setText(R.string.material_in_order_arrive_date);
        tv4.setText(R.string.material_in_order_status);
        etSearchInput.setHint(R.string.search_by_in_order_no);
        spinner.setVisibility(View.VISIBLE);
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.materialInOrderStatus);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        spinner.setAdapter(adapter);
//       只有在拥有垂直下拉列表的Fragment才显示这个分割线
        tvLine.setVisibility(View.VISIBLE);
//      默认情况下不激活setOnItemSelectedListener方法，只有选择的时候才调用该方法
        spinner.setSelection(0, false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getInOrderListFromNet("", String.valueOf(map.get(status[position])), 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        UniversalHelper.initRefresh(getActivity(), refreshLayout);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                      下拉刷新默认显示第一页（10条）内容
                        page = 1;
                        getInOrderListFromNet("", "", page);
                        refreshLayout.finishRefreshing();
                    }
                }, 400);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
//                            当page等于总页数的时候，提示“加载完成”
                        if (page == total) {
                            getInOrderListFromNet("", "", page);
                            Toast.makeText(getActivity(), R.string.finish_load_more, Toast.LENGTH_SHORT).show();
                            // 结束上拉刷新...
                            refreshLayout.finishLoadmore();
                            return;
                        }
                        getInOrderListFromNet("", "", page);
                        Toast.makeText(getActivity(), R.string.load_more, Toast.LENGTH_SHORT).show();
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
                        getInOrderListFromNet(input, "", 1);
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
                    getInOrderListFromNet("", "", 1);
                } else {
                    ivDelete.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /*
    * Fragment 从隐藏切换至显示，会调用onHiddenChanged(boolean hidden)方法
    * */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        Fragment重新显示到最前端中
        if (!hidden) {
            getInOrderListFromNet("", "", 1);
        }
    }

    public void getInOrderListFromNet(String query, String status, int page) {
        String token = UniversalHelper.getToken(_Url.MATERIAL_IN_ORDER);
        Api.getDefault().materialInOrder(query, status, page, token)
                .doOnNext(new Action1<BaseModel<ArrayList<MaterialInOrder>>>() {
                    @Override
                    public void call(BaseModel<ArrayList<MaterialInOrder>> response) {
                        count = response.getPage().getCount();
                        total = response.getPage().getTotal();
                        num = response.getPage().getNum();
                    }
                })
                .compose(RxHelper.<ArrayList<MaterialInOrder>>handleResult())
                .subscribe(new RxSubscriber<ArrayList<MaterialInOrder>>(getActivity()) {
                    @Override
                    protected void _onNext(ArrayList<MaterialInOrder> materialInOrders) {
                        //      数据小于10条或者当前页为最后一页就设置不能上拉加载更多
                        if (count <= 10 || num == total)
                            refreshLayout.setEnableLoadmore(false);
                        else
                            refreshLayout.setEnableLoadmore(true);
                        //  当前是第一页的时候，直接显示list内容；当显示更多页的时候，将后面页的list数据加到data中
                        if (num == 1) {
                            data = materialInOrders;
                            myAdapter = new BillAdapter(data, getActivity());
                            listView.setAdapter(myAdapter);
                        } else {
                            data.addAll(materialInOrders);
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
                getInOrderListFromNet(input, "", 1);
                break;
        }
    }

    class BillAdapter extends BaseAdapter {
        private Context context;
        private List<MaterialInOrder> list = null;

        public BillAdapter(List<MaterialInOrder> list, Context context) {
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

            holder.column1.setText(list.get(position).getCode());
//            供应商编码非空判断
            if (list.get(position).getSupply() != null)
                holder.column2.setText(list.get(position).getSupply().getCode());
            else
                holder.column2.setText("");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            holder.column3.setText(sdf.format(list.get(position).getArrivalDate()));
            holder.column4.setText(list.get(position).getInOrderStatusVo().getValue());
            holder.column1.setTextColor(getResources().getColor(R.color.colorBase));
            holder.column1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(context, MaterialInOrderDetailActivity.class);
//                    intent.putExtra("ID", list.get(position).getId());
//                    context.startActivity(intent);
                }
            });
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

package com.cnmar.benxiao;

/**
 * Created by Administrator on 2017/4/26.
 */

public class _Url {
    public static final String LOGIN = "login_commit";
    public static final String PROFILE = "system_user";
    public static final String CHECK_STOCK = "material_stock_check";
    public static final String STOCK_CHECK_MANAGE = "material_stock_check_manage";
    public static final String CHECK_COMMIT = "material_stock_check_manage";
    public static final String IN_ORDER_DETAIL = "material_in_order";
    public static final String OUT_ORDER_DETAIL = "material_out_order";
    public static final String OUT_COMMIT = "material_out_order";
    public static final String STOCK_DETAIL = "material_stock";
    public static final String PRODUCT_CHECK_STOCK = "product_stock_check";
    public static final String PRODUCT_CHECK_STOCK_MANAGE = "product_stock_check_manage";

    public static final String PRODUCT_CHECK_STOCK_COMMIT = "product_stock_check_manage";

    public static final String PRODUCT_STOCK_DETAIL = "product_stock";
    public static final String MATERIAL_IN_ORDER = "material_in_order";
    public static final String CHECK_MANAGE = "/material_stock_check_manage/list?query.code=&page.num={page}";
    public static final String CHECK_QUERY = "/material_stock_check/list?query.code=&page.num={page}";

    public static final String STOCK = "/material_stock/list?query.code=&page.num={page}";

    public static final String OUT_ORDER = "/material_out_order/list?query.code=&page.num={page}";

    public static final String PRODUCT_CHECK_MANAGE = "/product_stock_check_manage/list?query.code=&page.num={page}";

    public static final String PRODUCT_CHECK_QUERY = "/product_stock_check/list?query.code=&page.num={page}";

    public static final String PRODUCT_STOCK = "/product_stock/list?query.code=&page.num={page}";

    public static final String PRODUCE_PLAN = "/produce_plan/list?query.code=&page.num={page}";//加工单列表
    public static final String PRODUCE_BOM = "/produce_bom/list?query.code=&page.num={page}";//子加工单列表
    public static final String DELIVERY_PLAN = "custom_deliver_plan";

    public static final String RECEIVE_MATERIAL_ORDER = "/produce_receive/list?query.code=&page.num={page}";
    public static final String RECEIVE_MATERIAL_ORDER_DETAIL = "/produce_receive/detail/{ID}";

    public static final String PRODUCE_PLAN_DETAIL = "/produce_plan/detail/{ID}";//加工单详情
    public static final String PRODUCE_BOM_DETAIL = "/produce_bom/detail/{ID}";//子加工单详情

    public static final String RECEIVE_MATERIAL_COMMIT = "/produce_plan/receive_commit?producePlanId={ID}&receiveId={receiveId}";//加工单领料
    public static final String PRODUCT_PRE_IN_STOCK_COMMIT = "/produce_plan/pre_in_stock_commit?batchId={batchId}&successNum={successNum}&testId={testId}&testRemark={testRemark}";//成品提交待入库
    public static final String PRODUCT_ACTUAL_NUM_COMMIT = "/produce_plan/actual_num_commit?producePlanId={ID}&actualNum={actualNum}&actualId={actualId}&lastProcessBoxIds={lastProcessBoxIds}&status={status}";//成品提交待检验

    public static final String BOM_RECEIVE_MATERIAL_COMMIT = "/produce_bom/receive_commit?produceBomId={ID}&receiveId={receiveId}";//子加工单领料
    public static final String HALF_PRODUCT_PRE_IN_STOCK_COMMIT = "/produce_bom/pre_in_stock_commit?batchId={batchId}&successNum={successNum}&testId={testId}&testRemark={testRemark}";//半成品提交待入库
    public static final String HALF_PRODUCT_ACTUAL_NUM_COMMIT = "/produce_bom/actual_num_commit?produceBomId={ID}&actualNum={actualNum}&actualId={actualId}&lastProcessBoxIds={lastProcessBoxIds}&status={status}";//半成品提交待检验


    public static final String PRODUCT_IN_ORDER = "/product_in_order/list?query.code=&page.num={page}";
    public static final String PRODUCT_OUT_ORDER = "/product_out_order/list?query.code=&page.num={page}";
    public static final String PRODUCT_IN_ORDER_DETAIL = "/product_in_order/detail/{id}";
    public static final String PRODUCT_OUT_ORDER_DETAIL = "/product_out_order/detail/{id}";
    public static final String PRODUCT_IN_ORDER_COMMIT = "/product_in_order/in_stock_commit?inOrderId={inOrderId}&inOrderSpaceIds={inOrderSpaceIds}&preInStocks={preInStocks}&inStocks={inStocks}";
    public static final String PRODUCT_OUT_COMMIT = "/product_out_order/out_stock_commit?outOrderId={outOrderId}&outOrderSpaceIds={outOrderSpaceIds}&preOutStocks={preOutStocks}&outStocks={outStocks}";

    public static final String QC_LIST = "/material_in_order_test/list?query.code=&page.num={page}";

    public static final String QC_DETAIL = "/material_in_order_test/detail/{id}";

    public static final String HALF_QC_LIST = "/produce_half_test/list?query.code=&page.num={page}";
    public static final String HALF_QC_DETAIL = "/produce_half_test/detail/{ID}";


    public static final String PRODUCT_QC_LIST = "/produce_product_test/list?query.code=&page.num={page}";
    public static final String PRODUCT_QC_DETAIL = "/produce_product_test/detail/{ID}";

    public static final String COMPANY = "company";


    public static final String PRODUCT= "company_product";

    public static final String SUPPLY_LIST = "supply";


    //    半成品仓库
    public static final String HALF_STOCK = "half_stock";
    public static final String HALF_IN_ORDER = "half_in_order";
    public static final String HALF_OUT_ORDER = "half_out_order";
    public static final String HALF_CHECK_MANAGE = "half_stock_check_manage";
    public static final String HALF_CHECK_QUERY = "half_stock_check";

    //    系统设置
    public static final String SYSTEM_LOG = "system_log";
    public static final String SYSTEM_USER = "system_user";

    // 报表地址
    public static final String MATERIAL_REPORT = "http://benxiao.cnmar.com:8091/material_report/show";
    public static final String HALF_REPORT = "http://benxiao.cnmar.com:8091/half_report/show";
    public static final String PRODUCT_REPORT = "http://benxiao.cnmar.com:8091/product_report/show";

    //    生产管理
    public static final String SCANN_STATION = "/com_station/qrcode/";//机床扫描
    public static final String SCANN_STATION_DETAIL = "/com_station/detail/{ID}?planId={planId}&bomId={bomId}&processId={processId}&userId={userId}";//加工任务详情
    public static final String BOX = "/com_box/qrcode/";//料框扫描
    public static final String CHECK_FLOW = "/produce_test/list?query.userId={userId}&query.type={type}&query.code=&page.num={page}";//检验流水
    public static final String CHECK_FLOW_DETAIL = "produce_test";//检验流水详情
}

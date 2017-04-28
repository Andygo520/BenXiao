package com.cnmar.benxiao.retrofit;

import java.util.ArrayList;

import component.com.model.ComBox;
import component.com.model.ComStation;
import component.company.model.Company;
import component.company.model.CompanyProduct;
import component.custom.model.CustomDeliverPlan;
import component.half.model.HalfInOrder;
import component.half.model.HalfOutOrder;
import component.half.model.HalfStock;
import component.half.model.HalfStockCheck;
import component.material.model.MaterialInOrder;
import component.material.model.MaterialOutOrder;
import component.material.model.MaterialStock;
import component.material.model.MaterialStockCheck;
import component.produce.model.ProduceBom;
import component.produce.model.ProduceBomBatch;
import component.produce.model.ProducePlan;
import component.produce.model.ProducePlanBatch;
import component.produce.model.ProduceReceive;
import component.produce.model.ProduceTest;
import component.product.model.ProductInOrder;
import component.product.model.ProductOutOrder;
import component.product.model.ProductStock;
import component.product.model.ProductStockCheck;
import component.supply.model.Supply;
import component.system.model.SystemLog;
import component.system.model.SystemUser;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {
    /*
    * 用户登录
    * */
    @GET("/login_commit")
    Observable<BaseModel<SystemUser>> login(
            @Query("username") String username,
            @Query("password") String password,
            @Query("token") String token
    );

    /*
   * 用户资料
   * */
    @GET("/system_user/personal/{id}")
    Observable<BaseModel<SystemUser>> profile(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
   *  操作日志
   * */
    @GET("/system_log/list")
    Observable<BaseModel<ArrayList<SystemLog>>> systemLog(
            @Query("query.name") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
*  系统用户详情
* */
    @GET("/system_user/detail/{id}")
    Observable<BaseModel<SystemUser>> systemUserDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 原料库存列表
    * */
    @GET("/material_stock/list")
    Observable<BaseModel<ArrayList<MaterialStock>>> materialStock(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    * 原料库存详情
    * */
    @GET("/material_stock/detail/{id}")
    Observable<BaseModel<MaterialStock>> materialStockDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 原料入库单列表
    * */
    @GET("/material_in_order/list")
    Observable<BaseModel<ArrayList<MaterialInOrder>>> materialInOrder(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
   * 原料入库单详情
   * */
    @GET("/material_in_order/detail/{id}")
    Observable<BaseModel<MaterialInOrder>> materialInOrderDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 原料提交检验
    * */
    @GET("/material_in_order/test_commit")
    Observable<BaseModel> materialTest(
            @Query("inOrderId") int inOrderId,
            @Query("testId") int testId,
            @Query("testRemark") String testRemark,
            @Query("inOrderMaterialIds") String inOrderMaterialIds,
            @Query("res") int res,
            @Query("failNums") int failNums,
            @Query("token") String token
    );

    /*
    * 原料提交入库
    * */
    @GET("/material_in_order/in_stock_commit")
    Observable<BaseModel> materialInStock(
            @Query("inOrderId") int inOrderId,
            @Query("inOrderSpaceIds") String inOrderSpaceIds,
            @Query("preInStocks") String preInStocks,
            @Query("inStocks") String inStocks,
            @Query("token") String token
    );

    /*
    *原料出库列表
    * */
    @GET("/material_out_order/list")
    Observable<BaseModel<ArrayList<MaterialOutOrder>>> materialOutOrder(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    * 原料出库详情
    * */
    @GET("/material_out_order/detail/{id}")
    Observable<BaseModel<MaterialOutOrder>> materialOutOrderDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 原料提交出库
    * */
    @GET("/material_out_order/out_stock_commit")
    Observable<BaseModel> materialOutStock(
            @Query("outOrderId") int outOrderId,
            @Query("outOrderSpaceIds") String outOrderSpaceIds,
            @Query("preOutStocks") String preOutStocks,
            @Query("outStocks") String outStocks,
            @Query("token") String token
    );

    /*
    * 原料盘点管理列表
    * */
    @GET("/material_stock_check_manage/list")
    Observable<BaseModel<ArrayList<MaterialStock>>> materialCheckManage(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
   * 原料盘点管理详情
   * */
    @GET("/material_stock_check_manage/detail/{id}")
    Observable<BaseModel<MaterialStock>> materialCheckManageDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 原料提交盘点
    * */
    @GET("/material_stock_check_manage/check_commit")
    Observable<BaseModel> materialCheckCommit(
            @Query("stockId") int stockId,
            @Query("spaceStockIds") String spaceStockIds,
            @Query("spaceIds") String spaceIds,
            @Query("inOrderSpaceIds") String inOrderSpaceIds,
            @Query("beforeStocks") String beforeStocks,
            @Query("afterStocks") String afterStocks,
            @Query("token") String token
    );

    /*
    * 原料盘点查询列表
    * */
    @GET("/material_stock_check/list")
    Observable<BaseModel<ArrayList<MaterialStockCheck>>> materialCheckQuery(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    * 原料盘点查询详情
    * */
    @GET("/material_stock_check/detail/{id}")
    Observable<BaseModel<MaterialStockCheck>> materialCheckQueryDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 半成品库存列表
    * */
    @GET("/half_stock/list")
    Observable<BaseModel<ArrayList<HalfStock>>> halfStock(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    * 半成品库存详情
    * */
    @GET("/half_stock/detail/{id}")
    Observable<BaseModel<HalfStock>> halfStockDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 半成品入库单列表
    * */
    @GET("/half_in_order/list")
    Observable<BaseModel<ArrayList<HalfInOrder>>> halfInOrder(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );


    /*
    * 半成品入库单详情
    * */
    @GET("/half_in_order/detail/{id}")
    Observable<BaseModel<HalfInOrder>> halfInOrderDetail(
            @Path("id") int id,
            @Query("token") String token
    );


    /*
    * 半成品提交入库
    * */
    @GET("/half_in_order/in_stock_commit")
    Observable<BaseModel> halfInStock(
            @Query("inOrderId") int inOrderId,
            @Query("inOrderSpaceIds") String inOrderSpaceIds,
            @Query("preInStocks") String preInStocks,
            @Query("inStocks") String inStocks,
            @Query("token") String token
    );


    /*
    * 半成品出库列表
    * */
    @GET("/half_out_order/list")
    Observable<BaseModel<ArrayList<HalfOutOrder>>> halfOutOrder(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );


    /*
    * 半成品出库详情
    * */
    @GET("/half_out_order/detail/{id}")
    Observable<BaseModel<HalfOutOrder>> halfOutOrderDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 半成品提交出库
    * */
    @GET("/half_out_order/out_stock_commit")
    Observable<BaseModel> halfOutStock(
            @Query("outOrderId") int outOrderId,
            @Query("outOrderSpaceIds") String outOrderSpaceIds,
            @Query("preOutStocks") String preOutStocks,
            @Query("outStocks") String outStocks,
            @Query("token") String token
    );

    /*
    * 半成品盘点管理列表
    * */
    @GET("/half_stock_check_manage/list")
    Observable<BaseModel<ArrayList<HalfStock>>> halfCheckManage(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    * 半成品盘点管理详情
    * */
    @GET("/half_stock_check_manage/detail/{id}")
    Observable<BaseModel<HalfStock>> halfCheckManageDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 半成品提交盘点
    * */
    @GET("/half_stock_check_manage/check_commit")
    Observable<BaseModel> halfCheckCommit(
            @Query("stockId") int stockId,
            @Query("spaceStockIds") String spaceStockIds,
            @Query("spaceIds") String spaceIds,
            @Query("inOrderSpaceIds") String inOrderSpaceIds,
            @Query("beforeStocks") String beforeStocks,
            @Query("afterStocks") String afterStocks,
            @Query("token") String token
    );

    /*
    * 半成品盘点查询列表
    * */
    @GET("/half_stock_check/list")
    Observable<BaseModel<ArrayList<HalfStockCheck>>> halfCheckQuery(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    * 半成品盘点查询详情
    * */
    @GET("/half_stock_check/detail/{id}")
    Observable<BaseModel<HalfStockCheck>> halfCheckQueryDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 成品库存列表
    * */
    @GET("/product_stock/list")
    Observable<BaseModel<ArrayList<ProductStock>>> productStock(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    * 成品库存详情
    * */
    @GET("/product_stock/detail/{id}")
    Observable<BaseModel<ProductStock>> productStockDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 成品入库单列表
    * */
    @GET("/product_in_order/list")
    Observable<BaseModel<ArrayList<ProductInOrder>>> productInOrder(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );


    /*
    * 成品入库单详情
    * */
    @GET("/product_in_order/detail/{id}")
    Observable<BaseModel<ProductInOrder>> productInOrderDetail(
            @Path("id") int id,
            @Query("token") String token
    );


    /*
    * 成品提交入库
    * */
    @GET("/product_in_order/in_stock_commit")
    Observable<BaseModel> productInStock(
            @Query("inOrderId") int inOrderId,
            @Query("inOrderSpaceIds") String inOrderSpaceIds,
            @Query("preInStocks") String preInStocks,
            @Query("inStocks") String inStocks,
            @Query("token") String token
    );

    /*
    * 成品出库列表
    * */
    @GET("/product_out_rder/list")
    Observable<BaseModel<ArrayList<ProductOutOrder>>> productOutOrder(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );


    /*
     * 成品出库详情
     * */
    @GET("/product_out_order/detail/{id}")
    Observable<BaseModel<ProductOutOrder>> productOutOrderDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 成品提交出库
    */
    @GET("/product_out_order/out_stock_commit")
    Observable<BaseModel> productOutStock(
            @Query("outOrderId") int outOrderId,
            @Query("outOrderSpaceIds") String outOrderSpaceIds,
            @Query("preOutStocks") String preOutStocks,
            @Query("outStocks") String outStocks,
            @Query("token") String token
    );


    /*
    * 成品盘点管理列表
    * */
    @GET("/product_stock_check_manage/list")
    Observable<BaseModel<ArrayList<ProductStock>>> productCheckManage(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );


    /*
    * 成品盘点管理详情
    * */
    @GET("/product_stock_check_manage/detail/{id}")
    Observable<BaseModel<ProductStock>> productCheckManageDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 成品提交盘点
    * */
    @GET("/product_stock_check_manage/check_commit")
    Observable<BaseModel> productCheckCommit(
            @Query("stockId") int stockId,
            @Query("spaceStockIds") String spaceStockIds,
            @Query("spaceIds") String spaceIds,
            @Query("inOrderSpaceIds") String inOrderSpaceIds,
            @Query("beforeStocks") String beforeStocks,
            @Query("afterStocks") String afterStocks,
            @Query("token") String token
    );

    /*
    *成品盘点查询列表
    * */
    @GET("/product_stock_check/list")
    Observable<BaseModel<ArrayList<ProductStockCheck>>> productCheckQuery(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    * 成品盘点查询详情
    * */
    @GET("/product_stock_check/detail/{id}")
    Observable<BaseModel<ProductStockCheck>> productCheckQueryDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 加工单列表
    * */
    @GET("/produce_plan/list")
    Observable<BaseModel<ArrayList<ProducePlan>>> producePlan(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    * 加工单详情
    * */
    @GET("/produce_plan/detail/{id}")
    Observable<BaseModel<ProducePlan>> producePlanDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 加工单领料
    * */
    @GET("/produce_plan/receive_commit")
    Observable<BaseModel> planReceiveCommit(
            @Query("producePlanId") int producePlanId,
            @Query("receiveId") int receiveId,
            @Query("token") String token
    );

    /*
    * 加工单提交待检验
    * */
    @GET("/produce_plan/actual_num_commit")
    Observable<BaseModel> planTest(
            @Query("producePlanId") int producePlanId,
            @Query("actualNum") int actualNum,
            @Query("actualId") int actualId,
            @Query("lastProcessBoxIds") String lastProcessBoxIds,
            @Query("status") String status,
            @Query("token") String token
    );

    /*
    * 加工单提交入库
    * */
    @GET("/produce_plan/pre_in_stock_commit")
    Observable<BaseModel> planInStock(
            @Query("batchId") int batchId,
            @Query("successNum") int successNum,
            @Query("testId") int testId,
            @Query("testRemark") String testRemark,
            @Query("token") String token
    );

    /*
    *子加工单列表
    * */
    @GET("/produce_bom/list")
    Observable<BaseModel<ArrayList<ProduceBom>>> produceBom(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    * 子加工单详情
    * */
    @GET("/produce_bom/detail/{id}")
    Observable<BaseModel<ProduceBom>> produceBomDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
  * 子加工单领料
  * */
    @GET("/produce_bom/receive_commit")
    Observable<BaseModel> bomReceiveCommit(
            @Query("produceBomId") int produceBomId,
            @Query("receiveId") int receiveId,
            @Query("token") String token
    );

    /*
    * 子加工单提交待检验
    * */
    @GET("/produce_bom/actual_num_commit")
    Observable<BaseModel> bomTest(
            @Query("produceBomId") int produceBomId,
            @Query("actualNum") int actualNum,
            @Query("actualId") int actualId,
            @Query("lastProcessBoxIds") String lastProcessBoxIds,
            @Query("status") String status,
            @Query("token") String token
    );

    /*
    * 子加工单提交入库
    * */
    @GET("/produce_bom/pre_in_stock_commit")
    Observable<BaseModel> bomInStock(
            @Query("batchId") int batchId,
            @Query("successNum") int successNum,
            @Query("testId") int testId,
            @Query("testRemark") String testRemark,
            @Query("token") String token
    );

    /*
    * 领料单列表
    * */
    @GET("/produce_receive/list")
    Observable<BaseModel<ArrayList<ProduceReceive>>> produceReceive(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    * 领料单详情
    * */
    @GET("/produce_receive/detail/{id}")
    Observable<BaseModel<ProduceReceive>> produceReceiveDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 交付计划列表
    * */
    @GET("/custom_deliver_plan/list")
    Observable<BaseModel<ArrayList<CustomDeliverPlan>>> deliveryPlan(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    * 交付计划详情
    * */
    @GET("/custom_deliver_plan/detail/{id}")
    Observable<BaseModel<CustomDeliverPlan>> deliveryPlanDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 原料检验流水列表
    * */
    @GET("/material_in_order_test/list")
    Observable<BaseModel<ArrayList<MaterialInOrder>>> materialQC(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    * 原料检验流水详情
    * */
    @GET("/material_in_order_test/detail/{id}")
    Observable<BaseModel<MaterialInOrder>> materialQCDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 半成品检验流水列表
    * */
    @GET("/produce_half_test/list")
    Observable<BaseModel<ArrayList<ProduceBomBatch>>> halfQC(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    * 半成品检验流水详情
    * */
    @GET("/produce_half_test/detail/{id}")
    Observable<BaseModel<ProduceBomBatch>> halfQCDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 成品检验流水列表
    * */
    @GET("/produce_product_test/list")
    Observable<BaseModel<ArrayList<ProducePlanBatch>>> productQC(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    * 成品检验流水详情
    * */
    @GET("/produce_product_test/detail/{id}")
    Observable<BaseModel<ProducePlanBatch>> productQCDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 企业列表
    * */
    @GET("/company/list")
    Observable<BaseModel<ArrayList<Company>>> company(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    * 企业详情
    * */
    @GET("/company/detail/{id}")
    Observable<BaseModel<Company>> companyDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 产品列表
    * */
    @GET("/company_product/list")
    Observable<BaseModel<ArrayList<CompanyProduct>>> product(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    * 产品详情
    * */
    @GET("/company_product/detail/{id}")
    Observable<BaseModel<CompanyProduct>> productDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
    * 供应商列表
    * */
    @GET("/supply/list")
    Observable<BaseModel<ArrayList<Supply>>> supply(
            @Query("query.code") String query,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
    *  供应商详情
    * */
    @GET("/supply/detail/{id}")
    Observable<BaseModel<Supply>> supplyDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
 *  生产管理检验流水列表
 * */
    @GET("/produce_test/list")
    Observable<BaseModel<ArrayList<ProduceTest>>> checkFlow(
            @Query("query.userId") String userId,
            @Query("query.type") int type,
            @Query("query.code") String code,
            @Query("page.num") int page,
            @Query("token") String token

    );

    /*
   *  生产管理检验流水详情
   * */
    @GET("/produce_test/detail/{id}")
    Observable<BaseModel<ProduceTest>> checkFlowDetail(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
  *  机台扫描得到（子）加工单
  * */
    @GET("/com_station/qrcode")
    Observable<BaseModel<ProduceTest>> scanStation(
            @Path("id") int id,
            @Query("token") String token
    );

    /*
   *  机台扫描得到（子）加工单的详情
   * */
    @GET("/com_station/detail/{id}")
    Observable<BaseModel<ComStation>> scanStationDetail(
            @Path("id") int id,
            @Query("planId") int planId,
            @Query("bomId") int bomId,
            @Query("processId") int processId,
            @Query("userId") int userId,
            @Query("token") String token
    );

    /*
    * 入料框
    * */
    @GET("/com_box/in_commit")
    Observable<BaseModel<ComBox>> inBox(
            @Query("boxId") int boxId,
            @Query("planId") int planId,
            @Query("bomId") int bomId,
            @Query("processId") int processId,
            @Query("stationId") int stationId,
            @Query("num") int num,
            @Query("userId") int userId,
            @Query("token") String token
    );


    /*
  * 不合格品料框详情
  * */
    @GET("/com_box/qrcode/{id}")
    Observable<BaseModel<ComBox>> failBox(
            @Path("id") int id,
            @Query("op") String op,
            @Query("testBoxId") int testBoxId,
            @Query("token") String token
    );

    /*
   * 不合格品提交入料框
   * */
    @GET("/com_box/fail_in_commit")
    Observable<BaseModel> failInBox(
            @Query("boxId") int boxId,
            @Query("testBoxId") int testBoxId,
            @Query("planId") int planId,
            @Query("bomId") int bomId,
            @Query("processId") int processId,
            @Query("stationId") int stationId,
            @Query("failNum") String failNum,
            @Query("testRemark") String testRemark,
            @Query("testId") int testId,
            @Query("token") String token
    );

    /*
  * 线边仓
  * */
    @GET("/com_box/list")
    Observable<BaseModel<ArrayList<ComBox>>> bin(
            @Query("query.userId") int userId,
            @Query("query.type") int type,
            @Query("query.code") String code,
            @Query("page.num") int page,
            @Query("token") String token
    );

    /*
  *  上工序合格品出料框
  * */
    @GET("/com_box/prev_out_commit")
    Observable<BaseModel<ComBox>> prevOutBox(
            @Query("boxId") int boxId,
            @Query("num") int num,
            @Query("token") String token
    );

}






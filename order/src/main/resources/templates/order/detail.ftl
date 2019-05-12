<!DOCTYPE html>
<html lang="zh-CN">
<#include "../common/header.ftl">
<body>
    <#--顶部导航栏-->
    <#include "../common/headerbar.ftl">
    <div id="wrapper" class="toggled">
        <#--边栏sidebar-->
        <#include "../common/sidebar.ftl">
        <#--主要内容content-->
        <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单ID</th>
                            <th>顾客名</th>
                            <th>下单时间</th>
                            <th>商品总价</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderDto.orderId}</td>
                            <td>${buyer}</td>
                            <td>${orderDto.createTime}</td>
                            <td>${priceAmount}</td>
                        </tr>
                        </tbody>
                        <div class="button-group" data-id="${orderDto.orderId}">
                            <#if orderDto.orderStatus == 0>
                                <button type="button" class="btn btn-danger order-cancel-btn">取消订单</button>
                                <button type="button" class="btn btn-danger order-complete-btn">完结订单</button>
                            </#if>
                        </div>
                    </table>
                </div>
                <h2 class="page-header">商品详情</h2>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>商品ID</th>
                            <th>商品名称</th>
                            <th>商品价格</th>
                            <th>商品数量</th>
                            <th>总额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDetailList as orderDetail>
                            <tr>
                                <td>${orderDetail.productId}</td>
                                <td>${orderDetail.productName}</td>
                                <td>${orderDetail.productPrice}</td>
                                <td>${orderDetail.productQuantity}</td>
                                <td>${orderDetail.productPrice * orderDetail.productQuantity}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </div>
    <#--<#include "../common/footer.ftl">-->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../static/js/jquery.min.js"><\/script>')</script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../../static/js/sweetalert.min.js"></script>
<script src="../../static/js/myalert.js"></script>
<script src="../../static/js/myajax.js"></script>
<script src="../../static/js/order/detail.js"></script>
<script src="../../static/js/holder.min.js"></script>
</body>
</html>

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
                    <#--<h2 class="page-header">订单列表</h2>-->
                    <div class="col-md-12 column">
                        <table class="table table-bordered table-condensed">
                            <thead>
                            <tr>
                                <th width="188px">OrderID</th>
                                <th width="130px">姓名</th>
                                <th width="115px">手机号</th>
                                <th width="370px">地址</th>
                                <th width="58px">金额</th>
                                <th width="88px">订单状态</th>
                                <th width="88px">支付状态</th>
                                <th width="150px">创建时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list orderDtoPage.content as orderDto>
                                <tr data-id="${orderDto.orderId}">
                                    <td>${orderDto.orderId}</td>
                                    <td>${orderDto.buyerName}</td>
                                    <td>${orderDto.buyerPhone}</td>
                                    <td>${orderDto.buyerAddress}</td>
                                    <td>${orderDto.orderAmount}</td>
                                    <td>${orderDto.getOrderStatusEnum().message}</td>
                                    <td>${orderDto.getPayStatusEnum().message}</td>
                                    <td>${orderDto.createTime}</td>
                                    <td>
                                        <a href="/sell/seller/order/detail?orderId=${orderDto.orderId}">
                                            <button class="btn btn-default btn-sm order-detail-btn">详情</button>
                                        </a>
                                    </td>
                                    <td>
                                        <#if orderDto.orderStatus==0>
                                            <button class="btn btn-danger btn-sm order-delete-btn">取消</button>
                                        </#if>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                        <#--分页-->
                        <nav aria-label="Page navigation" style="margin-left: 500px">
                            <ul class="pagination pagination-lg">
                                <#if currentPage lte 1>
                                    <li class="disabled">
                                        <a href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                <#else>
                                    <li>
                                        <a href="/sell/seller/order/list?page=${currentPage-1}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </#if>
                                <#list 1..orderDtoPage.getTotalPages() as index>
                                    <#if currentPage==index>
                                        <li class="disabled"><a href="#">${index}</a></li>
                                    <#else>
                                        <li><a href="/sell/seller/order/list?page=${index}">${index}</a></li>
                                    </#if>
                                </#list>
                                <#if currentPage gte orderDtoPage.getTotalPages()>
                                    <li class="disabled">
                                        <a href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                <#else>
                                    <li>
                                        <a href="/sell/seller/order/list?page=${currentPage + 1}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </#if>
                            </ul>
                        </nav>
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
    <script src="../../static/js/order/order.js"></script>
    <script src="../../static/js/holder.min.js"></script>
</body>
</html>

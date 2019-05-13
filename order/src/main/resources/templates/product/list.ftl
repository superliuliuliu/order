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
            <div class="button-group">
                <button type="button" class="btn btn-success" data-toggle="modal" data-target=".add-modal">新增</button>
            </div>
            <div class="row clearfix">
                <#--<h2 class="page-header">订单列表</h2>-->
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th width="150px">商品ID</th>
                            <th width="130px">名称</th>
                            <th colspan="1">图片</th>
                            <th width="88px">单价</th>
                            <th width="58px">库存</th>
                            <th width="370px">介绍</th>
                            <th width="88px">类目</th>
                            <th width="150px">创建时间</th>
                            <th width="150px">修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productInfos.content as productInfo>
                            <tr data-id="${productInfo.productId}">
                                <#--data-name="${productInfo.productName}" data-url="${productInfo.productIcon}" data-price="${productInfo.productPrice}"-->
                                <#--data-stock="${productInfo.productStock}" data-desc="${productInfo.productDescription}" data-category="${productInfo.categoryType}"-->
                                <td>${productInfo.productId}</td>
                                <td>${productInfo.productName}</td>
                                <td>
                                    <a>
                                        <button class="btn btn-info btn-sm" data-toggle="modal" data-target=".picture-modal-sm">查看图片</button>
                                    </a>
                                </td>
                                <#--模态框查看图片-->
                                <div class="modal fade picture-modal-sm" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
                                    <div class="modal-dialog modal-sm" role="document">
                                        <div class="modal-content">
                                            <img src="${productInfo.productIcon}" alt="">
                                        </div>
                                    </div>
                                </div>
                                <#--<td><img height="100" width="100" src="${productInfo.productIcon}" alt=""></td>-->
                                <td>${productInfo.productPrice}</td>
                                <td>${productInfo.productStock}</td>
                                <td>${productInfo.productDescription}</td>
                                <td>${productInfo.categoryType}</td>
                                <td>${productInfo.createTime}</td>
                                <td>${productInfo.updateTime}</td>
                                <td>
                                    <a>
                                        <button class="btn btn-default btn-sm product-detail-btn">编辑</button>
                                    </a>
                                </td>
                                <td>
                                    <#if productInfo.productStatus==0>
                                        <#--如果当前商品是在售状态 按钮为下架-->
                                        <button class="btn btn-danger btn-sm product-down-btn">下架</button>
                                    <#else>
                                        <button class="btn btn-success btn-sm product-up-btn">上架</button>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
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
                                    <a href="/sell/seller/product/list?page=${currentPage-1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </#if>
                            <#list 1..productInfos.getTotalPages() as index>
                                <#if currentPage==index>
                                    <li class="disabled"><a href="#">${index}</a></li>
                                <#else>
                                    <li><a href="/sell/seller/product/list?page=${index}">${index}</a></li>
                                </#if>
                            </#list>
                            <#if currentPage gte productInfos.getTotalPages()>
                                <li class="disabled">
                                    <a href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            <#else>
                                <li>
                                    <a href="/sell/seller/product/list?page=${currentPage + 1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </#if>
                        </ul>
                    </nav>
                    <#--新增编辑模态框-->
                    <div class="modal fade add-modal" id="add-dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <#--模态框头部-->
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">商品</h4>
                                </div>
                                <#--模态框表单-->
                                <div class="modal-body">
                                    <form class="form-horizontal" action="">
                                        <div class="form-group">
                                            <label for="productName" class="col-sm-2 control-label">商品名称:</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="productName" placeholder="商品名称">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="url" class="col-sm-2 control-label">图片地址:</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="url" placeholder="图片地址">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="productPrice" class="col-sm-2 control-label">商品单价:</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="productPrice" placeholder="价格">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="productStock" class="col-sm-2 control-label">商品库存:</label>
                                            <div class="col-sm-10">
                                                <input type="number" class="form-control" id="productStock" placeholder="Weight">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="productDesc" class="col-sm-2 control-label">商品介绍:</label>
                                            <div class="col-sm-10">
                                                <textarea class="form-control" id="productDesc" placeholder="介绍一下本商品"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="categoryType" class="col-sm-2 control-label">商品类目:</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="categoryType" placeholder="商品类目">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
                                    <button type="button" class="btn btn-primary btn-sm " id="product-add-btn">新增</button>
                                </div>
                            </div>
                        </div>
                    </div>
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
<script src="../../static/js/product/product.js"></script>
<script src="../../static/js/holder.min.js"></script>
</body>
</html>

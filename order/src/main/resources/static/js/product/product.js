$(function () {
    $(".product-down-btn").click(function (event) {
        event.preventDefault();
        var self = $(this);
        var parent_tr = self.parent().parent();
        var productId = parent_tr.attr("data-id");
        console.log(productId);

        //准备执行下架操作
        lgyalert.alertConfirm({
            'msg':"您将下架该商品，请确认？",
            'confirmCallback': function(){
                myajax.post({
                    'url': '/sell/seller/product/Obtained',
                    'data':{
                        'productId': productId
                    },
                    'success': function(response) {
                        //console.log(response);
                        if (response['data']['code'] === 200){
                            window.location.reload();
                        }
                        else{
                            lgyalert.alertInfo(response['data']['message']);
                        }
                    },
                    'fail': function(error){
                        lgyalert.alertError("网络错误");
                    }
                });
            }
        });
    });
});

// 对商品执行上架操作
$(function () {
    $(".product-up-btn").click(function (event) {
        event.preventDefault();
        var self = $(this);
        var parent_tr = self.parent().parent();
        var productId = parent_tr.attr("data-id");

        //准备执行上架操作
        lgyalert.alertConfirm({
            'msg':"您确定要上架该商品？",
            'confirmCallback': function(){
                myajax.post({
                    'url': '/sell/seller/product/shelf',
                    'data':{
                        'productId': productId
                    },
                    'success': function(response) {
                        //console.log(response);
                        if (response['data']['code'] === 200){
                            window.location.reload();
                        }
                        else{
                            lgyalert.alertInfo(response['data']['message']);
                        }
                    },
                    'fail': function(error){
                        lgyalert.alertError("网络错误");
                    }
                });
            }
        });
    });
});

$(function () {
    $("#product-add-btn").click(function (event) {
        event.preventDefault();

        var dialog = $("#add-dialog");
        var productNameE = dialog.find("#productName");
        var urlE = dialog.find("#url");
        var productPriceE = dialog.find("#productPrice");
        var productStockE = dialog.find("#productStock");
        var productDescE = dialog.find("#productDesc");
        var categoryTypeE = dialog.find("#categoryType");

        var productName = productNameE.val();
        var productUrl = urlE.val();
        var productPrice = productPriceE.val();
        var productStock = productStockE.val();
        var productDesc = productDescE.val();
        var category = categoryTypeE.val();


        $.ajax({
            url: '/sell/seller/product/add',
            method: 'post',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify({
                'productName': productName,
                'productIcon': productUrl,
                'productPrice': productPrice,
                'productStock': productStock,
                'productDescription': productDesc,
                'categoryType': category
            }),
            dataType: 'JSON',
            success: function(data) {
                //提交成功后隐藏对话框
                dialog.modal("hide");
                if (data['data']['code'] == 200){
                    window.location.reload();//提交成功的话重新加载该页面
                }
                else{
                    var message = data['data']['message'];
                    lgyalert.alertInfo(message);
                }
            },
            error: function(error) {
                lgyalert.alertError("网络错误")
            }
        });
    });
});


//对应点击编辑商品按钮事件
$(function () {
    $(".product-detail-btn").click(function (event) {
        //event.preventDefault();
        //获取按钮本身
        var self = $(this);
        var dialog = $("#edit-dialog");
        dialog.modal("show");//编辑轮播图对话框
        //获取对话框中存储的属性
        var tr = self.parent().parent();
        var productId = tr.attr("data-id");
        var productName = tr.attr("productName");
        var url = tr.attr("productIcon");
        var productPrice = tr.attr("productPrice");
        var productStock = tr.attr("productStock");
        var productDesc = tr.attr("productDesc");
        var categoryType = tr.attr("categoryType");

        var productNameE = dialog.find("#productName");
        var urlE = dialog.find("#url");
        var productPriceE = dialog.find("#productPrice");
        var productStockE = dialog.find("#productStock");
        var productDescE = dialog.find("#productDesc");
        var categoryTypeE = dialog.find("#categoryType");
        var button = dialog.find("#product-save-btn");

        productNameE.val(productName);
        urlE.val(url);
        productPriceE.val(productPrice);
        productStockE.val(productStock);
        productDescE.val(productDesc);
        categoryTypeE.val(categoryType);

        button.attr("data-id", tr.attr("data-id"));//button新增属性id 用来表示是向哪一个商品更新信息
    });
});

// 编辑模态框中的保存按钮
$(function () {
    $("#product-save-btn").click(function (event) {
        event.preventDefault();
        var self = $(this);
        var productId = self.attr("data-id");
        console.log(productId);
        var dialog = $("#edit-dialog");
        var productNameE = dialog.find("#productName");
        var urlE = dialog.find("#url");
        var productPriceE = dialog.find("#productPrice");
        var productStockE = dialog.find("#productStock");
        var productDescE = dialog.find("#productDesc");
        var categoryTypeE = dialog.find("#categoryType");

        var productName = productNameE.val();
        var productUrl = urlE.val();
        var productPrice = productPriceE.val();
        var productStock = productStockE.val();
        var productDesc = productDescE.val();
        var category = categoryTypeE.val();

        $.ajax({
            url: '/sell/seller/product/edit',
            method: 'post',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify({
                'productId': productId,
                'productName': productName,
                'productIcon': productUrl,
                'productPrice': productPrice,
                'productStock': productStock,
                'productDescription': productDesc,
                'categoryType': category
            }),
            dataType: 'JSON',
            success: function(data) {
                //提交成功后隐藏对话框
                dialog.modal("hide");
                if (data['data']['code'] == 200){
                    window.location.reload();//提交成功的话重新加载该页面
                }
                else{
                    var message = data['data']['message'];
                    lgyalert.alertInfo(message);
                }
            },
            error: function(error) {
                lgyalert.alertError("网络错误")
            }
        });
    });
});

// 查看图片按钮
$(function () {
    $(".pic-btn").click(function (event) {
        event.preventDefault();
        var self = $(this);
        var dialog = $("#pic-dialog");
        var parent_tr = self.parent().parent();
        var pic = parent_tr.attr("productIcon");

        dialog.modal("show");


    });
});
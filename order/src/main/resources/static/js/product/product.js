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

$(function () {
    $(".product-up-btn").click(function (event) {
        event.preventDefault();
        var self = $(this);
        var parent_tr = self.parent().parent();
        var productId = parent_tr.attr("data-id");
        console.log(productId);

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

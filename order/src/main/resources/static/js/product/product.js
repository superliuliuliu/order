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

$(function () {
    $(".order-cancel-btn").click(function (event) {
        event.preventDefault();
        var self = $(this);
        var parent_tr = self.parent();
        var orderId = parent_tr.attr("data-id");
        //console.log(orderId);

        //准备执行取消订单的操作
        lgyalert.alertConfirm({
            'msg':"您确定要取消订单？",
            'confirmCallback': function(){
                myajax.post({
                    'url': '/sell/seller/order/cancel',
                    'data':{
                        'orderId': orderId
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
    $(".order-complete-btn").click(function (event) {
        event.preventDefault();
        var self = $(this);
        var parent_tr = self.parent();
        var orderId = parent_tr.attr("data-id");
        //console.log(orderId);

        //准备执行取消订单的操作
        lgyalert.alertConfirm({
            'msg':"完结此订单？",
            'confirmCallback': function(){
                myajax.post({
                    'url': '/sell/seller/order/complete',
                    'data':{
                        'orderId': orderId
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

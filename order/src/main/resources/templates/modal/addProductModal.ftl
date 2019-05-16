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
                            <input type="number" class="form-control" id="productStock" placeholder="库存">
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
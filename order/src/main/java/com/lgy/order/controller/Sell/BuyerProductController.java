package com.lgy.order.controller.Sell;

import com.lgy.order.model.VO.ProductInfoVo;
import com.lgy.order.model.VO.ProductVo;
import com.lgy.order.model.VO.ResultVo;
import com.lgy.order.model.DO.ProductCategory;
import com.lgy.order.model.DO.ProductInfo;
import com.lgy.order.service.CategoryService;
import com.lgy.order.service.ProductService;
import com.lgy.order.common.util.ResultVoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//我们在开发的过程之中需要注意的是数据库查询的相关内容不要放在for循环之中
@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/list")
    public ResultVo index(){
        //首先查询所有上架的商品信息
        List<ProductInfo> productInfoList = productService.findOnAll();

        //根据上架的商品信息去查询其所在的商品类别信息 lamda
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e->e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVo> productVoList = new ArrayList<>();
        //对以上得到的数据进行数据封装
        for(ProductCategory productCategory: productCategoryList){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for(ProductInfo productInfo: productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    //利用工具类 将实体类的属性信息拷贝到视图类的属性中
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }
        return ResultVoUtil.success(productVoList);
    }
}

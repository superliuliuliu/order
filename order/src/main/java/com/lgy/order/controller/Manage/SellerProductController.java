package com.lgy.order.controller.Manage;

import com.lgy.order.model.VO.ResultVo;
import com.lgy.order.model.DO.OperationResult;
import com.lgy.order.model.DO.ProductInfo;
import com.lgy.order.common.enums.ProductStatusEnum;
import com.lgy.order.common.enums.ResultEnum;
import com.lgy.order.common.exception.SellException;
import com.lgy.order.model.form.ProductForm;
import com.lgy.order.service.ProductService;
import com.lgy.order.common.util.KeyUtil;
import com.lgy.order.common.util.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

/**
 *
 * @description 买家端商品信息API 包括对已经存在的商品相关操作 新增商品操作等
 * @author liugaoyang
 * @date 2019/5/11 22:48
 * @version 1.0.0
 */
@Controller
@Slf4j
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductService productService;

    /**
     * getProductList
     * @description 后台管理界面 查询店铺当前的所有商品信息
     * @param page 展示数据的起始页码
     * @param size 每个分页数据显示数据的条数
     * @param map
     * @return org.springframework.web.servlet.ModelAndView
     * @author liugaoyang
     * @date 2019/5/11 23:05
     * @version 1.0.0
     */
    @GetMapping("/list")
    public ModelAndView getProductList(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                       @RequestParam(name = "size", defaultValue = "10") Integer size,
                                       Map<String, Object> map){
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
         Page<ProductInfo> productInfos = productService.findAll(pageRequest);
        map.put("productInfos", productInfos);
        map.put("currentPage", page);
        return new ModelAndView("product/list", map);
    }

    /**
     * doObtained
     * @description 商品下架
     * @param productId 商品ID
     * @return com.lgy.order.model.VO.ResultVo
     * @author liugaoyang
     * @date 2019/5/12 9:49
     * @version 1.0.0
     */
    @ResponseBody
    @PostMapping("/Obtained")
    public ResultVo doObtained(@RequestParam(value = "productId") String productId){
        try{
            ProductInfo productInfo = productService.findOne(productId);
            productService.obtain(productInfo);
        }catch (SellException e){
            log.error("【商品下架】：{}", e);
            return ResultVoUtil.error(e.getCode(), e.getMessage());
        }
        return ResultVoUtil.success(new OperationResult(200, "操作成功"));
    }

    /**
     * doShelf
     * @description 商品上架操作
     * @param productId 商品ID
     * @return com.lgy.order.model.VO.ResultVo
     * @author liugaoyang
     * @date 2019/5/12 11:16
     * @version 1.0.0
     */
    @ResponseBody
    @PostMapping("/shelf")
    public ResultVo doShelf(@RequestParam(value = "productId") String productId){
        try{
            ProductInfo productInfo = productService.findOne(productId);
            productService.Shelf(productInfo);
        }catch (SellException e){
            log.error("【商品上架】：{}", e);
            return ResultVoUtil.error(e.getCode(), e.getMessage());
        }
        return ResultVoUtil.success(new OperationResult(200, "操作成功"));
    }


    /**
     * addProduct
     * @description 新增商品  默认上架状态
     * @param
     * @return com.lgy.order.model.VO.ResultVo
     * @author liugaoyang
     * @date 2019/5/12 17:22
     * @version 1.0.0
     */
    //@CacheEvict(cacheNames = "product", key = "123")
    @ResponseBody
    @PostMapping("/add")
    public ResultVo addProduct(@Valid @RequestBody ProductForm productForm,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【创建商品】参数不正确 ProductForm={}", productForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(productForm, productInfo);
        productInfo.setProductId(KeyUtil.getUUID());
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        try{
            productService.save(productInfo);
        }catch (SellException e){
            log.error("【创建商品】：{}", e);
            return ResultVoUtil.error(e.getCode(), e.getMessage());
        }
        return ResultVoUtil.success(new OperationResult(200, "新增商品信息成功！"));
    }

    //@CacheEvict(cacheNames = "product", key = "123")
    @ResponseBody
    @PostMapping("/edit")
    public ResultVo aditProduct(@Valid @RequestBody ProductForm productForm,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors() || StringUtils.isEmpty(productForm.getProductId())){
            log.error("【编辑商品】参数不正确 ProductForm={}", productForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        try{
            productService.update(productForm);
        }catch (SellException e){
            return ResultVoUtil.error(e.getCode(), e.getMessage());
        }
        return ResultVoUtil.success(new OperationResult(200, "更新商品操作成功！"));
    }
}

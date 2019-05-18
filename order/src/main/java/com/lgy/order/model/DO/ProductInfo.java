package com.lgy.order.model.DO;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@DynamicUpdate
@Data
@Entity
@Table(name = "product_info")
public class ProductInfo {

    @Id
    @Size(max = 32)
    @Column(name = "product_id")
    private String productId;

    /**
     * 商品名称
     */
    @Size(max = 64)
    @Column(name = "product_name")
    private String productName;

    /**
     * 商品价格
     */
    @Column(name = "product_price")
    private BigDecimal productPrice;

    /**
     * 商品库存
     */
    @Column(name = "product_stock")
    private Integer productStock;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 商品介绍
     */
    @Column(name = "product_description")
    private String productDescription;

    /**
     * 商品图片
     */
    @Column(name = "product_icon")
    @Size(max = 512)
    private String productIcon;

    /**
     * 分类编码
     */
    @Column(name = "category_type")
    private Integer categoryType;

    /**
     * 商品状态
     */
    @Column(name = "product_status")
    private Integer productStatus;

}

package com.lgy.order.model.DO;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品类目
 */
//注解dynamicUpdate的作用是动态的更新
@DynamicUpdate
@Entity
@Data
@Table(name = "product_category")
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 7192543308557989612L;
    /**
     * 类目id
     */
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 类目名称
     */
    @Size(max = 64)
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 类目编号
     */
    @Column(name = "category_type")
    private Integer categoryType;

    @Column(name = "create_time")
    private Date creatTime;

    @Column(name = "update_time")
    private Date updateTime;

    private ProductCategory(){

    }

    public ProductCategory(String categoryName, Integer categoryType){
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}

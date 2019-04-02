package com.lgy.order.repository;

import com.lgy.order.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * findByBuyerOpenid
     * @description 根据购买者的微信号查询订单信息
     * @param buyerOpenid  购买者微信openid  由微信提供
     * @param pageable  分页数据格式
     * @return org.springframework.data.domain.Page<com.lgy.order.dataobject.OrderMaster>
     * @author liugaoyang
     * @date 2019/4/2 14:23
     * @version 1.0.0
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

    //根据购买者的手机号查找购买信息
    /**
     * findByBuyerPhone
     * @description
     * @param buyerPhone  购买者手机号码
     * @param pageable  分页数据格式
     * @return org.springframework.data.domain.Page<com.lgy.order.dataobject.OrderMaster>
     * @author liugaoyang
     * @date 2019/4/2 14:30
     * @version 1.0.0
     */
    Page<OrderMaster> findByBuyerPhone(String buyerPhone, Pageable pageable);

    /**
     * findByPhone
     * @description 根据购买者手机号模糊查询订单信息
     * @param phone 购买者的手机号码 （模糊查询）
     * @param pageable 分页数据格式
     * @return org.springframework.data.domain.Page<com.lgy.order.dataobject.OrderMaster>
     * @author liugaoyang
     * @date 2019/4/2 14:24
     * @version 1.0.0
     */
    @Query("SELECT o FROM OrderMaster o WHERE o.buyerPhone LIKE CONCAT('%',:phone,'%')")
    Page<OrderMaster> findByPhone(@Param("phone") String phone, Pageable pageable);

    /**
     * findByBuyerNameLike
     * @description 根据购买者的姓名查询订单信息
     * @param name 购买者的姓名 （模糊查询）
     * @param pageable 分页数据格式
     * @author liugaoyang
     * @date 2019/4/2 14:28
     * @version 1.0.0
     */
    @Query("SELECT o FROM OrderMaster o WHERE o.buyerName LIKE CONCAT('%',:name,'%')")
    Page<OrderMaster> findByBuyerNameLike(@Param("name") String name, Pageable pageable);
}

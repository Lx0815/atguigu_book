package book.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: Ding
 * @date: 2022/6/29
 * @description: 订单的实体类
 * @modify:
 */

public class Order {
    /**
     * id
     */
    private Integer id;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 订单创建时间
     */
    private LocalDateTime orderDatetime;

    /**
     * 订单金额
     */
    private BigDecimal orderMoney;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    public Order() {}

    public Order(String orderNumber, LocalDateTime orderDatetime, BigDecimal orderMoney, Integer orderStatus) {
        this.orderNumber = orderNumber;
        this.orderDatetime = orderDatetime;
        this.orderMoney = orderMoney;
        this.orderStatus = orderStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDateTime getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(LocalDateTime orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}

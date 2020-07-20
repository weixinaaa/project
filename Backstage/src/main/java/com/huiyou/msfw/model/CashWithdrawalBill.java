package com.huiyou.msfw.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 表名：cash_withdrawal_bill
 * 注释：
 */
@Data
public class CashWithdrawalBill {
    /**
     * 列名：id
     * 注释：
     */
    private Long id;

    /**
     * 列名：title
     * 注释：标题
     */
    private String title;

    /**
     * 列名：user_id
     * 注释：用户ID
     */
    private Long userId;

    /**
     * 列名：order_code
     * 注释：订单号
     */
    private String orderCode;

    /**
     * 列名：opening_bank
     * 注释：开户行
     */
    private String openingBank;

    /**
     * 列名：name_of_account_holder
     * 注释：开户人姓名
     */
    private String nameOfAccountHolder;

    /**
     * 列名：bank_card_number
     * 注释：收款银行卡账号
     */
    private String bankCardNumber;

    /**
     * 列名：amount_of_money
     * 注释：提取金额
     */
    private BigDecimal amountOfMoney;

    /**
     * 列名：create_time
     * 注释：创建时间
     */
    private Date createTime;

    /**
     * 列名：update_time
     * 注释：更新时间
     */
    private Date updateTime;

    /**
     * 列名：state
     * 注释：状态 0:审核中 1审核完成 2已到账
     */
    private Integer state;

    /**
     * 列名：is_del
     * 注释：
     */
    private Integer isDel;

    /**
     * 列名：deduction
     * 注释：
     */
    private BigDecimal deduction;
}
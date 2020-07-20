package com.huiyou.msfw.service;

import com.huiyou.msfw.model.CashWithdrawalBill;
import com.huiyou.msfw.model.CashWithdrawalBillExample;

import java.util.List;

public interface CashWithdrawalBillService {
    List<CashWithdrawalBill> selectCashWithdrawalBill(CashWithdrawalBillExample cashWithdrawalBillExample);

    Integer countCashwithdrawalBill(CashWithdrawalBillExample cashWithdrawalBillExample);

    Integer insertCashwithdrawalBill(CashWithdrawalBill cashWithdrawalBill);

    int updateCashWithdrawalBill(CashWithdrawalBill cashWithdrawalBill);
}

package com.huiyou.msfw.controller.console;


import com.alibaba.druid.sql.PagerUtils;
import com.huiyou.msfw.model.*;
import com.huiyou.msfw.service.BankService;
import com.huiyou.msfw.service.CashWithdrawalBillService;
import com.huiyou.msfw.service.UserService;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.PageResultEntity;
import me.fishlord.common.result.ResultEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wxpay.sdk.WXPayUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/console")
public class ConsoleCashWithdrawalBillController {

    @Autowired
    private CashWithdrawalBillService cashWithdrawalBillService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private BankService bankService;

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @ResponseBody
    @RequestMapping("/cashWithdrawalBill/getCashWithdrawalBillAll")
    public BaseResultEntity getCashWithdrawalBillAll(Integer pageNo,Integer pageSize,String title,Integer state,String createTime,String endTime) throws ParseException {
        CashWithdrawalBillExample cashWithdrawalBillExample = new CashWithdrawalBillExample();
        cashWithdrawalBillExample.setIsPage(1);
        cashWithdrawalBillExample.setPageNo(pageNo);
        cashWithdrawalBillExample.setPageSize(pageSize);

        CashWithdrawalBillExample.Criteria criteria = cashWithdrawalBillExample.or().andIsDelEqualTo(0);
        if(title!=null && title!=""){
            criteria.andTitleLike("%"+title+"%");
        }

        if(state!=null){
            criteria.andStateEqualTo(state);
        }

        if(createTime!=null && createTime!=""){
            criteria.andCreateTimeGreaterThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime));
        }

        if(endTime!=null && endTime!=""){
            criteria.andCreateTimeLessThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime));
        }


        List<CashWithdrawalBill> cashWithdrawalBills = cashWithdrawalBillService.selectCashWithdrawalBill(cashWithdrawalBillExample);
        Integer count = cashWithdrawalBillService.countCashwithdrawalBill(cashWithdrawalBillExample);
            List<Map<String,Object>> mapList = new ArrayList<>();
            for (CashWithdrawalBill cashWithdrawalBill:cashWithdrawalBills){
                Map<String,Object> map = new HashMap<>();
                map.put("id",cashWithdrawalBill.getId());
                map.put("title",cashWithdrawalBill.getTitle());
                User user = userService.getUserId(cashWithdrawalBill.getUserId());
                if(user!=null&& user.getUsername()!=null){
                    map.put("userName",user.getUsername());
                    map.put("userType",user.getType());
                }else{
                    map.put("userName","数据已丢失！");
                }
                map.put("orderCode",cashWithdrawalBill.getOrderCode());
                map.put("openingBank",cashWithdrawalBill.getOpeningBank());
                map.put("nameOfAccountHolder",cashWithdrawalBill.getNameOfAccountHolder());
                map.put("bankCardNumber",cashWithdrawalBill.getBankCardNumber());
                map.put("amountOfMoney",cashWithdrawalBill.getAmountOfMoney());
                map.put("createTime",sdf.format(cashWithdrawalBill.getCreateTime()));
                map.put("updateTime",sdf.format(cashWithdrawalBill.getUpdateTime()));
                map.put("state",cashWithdrawalBill.getState());
                map.put("deduction",cashWithdrawalBill.getDeduction());
                mapList.add(map);
            }
            return PageResultEntity.success("成功!",count,mapList);
    }

    @ResponseBody
    @RequestMapping("/cashWithdrawalBill/updateStatue")
    public BaseResultEntity updateStatue(Long id){
        CashWithdrawalBill cashWithdrawalBill = new CashWithdrawalBill();
        cashWithdrawalBill.setState(1);
        cashWithdrawalBill.setId(id);
        cashWithdrawalBill.setUpdateTime(new Date());
        int res = cashWithdrawalBillService.updateCashWithdrawalBill(cashWithdrawalBill);
        if (res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }



    /*
     * 转XML格式
     */
    private String getXML(Map<String, String> map) throws UnsupportedEncodingException {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<xml>");
        for (String key : map.keySet()) {
            buffer.append("<" + key + ">");
            buffer.append(map.get(key));
            buffer.append("</" + key + ">");

        }
        //<key></key>
        buffer.append("</xml>");
        return new String(buffer.toString().getBytes(),"ISO8859-1");


    }
    /*
     * XML转map
     */
    private Map<String, String> getMap(InputStream in) {

        Map<String,String> map = new HashMap<>();

        try {
            SAXBuilder builder = new SAXBuilder();
            Document build = builder.build(in);
            Element rootElement = build.getRootElement();
            List<Element> children = rootElement.getChildren();
            for (Element element : children) {
                String key = element.getName();
                List<Element> children2 = element.getChildren();
                String value = "";
                if (children2.isEmpty()) {
                    value = element.getTextNormalize();
                }
                map.put(key, value);
            }

        } catch (JDOMException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
    /*
     * 获取随机数
     */
    public String getNonceStr(int len) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < len; i++) {
            int num = (int)Math.random()*10;//随机数取值范围0-1，乘10之后就是1-9
            buffer.append(num);
        }
        return buffer.toString();
    }

    /**
     * 提现
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/cashWithdrawalBill/withdrawal")
    public BaseResultEntity withdrawal(Long id){
        /*CashWithdrawalBill cashWithdrawalBill = new CashWithdrawalBill();
        cashWithdrawalBill.setState(1);
        cashWithdrawalBill.setId(id);
        cashWithdrawalBill.setUpdateTime(new Date());*/
        CashWithdrawalBillExample cashWithdrawalBillExample = new CashWithdrawalBillExample();
        if (id==null){
            return ResultEntity.fail();
        }
        cashWithdrawalBillExample.or().andIdEqualTo(id);
        List<CashWithdrawalBill> cashWithdrawalBills = cashWithdrawalBillService.selectCashWithdrawalBill(cashWithdrawalBillExample);
        if (cashWithdrawalBills.size()==0){
            return ResultEntity.fail();
        }
        CashWithdrawalBill cashWithdrawalBill = cashWithdrawalBills.get(0);


        //微信支付分配的商户号   (微信支付功能申请获取)
        String mch_id = "2302758702";
        //商户订单号，需保持唯一（只允许数字[0~9]或字母[A~Z]和[a~z]，最短8位，最长32位）
        String partner_trade_no = cashWithdrawalBill.getOrderCode();
        //随机字符串，不长于32位
        String nonce_str = getNonceStr(24);
        //收款方银行卡号（采用标准RSA算法，公钥由微信侧提供）
        String enc_bank_no = cashWithdrawalBill.getBankCardNumber();
        //收款方用户名
        String enc_true_name = cashWithdrawalBill.getNameOfAccountHolder();
        //银行卡所在开户行编号,
        Bank bank = new Bank();
        bank.setBankName(cashWithdrawalBill.getOpeningBank());
        String bank_code = bankService.selBank(bank).get(0).getBankNumber();
        //付款金额：RMB分（支付总额，不含手续费）
        //注：大于0的整数
        String s = cashWithdrawalBill.getAmountOfMoney().toString();
        String replace = s.replace(".", "");
        int amount  = Integer.parseInt(replace);
        //企业付款到银行卡付款说明,即订单备注（UTF8编码，允许100个字符以内）,
        String desc = "test";

        String payURL = "https://api.mch.weixin.qq.com/mmpaysptrans/pay_bank";//提现接口

        Map<String,String> map = new HashMap<String,String>();
        map.put("mch_id",mch_id);//商户号
        map.put("partner_trade_no",partner_trade_no);//商户订单号
        map.put("nonce_str",nonce_str);//随机字符串
        map.put("enc_bank_no",enc_bank_no);//收款方银行卡号
        map.put("enc_true_name",enc_true_name);//收款方用户名
        map.put("bank_code",bank_code);//收款方开户行
        map.put("amount", String.valueOf(amount));//付款金额
        map.put("desc",desc);//付款说明
        String return_msg="";

        try {
            //通过MD5签名算法计算得出的签名值，sign
            map.put("sign",WXPayUtil.generateSignature(map, "1b3375482ac61d952aab56b534608971"));//生成签名////key商户号密钥
            //数据格式转XML格式
            String xml = getXML(map);

            HttpClient client = HttpClients.createDefault();

            HttpPost post = new HttpPost(payURL);
            StringEntity entity = new StringEntity(xml);
            post.setEntity(entity);
            post.setHeader("Content-Type","text/xml;charset=UTF-8");
            HttpResponse execute = client.execute(post);

            InputStream in = execute.getEntity().getContent();
            Map<String, String> results = getMap(in);
            return_msg = results.get("return_msg");

            if (results.get("return_code").equals("SUCCESS")) {
                if (results.get("result_code").equals("SUCCESS")) {
                    String string = results.get("code_url");
                    return ResultEntity.success(string);

                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResultEntity.success(return_msg);

    }

    /**
     * 查看提现进度
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/cashWithdrawalBill/look")
    public BaseResultEntity look(Long id){
        /*CashWithdrawalBill cashWithdrawalBill = new CashWithdrawalBill();
        cashWithdrawalBill.setState(1);
        cashWithdrawalBill.setId(id);
        cashWithdrawalBill.setUpdateTime(new Date());*/
        CashWithdrawalBillExample cashWithdrawalBillExample = new CashWithdrawalBillExample();
        if (id==null){
            return ResultEntity.fail();
        }
        cashWithdrawalBillExample.or().andIdEqualTo(id);
        List<CashWithdrawalBill> cashWithdrawalBills = cashWithdrawalBillService.selectCashWithdrawalBill(cashWithdrawalBillExample);
        if (cashWithdrawalBills.size()==0){
            return ResultEntity.fail();
        }
        CashWithdrawalBill cashWithdrawalBill = cashWithdrawalBills.get(0);


        //微信支付分配的商户号   (微信支付功能申请获取)
        String mch_id = "2302758702";
        //商户订单号，需保持唯一（只允许数字[0~9]或字母[A~Z]和[a~z]，最短8位，最长32位）
        String partner_trade_no = cashWithdrawalBill.getOrderCode();
        //随机字符串，不长于32位
        String nonce_str = getNonceStr(24);

        String selURL = "https://api.mch.weixin.qq.com/mmpaysptrans/query_bank";//查询单号接口

        Map<String,String> map = new HashMap<String,String>();
        map.put("mch_id",mch_id);//商户号
        map.put("partner_trade_no",partner_trade_no);//商户订单号
        map.put("nonce_str",nonce_str);//随机字符串
        String return_msg="";

        try {
            map.put("sign",WXPayUtil.generateSignature(map, "1b3375482ac61d952aab56b534608971"));//生成签名////key商户号密钥
            //数据格式转XML格式
            String xml = getXML(map);

            HttpClient client = HttpClients.createDefault();

            HttpPost post = new HttpPost(selURL);
            StringEntity entity = new StringEntity(xml);
            post.setEntity(entity);
            post.setHeader("Content-Type","text/xml;charset=UTF-8");
            HttpResponse execute = client.execute(post);

            InputStream in = execute.getEntity().getContent();
            Map<String, String> results = getMap(in);
            return_msg = results.get("return_msg");
            System.out.println(results.get("return_msg"));
            if (results.get("return_code").equals("SUCCESS")) {
                if (results.get("result_code").equals("SUCCESS")) {
                    String string = results.get("code_url");
                    System.out.println(string);
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResultEntity.success(return_msg);

    }
}

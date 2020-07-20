package com.huiyou.msfw.controller;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.zxing.WriterException;
import com.huiyou.msfw.model.*;
import com.huiyou.msfw.service.*;
import com.huiyou.msfw.utils.QRCodeGenerator;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.PageResultEntity;
import me.fishlord.common.result.ResultEntity;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private UserService userService;

    @Autowired
    private BankService bankService;

    @Autowired
    private DetailedIncomeService detailedIncomeService;

    @Autowired
    private CashWithdrawalBillService cashWithdrawalBillService;

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 个人信息
     * @param userId
     * @return
     */
    @CrossOrigin
    @RequestMapping("/user/PersonalInformation")
    @ResponseBody
    public BaseResultEntity PersonalInformation(Long userId){
        User user = userService.getUserId(userId);
        if(user!=null){
            Map<String,Object> data =new HashMap<>();
            data.put("id",user.getId());//ID
            data.put("name",user.getUsername());//名称
            data.put("img",user.getImg());//头像
            data.put("tel",user.getTel());//手机号
            data.put("gender",user.getGender());//性别
            data.put("mailbox",user.getMailbox());//电子邮箱
            data.put("myInvitationCode",user.getInvitationCode());//邀请码
            if(user.getInviterId()!=null&&user.getInviterId()!=0){
                user = userService.getUserId(user.getInviterId());
                if(user!=null){
                    data.put("invitationcode",user.getInvitationCode());
                }else{
                    data.put("invitationcode","");
                }
            }else{
                data.put("invitationcode","");
            }

            return ResultEntity.success("成功！",data);
        }else{
            return ResultEntity.fail();
        }
    }

    @CrossOrigin
    @RequestMapping("/user/getCommissionBalanceById")
    @ResponseBody
    public BaseResultEntity getCommissionBalanceById(Long id){
        User user = userService.getUserId(id);
        if(user!=null){
            return ResultEntity.success("成功！",user.getCommissionBalance());
        }else{
            return ResultEntity.fail("查询失败!");
        }
    }

    /**
     * 修改用户信息
     * @param user
     * @param code
     * @return
     */
    @CrossOrigin
    @RequestMapping("/user/updateUser")
    @ResponseBody
    public BaseResultEntity updateUser(User user,String code,String message){
        if(code.getBytes().length!=0){
            UserExample userExample = new UserExample();
            userExample.or().andInvitationCodeEqualTo(code);
            List<User> users = userService.selectByUserExample(userExample);
            if(users.size()>0){
                UserExample userExample1 = new UserExample();
                userExample1.or().andInviterIdEqualTo(user.getId());
                List<User> user2 = userService.selectByUserExample(userExample1);

                if (user2.size()!=0&&users.get(0).getId()==user2.get(0).getId()){
                    return ResultEntity.fail("用户之间不能互相邀请!");
                }else{
                    user.setInviterId(users.get(0).getId());
                }
            }else {
                return ResultEntity.fail("邀请码不存在!");
            }
        }
        String tel = userService.getUserId(user.getId()).getTel();
        if (!user.getTel().equals(tel)){
            List<Sms> sms = smsService.selSms(tel, message);
            if (sms.size()==0){
                return ResultEntity.fail("验证码错误!");
            }
        }
        user.setUpdateTime(new Date());
        Integer res = userService.updateUser(user);
        if(res >0){
            return ResultEntity.success("成功");
        }else{
            return ResultEntity.fail("失败");
        }

    }

    /**
     * 我的团队
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @CrossOrigin
    @RequestMapping("/user/queryTeamByUserId")
    @ResponseBody
    public Map<String,Object> queryTeam(Long userId,Integer pageNo,Integer pageSize){
        User superiorUser = userService.getUserId(userId);
        Map<String,Object> returnMap = new HashMap<>();
        UserExample userExample = new UserExample();
        userExample.setIsPage(1);
        userExample.setPageNo(pageNo);
        userExample.setPageSize(pageSize);
        userExample.or().andInviterIdEqualTo(userId);
        List<User> users = userService.selectByUserExample(userExample);
        Integer count = userService.countByUserExample(userExample);
        if(users.size()>0){
            List<Map<String,Object>> mapList = new ArrayList<>();
            for(User user:users){
                Map<String,Object> data =new HashMap<>();
                data.put("id",user.getId());//ID
                data.put("name",user.getUsername());//用户名称
                data.put("img",user.getImg());//用户图片
                data.put("teamNickname",superiorUser.getUsername());
                data.put("teamNickImg",superiorUser.getImg());
                mapList.add(data);
            }
            returnMap.put("code",0);
            returnMap.put("totle",count);
            returnMap.put("msg","查询成功！");
            returnMap.put("data",mapList);
            return returnMap;
        }else{
            returnMap.put("code",-1);
            returnMap.put("msg","查询失败！");
            return returnMap;
        }


    }


    /**
     *  提现订单查询
     * @param userId
     * @param pageNo
     * @param pageSize
     * @param state
     * @return
     */
    @CrossOrigin
    @RequestMapping("/user/getCashWithdrawalBill")
    @ResponseBody
    public Map<String,Object> getCashWithdrawalBill(Long userId,Integer pageNo,Integer pageSize,Integer state){
        Map<String,Object> returnMap = new HashMap<>();
        CashWithdrawalBillExample cashWithdrawalBillExample = new CashWithdrawalBillExample();
        cashWithdrawalBillExample.setIsPage(1);
        cashWithdrawalBillExample.setPageNo(pageNo);
        cashWithdrawalBillExample.setPageSize(pageSize);
        if(state!=null){
            cashWithdrawalBillExample.or().andUserIdEqualTo(userId).andStateEqualTo(state);
        }else{
            cashWithdrawalBillExample.or().andUserIdEqualTo(userId);
        }

        Integer count = cashWithdrawalBillService.countCashwithdrawalBill(cashWithdrawalBillExample);
        List<CashWithdrawalBill> cashWithdrawalBills = cashWithdrawalBillService.selectCashWithdrawalBill(cashWithdrawalBillExample);
        List<Map<String,Object>> mapList =new ArrayList<>();
        if(cashWithdrawalBills.size()>0){
            for(CashWithdrawalBill cashWithdrawalBill : cashWithdrawalBills){
                Map<String,Object> data = new HashMap<>();
                data.put("id",cashWithdrawalBill.getId());//ID
                data.put("title",cashWithdrawalBill.getTitle());//标题
                data.put("state",cashWithdrawalBill.getState()); //状态
                data.put("amountOfMoney",cashWithdrawalBill.getAmountOfMoney());//提现金额
                data.put("createTime",sdf.format(cashWithdrawalBill.getCreateTime()));//订单创建时间
                mapList.add(data);
            }
            returnMap.put("code",0);
            returnMap.put("msg","查询成功！");
            returnMap.put("totle",count);
            returnMap.put("data",mapList);
            return returnMap;
        }else{
            returnMap.put("code",-1);
            returnMap.put("msg","查询失败！");
            return returnMap;
        }

    }

    /**
     * 发送验证码
     * @param tel 电话号码
     * @return
     */
    @CrossOrigin
    @RequestMapping("/user/send")
    @ResponseBody
    public  BaseResultEntity send(String tel) {

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GKAL4cFrQFB6cEwb6dq", "YmPndMWVXJfH87bmVCqxk58TcOFUio");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", tel);
        request.putQueryParameter("SignName", "民生用户平台");
        request.putQueryParameter("TemplateCode", "SMS_193140630");

        StringBuilder sm = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            sm.append(r.nextInt(10));
        }
        request.putQueryParameter("TemplateParam", "{\"code\":\""+sm+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        Sms sms = new Sms();
        sms.setTel(tel);
        sms.setMessage(String.valueOf(sm));
        smsService.addSms(sms);
        List<Map<String,Object>> mapList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("tel",tel);
        map.put("message",String.valueOf(sm));
        mapList.add(map);
        //验证码60秒后作废
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                smsService.delSms(tel);
                this.cancel();
            }
        }, 60*1000);// ·这里是一分钟,60*60*1000就是一小时
        return PageResultEntity.success("成功!" , 1 , mapList);
    }

    /**
     * 注册
     *
     * @param tel//电话
     * @param password//密码
     * @param verificationCode//验证码
     * @param InvitationCode1//邀请码
     * @return
     */

    @CrossOrigin
    @RequestMapping("/user/register")
    @ResponseBody
    public BaseResultEntity register(String tel, String password, String verificationCode, String InvitationCode1) throws NoSuchAlgorithmException {
        UserExample userExample = new UserExample();
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        userExample.or().andTelLike(tel);
        List<User> users = userService.selectByUserExample(userExample);
        if(users.size()>0){
            return PageResultEntity.fail("该手机号已被注册");
        }
        List<Sms> sms = smsService.selSms(tel, verificationCode);
        if (sms.isEmpty()){
            return ResultEntity.fail("验证码错误！");
        }
        User user = new User();
        if(!InvitationCode1.isEmpty()){
            UserExample userExample1 = new UserExample();
            userExample1.or().andInvitationCodeEqualTo(InvitationCode1);
            List<User> users1 = userService.selectByUserExample(userExample1);
            if(users1.size()!=0){
                Long inviterId = users1.get(0).getId();
                user.setInviterId(inviterId);
            }else {
                return ResultEntity.fail("无效邀请码");
            }
        }

        user.setTel(tel);
        user.setUsername("用户"+tel);
        user.setPasswrod(password);
        user.setCreateTime(new Date());
        String InvitationCode = String.valueOf((int)((Math.random()*9+1)*100000));
        user.setInvitationCode(InvitationCode);
        int res = userService.register(user);
        if(res >0){
            UserExample userExample2 = new UserExample();
            userExample2.or().andTelLike(tel);
            User user1 = userService.selectByUserExample(userExample2).get(0);
            Long id = user1.getId();
            map.put("userId",id);
            map.put("commissionBalance",user1.getCommissionBalance());
            mapList.add(map);
            return PageResultEntity.success("成功!" , 1 , mapList);
        }else{
            return ResultEntity.fail();
        }
    }

    /**
     * 登录
     * @param tel
     * @param verificationCode
     * @return
     */
    @CrossOrigin
    @RequestMapping("user/login")
    @ResponseBody
    public BaseResultEntity login(String tel,String verificationCode){

        UserExample userExample = new UserExample();
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        userExample.or().andTelLike(tel);
        List<User> users = userService.selectByUserExample(userExample);
        if(users.size()==0){
            return PageResultEntity.fail("该手机号未注册");
        }
        List<Sms> sms = smsService.selSms(tel, verificationCode);
        if (sms.isEmpty()){
            return ResultEntity.fail("验证码错误！");
        }
        map.put("userId",users.get(0).getId());
        map.put("tel",users.get(0).getTel());
        return ResultEntity.success("成功",map);
    }

    /**
     * 个人二维码生成
     * @param userId
     * @return
     * @throws Exception
     */

    @CrossOrigin
    @RequestMapping("user/qrCode")
    @ResponseBody
    public ResponseEntity<byte[]> qrCode(Long userId) throws Exception {
        User user = userService.getUserId(userId);

        //二维码内的信息
        String info = user.getInvitationCode();

        byte[] qrcode = null;
        try {
            qrcode = QRCodeGenerator.getQRCodeImage(info, 360, 360);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }

        // Set headers
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.add("invitationCode",info);

        return new ResponseEntity<byte[]>(qrcode, headers, HttpStatus.CREATED);
    }



    @CrossOrigin
    @RequestMapping("/user/getUserProfit")
    @ResponseBody
    public Map<String,Object> getUserProfit(Long userId,Integer pageNo,Integer pageSize){
        Map<String,Object> returnMap = new HashMap<>();
        DetailedIncomeExample detailedIncomeExample = new DetailedIncomeExample();
        detailedIncomeExample.setIsPage(1);
        detailedIncomeExample.setPageNo(pageNo);
        detailedIncomeExample.setPageSize(pageSize);
        detailedIncomeExample.or().andUserIdEqualTo(userId);
        List<DetailedIncome> detailedIncomes = detailedIncomeService.selectDetailedIncomeExample(detailedIncomeExample);
        try{
            if(detailedIncomes.size()>0){
                List<Map<String,Object>> mapList = new ArrayList<>();
                for(DetailedIncome detailedIncome:detailedIncomes){
                    Map<String,Object> dataMap = new HashMap<>();
                    dataMap.put("Id",detailedIncome.getId());//ID
                    dataMap.put("title",detailedIncome.getTitle());//标题
                    dataMap.put("createTime",detailedIncome.getCreateTime());//时间
                    dataMap.put("profit",detailedIncome.getProfit());//金额
                    mapList.add(dataMap);
                }
                returnMap.put("code",0);
                returnMap.put("msg","查询成功！");
                returnMap.put("data",mapList);
                return returnMap;
            }else{
                returnMap.put("code",0);
                returnMap.put("msg","查询成功！");
                returnMap.put("data",null);
                return returnMap;
            }
        }catch (Exception e){
            returnMap.put("code",-1);
            returnMap.put("msg","查询失败！");
            return returnMap;
        }
    }

    /**
     * 添加收益详细
     * @param userId 用户ID
     * @param profit 金额
     * @param title 标题
     * @return
     */
    @CrossOrigin
    @RequestMapping("/user/addProfit")
    @ResponseBody
    public BaseResultEntity addProfit(Long userId, BigDecimal profit,String title){
        DetailedIncome detailedIncome =new DetailedIncome();
        detailedIncome.setUserId(userId);
        detailedIncome.setCreateTime(new Date());
        detailedIncome.setProfit(profit);
        detailedIncome.setTitle(title);
        Integer res = detailedIncomeService.insertDetailedIncome(detailedIncome);
        if(res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }

    /**
     *  提现申请提交
     * @param userId 用户ID
     * @param bankNumber 开户银行编码
     * @param nameOfAccountHolder 开户人姓名
     * @param bankCardNumber 收款银行卡号
     * @param amountOfMoney 金额
     * @return
     */
    @CrossOrigin
    @RequestMapping("/user/addCashWithdrawalBill")
    @ResponseBody
        public BaseResultEntity addCashWithdrawalBill(Long userId,String bankNumber,String nameOfAccountHolder,String bankCardNumber,BigDecimal amountOfMoney){
        CashWithdrawalBill cashWithdrawalBill = new CashWithdrawalBill();

        cashWithdrawalBill.setUserId(userId);//用户ID
        Bank bank = new Bank();
        bank.setBankNumber(bankNumber);
        String bankName = bankService.selBank(bank).get(0).getBankName();
        cashWithdrawalBill.setOpeningBank(bankName);//开户银行
        cashWithdrawalBill.setNameOfAccountHolder(nameOfAccountHolder);//开户人姓名
        cashWithdrawalBill.setBankCardNumber(bankCardNumber);//收款银行卡号
        cashWithdrawalBill.setAmountOfMoney(amountOfMoney);//金额
        cashWithdrawalBill.setState(0);//状态 0审核中
        cashWithdrawalBill.setCreateTime(new Date());//创建时间
        cashWithdrawalBill.setTitle("余额提现到"+bankName);//标题

        //订单号
        Random random = new Random();
        String orderCode = String.valueOf(random.nextInt(10));
        CashWithdrawalBillExample cashWithdrawalBillExample = new CashWithdrawalBillExample();
        cashWithdrawalBillExample.or().andOrderCodeLike(orderCode);
        Integer res = cashWithdrawalBillService.countCashwithdrawalBill(cashWithdrawalBillExample);
        if(res>0){
            orderCode = String.valueOf(random.nextInt(10));
        }
        cashWithdrawalBill.setOrderCode(orderCode);

        res = cashWithdrawalBillService.insertCashwithdrawalBill(cashWithdrawalBill);
        if(res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }


}

package com.woof.test;


import ecpay.payment.integration.AllInOne;

import ecpay.payment.integration.domain.AioCheckOutOneTime;
import ecpay.payment.integration.domain.QueryTradeInfoObj;


import javax.servlet.annotation.WebServlet;
import java.io.UnsupportedEncodingException;


public class test {

public static AllInOne all;
    public static void main(String[] args) throws UnsupportedEncodingException {
        initial();

        System.out.println("aioCheckOutOneTime: " + genAioCheckOutOneTime());

//        System.out.println(postQueryTradeInfo());
    }

    private static void initial() throws UnsupportedEncodingException {
        all = new AllInOne("");
    }



    public static String genAioCheckOutOneTime(){
        AioCheckOutOneTime obj = new AioCheckOutOneTime();
        obj.setMerchantTradeNo("2001544545"); // 該位數為上限
        obj.setMerchantTradeDate("2017/01/01 08:05:23");
        obj.setTotalAmount("50");
        obj.setTradeDesc("test Description");
        obj.setItemName("TestItem");
        obj.setReturnURL("http://211.23.128.214:5000");
        obj.setNeedExtraPaidInfo("N");
        obj.setRedeem("Y");
        obj.setClientBackURL("http://211.23.128.214:5000");
        String form = all.aioCheckOut(obj, null);
        return form;
    }

    public static String postQueryTradeInfo(){
        QueryTradeInfoObj obj = new QueryTradeInfoObj();
        obj.setMerchantTradeNo("test5445454544541110");
        return all.queryTradeInfo(obj);
    }

//    一般信用卡測試卡號 : 4311-9522-2222-2222 安全碼 : 222
//圓夢彈性分期信用卡測試卡號 : 4938-1777-7777-7777 安全碼 : 222　


}



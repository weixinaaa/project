package com.huiyou.msfw.utils;

import com.alibaba.druid.util.StringUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import sun.net.www.content.image.png;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class QrCode {

    public static void getBarCode(String msg,String path){
        try {
            File file=new File(path);
            OutputStream ous = new FileOutputStream(file);
            if(StringUtils.isEmpty(msg) || ous==null)
                return;
            String format = "png";
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map<EncodeHintType,String> map =new HashMap<EncodeHintType, String>();
            //设置编码 EncodeHintType类中可以设置MAX_SIZE， ERROR_CORRECTION，CHARACTER_SET，DATA_MATRIX_SHAPE，AZTEC_LAYERS等参数
            map.put(EncodeHintType.CHARACTER_SET,"UTF-8");
            map.put(EncodeHintType.MARGIN,"2");
            //生成二维码
            BitMatrix bitMatrix = new MultiFormatWriter().encode(msg, BarcodeFormat.QR_CODE,300,300,map);
            MatrixToImageWriter.writeToStream(bitMatrix,format,ous);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String main(String msg) {
        String path = "http:\\47.96.28.95\\msfw\\file_data\\"+msg+".png";
        QrCode.getBarCode(msg,path);
        return "http://47.96.28.95/msfw/file_data/"+ msg +".png";
    }
}

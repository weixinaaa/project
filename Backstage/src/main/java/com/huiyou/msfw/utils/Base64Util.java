package com.huiyou.msfw.utils;


import com.huiyou.msfw.model.Base64File;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.IOException;

public class Base64Util {
	public static MultipartFile Base64ToMultipartFile(String base64) {
	    try {
	        String[] baseStrs = base64.split(",");

	        BASE64Decoder decoder = new BASE64Decoder();
	        byte[] b = new byte[0];
	        b = decoder.decodeBuffer(baseStrs[1]);

	        for(int i = 0; i < b.length; ++i) {
	            if (b[i] < 0) {
	                b[i] += 256;
	            }
	        }

	        return new Base64File(b, baseStrs[0]);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}

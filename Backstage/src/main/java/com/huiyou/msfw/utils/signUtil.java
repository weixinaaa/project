package com.huiyou.msfw.utils;

import me.fishlord.common.utils.MD5Utils;

public class signUtil {

	public static final String signKey="c058f456b4ce441ca0b64705150b9885";
	
	
	public static boolean checkSign(String param,String sign){
		System.err.println(MD5Utils.sign(param+signKey));
		if (MD5Utils.sign(param+signKey).equals(sign)) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	public static void main(String[] args){
		
		System.out.println(MD5Utils.sign("sn=11111111"+signKey));
		
	}
	
}

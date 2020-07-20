package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.fishlord.common.utils.HttpUtils;
import me.fishlord.common.utils.MD5Utils;
import me.fishlord.common.utils.UUIDUtils;

public class AppTest {

	@org.junit.Test
	public void sendCommand() throws Exception{
		
		Map<String, String> param = new HashMap<>();
		param.put("sn", "11111111");
		param.put("command", "<STX>R11111111|0<ETX>8B<CR><LF>");
		String sign = MD5Utils.sign("sn=11111111&command=<STX>R11111111|0<ETX>8B<CR><LF>"+"c058f456b4ce441ca0b64705150b9885");
		param.put("sign", sign);
		
		System.out.println(HttpUtils.doGet("http://127.0.0.1:8080/phoneapp/app/sendCommand",param));
		
		
		
		
	}
	
	
}

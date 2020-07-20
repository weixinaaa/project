﻿var code = GetQueryString('code');
var openid = getCookie('openid');
var ip_addr = getUrl();
var ipad = encodeURIComponent(document.location.href);
if(!openid) {
	if(code == null || code == 'undefined') {
		window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0834f59fd9f5f666&redirect_uri=" + ipad + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	} else {
		GetUserOpenID();
	}

	function GetUserOpenID() {
		console.log(code);
		$.ajax({
			dataType: "json",
            type: 'post',

            url: ip_addr + '/user/addUser?content={"code":"' + code + '"}',
			success: function(data) {
				var result = data.rows[1];
				if(data.code == 0) {
					var openid = result.openId;
					var userid = result.usergid;
					var username = encodeURI(encodeURI(result.username));
					var sex = encodeURI(encodeURI(result.sex));
					var headimgurl = encodeURI(encodeURI(result.headimgurl));
					setCookie("openid", openid);
					setCookie("userid", userid);
					setCookie("username", username);
					setCookie("sex", sex);
					setCookie("headimgurl", headimgurl);
					// getUserIsFocus(openid);
				}
			}
		});
	}
	} else {
	// getUserIsFocus(openid);
}

//function getUserIsFocus(openid) {
//  //alert("判断是否关注，openid=" + openid);
//  $.ajax({
//      dataType: "json",
//      type: 'post',
//      url: ip_addr + 'GetUserIsFocus?content={"openid":"' + openid + '"}',
//      success: function (data) {
//          var result = data.result[0];
//          if (data.status == 0) {
////                var openid = result.openid;
////                var userid = result.usergid;
////                var username = encodeURI(encodeURI(result.nickname));
////                var sex = encodeURI(encodeURI(result.sex));
////                var headimgurl = encodeURI(encodeURI(result.headimgurl));
////                setCookie("openid", openid);
////                setCookie("userid", userid);
////                setCookie("username", username);
////                setCookie("sex", sex);
////                setCookie("headimgurl", headimgurl);
//          }
//          else{
//              //alert("请关注我们的公众号");
//              //window.location.href = 'https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzIxMTYxNDQ0OQ==&scene=124#wechat_redirect';
//          }
//      }
//  });
//
//}
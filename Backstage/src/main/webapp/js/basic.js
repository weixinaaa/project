﻿function jump(page) {
	window.location.href = page;
	cursor: pointer;
}
//获取URL参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");

	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
}

function getTime() {
	var timestamp = Date.parse(new Date());
	return formatDate(timestamp);
}

function formatDate(now) {
	var time = new Date(now);
	var year = time.getYear() + 1900;
	var month = time.getMonth() + 1;
	var date = time.getDate();
	var hour = time.getHours();
	var minute = time.getMinutes();
	var second = time.getSeconds();
	if(second < 10) {
		second = "0" + second;
	}
	if(minute < 10) {
		minute = "0" + minute;
	}
	if(hour < 10) {
		hour = "0" + hour;
	}
	if(date < 10) {
		date = "0" + date;
	}
	if(month < 10) {
		month = "0" + month;
	}
	return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}

function formatDateD(now) {
	var time = new Date(now);
	var year = time.getYear() + 1900;
	var month = time.getMonth() + 1;
	var date = time.getDate();
	var hour = time.getHours();
	var minute = time.getMinutes();
	var second = time.getSeconds();
	if(second < 10) {
		second = "0" + second;
	}
	if(minute < 10) {
		minute = "0" + minute;
	}
	if(hour < 10) {
		hour = "0" + hour;
	}
	if(date < 10) {
		date = "0" + date;
	}
	if(month < 10) {
		month = "0" + month;
	}
	return year + "-" + month + "-" + date;
}

function formatDateM(now) {
	var time = new Date(now);
	var year = time.getYear() + 1900;
	var month = time.getMonth() + 1;
	var date = time.getDate();
	var hour = time.getHours();
	var minute = time.getMinutes();
	var second = time.getSeconds();
	if(second < 10) {
		second = "0" + second;
	}
	if(minute < 10) {
		minute = "0" + minute;
	}
	if(hour < 10) {
		hour = "0" + hour;
	}
	if(date < 10) {
		date = "0" + date;
	}
	if(month < 10) {
		month = "0" + month;
	}
	return year + "-" + month + "-" + date + " " + hour + ":" + minute;
}

function formatDateM2(now) {
	var time = new Date(now);
	var year = time.getYear() + 1900;
	var month = time.getMonth() + 1;
	var date = time.getDate();
	var hour = time.getHours();
	var minute = time.getMinutes();
	var second = time.getSeconds();
	if(second < 10) {
		second = "0" + second;
	}
	if(minute < 10) {
		minute = "0" + minute;
	}
	if(hour < 10) {
		hour = "0" + hour;
	}
	if(date < 10) {
		date = "0" + date;
	}
	if(month < 10) {
		month = "0" + month;
	}
	return year + "." + month + "." + date + " " + hour + ":" + minute;
}

function formateDateSpacial(beginTime) {
	beginTime = beginTime.replace(/-/g, ':').replace(' ', ':');
	beginTime = beginTime.split(':');
	var time = new Date(beginTime[0], (beginTime[1] - 1), beginTime[2], beginTime[3], beginTime[4], beginTime[5]);
	var year = time.getYear() + 1900;
	var month = time.getMonth() + 1;
	var date = time.getDate();
	var hour = time.getHours();
	var minute = time.getMinutes();
	var second = time.getSeconds();
	if(second < 10) {
		second = "0" + second;
	}
	if(minute < 10) {
		minute = "0" + minute;
	}
	if(hour < 10) {
		hour = "0" + hour;
	}
	if(date < 10) {
		date = "0" + date;
	}
	if(month < 10) {
		month = "0" + month;
	}

	return year + "-" + month + "-" + date + " " + hour + ":" + minute

}

function comptime(beginTime, endTime) {
	beginTime = beginTime.replace(/-/g, ':').replace(' ', ':');
	beginTime = beginTime.split(':');
	var time1 = new Date(beginTime[0], (beginTime[1] - 1), beginTime[2], beginTime[3], beginTime[4], beginTime[5]);

	var year1 = time1.getYear() + 1900;
	var month1 = time1.getMonth() + 1;
	var date1 = time1.getDate();
	var hour1 = time1.getHours();
	var minute1 = time1.getMinutes();
	var second1 = time1.getSeconds();

	endTime = endTime.replace(/-/g, ':').replace(' ', ':');
	endTime = endTime.split(':');
	var time2 = new Date(endTime[0], (endTime[1] - 1), endTime[2], endTime[3], endTime[4], endTime[5]);

	var year2 = time2.getYear() + 1900;
	var month2 = time2.getMonth() + 1;
	var date2 = time2.getDate();
	var hour2 = time2.getHours();
	var minute2 = time2.getMinutes();
	var second2 = time2.getSeconds();

	if(year1 < year2) {
		return true;
	} else if(month1 < month2) {
		return true;
	} else if(date1 < date2) {
		return true;
	} else if(hour1 < hour2) {
		return true;
	} else if(minute1 < minute2) {
		return true;
	} else if(second1 < second2) {
		return true;
	} else {
		return false;
	}

}

function phone() {
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
	if(!myreg.test($("#phone").val())) {
		alert('请输入有效的手机号码！');
		return false;
	}
}

function conLogClassName() {
	$("body").click(function(event) {
		console.log(event.target.className);
	});
}

function calcBox() {
	$(".jian").click(function() {
		var $jian = parseInt($(this).prev().val());
		if($jian > 1)
			$jian--;
		$(this).prev().val($jian);
	});
	$(".jia").click(function() {
		var $jian = parseInt($(this).next().val());
		$jian++;
		$(this).next().val($jian);
	});
}

function setDoublePoint(value) {
	if(value == null) return 0;
	var value = Math.round(parseFloat(value) * 100) / 100;
	var xsd = value.toString().split(".");
	if(xsd.length == 1) {
		value = value.toString() + ".00";
		return value;
	}
	if(xsd.length > 1) {
		if(xsd[1].length < 2) {
			value = value.toString() + "0";
		}
		return value;
	}
}
//三位数一个逗号
function formatNum(str) {
	str = str.toString();
	var newStr = "";
	var count = 0;
	if(str.indexOf(".") == -1) {
		for(var i = str.length - 1; i >= 0; i--) {
			if(count % 3 == 0 && count != 0) {
				newStr = str.charAt(i) + "," + newStr;
			} else {
				newStr = str.charAt(i) + newStr;
			}
			count++;
		}
		//		str = newStr + ".00"; //自动补小数点后两位

		str = newStr;
		console.log(str)
		return str;
	} else {
		for(var i = str.indexOf(".") - 1; i >= 0; i--)
			if(count % 3 == 0 && count != 0) {
				newStr = str.charAt(i) + "," + newStr;
			} else {
				newStr = str.charAt(i) + newStr; //逐个字符相接起来

			}
		count++;
	}
	str = newStr + (str + "00").substr((str + "00").indexOf("."), 3);
	console.log(str)
	return str;
}
//}

function changeNum(x) {
	if(x > 99999999) {
		x = x / 100000000;
		return(Math.round(x * 10) / 10).toString() + '亿';
	} else if(x > 9999) {
		x = x / 10000;
		return(Math.round(x * 10) / 10).toString() + '万';
	} else {
		return x;
	}
}

function setImg(imgstr) {
	if(!imgstr) {
		console.log("imgstr:" + imgstr);
	}
	if(imgstr.indexOf(",") > 0) {
		return imgstr.split(",")[0];
	} else {
		return imgstr;
	}
}

function changeMoneyToChinese(money) {
	var cnNums = new Array("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"); //汉字的数字  
	var cnIntRadice = new Array("", "拾", "佰", "仟"); //基本单位  
	var cnIntUnits = new Array("", "万", "亿", "兆"); //对应整数部分扩展单位  
	var cnDecUnits = new Array("角", "分", "毫", "厘"); //对应小数部分单位  
	//var cnInteger = "整"; //整数金额时后面跟的字符  
	var cnIntLast = "元"; //整型完以后的单位  
	var maxNum = 999999999999999.9999; //最大处理的数字  

	var IntegerNum; //金额整数部分  
	var DecimalNum; //金额小数部分  
	var ChineseStr = ""; //输出的中文金额字符串  
	var parts; //分离金额后用的数组，预定义  
	if(money == "") {
		return "";
	}
	money = parseFloat(money);
	if(money >= maxNum) {
		alert('超出最大处理数字');
		return "";
	}
	if(money == 0) {
		//ChineseStr = cnNums[0]+cnIntLast+cnInteger;  
		ChineseStr = cnNums[0] + cnIntLast
		//document.getElementById("show").value=ChineseStr;  
		return ChineseStr;
	}
	money = money.toString(); //转换为字符串  
	if(money.indexOf(".") == -1) {
		IntegerNum = money;
		DecimalNum = '';
	} else {
		parts = money.split(".");
		IntegerNum = parts[0];
		DecimalNum = parts[1].substr(0, 4);
	}
	if(parseInt(IntegerNum, 10) > 0) { //获取整型部分转换  
		zeroCount = 0;
		IntLen = IntegerNum.length;
		for(i = 0; i < IntLen; i++) {
			n = IntegerNum.substr(i, 1);
			p = IntLen - i - 1;
			q = p / 4;
			m = p % 4;
			if(n == "0") {
				zeroCount++;
			} else {
				if(zeroCount > 0) {
					ChineseStr += cnNums[0];
				}
				zeroCount = 0; //归零  
				ChineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
			}
			if(m == 0 && zeroCount < 4) {
				ChineseStr += cnIntUnits[q];
			}
		}
		ChineseStr += cnIntLast;
		//整型部分处理完毕  
	}
	if(DecimalNum != '') { //小数部分  
		decLen = DecimalNum.length;
		for(i = 0; i < decLen; i++) {
			n = DecimalNum.substr(i, 1);
			if(n != '0') {
				ChineseStr += cnNums[Number(n)] + cnDecUnits[i];
			}
		}
	}
	if(ChineseStr == '') {
		//ChineseStr += cnNums[0]+cnIntLast+cnInteger;  
		ChineseStr += cnNums[0] + cnIntLast;
	}
	/* else if( DecimalNum == '' ){ 
	                ChineseStr += cnInteger; 
	                ChineseStr += cnInteger; 
	            } */
	return ChineseStr;
}

function isCardNo(card) {
	// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	if(reg.test(card) === false) {
		return false;
	}
	return true;
}

function checkPhone(phone) {
	//  phone = $("#phone").val().replace(/\s/g,"");
	var reg = new RegExp("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$", "g");
	return reg.exec(phone);
}

function checkLogin() {
	if(!sessionStorage.getItem("userId")) {
		$.alert("请先登录", function() {
			window.location.href = 'login.html';
		});
	}
}

function renumdou(str) {
	var regexp = /[^A-Za-z0-9\u4e00-\u9fa5]/g;
	newstr = str.replace(regexp, "");
	return newstr
}

function checkNum(num) {
	var reg = new RegExp("^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$");
	return reg.exec(num);
}

function readStoke(itemList) {
	var itemArr = [];
	var itemStr = [];
	for(var i = 0; i < $(".showtypeitem .active").length; i++) {
		var item = $(".showtypeitem .active").eq(i);
		itemArr.push(item.attr("data-mallitemattrs"));
		itemStr.push(item.text());
	}
	var html = ' 已选择: ';
	for(var i = 0; i < itemStr.length; i++) {
		html += ' <span>' + itemStr[i] + '</span>';
	}
	$(".sku-info").html(html);

	console.log(itemArr);
	var itemIds = itemArr.join(",")
	console.log(itemIds);

	for(var i = 0; i < itemList.length; i++) {
		if(itemList[i].mallItemAttrs == itemIds) {
			$("#itemStock").text(itemList[i].stock);
			$("#itemPrice").text(itemList[i].price / 100);
		}
	}
}
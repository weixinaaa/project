//admin
$("#admin").click(function(event) {
	event.stopPropagation();
	$(this).toggleClass("on");
	$(this).find(".admin_list").slideToggle(200);
});
$("html").click(function(e) {
	if($(e.target) != $("#admin")) {
		$("#admin").removeClass("on");
		$(".admin_list").slideUp("slow");
	}
})
//刷新
$("#refresh").hover(function() {
	$(this).find(".layui-icon").toggleClass("layui-anim-rotate");
});

function formatDate(now) {
	var time = new Date(now);
	var year = time.getYear() + 1900;
	var month = time.getMonth() + 1;
	var date = time.getDate();
	var hour = time.getHours();
	var minute = time.getMinutes();
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
	var second = time.getSeconds();
	if(second < 10) {
		second = "0" + second;
	}
	return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}

function check(container, text) {
	var flag = true;
	var val = $("#" + container).val();
	if(val == undefined || val.trim() == "") {
		show("提示", text + "不能为空!");
		$("#" + container).parent().addClass("has-error");
		flag = false;
	}
	return flag;
}

/**
 * 脉动消息提示,3秒自动关闭
 *
 * @param title 提示标题
 * @param text 提示内容
 */
function show(title, text) {
	$.gritter.add({
		title: title,
		text: text,
		sticky: false
	});
}

/**
 * 脉动消息提示,3秒自动关闭
 *
 * @param title 提示标题
 * @param text 提示内容
 * @param falg 是否自动关闭,false关闭 true不关闭
 */
function show(title, text, flag) {
	$.gritter.add({
		title: title,
		text: text,
		sticky: flag
	});
}

/**
 * 信息提示框
 *
 * @param title 显示标题
 * @param text 信息内容
 */
function showMsg(title, text) {
	showMsg(title, text, false);
}

/**
 * 带刷新回调事件的信息提示框,当auto_back为true时,需要定义refresh()方法
 * @param title
 * @param text
 * @param auto_back
 */
function showMsg(title, text, auto_back) {
	$("#dialog").html(text);
	$("#dialog").dialog({
		autoOpen: true,
		draggable: false,
		dialogClass: "alert",
		width: 400,
		title: title,
		buttons: [{
			text: "确定",
			click: function() {
				$(this).dialog("close");
				if(auto_back) {
					refresh();
				}
			}
		}]
	});
}

var config = {
	pageSize: 10,
	serverUrl: "/msfw/console/",
	loadIcon: 3,
}

var hyAjax = {
	/**
	 * 基本的Ajax请求
	 *
	 * 无需每次都设置请求地址,主要用户获取请求接口
	 *
	 * @interfaceName    {string}  接口名称
	 * @requestData    {Object}   请求的数据
	 * @callack   回调函数
	 *
	 */
	baseAjax: function(interfaceName, requestData, callback) {
		$.ajax({
			type: "post",
			dataType: "json",
			url: config.serverUrl + interfaceName,
			data: requestData,
			success: function(data) {
				return callback(data);
			}
		})
	},
	formatDate: function(str) {
		var date = new Date(str);
		var y = date.getFullYear();
		if(y < 2000)
			return "";
		var m = date.getMonth() + 1;
		m = m < 10 ? '0' + m : m;
		var d = date.getDate();
		d = d < 10 ? ('0' + d) : d;
		return y + '-' + m + '-' + d;
	},
	formatDateTime: function(str) {
		var date = new Date(str);
		var y = date.getFullYear();
		if(y < 2000)
			return "";
		var m = date.getMonth() + 1;
		m = m < 10 ? '0' + m : m;
		var d = date.getDate();
		d = d < 10 ? ('0' + d) : d;

		var h = date.getHours();
		h = h < 10 ? ('0' + h) : h;

		var min = date.getMinutes();
		min = min < 10 ? ('0' + min) : min;

		var s = date.getSeconds();
		s = s < 10 ? ('0' + s) : s;

		return y + '-' + m + '-' + d + " " + h + ":" + min + ":" + s;
	}
}

/**
 * 通用获取数据列表（包括分页器）
 * @param {Object} interfaceName 接口名称
 * @param {Object} requestData 请求的参数
 * @param {Object} pageNo 页数
 * @param {Object} tmpListId  使用的模板对象id
 * @param {Object} listViewId 显示列表对象id
 * @param {Object} laypageId 分页对象id
 */
var page = 1;

function CommonLoadList(interfaceName, requestData, pageNo, tmpListId, listViewId, laypageId) {
	//原先为page=pageNo
	page=1;
	
	var loadIndex = loadCut();
	//模板引擎

	var laytpl = layui.laytpl;
	var laypage = layui.laypage;
	var pageSize = config.pageSize;
	requestData.pageNo = pageNo;
	requestData.pageSize = pageSize;

	hyAjax.baseAjax(interfaceName, requestData, function(data) {

		var getTpl = $("#" + tmpListId).html();
		laytpl(getTpl).render(data, function(htmlString) {
			$("#" + listViewId).html(htmlString);
		});

		//console.log(data.total);
		var total = data.total;
		if($("#totalCount").length > 0)
			$("#totalCount").html(total)

		//		laypage({
		//			cont: laypageId,
		//
		//			pages: (total % pageSize > 0 ? (total / pageSize + 1) : total / pageSize), //总页数,
		//			curr: pageNo,
		//			groups: 4, //连续显示分页数,
		//			skin: '#09C', //当前页码的背景色
		//			skip: true,
		//			jump: function(obj, first) { //触发分页后的回调
		//				if(!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
		//					console.log(obj.curr);
		//					CommonLoadList(interfaceName, requestData, obj.curr, tmpListId, listViewId, laypageId);
		//					
		//				}
		//			}
		//		});
		//		

		laypage.render({
			elem: laypageId,
			count: total //数据总数，从服务端得到
				,
			curr: pageNo,
			layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
			jump: function(obj, first) {
				//obj包含了当前分页的所有参数，比如：
				console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
				console.log(obj.limit); //得到每页显示的条数

				//首次不执行
				if(!first) {
					console.log(obj.curr);
					CommonLoadList(interfaceName, requestData, obj.curr, tmpListId, listViewId, laypageId);
					//do something
				}
			}
		});

		setCheckBox();

	});
	setTimeout(function() {
		layer.close(loadIndex);
	}, 500);

}

function CommonLoadTable(tableDom,data, url, dataCols, donFunc) {
	var tableIns = layui.table.render({
		// 表格基础参数 : http://www.layui.com/doc/modules/table.html#options
		elem: tableDom,
		where:data,
		loading: true,
		url: url //数据接口
			,
		method: 'post',
		page: true //开启分页
			,
		request: {
			pageName: 'page' //页码的参数名称，默认：page
				,
			limitName: 'limit' //每页数据量的参数名，默认：limit
		},
		response: {
			statusName: 'code' //数据状态的字段名称，默认：code
				,
			statusCode: 0 //成功的状态码，默认：0
				,
			msgName: 'msg' //状态信息的字段名称，默认：msg
				,
			countName: 'total' //数据总数的字段名称，默认：count
				,
			dataName: 'rows' //数据列表的字段名称，默认：data
		},
		cols: [dataCols],
		done: function(res, curr, count) {
			donFunc(tableIns, res, curr, count);
		}
	});

}

function loadCut() {
	return layer.load(config.loadIcon);
}

function setCheckBox() {
	layui.form.render('checkbox');
	layui.form.on('checkbox(allChoose)', function(data) {
		var child = $(data.elem).parents('table').find('tbody tr td:first-child input[type="checkbox"]');
		child.each(function(index, item) {
			item.checked = data.elem.checked;
		});
		layui.form.render('checkbox');
	});
}

function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
}

function limit(str, n) {
	if(str.length > n) {
		return str.substring(0, n) + "...";
	} else {
		return str;
	}
}

function isInteger(s) { //是否为正整数
	var re = /^\d+(?=\.{0,1}\d+$|$)/;
	return re.test(s)
}
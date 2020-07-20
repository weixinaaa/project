var $ = layui.jquery,
	form = layui.form;

/**
 * 通用操作
 */

function showDialogTip() {
	setTimeout(function() {
		layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
			tips: 3
		});
	}, 500)
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
function CommonLoadList(interfaceName, requestData, pageNo, tmpListId, listViewId, laypageId) {
	var loadIndex = loadCut();

	//模板引擎
	var laytpl = layui.laytpl;
	var laypage = layui.laypage;
	var pageSize = config.pageSize;
	requestData.pageNo = pageNo;
	requestData.pageSize = pageSize;

	hyAjax.baseAjax(interfaceName, requestData, function(data) {

		var getTpl = $("#" + tmpListId).html();
		if(getTpl == undefined) {
			layer.msg("未找到模板");
			return;
		}
		laytpl(getTpl).render(data, function(htmlString) {
			$("#" + listViewId).html(htmlString);
			form.render('checkbox');
		});
		var total = data.total;
		laypage.render({
			elem: laypageId,
			curr: pageNo,
			count: total,
			pages: (total % pageSize > 0 ? (total / pageSize + 1) : total / pageSize), //总页数,
			groups: 4, //连续显示分页数,
			skin: '#09C', //当前页码的背景色
			jump: function(obj, first) { //触发分页后的回调
				if(!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
					CommonLoadList(interfaceName, requestData, obj.curr, tmpListId, listViewId, laypageId);
				}
			}
		});
	});
	setTimeout(function() {
		layer.close(loadIndex);
	}, 500);
}
/**
 * 通用删除
 * @param {Object} id 删除的记录id
 * @param {Object} url 请求的接口
 * @param {Object} okcallback 成功的回调函数
 */
function CommonRemove(id, url, okcallback) {
	layer.confirm("确认删除？", {
		btn: ['确认', '取消'],
		icon: 3,
		title: '消息'
		//按钮
	}, function(index, layero) {
		var p = {
			id: id
		};
		hyAjax.baseAjax(url, p, function(data) {
			if(data.code == 0) {
				layer.close(index); //关闭对话框
				return okcallback(data);
			} else {
				layer.msg(data.msg);
			}
		});

	}, function() {

	});

}

/**
 * 
 * @param {Object} id 数据列表的id
 * @param {Object} url 请求的接口
 */
function CommonOperation(id, url) {
	var p = {
		id: id
	};
	hyAjax.baseAjax(url, p, function(data) {

		if(data.code == 0) {

		} else {
			layer.msg(data.msg);
		}
	});

}
/**
 * 
 * @param {Object} id 数据列表的id
 * @param {Object} url 请求的接口
 * @param {Object} okcallback 成功的回调函数
 */
function CommonBatchOperation(id, url, okcallback) {
	layer.confirm("确认继续操作？", {
		btn: ['确认', '取消'],
		icon: 3,
		title: '消息'
		//按钮
	}, function(index, layero) {
		var p = {
			id: id
		};
		hyAjax.baseAjax(url, p, function(data) {
			if(data.code == 0) {
				layer.close(index); //关闭对话框

				if(okcallback != null && okcallback != undefined)
					return okcallback(data);
			} else {
				layer.msg(data.msg);
			}
		});

	}, function() {

	});
}

function getCheckedId(id) {
	var arr = [];
	var child = $("#" + id).find('tbody input[type="checkbox"][name="chk"]');
	child.each(function(index, item) {
		if(item.checked)
			arr.push(item.value);
	});
	return arr;
}
/*
	function getCheckedId(name) {
		var spCodesTemp = "";
		$('input:checkbox[name="' + name + '"]: checked ').each(function(i) {
				if(0 == i) {
					spCodesTemp = $(this).val();
				} else {
					spCodesTemp += ("," + $(this).val());
				}
			}
			return spCodesTemp;
		}
	}*/

function loadCut() {
	return layer.load(config.loadIcon);
	return -1;
}

/**
 * 绑定数据
 * @param {Object} fromId
 * @param {Object} data
 */
function bindRowData(fromId, data) {
	//var from = $("#" + fromId);
	for(var key in data) {
		var value = data[key];
		try {
			$("#" + fromId + " input[type!='radio'][name='" + key + "']").val(value);
			$("#" + fromId + " textarea[name='" + key + "']").val(value);
			$("#" + fromId + " select[name='" + key + "']").val(value);
			$("#" + fromId + " input[type=radio][name=" + key + "][value=" + value + "]").attr("checked", true);
			$("#" + fromId + " input[type=radio][name=" + key + "][value=" + value + "]").next().find("i").click();
			form.render();
		} catch(e) {
			continue;
		}
	}
}

function CreateLayEdit(tbid) {
	layui.use('layedit', function() {
		var layedit = layui.layedit;
		layedit.set({
			uploadImage: {
				url: config.serverUrl + 'uploadfile/layeditimage', //接口url
				type: 'post' //默认post
			}
		});

		layeditIndex = layedit.build(tbid); //
	})
}

/**
 * 设置日期选择器
 * @param {Object} id
 */
function renderDateSelector(id) {
	var laydate = layui.laydate;
	laydate.render({
		elem: '#' + id
	});
}

/**
 * 设置年月选择器
 * @param {Object} id
 */
function renderYearMonthSelector(id) {
	var laydate = layui.laydate;
	//年月选择器
	laydate.render({
		elem: '#' + id,
		type: 'month'
	});

}
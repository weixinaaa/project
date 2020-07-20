// 正则表达式方法集合
//function noNull(str,strName){
//
//  layui.use(['form'], function() {
//		var form = layui.form,
//			layer = layui.layer;
//	});
//	
//	if (str == "") {
//		layer.msg('请输入'+strName, {
//			icon: 2
//		});
//		return;
//  }
//}

function noSpaces(str){

	layui.use(['form'], function() {
		var form = layui.form,
			layer = layui.layer;
	});

    var reg = /^[^ ]+$/;
	if (!reg.test(str)) {
	    layer.msg('输入内容不能包含空格',{
		    icon: 2	
		});
		return;
	}
}
//function noSpaces(str){
//	var reg = /^[^ ]+$/;
//	if (!reg.test(str)) {
//	    layer.msg('输入内容不能包含空格',{
//		    icon; 2	
//		});
//		return;
//	}
//}
//function noSpaces(str){
//	var reg = /^[^ ]+$/;
//	if (!reg.test(str)) {
//	    layer.msg('输入内容不能包含空格',{
//		    icon; 2	
//		});
//		return;
//	}
//}
$(document).ready(function(e) {
	//alert(1);
    var str = "<select id='sheng'></select><select id='shi'></select><select id='qu'></select>";//三个下拉交给一个字符串
    $("#sanji").html(str);//将三个下拉的字符串交给前边的div
    tiansheng();//加载省的数据
    tianshi();//加载市的数据
    tianqu();//加载区 的数据
    
     $("#sheng").change(function(){                
            tianshi();//重新加载市
            tianqu();//重新加载区
        })
    $("#shi").change(function(){
            tianqu();//加载区的数据
        })
});

function tiansheng()
{
     var pcode = "0"; //找出省的父级代号
      $.ajax({
            async:false,//同步
            url:"../console/mallItemCategory/list",
            data:{pId:pcode},
            type:"POST",
            dataType:"json",
            success: function(data){
            		var result = data.data;
                    var str = "";
                    for (var i=0;i<result.length;i++) {
						str += "<option value='"+result[i].id+"'>"+result[i].name+"</option>";
					}                    
                    $("#sheng").html(str); 
                }
        });
}

function tianshi()
{
    var pcode = $("#sheng").val();//找市的父级代号，省选中项的值
    $.ajax({
            async:false,
            url:"../console/mallItemCategory/list",
            data:{pId:pcode},
            type:"POST",
            dataType:"json",
            success: function(data){
                    var result = data.data;
                    var str = "";
                    for (var i=0;i<result.length;i++) {
						str += "<option value='"+result[i].id+"'>"+result[i].name+"</option>";
					} 
                    $("#shi").html(str);
                }
    });
}

function tianqu()
{
    var pcode = $("#shi").val();//找区的父级代号，市选中项的值
    $.ajax({
            url:"../console/mallItemCategory/list",
            data:{pId:pcode},
            type:"POST",
            dataType:"json",
            success: function(data){
            	alert(data.data)
                    var result = data.data;
                    var str = "";
                    if(result==""){
                    	$("#qu").hide();
                    }else{
                    	$("#qu").show();
                    	for (var i=0;i<result.length;i++) {
							str += "<option value='"+result[i].id+"'>"+result[i].name+"</option>";
						}
                    	$("#qu").html(str);
					}                    
               }
        });
}
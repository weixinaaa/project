
layui.use('laydate', function(){
  var laydate = layui.laydate;
  
  //开始时间
  laydate.render({
    elem: '#LAY_demorange_s'
  });
  //结束时间
  laydate.render({
    elem: '#LAY_demorange_e'
  });
  
   //日期时间范围
  laydate.render({
    elem: '#LAY_demorange_T'
    ,type: 'datetime'
    ,range: true
  });
});

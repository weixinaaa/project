





//返回上一页 或 指定页面
function toUrl(url) {
  if (!url) {
    window.history.go(-1);
  } else {
    window.location.href = url;
  }
}

//获取链接中参数

function GetUrlParam(paraName) {
  var url = document.location.toString();
  var arrObj = url.split("?");

  if (arrObj.length > 1) {
    var arrPara = arrObj[1].split("&");
    var arr;

    for (var i = 0; i < arrPara.length; i++) {
      arr = arrPara[i].split("=");

      if (arr != null && arr[0] == paraName) {
        return decodeURI(arr[1]);
      }
    }
    return "";
  } else {
    return "";
  }
}

//验证手机号是否合法
function checkPhone(phone) {
  if (!/^[1][3,4,5,7,8][0-9]{9}$/.test(phone)) {
    // alert("手机号码有误，请重填或注册");

    return false;
  } else {
    return true;
  }
}


 
//验证码重发倒计时

 function  setTime(){
      let time = 60;
      let i = 0;
     
      //1.在60秒之内，button上倒计时60秒，并显示
      //2.向controller请求验证码，并发送到指定的手机号上面
      let set = setInterval(function() {
          document.getElementById("verifycode").innerHTML = (--time)+"秒后重发";
          i++;	
          //此变量用于标识只调用一次获取验证码的函数
          if (i==1) {
              console.log("get code start1 ... ");
              //使获取验证码按钮不可点击
              document.getElementById("verifycode").disabled=true;
              document.getElementById('verifycode').style.background='#999b9b';
          }
      }, 1000);
      
      //60秒之后需要做的事情
      setTimeout(function() {
          //恢复获取验证码按钮可点击
          document.getElementById("verifycode").disabled=false;
          document.getElementById('verifycode').style.background='#3e9dfe';
          document.getElementById("verifycode").innerHTML = "获取验证码";
          clearInterval(set);//清除计时器
      }, time * 1000); //60000  
    }
    




























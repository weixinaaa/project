var url = "http://client2.365hy.com/IN_minsheng/trunk/H5/index.html";
 let appid = 'wx778e232fd776c31b';

/**
 * 监听对象里面的某个字段,发生改变执行   类似于 vue -  return data
 * 用法：watch('msg', item=>{console.log(item);});
 * @param name  对象里面的 字段
 * @param callback  对象里面的 字段被改变，执行方法
 */
function watch(name, callback) {
  let value = IM.name;
  Object.defineProperty(IM, name, {
    set(msg) {
      value = msg;
      callback(value);
    },
    get() {
      return value;
    },
  });
}
$(function () {
 
  let ua = navigator.userAgent.toLowerCase();
  let isWeixin = ua.indexOf("micromessenger") != -1; //判断是否为微信浏览器
  if (isWeixin) {

    let code = "";
    let local = window.location.href; // 获取页面url
    code = getUrlCode().code;
//    alert(code);
//    document.getElementById("shouye").innerHTML = code
//    localStorage.setItem("mycode", code);
    if (code == null || code == "" || code == undefined) {
      window.location.href = `https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${encodeURIComponent(
        local
      )}&response_type=code&scope=snsapi_userinfo#wechat_redirect`;
    } else {
      if (
        localStorage.getItem("openId") == "null" ||
        localStorage.getItem("openId") == "undefined" ||
        localStorage.getItem("openId") == ""
      ) {
        userLogin(code);
      }
    }
  }
});

function getUrlCode() {
  // 截取url中的code方法
  var url = location.search;
  let winUrl = url;
  var theRequest = new Object();
  if (url.indexOf("?") != -1) {
    let str = url.substr(1);
    let strs = str.split("&");
    for (let i = 0; i < strs.length; i++) {
      theRequest[strs[i].split("=")[0]] = strs[i].split("=")[1];
    }
  }
  return theRequest;
}

function userLogin(code) {
  ajax.getAjax(
    "   getOpenid",
    {
      code: code,
    },
    (res) => {
      localStorage.setItem("openId", res.data);
      console.log(res);

    }
  );
}

//判断登录
function isLogin() {
  let userId = localStorage.getItem("userId");
  if (userId == "" || userId == null) {
    return false;
  } else {
    return true;
  }
}

//返回上一页 或 指定页面
function toUrl(url) {
  if (!url) {
    window.history.go(-1);
  } else {
    window.location.href = url;
  }
}

/*获取url参数*/
function geturl(name) {
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
  var r = window.location.search.substr(1).match(reg);
  if (r != null) return decodeURI(r[2]);
  return null;
}

/**
 *  一维数组 去重
 * @param array 数组
 * @returns {Array} 返回去重后的数值
 */
function uniq(array) {
  var temp = [];
  for (var i = 0; i < array.length; i++) {
    if (temp.indexOf(array[i]) == -1) {
      temp.push(array[i]);
    }
  }
  return temp;
}

// $("img").addEventListener("onError",function () {
//    console.log("图片报错 ");
// });

// $("img").addEventListener("onError",function () {
//    console.log("图片报错 ");
// });

/**
 * 时间戳 转 时间
 * 时间戳 只能返回 13位和10位
 * @param timestamp 时间戳
 * @param isYMD  不空 返回 年月日【默认返回 年月日时分秒】
 * @returns 时间
 */
function timestampToTime(timestamp, isYMD) {
  if (timestamp == null) {
    return;
  }
  if ((timestamp + "").length == 13) {
    var date = new Date(timestamp * 1);
  } else if ((timestamp + "").length == 10) {
    var date = new Date(timestamp * 1000);
  }
  var Y = date.getFullYear() + "-";
  var M =
    (date.getMonth() + 1 < 10
      ? "0" + (date.getMonth() + 1)
      : date.getMonth() + 1) + "-";
  var D = change(date.getDate()) + " ";
  var D2 = change(date.getDate());
  var h = change(date.getHours()) + ":";
  var m = change(date.getMinutes()) + ":";
  var s = change(date.getSeconds());
  if (isYMD == null) {
    return Y + M + D + h + m + s;
  } else {
    return Y + M + D2;
  }

  function change(t) {
    if (t < 10) {
      return "0" + t;
    } else {
      return t;
    }
  }
}

stringifyDate = function (datetime, simple = false) {
  let weekMap = [
    "星期日",
    "星期一",
    "星期二",
    "星期三",
    "星期四",
    "星期五",
    "星期六",
  ];
  datetime = new Date(datetime);
  let year = datetime.getFullYear();
  let simpleYear = datetime.getYear() - 100;
  let month = datetime.getMonth() + 1;
  month = month > 9 ? month : "0" + month;
  let day = datetime.getDate();
  day = day > 9 ? day : "0" + day;
  let hour = datetime.getHours();
  hour = hour > 9 ? hour : "0" + hour;
  let min = datetime.getMinutes();
  min = min > 9 ? min : "0" + min;
  let week = datetime.getDay();
  week = weekMap[week];
  let thatDay = new Date(year, month - 1, day, 0, 0, 0).getTime();

  if (simple) {
    return {
      withYear: `${day}/${month}/${simpleYear}`,
      withMonth: `${month}-${day}`,
      withDay: `${week}`,
      withLastDay: `昨天`,
      withHour: `${hour}:${min}`,
      thatDay,
    };
  } else {
    return {
      withYear: `${year}-${month}-${day} ${hour}:${min}`,
      withMonth: `${month}-${day} ${hour}:${min}`,
      withDay: `${week} ${hour}:${min}`,
      withLastDay: `昨天 ${hour}:${min}`,
      withHour: `${hour}:${min}`,
      thatDay,
    };
  }
};

/* 格式化日期 */
formatDate = function (datetime, simple = false) {
  let tempDate = new Date().getTime();
  let result = this.stringifyDate(datetime, simple);
  let thatDay = result.thatDay;
  let deltaTime = (tempDate - thatDay) / 1000;

  if (deltaTime < 3600 * 24) {
    return result.withHour;
  } else if (deltaTime < 3600 * 24 * 2) {
    return result.withLastDay;
  } else if (deltaTime < 3600 * 24 * 7) {
    return result.withDay;
  } else if (deltaTime < 3600 * 24 * 30) {
    return result.withMonth;
  } else {
    return result.withYear;
  }
};

//获取当前时间
function getTime() {
  var myDate = new Date();
  var year = myDate.getFullYear(); //获取当前年
  var month = myDate.getMonth() + 1; //获取当前月
  var date = myDate.getDate(); //获取当前日
  var h = myDate.getHours(); //获取当前小时数(0-23)
  var m = myDate.getMinutes(); //获取当前分钟数(0-59)
  var s = myDate.getSeconds();
  month = month < 10 ? "0" + month : month;
  date = date < 10 ? "0" + date : date;
  h = h < 10 ? "0" + h : h;
  m = m < 10 ? "0" + m : m;
  s = s < 10 ? "0" + s : s;
  return year + "-" + month + "-" + date + " " + h + ":" + m + ":" + s;
}

//截取视频第一帧 返回图片
//url 不支持跨域
//建议：在上传之前将拿到视频路径，在调用这个方法
//拿视频路径 方法：getObjectURL(this.files[0])
//file 是否需要返回file，默认 false
function videoToImg(url, file) {
  var video = document.createElement("video");
  var scale = 0.8;
  video.src = url;
  document.body.appendChild(video);
  document.body.removeChild(video);
  return new Promise((resolve, reject) => {
    video.addEventListener("loadeddata", () => {
      var canvas = document.createElement("canvas"); // 创建一个画布
      canvas.width = video.videoWidth * scale;
      canvas.height = video.videoHeight * scale;
      canvas
        .getContext("2d")
        .drawImage(video, 0, 0, canvas.width, canvas.height); // getContext:设置画布环境；drawImage:画画
      var img = new Image();
      img.setAttribute("crossOrigin", "anonymous");
      img.src = canvas.toDataURL("image/png"); // 获取图片的url
      if (!file) {
        resolve(canvas.toDataURL("image/png"));
      } else {
        resolve([
          canvas.toDataURL("image/png"),
          dataURLtoFile(canvas.toDataURL("image/png")),
        ]);
      }
    });
  });
}

//将base64转换为文件
function dataURLtoFile(dataurl) {
  var arr = dataurl.split(","),
    mime = arr[0].match(/:(.*?);/)[1],
    bstr = atob(arr[1]),
    n = bstr.length,
    u8arr = new Uint8Array(n);
  while (n--) {
    u8arr[n] = bstr.charCodeAt(n);
  }
  return new File([u8arr], Math.ceil(Math.random() * 1000) + ".png", {
    type: mime,
  });
}

//video上传 前获取到 视频路径
//配合videoToImg（） 可获取视频第一帧
function getObjectURL(file) {
  var url = null;
  if (window.createObjectURL != undefined) {
    // basic
    url = window.createObjectURL(file);
  } else if (window.URL != undefined) {
    // mozilla(firefox)
    url = window.URL.createObjectURL(file);
  } else if (window.webkitURL != undefined) {
    // webkit or chrome
    url = window.webkitURL.createObjectURL(file);
  }
  return url;
}

//文件上传
function upload(data, func) {
  $.ajax({
    type: "post",
    url: url + "/file/upload",
    dataType: "json",
    processData: false,
    contentType: false,
    data: data,
    success: function (res) {
      // console.log(res);
      func(res);
    },
  });
}

//多文件上传
function upload2(data, func) {
  $.ajax({
    type: "post",
    url: url + "/file/uploadMultiple",
    dataType: "json",
    processData: false,
    contentType: false,
    data: data,
    success: function (res) {
      // console.log(res);
      func(res);
    },
  });
}

//img报错时 替换新的图片
//用法： <img onerror='isError(this)' src="../images/aaa.jpg">
function isError(that) {
  $(that).attr("src", "../../images/errorImg.jpg");
}

////////////////////////////////////////
//登录获取 即时通讯token
function loginIMToken(userId) {
  $.ajax({
    type: "post",
    url: url + "/console/user/getToken",
    data: {
      userId: userId,
    },
    success: function (res) {
      localStorage.setItem("userToken", res.data.token);
      // userToken = res.data.token
    },
  });
}


//点击轮播图跳转
function swiperimg(data){
  //console.log(data)
  switch(data){
    case 'banner': window.location.href="./page/homecenter/cooperation.html";
    break;
    case 'cjj': window.location.href="./page/homecenter/partner.html";
    break;
    case 'look': window.location.href="./page/homecenter/contactus.html";
    break;
    default: return;
  }
}


//点击底部图片跳转
function bottomimg(data){
  console.log(data)
  switch(data){
    case 'img2': window.location.href="./son.html?id=1&title=财税服务";
    break;
    case 'img4': window.location.href="./son.html?id=6&title=游戏";
    break;
    case 'img3': window.location.href="./son.html?id=3&title=精品特产";
    break;
    case 'img1': window.location.href="./son.html?id=2&title=法律政务";
    break;
    default: return;
  }
}


















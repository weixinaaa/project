console.log("已经进入ajax.js");
function getUrl() {
  //服务器运行时打开
  var url = "http://chejiajia.365hy.com/msfw/";
  //本地运行时打开
  // var url = "/api";
  //var url = "httpi、://127.0.0.1:8080/zjfxh";
  //var url = "http://47.96.171.205";
  //	var url = "http://www.zjfxh.com";
  return url;
}

function getRealtorUrl() {
  var url = "../../webrealtor/";
  return url;
}

function getImgUrl() {
  var url = "";
  return url;
}

// 获取URL参数
function GetQueryString(name) {
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
  var r = window.location.search.substr(1).match(reg);
  if (r != null) return decodeURI(r[2]);
  return null;
}

// AJAX封装
var ajax = {
  /**
   * 基本的Ajax请求
   *
   * 无需每次都设置请求地址,主要用户获取请求接口
   *
   * @interfaceName    {string}  接口名称
   * @requestData    {string}   请求的数据
   * @callback   回调函数
   *
   */
  fileAjax: function (interfaceName, callback) {
    $.ajax({
      type: "get",
      dataType: "json",
      url: getUrl() + interfaceName,
      success: function (data) {
        return callback(data);
      },
    });
  },
  /**
   * 基本的Ajax请求
   *
   * 无需每次都设置请求地址,主要用户获取请求接口
   *
   * @interfaceName    {string}  接口名称
   * @requestData    {string}   请求的数据
   * @callback   回调函数
   *
   */
  postAjax: function (interfaceName, requestData, callback) {
    $.ajax({
      type: "post",
      dataType: "jsonp",
      jsonp: "theFunction", //用以获得jsonp回调函数名的参数名(默认为:callback)
      jsonpCallback: "showData", //指定回调函数名称
      url: getUrl() + interfaceName,
      data: requestData,
      success: function (data) {
        return callback(data);
      },
      error: function (res) {
        return callback(res);
      },
    });
  },

  getAjax: function (interfaceName, requestData, callback) {
    $.ajax({
      type: "get",
      dataType: "jsonp",
      jsonp: "theFunction", //用以获得jsonp回调函数名的参数名(默认为:callback)
      jsonpCallback: "showData", //指定回调函数名称
      url: getUrl() + interfaceName,
      data: requestData,
      success: function (data) {
        return callback(data);
      },
      error: function (res) {
        return callback(res);
      },
    });
  },
  /**
   * 同步Ajax请求
   *
   *
   * @interfaceName    {string}  接口名称
   * @requestData    {string}   请求的数据
   * @callback   回调函数
   *
   */
  asyncAjax: function (interfaceName, requestData, callback) {
    $.ajax({
      type: "post",
      dataType: "json",
      url: getUrl() + interfaceName,
      data: requestData,
      async: false,
      success: function (data) {
        return callback(data);
      },
      error: function (res) {
        return callback(res);
      },
    });
  },
  /**
   * 带用户验证的Ajax请求
   *
   * 无需每次都设置请求地址,主要用户获取请求接口
   *
   * @interfaceName    {string}  接口名称
   * @requestData    {string}   请求的数据
   * @callback   回调函数
   *
   */
  verifyUserAjax: function (interfaceName, requestData, callback) {
    $.ajax({
      type: "post",
      dataType: "json",
      url: getUrl() + interfaceName,
      data: requestData,
      success: function (data) {
        if (data.code != 0 && data.code != 1) {
          alert(data.msg);
        } else if (data.code == -99) {
          //登录状态已过期(用户已在别处登录)
          alert(data.msg);
          window.location.href = "login.html";
        } else {
          return callback(data);
        }
      },
    });
  },
};

function setToken(token) {
  if (token === 0) {
    sessionStorage.removeItem("token");
  } else if (token) {
    sessionStorage.removeItem("token");
    sessionStorage.setItem("token", token);
  } else {
    console.log("noTokenToSet");
  }
}

function getToken() {
  if (sessionStorage.getItem("token")) {
    return sessionStorage.getItem("token");
  } else {
    return undefined;
  }
}

function removeToken() {
  sessionStorage.removeItem("token");
}
// 获取接口地址
var wxUrl = "https://open.weixin.qq.com/";

function getRealtorUrl() {
  var url = "../../webrealtor/";
  return url;
}
function getImgUrl() {
  var url = "";
  return url;
}
// 获取URL参数
function GetQueryString(name) {
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
  var r = window.location.search.substr(1).match(reg);
  if (r != null) return decodeURI(r[2]);
  return null;
}

// AJAX封装
var ajax = {
  /**
   * 基本的Ajax请求
   *
   * 无需每次都设置请求地址,主要用户获取请求接口
   *
   * @interfaceName    {string}  接口名称
   * @requestData    {string}   请求的数据
   * @callback   回调函数
   *
   */
  fileAjax: function (interfaceName, callback) {
    $.ajax({
      type: "get",
      dataType: "json",
      url: getUrl() + interfaceName,
      success: function (data) {
        return callback(data);
      },
    });
  },

  /**
   * 基本的Ajax请求
   *
   * 无需每次都设置请求地址,主要用户获取请求接口
   *
   * @interfaceName    {string}  接口名称
   * @requestData    {string}   请求的数据
   * @callback   回调函数
   *
   */
  postAjax: function (interfaceName, requestData, callback) {
    $.ajax({
      type: "post",
      dataType: "json",
      url: getUrl() + interfaceName,
      data: requestData,
      success: function (data) {
        return callback(data);
      },
      error: function (res) {
        return callback(res);
      },
    });
  },

  wxAjax: function (interfaceName, requestData, callback) {
    $.ajax({
      type: "get",
      dataType: "jsonp",
      url: wxUrl + interfaceName,
      data: requestData,
      jsonp: "theFunction", //用以获得jsonp回调函数名的参数名(默认为:callback)
      jsonpCallback: "showData", //指定回调函数名称
      success: function (data) {
        return callback(data);
      },
      error: function (res) {
        return callback(res);
      },
    });
  },

  getAjax: function (interfaceName, requestData, callback) {
    $.ajax({
      type: "get",
      dataType: "json",
      url: getUrl() + interfaceName,
      data: requestData,
      success: function (data) {
        return callback(data);
      },
      error: function (res) {
        return callback(res);
      },
    });
  },
  /**
   * 同步Ajax请求
   *
   *
   * @interfaceName    {string}  接口名称
   * @requestData    {string}   请求的数据
   * @callback   回调函数
   *
   */
  asyncAjax: function (interfaceName, requestData, callback) {
    $.ajax({
      type: "post",
      dataType: "json",
      url: getUrl() + interfaceName,
      data: requestData,
      async: false,
      success: function (data) {
        return callback(data);
      },
      error: function (res) {
        return callback(res);
      },
    });
  },
  /**
   * 带用户验证的Ajax请求
   *
   * 无需每次都设置请求地址,主要用户获取请求接口
   *
   * @interfaceName    {string}  接口名称
   * @requestData    {string}   请求的数据
   * @callback   回调函数
   *
   */
  verifyUserAjax: function (interfaceName, requestData, callback) {
    $.ajax({
      type: "post",
      dataType: "json",
      url: getUrl() + interfaceName,
      data: requestData,
      success: function (data) {
        if (data.code != 0 && data.code != 1) {
          alert(data.msg);
        } else if (data.code == -99) {
          //登录状态已过期(用户已在别处登录)
          alert(data.msg);
          window.location.href = "login.html";
        } else {
          return callback(data);
        }
      },
    });
  },
};

function setToken(token) {
  if (token === 0) {
    sessionStorage.removeItem("token");
  } else if (token) {
    sessionStorage.removeItem("token");
    sessionStorage.setItem("token", token);
  } else {
    console.log("noTokenToSet");
  }
}

function getToken() {
  if (sessionStorage.getItem("token")) {
    return sessionStorage.getItem("token");
  } else {
    return undefined;
  }
}
function removeToken() {
  sessionStorage.removeItem("token");
}

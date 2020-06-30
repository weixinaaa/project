var url = "http://47.96.28.95:80/msfw/";


//var url="http://chejiajia.365hy.com/msfw/"
// 个人资料
function ziliao() {
  $.ajax({
    url: url + "/user/PersonalInformation",
    data: {
      userId: 1,
    },
    success: (res) => {
      console.log(res);
      let xb = res.data.gender;
      let html = "";
      html +=
        '<img src="' +
        res.data.img +
        '" style="width: 30%;border-radius: 100%">' +
        '<div class="list">' +
        "<span>姓名</span>" +
        '<input type="text" name="" class="input" placeholder="' +
        res.data.name +
        '" id="name">' +
        "</div>" +
        '<div class="list">' +
        "<span>联系电话</span>" +
        '<input type="text" name=""  class="input" placeholder="' +
        res.data.tel +
        '" id="tel">' +
        "</div>" +
        '<div class="list">' +
        "<span>性别</span>" +
        '<div class="sex">' +
        '<input name="sex" type="radio" value="" id="boy">男' +
        '<input name="sex" type="radio" value="" id="girl">女' +
        "</div>" +
        "</div>" +
        '<div class="list">' +
        "<span>邮箱</span>" +
        '<input type="text" name="" placeholder="' +
        res.data.mailbox +
        '" class="input" id="email">' +
        "</div>" +
        '<div class="list">' +
        "<span>我的邀请码</span>" +
        '<input type="number" name="" value="' +
        res.data.myInvitationCode +
        '" class="input" readonly>' +
        "</div>" +
        '<div class="list">' +
        "<span>邀请人邀请码</span>" +
        '<input type="number" maxlength="6" min="000001" pattern="[0-9]" name="" value="' +
        res.data.invitationcode +
        '" class="input" id="yaoqing">' +
        "</div>";
      document.getElementById("ziliao").innerHTML = html;
      if (res.data.invitationcode != "") {
        $("#yaoqing").attr("readonly", "readonly");
      }
      if (xb == 1) {
        $("#boy").prop("checked", true);
      }
      if (xb == 2) {
        $("#girl").prop("checked", true);
      }
    },
  });
}

function ziliao_onload() {
  let name = $("#name").val();
  let mail = $("#email").val();
  let phone = $("#tel").val();
  let yaoqing = $("#yaoqing").val();
  let sex;
  var boy = $("#boy").prop("checked");
  var girl = $("#girl").prop("checked");
  console.log(boy);
  if (boy == true) {
    sex = 1;
  } else {
    sex = 2;
  }
  if (name == "") {
    name = $("#name").attr("placeholder");
  }
  if (mail == "") {
    mail = $("#email").attr("placeholder");
  }
  if (phone == "") {
    phone = $("#tel").attr("placeholder");
  }

  console.log(name, mail, phone, sex);
  $.ajax({
    url: "http://47.96.28.95:80/msfw//user/updateUser",
    data: {
      Id: 1,
      name: name,
      img: "./image/jr.jpg",
      mailbox: mail,
      gender: sex,
      tel: phone,
      code: yaoqing,
    },
    success: (res) => {
      console.log(res);
      if (res.code == "-1") {
        layer.msg(res.msg);
        if (res.msg != "成功") {
          setTimeout(function () {
            location.reload();
          }, 1500);
        }
        return;
      } else {
        layer.msg(res.msg);
      }
    },
  });
}

// 我的提现
function tixina() {
  $.ajax({
    url: url + "/user/getCashWithdrawalBill",
    data: {
      userId: 1,
      pagesize: 10,
      pageNo: 1,
    },
    success: (res) => {
      console.log(res);
      let html = "";
      let sh = "";
      let imgurl = "";
      for (i = 0; i < res.data.length; i++) {
        if (res.data[i].state == 0) {
          sh = "审核中";
          imgurl = "./image/icon/time.jpg";
        }
        if (res.data[i].state == 1) {
          sh = "审核完成";
          imgurl = "./image/icon/ok.jpg";
        }
        if (res.data[i].state == 2) {
          sh = "已到账";
          imgurl = "./image/icon/shouqian.jpg";
        }

        html +=
          '<div class="list-left">\n' +
          '<img src="./image/icon/money.jpg" class="icon">\n' +
          '<div class="center">\n' +
          "\t\t\t<p>余额提现到微信红包</p>\n" +
          '\t\t\t<p class="time">2019-12-15 21:23</p>\n' +
          "\t\t</div>\n" +
          '\t\t<p class="money">' +
          res.data[i].amountOfMoney +
          "</p>\n" +
          '\t\t<div class="right">\n' +
          '\t\t\t<img src="' +
          imgurl +
          '" >\n' +
          "\t\t\t<p >" +
          sh +
          "</p>\n" +
          "\t\t</div>\n" +
          "\t</div>";
      }
      document.getElementById("zt").innerHTML = html;
    },
  });
}
//首页
// function home() {
// 	$.ajax({
// 		url:url+'/HomePage/getAll',
// 		data:{pageSize:10,pageNo:1},
// 		success:res=>{
// 			console.log(res)
// 			let home_banner=''
// 			let time=''
//             let people=res.data.navigtionBar[0]
//             let driver=res.data.navigtionBar[1]
// 			let people_server_in=''
// 			let dirver_sever=''
//             let people_server=''
//
//
//             console.log(people.navigtionBarList)
//             people_server+='<p class="title">'+ people.title + '</p>'
//
//             for (i=0;i<res.data.rotationChart.length;i++){
//                 home_banner+='<img src="'+ res.data.rotationChart[i].img+'" class="swiper-slide banner">'
//             }
//         }
// 	})
// }

// 广告列表
function list_guanggao() {
  let html = "";
  $.ajax({
    url: url + "/HomePage/getAll",
    data: {
      pageSize: 10,
      pageNo: 1,
    },
    success: (res) => {
      let gg = res.data.advertisment;
      console.log(res.data.advertisment);
      //  for (i = 0; i < gg.length; i++) {
      //      html += '<a href="list_detail.html?id='+ gg[i].id+'">\n' +
      //          '<div class="list">\n' +
      //          '<img src="' + gg[i].img + '">\n' +
      //          '<div class="div">\n' +
      //          '<div class="first">\n' +
      //          '<span>' + gg[i].title + '</span>\n' +
      //          '<b class="number">点击量：' + gg[i].clicks + '</b>\n' +
      //          '</div>\n' +
      //          '<p class="text">' + gg[i].briefIntroduction + '</p>\n' +
      //          '<p class="time">' + gg[i].createTime + '</p>\n' +
      //          '</div>\n' +
      //          '</div>\n' +
      //          '</a>'
      //  }
      for (i = 0; i < gg.length; i++) {
        html +=
          '<img src="./image/icon/look.png" alt="" style="width: 100%;background: white;padding: 10px 0;">';
      }
    },
  });
}
// 广告详情
function guanggao() {
  var urls = window.location.search; //ndow.location对象得到URL的query部分
  console.log(urls);
  var id = urls.split("=")[1];
  console.log(id);
  $.ajax({
    url: url + "/AdverTisement/getAdvertisementById",
    data: {
      id: id,
    },
    success: (res) => {
      console.log(res);
      var time = res.data.createTime.split(" ")[0];
      let html = "";
      html +=
        '<p class="title">hhhh</p>\n' +
        '\t<div class="top">\n' +
        '\t\t<span class="time">' +
        time +
        "</span>\n" +
        '\t\t<b class="number">点击量：' +
        res.data.clicks +
        "</b>\n" +
        "\t</div>" +
        '<div style="text-indent:2em;">\n' +
        res.data.content +
        "\t</div>";
      document.getElementById("neirong").innerHTML = html;
    },
  });
}

// 我的团队
function team() {
  let html = "";
  $.ajax({
    url: url + "/user/queryTeamByUserId",
    data: {
      userId: 1,
      pageSize: 10,
      pageNo: 1,
    },
    success: (res) => {
      console.log(res);
    },
  });
}

// 收益明细
function qian_list() {
  let html = "";
  $.ajax({
    url: url + "/user/getUserProfit",
    data: {
      userId: 1,
      pageSize: 10,
      pageNo: 1,
    },
    success: (res) => {
      console.log(res);
      console.log(res.data);
      for (i = 0; i < res.data.length; i++) {
        var date = new Date(res.data[i].createTime); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
        var Y = date.getFullYear() + "-";
        var M =
          (date.getMonth() + 1 < 10
            ? "0" + (date.getMonth() + 1)
            : date.getMonth() + 1) + "-";
        var D = date.getDate() + " ";
        var h = date.getHours() + ":";
        var m = date.getMinutes();
        // var s = date.getSeconds();
        console.log();
        let time = Y + M + D + h + m;
        console.log(time);

        html +=
          '<div class="list">\n' +
          '\t\t<img src="./image/icon/money.jpg" style="width: 20%;">\n' +
          "\t\t<div>\n" +
          "\t\t\t<p>" +
          res.data[i].title +
          "</p>\n" +
          "\t\t\t<p>" +
          time +
          "</p>\n" +
          "\t\t</div>\n" +
          '\t\t<span class="money">+' +
          res.data[i].profit +
          "元</span>\n" +
          "\t</div>";
      }
      document.getElementById("rmb").innerHTML = html;
    },
  });
}
// 收益
function qian() {
  $.ajax({
    url: url + "/user/getCommissionBalanceById",
    data: {
      id: 1,
    },
    success: (res) => {
      console.log(res);
      var getprice = res.data;
      document.getElementById("getmoney").innerHTML = "￥" + hasDot(getprice);
    },
  });
}
function hasDot(num) {
  if (!isNaN(num)) {
    // return ( (num + '').indexOf('.') != -1 ) ? num : num.toFixed(2);
    if ((num + "").indexOf(".") != -1) {
      var pricelist = String(num).split(".");
      var decimal = pricelist[1];
      if (decimal.length == 1) {
        return num.toFixed(2);
      } else if (decimal.length >= 2) {
        return num;
      }
    } else {
      alert("123");
      return num.toFixed(2);
    }
  }
}
function allcar() {
  let html1 = "";
  let html2 = "";
  let html3 = "";
  $.ajax({
    url: url + "/bookACar/getVehicleTypeAll",
    success: (res) => {
      console.log(res);
      html1 +=
        '<img src="' +
        res.data[0].img +
        '">\n' +
        '\t\t<div class="right">\n' +
        "\t\t\t<div>\n" +
        "\t\t\t\t<p>" +
        res.data[0].charterPrice +
        "</p>\n" +
        "\t\t\t\t<p>费用(元)/天</p>\n" +
        "\t\t\t</div>\n" +
        "\t\t</div>";
      html2 +=
        '<img src="' +
        res.data[1].img +
        '">\n' +
        '\t\t<div class="right">\n' +
        "\t\t\t<div>\n" +
        "\t\t\t\t<p>" +
        res.data[1].charterPrice +
        "</p>\n" +
        "\t\t\t\t<p>费用(元)/天</p>\n" +
        "\t\t\t</div>\n" +
        "\t\t</div>";
      html3 +=
        '<img src="' +
        res.data[2].img +
        '">\n' +
        '\t\t<div class="right">\n' +
        "\t\t\t<div>\n" +
        "\t\t\t\t<p>" +
        res.data[2].charterPrice +
        "</p>\n" +
        "\t\t\t\t<p>费用(元)/天</p>\n" +
        "\t\t\t</div>\n" +
        "\t\t</div>";
      document.getElementById("car1").innerHTML = html1;
      document.getElementById("car2").innerHTML = html2;
      document.getElementById("car3").innerHTML = html3;
    },
  });
}

//判断手机号格式是否正确
function checkPhone(phone) {
  if (!/^[1][3,4,5,7,8][0-9]{9}$/.test(phone)) {
    // alert("手机号码有误，请重填或注册");
    return false;
  } else {
    return true;
  }
}




//判断是否登录

function isuserlogin() {
    var myphone = localStorage.getItem("myphone");
    var userid = localStorage.getItem("userid");
    let getlogin="";
    if (myphone != undefined && userid != null) {
      $.ajax({
        url: url + "/user/register",
        data: {
          tel: myphone,
          verificationCode: "",
          password: "",
        },
        success: (res) => {
          console.log(res);
          if (res.msg == "该手机号已被注册") {            
            sessionStorage.setItem("delogin","true");
          } else {
              var isbeauty = confirm("确认前往登录/注册页面？");
              if (isbeauty) {
                window.location.href = "../person.html";
              } 
             
              sessionStorage.setItem("delogin","false");
          }
        },
      });
    } else {
      var isbeauty = confirm("确认前往登录/注册页面？");
              if (isbeauty) {
                window.location.href = "../person.html";
              } 
              sessionStorage.setItem("delogin","false");
    }
    getlogin = sessionStorage.getItem("delogin");
    return getlogin;
  }
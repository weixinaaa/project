//点击轮播图跳转
function swiperimg(data){
    //console.log(data)
    switch(data){
      case 'banner': window.location.href="./contactus.html";
      break;
      case 'cjj': window.location.href="./cooperation.html";
      break;
      case 'look': window.location.href="./partner.html";
      break;
      default: return;
    }
  }
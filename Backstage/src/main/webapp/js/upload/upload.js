﻿function FileUpload(obj, imgid, type, hfname) {

    $.ajaxFileUpload
    (
    {
        //url: '/Admin/common/UploadFile?filename=' + obj + '&type=' + type, //用于文件上传的服务器端请求地址
        url: 'http://120.27.196.116:8080/ImageJson.ashx?filename=' + obj + '&type=' + type + "&sourcetype=Server", //用于文件上传的服务器端请求地址
        secureuri: false, //是否需要安全协议，一般设置为false
        fileElementId: obj, //文件上传域的ID
        dataType: 'json', //返回值类型 一般设置为json
        data: "{filename:" + imgid + "}",
        success: function (data)  //服务器成功响应处理函数
        {
            data = JSON.parse(data);
            if (data.status == 0) {
                //alert(data);
                $("#" + imgid).attr("src", data.result);
                $("#" + hfname).val(data.result);
            }
            else
                alert(data.result);
        },
        error: function (data, status, e)//服务器响应失败处理函数
        {
            alert(e);
        }
    }
    )
    return false;
}


function getFileName(path) {
    var pos1 = path.lastIndexOf('/');
    var pos2 = path.lastIndexOf('\\');
    var pos = Math.max(pos1, pos2)
    if (pos < 0)
        return path;
    else
        return path.substring(pos + 1);
}

//判断是否有图片路径，无则清空相关控件值，有则设置相关控件
function imgcheck(checkdata, hfname, imgname) {
    if (checkdata.length > 0) {
        if (hfname)
            document.getElementById(hfname).value = checkdata;
        document.getElementById(imgname).src = config.imageUrl+ checkdata;
    }
    else {
        if (hfname)
            document.getElementById(hfname).value = '';
        document.getElementById(imgname).src = "/upload/addfile.png";
    }
}

function checkid(id, hfname) {
    if (id > 0 && id.length > 0) {
        document.getElementById(hfname).value = id;

    }
    else {
        document.getElementById(hfname).value = '0';

    }
}

/*多图上传*/
//var imgcount = 1;
//var imgsumcount = 3;
//var imgsarr = "";

function FileUpload2(obj, imgid, type, hfname, commend) {
    $.ajaxFileUpload
    (
        {
            url: '/Admin/common/UploadFile?filename=' + obj + '&type=' + type, //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: obj, //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            data: "{filename:" + imgid + "}",
            success: function (data)  //服务器成功响应处理函数
            {
                if (data.success) {
                    $("#" + imgid).attr("src", "/" + data.url);
                    $("#" + hfname).val(data.url);

                    if (commend == "add") {
                        addImg(hfname);
                    }
                    else if (commend == "badd") {
                        addBusinessImg(hfname);
                    }
                }
                else
                    alert(data.errorMsg);
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                alert(e);
            }
        }
    )
    return false;
}

//function addImg(oldhdname) {
//    var upindex = oldhdname.replace("hdimage", "");
//    $("#delimg" + (imgcount)).removeClass("hidden");
//    //不存在
//    if (upindex < imgcount) {
//        $("#delimg" + (imgcount)).addClass("hidden");
//    }
//    else {
//        if (imgcount < imgsumcount) {
//            imgcount++;
//            var html = "";
//            var imgname = "imgimage" + imgcount;
//            var filename = "fileimage" + imgcount;
//            var hdname = "hdimage" + imgcount;
//            var divname = "dimage_" + imgcount;
//            var delname = "delimg" + imgcount;

//            html += "<div class=\"dimage img100_70\" id=\"" + divname + "\">";
//            html += "<input type=\"hidden\" id=\"" + hdname + "\" />";
//            html += "<img id=\"" + imgname + "\" src=\"/upload/addfile.png\" class=\"img100_70\" title=\"点击上传\" onclick=\"javascript: return $('#" + filename + "').click();\" />";
//            html += "<input class=\"hidden\" type=\"file\" id=\"" + filename + "\" name=\"" + filename + "\" onchange=\"javascript: FileUpload2('" + filename + "', '" + imgname + "', 'infoimg', '" + hdname + "', 'add');\" accept=\"image/*\" />";
//            html += "<img id=\"" + delname + "\" class=\"image_del hidden\" src=\"/Content/img/close.png\" onclick=\"delImg('" + divname + "')\" />";
//            html += "</div>";
//            $("#divimages").append(html);

//        }
//    }
//}

//function delImg(num) {
//    var nowcount = num.split('_')[1];
//    $("#" + num).remove();
//    imgsumcount++;
//    addImg("hdimage" + nowcount);
//}

function Getimgs(hdname, imgcount) {
    var imgsarr = "";
    for (var i = 1; i <= imgcount; i++) {
        var qqq = $("#" + hdname + i).val();
        if (qqq != undefined && qqq != null && qqq != "")
            imgsarr += qqq + ",";
    }
    return imgsarr;
}

//function LoadImg(url) {
//    imgcount = 0;
//    document.getElementById("divimages").innerHTML = "";

//    var imgs = url.split(',');
//    for (var i = 0; i < imgs.length; i++) {
//        var img = imgs[i];
//        if(img)
//        {
//            addImg("hdimage" + imgcount);
//        }
//    }
//}

//Regional开始
function setBinders() {

	$(".Regional").click(function() {
		if($('.grade-eject').hasClass('grade-w-roll')) {
			$('.grade-eject').removeClass('grade-w-roll');
			$('.grade-eject').append();
			
		} else {
			$('.grade-eject').addClass('grade-w-roll');
			$('.coverBox').css('top', $('.grade-eject').height() + $('.screening').height());
			$('.coverBox').css('height', $(window).height() - ($('.grade-eject').height() + $('.screening').height()));
			console.log($('.grade-eject').height() + $('.screening').height());
		}
	});
	$('.coverBox').click(function() {
		hideList();
	})

	//Brand开始
	$(".Brand").click(function() {
		if($('.Category-eject').hasClass('grade-w-roll')) {
			$('.Category-eject').removeClass('grade-w-roll');
		} else {
			$('.Category-eject').addClass('grade-w-roll');
			$('.coverBox').css('top', $('.Category-eject').height() + $('.screening').height());
			$('.coverBox').css('height', $(window).height() - ($('.grade-eject').height() + $('.screening').height()));
		}
	});

	$(".Category-w>li").click(function() {
		$(".Category-t")
			.css("left", "50%");
	});

	$(".Category-t>li").click(function() {

		$(".Category-s")
			.css("left", "66.96%");
	});

	//Sort开始
	$(".Sort").click(function() {
		if($('.Sort-eject').hasClass('grade-w-roll')) {
			$('.Sort-eject').removeClass('grade-w-roll');
		} else {
			$('.Sort-eject').addClass('grade-w-roll');
			$('.coverBox').css('top', $('.Sort-eject').height() + $('.screening').height());
			$('.coverBox').css('height', $(window).height() - ($('.Sort-eject').height() + $('.screening').height()));
		}
	});
	$(".Cate").click(function() {
		if($('.Cate-eject').hasClass('grade-w-roll')) {
			$('.Cate-eject').removeClass('grade-w-roll');
		} else {
			$('.Cate-eject').addClass('grade-w-roll');
			$('.coverBox').css('top', $('.Cate-eject').height() + $('.screening').height());
			$('.coverBox').css('height', $(window).height() - ($('.Cate-eject').height() + $('.screening').height()));
		}
	});

	//判断页面是否有弹出
	$(".Regional").click(function() {
		if($('.Category-eject').hasClass('grade-w-roll')) {
			$('.Category-eject').removeClass('grade-w-roll');
		};
		if($('.Sort-eject').hasClass('grade-w-roll')) {
			$('.Sort-eject').removeClass('grade-w-roll');
		};
		if($('.Cate-eject').hasClass('grade-w-roll')) {
			$('.Cate-eject').removeClass('grade-w-roll');
		};
	});

	$(".Brand").click(function() {
		if($('.Sort-eject').hasClass('grade-w-roll')) {
			$('.Sort-eject').removeClass('grade-w-roll');
		};
		if($('.grade-eject').hasClass('grade-w-roll')) {
			$('.grade-eject').removeClass('grade-w-roll');
		};
		if($('.Cate-eject').hasClass('grade-w-roll')) {
			$('.Cate-eject').removeClass('grade-w-roll');
		};
	});

	$(".Sort").click(function() {
		if($('.Category-eject').hasClass('grade-w-roll')) {
			$('.Category-eject').removeClass('grade-w-roll');
		};
		if($('.grade-eject').hasClass('grade-w-roll')) {
			$('.grade-eject').removeClass('grade-w-roll');
		};
		if($('.Cate-eject').hasClass('grade-w-roll')) {
			$('.Cate-eject').removeClass('grade-w-roll');
		};
	});
	$(".Cate").click(function() {
		if($('.Category-eject').hasClass('grade-w-roll')) {
			$('.Category-eject').removeClass('grade-w-roll');
		};
		if($('.grade-eject').hasClass('grade-w-roll')) {
			$('.grade-eject').removeClass('grade-w-roll');
		};
		if($('.Sort-eject').hasClass('grade-w-roll')) {
			$('.Sort-eject').removeClass('grade-w-roll');
		};
	});
}

function hideList() {
	$('.Sort-eject').removeClass('grade-w-roll');
	$('.Cate-eject').removeClass('grade-w-roll');
	$('.grade-eject').removeClass('grade-w-roll');
	$('.Category-eject').removeClass('grade-w-roll');
	$('.coverBox').css('height', "0px");
	$(".screening ul li").removeClass("on");
}
//js点击事件监听开始
function grade1(wbj) {
	if($(wbj).index() == 0) {
		$(".grade-t").css("left", "100%")
	} else {
		$(".grade-t").css("left", "33.48%")
	}
	$(wbj).addClass("gradeSelected").siblings().removeClass("gradeSelected");
	searchList(1, $("#pageMsg").attr("page-num"));
	var arr = document.getElementById("gradew").getElementsByTagName("li");
	for(var i = 0; i < arr.length; i++) {
		var a = arr[i];
		a.style.background = "#ffffff";
		a.style.color = "#333";
	};
	wbj.style.background = "#efefef"
	wbj.style.color = "#3687c7"

	//	console.log($(wbj).index());
	//	$(".s_province").selectIndex=$(wbj).index();
	//	console.log($(".s_province").selectIndex);

	var str = "0";

	str += ("_" + parseInt($(wbj).index() - 1));
	if($(wbj).index() != 1 && $(wbj).index() != 2 && $(wbj).index() != 3 && $(wbj).index() != 4) {
		var html2 = '<li onclick="gradet(this)">全省</li>';
	} else {
		var html2 = '<li onclick="gradet(this)">全市</li>';
	}

	console.log(str);
	$("#gradew").attr("value", str);
	if(dsy.Exists(str)) {
		ar = dsy.Items[str];
		for(i = 0; i < ar.length; i++) {
			html2 += '<li onclick="gradet(this)">' + ar[i] + '</li>';
		} //end for
		$("#gradet").html(html2);
	}

}

function gradet(tbj) {
	console.log($(tbj).index());
	if($(tbj).index() == 0) {
		$(".grade-s")
			.css("left", "100%")
	} else {
		$(".grade-s")
			.css("left", "66.96%")
	}
	$(tbj).addClass("gradeSelected").siblings().removeClass("gradeSelected");
	searchList(1, $("#pageMsg").attr("page-num"));
	var arr = document.getElementById("gradet").getElementsByTagName("li");
	for(var i = 0; i < arr.length; i++) {
		var a = arr[i];
		a.style.background = "#f6f6f6";
		a.style.color = "#333";
	};
	tbj.style.background = "#eeeeee"
	tbj.style.color = "#3687c7"

	var html2 = '<li onclick="grades(this)">全市</li>';
	var str = "0";
	if($("#gradew").attr("value") == "0_0" || $("#gradew").attr("value") == "0_1" || $("#gradew").attr("value") == "0_2" || $("#gradew").attr("value") == "0_3") {
		$(".grade-s")
			.css("left", "100%")
		hideList();
		return;
	}
	str = ($("#gradew").attr("value") + "_" + parseInt($(tbj).index() - 1));

	if(dsy.Exists(str)) {
		ar = dsy.Items[str];
		for(i = 0; i < ar.length; i++) {
			html2 += '<li onclick="grades(this)">' + ar[i] + '</li>';
		} //end for
		$("#grades").html(html2);
	}

}

function grades(sbj) {

	$(sbj).addClass("gradeSelected").siblings().removeClass("gradeSelected");
	searchList(1, $("#pageMsg").attr("page-num"));
	var arr = document.getElementById("grades").getElementsByTagName("li");
	for(var i = 0; i < arr.length; i++) {
		var a = arr[i];
		a.style.background = "#efefef";
		a.style.color = "#333";
	};
	//  sbj.style.borderBottom = "solid 1px #ff7c08"
	sbj.style.background = "#f6f6f6";
	sbj.style.color = "#3687c7";
	hideList();
}

function Categorytw(wbj) {

	$(wbj).addClass("gradeSelected").siblings().removeClass("gradeSelected");
	searchList(1, $("#pageMsg").attr("page-num"));
	var arr = document.getElementById("Categorytw").getElementsByTagName("li");
	for(var i = 0; i < arr.length; i++) {
		var a = arr[i];
		a.style.color = "#333";
		a.style.background = "#FFFFFF";
	};
	wbj.style.background = "#efefef";
	wbj.style.color = "#3687c7"
	console.log($(wbj).index());
	setCategoryt($(wbj).index());

}

function setCategoryt(id) {
	$.ajax({
		dataType: "json",
		type: 'post',
		url: "../pledge/style",
		data: {
			type: id,
			departmentId: 1
			//id的获取方式还不详
		},
		success: function(data) {
			if(data.code == 0) {
				var html = '';
				var result = data.rows;
				html += '<li onclick="Categoryt(this,0)">全部</li>';
				for(var i = 0; i < result.length; i++) {
					html += '<li onclick="Categoryt(this)" value="' + result[i].id + '">' + result[i].name + '</li>';
				}
				$("#Categoryt").html(html);
			} else {
				alert(data.msg);
			}
		}
	});
}

function Categoryt(tbj) {

	$(tbj).addClass("gradeSelected").siblings().removeClass("gradeSelected");
	searchList(1, $("#pageMsg").attr("page-num"));
	var arr = document.getElementById("Categoryt").getElementsByTagName("li");
	for(var i = 0; i < arr.length; i++) {
		var a = arr[i];
		a.style.color = "#333";
		a.style.background = "#efefef";
	};
	tbj.style.background = "#f6f6f6";
	tbj.style.color = "#3687c7";
	hideList();
}

function Categorys(sbj) {
	var arr = document.getElementById("Categorys").getElementsByTagName("li");
	for(var i = 0; i < arr.length; i++) {
		var a = arr[i];
		a.style.borderBottom = "";
	};
	//  sbj.style.borderBottom = "solid 1px #ff7c08"
	searchList(1, $("#pageMsg").attr("page-num"));
}

function Sorts(sbj) {
	var arr = document.getElementById("Sort-Sort").getElementsByTagName("li");
	for(var i = 0; i < arr.length; i++) {
		var a = arr[i];
		a.style.borderBottom = "";
	};
	//  sbj.style.borderBottom = "solid 1px #ff7c08"
	searchList(1, $("#pageMsg").attr("page-num"));
	//	$("#pageMsg").attr("page-no")
}
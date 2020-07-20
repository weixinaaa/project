var nameEl = document.getElementById("picker");

var first = []; /* 省，直辖市 */
var second = []; /* 市 */
var third = []; /* 镇 */

var selectedIndex = [0, 0, 0]; /* 默认选中的地区 */

var checked = [0, 0, 0]; /* 已选选项 */

function creatList(obj, list) {
  obj.forEach(function (item, index, arr) {
    var temp = new Object();
    temp.text = item.name;
    temp.value = index;
    list.push(temp);
  });
}

creatList(city, first);

if (city[selectedIndex[0]].hasOwnProperty("sub")) {
  creatList(city[selectedIndex[0]].sub, second);
} else {
  second = [{ text: "", value: 0 }];
}

if (city[selectedIndex[0]].sub[selectedIndex[1]].hasOwnProperty("sub")) {
  creatList(city[selectedIndex[0]].sub[selectedIndex[1]].sub, third);
} else {
  third = [{ text: "", value: 0 }];
}

var picker = new Picker({
  data: [first, second, third],
  selectedIndex: selectedIndex,
  title: "地址选择",
});

picker.on("picker.select", function (selectedVal, selectedIndex) {
  var text1 = first[selectedIndex[0]].text;
  var text2 = second[selectedIndex[1]].text;
  var text3 = third[selectedIndex[2]] ? third[selectedIndex[2]].text : "";
  nameEl.value = text1 + " " + text2 + " " + text3;
});

picker.on("picker.change", function (index, selectedIndex) {
  if (index === 0) {
    firstChange();
  } else if (index === 1) {
    secondChange();
  }

  function firstChange() {
    second = [];
    third = [];
    checked[0] = selectedIndex;
    var firstCity = city[selectedIndex];
    if (firstCity.hasOwnProperty("sub")) {
      creatList(firstCity.sub, second);

      var secondCity = city[selectedIndex].sub[0];
      if (secondCity.hasOwnProperty("sub")) {
        creatList(secondCity.sub, third);
      } else {
        third = [{ text: "", value: 0 }];
        checked[2] = 0;
      }
    } else {
      second = [{ text: "", value: 0 }];
      third = [{ text: "", value: 0 }];
      checked[1] = 0;
      checked[2] = 0;
    }

    picker.refillColumn(1, second);
    picker.refillColumn(2, third);
    picker.scrollColumn(1, 0);
    picker.scrollColumn(2, 0);
  }

  function secondChange() {
    third = [];
    checked[1] = selectedIndex;
    var first_index = checked[0];
    if (city[first_index].sub[selectedIndex].hasOwnProperty("sub")) {
      var secondCity = city[first_index].sub[selectedIndex];
      creatList(secondCity.sub, third);
      picker.refillColumn(2, third);
      picker.scrollColumn(2, 0);
    } else {
      third = [{ text: "", value: 0 }];
      checked[2] = 0;
      picker.refillColumn(2, third);
      picker.scrollColumn(2, 0);
    }
  }
});

picker.on("picker.valuechange", function (selectedVal, selectedIndex) {
  var arealist = $("#picker").val().split(" ");


  var pro = arealist[0];
  var city = arealist[1];
  var area = arealist[2];

  if (area == "") {
    area = city;
    city = pro;
  }
  var proid = "";
  var cityid = "";
  var areaid = "";

  $.ajax({
    type: "get",
    url: "http://47.96.28.95/msfw/pca/selPro",
    data: {},
    success: (res) => {
    

      for (let i = 0; i < res.rows.length; i++) {
        if (res.rows[i].proName.indexOf(pro) == 0) {
         
          proid = res.rows[i].id;
          $.ajax({
            type: "get",
            url: "http://47.96.28.95/msfw/pca/selCity",
            data: {
              pid: proid,
            },
            success: (res) => {
              
              for (let i = 0; i < res.rows.length; i++) {
                if (res.rows[i].cityName.indexOf(city) == 0) {
                
                  cityid = res.rows[i].id;
                  $.ajax({
                    type: "get",
                    url: "http://47.96.28.95/msfw/pca/selArea",
                    data: {
                      pid: cityid,
                    },
                    success: (res) => {
                     
                      for (let i = 0; i < res.rows.length; i++) {
                        if (res.rows[i].areaName.indexOf(area) == 0) {
                        
                          areaid = res.rows[i].id;   
                          
                          sessionStorage.setItem("choosearea", proid+" "+cityid+" "+areaid);  
                          sessionStorage.setItem("showarea", pro+" "+city+" "+area); 
                          console.log(sessionStorage.getItem("choosearea"));
                          console.log(sessionStorage.getItem("showarea"));
                          break;
                        }
                      }              
                    },
                  });
                  break;
                }
              }
            },
          });
          break;
        }
      }
    },
  });

  document.getElementById("picker").value = pro + " " + city + " " + area;
  
  



});

nameEl.addEventListener("click", function () {
  picker.show();
});

var addressInit = function(_carea, _cmbProvince, _cmbCity, _cmbArea, defaultarea1, defaultProvince, defaultCity, defaultArea) {
	var area = document.getElementById(_carea);
	var cmbProvince = document.getElementById(_cmbProvince);
	var cmbCity = document.getElementById(_cmbCity);
	var cmbArea = document.getElementById(_cmbArea);

	function cmbSelect(cmb, str) {
		for(var i = 0; i < cmb.options.length; i++) {
			if(cmb.options[i].value == str) {
				cmb.selectedIndex = i;
				return;
			}
		}
	}

	function cmbAddOption(cmb, str, obj) {
		var option = document.createElement("OPTION");
		option.innerHTML = str;
		option.value = str;
		option.obj = obj;
		cmb.options.add(option);
	}

	function changeCity() {
		cmbArea.options.length = 0;
		if(cmbCity.selectedIndex == -1) return;
		var item = cmbCity.options[cmbCity.selectedIndex].obj;
		for(var i = 0; i < item.areaList.length; i++) {
			cmbAddOption(cmbArea, item.areaList[i], null);
		}
		cmbSelect(cmbArea, defaultArea);
	}

	function changeProvince() {
		cmbCity.options.length = 0;
		cmbCity.onchange = null;
		if(cmbProvince.selectedIndex == -1) return;

		var item = cmbProvince.options[cmbProvince.selectedIndex].obj;
		for(var i = 0; i < item[cmbProvince.selectedIndex].cityList.length; i++) {
			cmbAddOption(cmbCity, item[cmbProvince.selectedIndex].cityList[i].name, item[cmbProvince.selectedIndex].cityList[i]);
		}
		cmbSelect(cmbCity, defaultCity);
		changeCity();
		cmbCity.onchange = changeCity;
	}

	function changeArea() {
		cmbProvince.options.length = 0;
		cmbProvince.onchange = null;
		if(area.selectedIndex == -1) return;
		var item = area.options[area.selectedIndex].obj;
		for(var i = 0; i < item.Allcity.length; i++) {
			cmbAddOption(cmbProvince, item.Allcity[i].name, item.Allcity);
		}
		cmbSelect(cmbProvince, defaultProvince);
		changeProvince();
		cmbProvince.onchange = changeProvince;
	}

	for(var i = 0; i < provinceList.length; i++) {
		//alert(provinceList[i].Allcity[0].name);  
		//var mess=provinceList[i].Allcity.join('--');  
		//alert(mess);  
		cmbAddOption(area, provinceList[i]._area, provinceList[i]);
	}

	cmbSelect(area, defaultarea1);
	changeArea();
	area.onchange = changeArea;
}

var provinceList = [{
		_area: "江新苑",
		Allcity: [

			{
				name: '1',
				cityList: [{
					name: '1',
					areaList: ['201', '202', '301', '302', '401', '402', '501', '502', '601', '602', '701', '702', '801', '802', '901', '902', '1001', '1002', '1101', '1102', '1201', '1202', '1301', '1302', '1401', '1402', '1501', '1502', '1602']
				}, ]
			},

			{
				name: '2',
				cityList: [{
					name: '1',
					areaList: ['201', '202', '301', '302', '401', '402', '501', '502', '601', '602', '701', '702', '801', '802', '901', '902', '1001', '1002', '1101', '1102', '1201', '1202', '1301', '1302', '1401', '1402', '1501', '1502', '1602']
				}, ]
			}, {
				name: '3',
				cityList: [{
					name: '合肥市',
					areaList: ['市辖区', '瑶海区', '庐阳区', '蜀山区', '包河区', '长丰县', '肥东县', '肥西县']
				}, {
					name: '芜湖市',
					areaList: ['市辖区', '镜湖区', '马塘区', '新芜区', '鸠江区', '芜湖县', '繁昌县', '南陵县']
				}, {
					name: '蚌埠市',
					areaList: ['市辖区', '龙子湖区', '蚌山区', '禹会区', '淮上区', '怀远县', '五河县', '固镇县']
				}, {
					name: '淮南市',
					areaList: ['市辖区', '大通区', '田家庵区', '谢家集区', '八公山区', '潘集区', '凤台县']
				}, {
					name: '马鞍山市',
					areaList: ['市辖区', '金家庄区', '花山区', '雨山区', '当涂县']
				}, {
					name: '淮北市',
					areaList: ['市辖区', '杜集区', '相山区', '烈山区', '濉溪县']
				}, {
					name: '铜陵市',
					areaList: ['市辖区', '铜官山区', '狮子山区', '郊　区', '铜陵县']
				}, {
					name: '安庆市',
					areaList: ['市辖区', '迎江区', '大观区', '郊　区', '怀宁县', '枞阳县', '潜山县', '太湖县', '宿松县', '望江县', '岳西县', '桐城市']
				}, {
					name: '黄山市',
					areaList: ['市辖区', '屯溪区', '黄山区', '徽州区', '歙　县', '休宁县', '黟　县', '祁门县']
				}, {
					name: '滁州市',
					areaList: ['市辖区', '琅琊区', '南谯区', '来安县', '全椒县', '定远县', '凤阳县', '天长市', '明光市']
				}, {
					name: '阜阳市',
					areaList: ['市辖区', '颍州区', '颍东区', '颍泉区', '临泉县', '太和县', '阜南县', '颍上县', '界首市']
				}, {
					name: '宿州市',
					areaList: ['市辖区', '墉桥区', '砀山县', '萧　县', '灵璧县', '泗　县']
				}, {
					name: '巢湖市',
					areaList: ['市辖区', '居巢区', '庐江县', '无为县', '含山县', '和　县']
				}, {
					name: '六安市',
					areaList: ['市辖区', '金安区', '裕安区', '寿　县', '霍邱县', '舒城县', '金寨县', '霍山县']
				}, {
					name: '亳州市',
					areaList: ['市辖区', '谯城区', '涡阳县', '蒙城县', '利辛县']
				}, {
					name: '池州市',
					areaList: ['市辖区', '贵池区', '东至县', '石台县', '青阳县']
				}, {
					name: '宣城市',
					areaList: ['市辖区', '宣州区', '郎溪县', '广德县', '泾　县', '绩溪县', '旌德县', '宁国市']
				}]
			},

			{
				name: '4',
				cityList: [{
					name: '杭州市',
					areaList: ['市辖区', '上城区', '下城区', '江干区', '拱墅区', '西湖区', '滨江区', '萧山区', '余杭区', '桐庐县', '淳安县', '建德市', '富阳市', '临安市']
				}, {
					name: '宁波市',
					areaList: ['市辖区', '海曙区', '江东区', '江北区', '北仑区', '镇海区', '鄞州区', '象山县', '宁海县', '余姚市', '慈溪市', '奉化市']
				}, {
					name: '温州市',
					areaList: ['市辖区', '鹿城区', '龙湾区', '瓯海区', '洞头县', '永嘉县', '平阳县', '苍南县', '文成县', '泰顺县', '瑞安市', '乐清市']
				}, {
					name: '嘉兴市',
					areaList: ['市辖区', '秀城区', '秀洲区', '嘉善县', '海盐县', '海宁市', '平湖市', '桐乡市']
				}, {
					name: '湖州市',
					areaList: ['市辖区', '吴兴区', '南浔区', '德清县', '长兴县', '安吉县']
				}, {
					name: '绍兴市',
					areaList: ['市辖区', '越城区', '绍兴县', '新昌县', '诸暨市', '上虞市', '嵊州市']
				}, {
					name: '金华市',
					areaList: ['市辖区', '婺城区', '金东区', '武义县', '浦江县', '磐安县', '兰溪市', '义乌市', '东阳市', '永康市']
				}, {
					name: '衢州市',
					areaList: ['市辖区', '柯城区', '衢江区', '常山县', '开化县', '龙游县', '江山市']
				}, {
					name: '舟山市',
					areaList: ['市辖区', '定海区', '普陀区', '岱山县', '嵊泗县']
				}, {
					name: '台州市',
					areaList: ['市辖区', '椒江区', '黄岩区', '路桥区', '玉环县', '三门县', '天台县', '仙居县', '温岭市', '临海市']
				}, {
					name: '丽水市',
					areaList: ['市辖区', '莲都区', '青田县', '缙云县', '遂昌县', '松阳县', '云和县', '庆元县', '景宁畲族自治县', '龙泉市']
				}]
			},

		]
	},

	{
		_area: "华南地区",
		Allcity: [

			{
				name: '广东',
				cityList: [{
					name: '广州市',
					areaList: ['市辖区', '东山区', '荔湾区', '越秀区', '海珠区', '天河区', '芳村区', '白云区', '黄埔区', '番禺区', '花都区', '增城市', '从化市']
				}, {
					name: '韶关市',
					areaList: ['市辖区', '武江区', '浈江区', '曲江区', '始兴县', '仁化县', '翁源县', '乳源瑶族自治县', '新丰县', '乐昌市', '南雄市']
				}, {
					name: '深圳市',
					areaList: ['市辖区', '罗湖区', '福田区', '南山区', '宝安区', '龙岗区', '盐田区']
				}, {
					name: '珠海市',
					areaList: ['市辖区', '香洲区', '斗门区', '金湾区']
				}, {
					name: '汕头市',
					areaList: ['市辖区', '龙湖区', '金平区', '濠江区', '潮阳区', '潮南区', '澄海区', '南澳县']
				}, {
					name: '佛山市',
					areaList: ['市辖区', '禅城区', '南海区', '顺德区', '三水区', '高明区']
				}, {
					name: '江门市',
					areaList: ['市辖区', '蓬江区', '江海区', '新会区', '台山市', '开平市', '鹤山市', '恩平市']
				}, {
					name: '湛江市',
					areaList: ['市辖区', '赤坎区', '霞山区', '坡头区', '麻章区', '遂溪县', '徐闻县', '廉江市', '雷州市', '吴川市']
				}, {
					name: '茂名市',
					areaList: ['市辖区', '茂南区', '茂港区', '电白县', '高州市', '化州市', '信宜市']
				}, {
					name: '肇庆市',
					areaList: ['市辖区', '端州区', '鼎湖区', '广宁县', '怀集县', '封开县', '德庆县', '高要市', '四会市']
				}, {
					name: '惠州市',
					areaList: ['市辖区', '惠城区', '惠阳区', '博罗县', '惠东县', '龙门县']
				}, {
					name: '梅州市',
					areaList: ['市辖区', '梅江区', '梅　县', '大埔县', '丰顺县', '五华县', '平远县', '蕉岭县', '兴宁市']
				}, {
					name: '汕尾市',
					areaList: ['市辖区', '城　区', '海丰县', '陆河县', '陆丰市']
				}, {
					name: '河源市',
					areaList: ['市辖区', '源城区', '紫金县', '龙川县', '连平县', '和平县', '东源县']
				}, {
					name: '阳江市',
					areaList: ['市辖区', '江城区', '阳西县', '阳东县', '阳春市']
				}, {
					name: '清远市',
					areaList: ['市辖区', '清城区', '佛冈县', '阳山县', '连山壮族瑶族自治县', '连南瑶族自治县', '清新县', '英德市', '连州市']
				}, {
					name: '东莞市',
					areaList: ['东莞市']
				}, {
					name: '中山市',
					areaList: ['中山市']
				}, {
					name: '潮州市',
					areaList: ['市辖区', '湘桥区', '潮安县', '饶平县']
				}, {
					name: '揭阳市',
					areaList: ['市辖区', '榕城区', '揭东县', '揭西县', '惠来县', '普宁市']
				}, {
					name: '云浮市',
					areaList: ['市辖区', '云城区', '新兴县', '郁南县', '云安县', '罗定市']
				}]
			},

			{
				name: '广西',
				cityList: [{
					name: '南宁市',
					areaList: ['市辖区', '兴宁区', '青秀区', '江南区', '西乡塘区', '良庆区', '邕宁区', '武鸣县', '隆安县', '马山县', '上林县', '宾阳县', '横　县']
				}, {
					name: '柳州市',
					areaList: ['市辖区', '城中区', '鱼峰区', '柳南区', '柳北区', '柳江县', '柳城县', '鹿寨县', '融安县', '融水苗族自治县', '三江侗族自治县']
				}, {
					name: '桂林市',
					areaList: ['市辖区', '秀峰区', '叠彩区', '象山区', '七星区', '雁山区', '阳朔县', '临桂县', '灵川县', '全州县', '兴安县', '永福县', '灌阳县', '龙胜各族自治县', '资源县', '平乐县', '荔蒲县', '恭城瑶族自治县']
				}, {
					name: '梧州市',
					areaList: ['市辖区', '万秀区', '蝶山区', '长洲区', '苍梧县', '藤　县', '蒙山县', '岑溪市']
				}, {
					name: '北海市',
					areaList: ['市辖区', '海城区', '银海区', '铁山港区', '合浦县']
				}, {
					name: '防城港市',
					areaList: ['市辖区', '港口区', '防城区', '上思县', '东兴市']
				}, {
					name: '钦州市',
					areaList: ['市辖区', '钦南区', '钦北区', '灵山县', '浦北县']
				}, {
					name: '贵港市',
					areaList: ['市辖区', '港北区', '港南区', '覃塘区', '平南县', '桂平市']
				}, {
					name: '玉林市',
					areaList: ['市辖区', '玉州区', '容　县', '陆川县', '博白县', '兴业县', '北流市']
				}, {
					name: '百色市',
					areaList: ['市辖区', '右江区', '田阳县', '田东县', '平果县', '德保县', '靖西县', '那坡县', '凌云县', '乐业县', '田林县', '西林县', '隆林各族自治县']
				}, {
					name: '贺州市',
					areaList: ['市辖区', '八步区', '昭平县', '钟山县', '富川瑶族自治县']
				}, {
					name: '河池市',
					areaList: ['市辖区', '金城江区', '南丹县', '天峨县', '凤山县', '东兰县', '罗城仫佬族自治县', '环江毛南族自治县', '巴马瑶族自治县', '都安瑶族自治县', '大化瑶族自治县', '宜州市']
				}, {
					name: '来宾市',
					areaList: ['市辖区', '兴宾区', '忻城县', '象州县', '武宣县', '金秀瑶族自治县', '合山市']
				}, {
					name: '崇左市',
					areaList: ['市辖区', '江洲区', '扶绥县', '宁明县', '龙州县', '大新县', '天等县', '凭祥市']
				}]
			},

			{
				name: '海南',
				cityList: [{
					name: '海口市',
					areaList: ['市辖区', '秀英区', '龙华区', '琼山区', '美兰区']
				}, {
					name: '三亚市',
					areaList: ['市辖区']
				}, {
					name: '省直辖县级行政单位',
					areaList: ['五指山市', '琼海市', '儋州市', '文昌市', '万宁市', '东方市', '定安县', '屯昌县', '澄迈县', '临高县', '白沙黎族自治县', '昌江黎族自治县', '乐东黎族自治县', '陵水黎族自治县', '保亭黎族苗族自治县', '琼中黎族苗族自治县', '西沙群岛', '南沙群岛', '中沙群岛的岛礁及其海域']
				}]
			}

		]
	},

	{
		_area: "西南地区",
		Allcity: [

			{
				name: '重庆',
				cityList: [{
					name: '市辖区',
					areaList: ['万州区', '涪陵区', '渝中区', '大渡口区', '江北区', '沙坪坝区', '九龙坡区', '南岸区', '北碚区', '万盛区', '双桥区', '渝北区', '巴南区', '黔江区', '长寿区']
				}, {
					name: '县',
					areaList: ['綦江县', '潼南县', '铜梁县', '大足县', '荣昌县', '璧山县', '梁平县', '城口县', '丰都县', '垫江县', '武隆县', '忠　县', '开　县', '云阳县', '奉节县', '巫山县', '巫溪县', '石柱土家族自治县', '秀山土家族苗族自治县', '酉阳土家族苗族自治县', '彭水苗族土家族自治县']
				}, {
					name: '市',
					areaList: ['江津市', '合川市', '永川市', '南川市']
				}]
			}, {
				name: '四川',
				cityList: [{
					name: '成都市',
					areaList: ['市辖区', '锦江区', '青羊区', '金牛区', '武侯区', '成华区', '龙泉驿区', '青白江区', '新都区', '温江县', '金堂县', '双流县', '郫　县', '大邑县', '蒲江县', '新津县', '都江堰市', '彭州市', '邛崃市', '崇州市']
				}, {
					name: '自贡市',
					areaList: ['市辖区', '自流井区', '贡井区', '大安区', '沿滩区', '荣　县', '富顺县']
				}, {
					name: '攀枝花市',
					areaList: ['市辖区', '东　区', '西　区', '仁和区', '米易县', '盐边县']
				}, {
					name: '泸州市',
					areaList: ['市辖区', '江阳区', '纳溪区', '龙马潭区', '泸　县', '合江县', '叙永县', '古蔺县']
				}, {
					name: '德阳市',
					areaList: ['市辖区', '旌阳区', '中江县', '罗江县', '广汉市', '什邡市', '绵竹市']
				}, {
					name: '绵阳市',
					areaList: ['市辖区', '涪城区', '游仙区', '三台县', '盐亭县', '安　县', '梓潼县', '北川羌族自治县', '平武县', '江油市']
				}, {
					name: '广元市',
					areaList: ['市辖区', '市中区', '元坝区', '朝天区', '旺苍县', '青川县', '剑阁县', '苍溪县']
				}, {
					name: '遂宁市',
					areaList: ['市辖区', '船山区', '安居区', '蓬溪县', '射洪县', '大英县']
				}, {
					name: '内江市',
					areaList: ['市辖区', '市中区', '东兴区', '威远县', '资中县', '隆昌县']
				}, {
					name: '乐山市',
					areaList: ['市辖区', '市中区', '沙湾区', '五通桥区', '金口河区', '犍为县', '井研县', '夹江县', '沐川县', '峨边彝族自治县', '马边彝族自治县', '峨眉山市']
				}, {
					name: '南充市',
					areaList: ['市辖区', '顺庆区', '高坪区', '嘉陵区', '南部县', '营山县', '蓬安县', '仪陇县', '西充县', '阆中市']
				}, {
					name: '眉山市',
					areaList: ['市辖区', '东坡区', '仁寿县', '彭山县', '洪雅县', '丹棱县', '青神县']
				}, {
					name: '宜宾市',
					areaList: ['市辖区', '翠屏区', '宜宾县', '南溪县', '江安县', '长宁县', '高　县', '珙　县', '筠连县', '兴文县', '屏山县']
				}, {
					name: '广安市',
					areaList: ['市辖区', '广安区', '岳池县', '武胜县', '邻水县', '华莹市']
				}, {
					name: '达州市',
					areaList: ['市辖区', '通川区', '达　县', '宣汉县', '开江县', '大竹县', '渠　县', '万源市']
				}, {
					name: '雅安市',
					areaList: ['市辖区', '雨城区', '名山县', '荥经县', '汉源县', '石棉县', '天全县', '芦山县', '宝兴县']
				}, {
					name: '巴中市',
					areaList: ['市辖区', '巴州区', '通江县', '南江县', '平昌县']
				}, {
					name: '资阳市',
					areaList: ['市辖区', '雁江区', '安岳县', '乐至县', '简阳市']
				}, {
					name: '阿坝藏族羌族自治州',
					areaList: ['汶川县', '理　县', '茂　县', '松潘县', '九寨沟县', '金川县', '小金县', '黑水县', '马尔康县', '壤塘县', '阿坝县', '若尔盖县', '红原县']
				}, {
					name: '甘孜藏族自治州',
					areaList: ['康定县', '泸定县', '丹巴县', '九龙县', '雅江县', '道孚县', '炉霍县', '甘孜县', '新龙县', '德格县', '白玉县', '石渠县', '色达县', '理塘县', '巴塘县', '乡城县', '稻城县', '得荣县']
				}, {
					name: '凉山彝族自治州',
					areaList: ['西昌市', '木里藏族自治县', '盐源县', '德昌县', '会理县', '会东县', '宁南县', '普格县', '布拖县', '金阳县', '昭觉县', '喜德县', '冕宁县', '越西县', '甘洛县', '美姑县', '雷波县']
				}]
			}, {
				name: '贵州',
				cityList: [{
					name: '贵阳市',
					areaList: ['市辖区', '南明区', '云岩区', '花溪区', '乌当区', '白云区', '小河区', '开阳县', '息烽县', '修文县', '清镇市']
				}, {
					name: '六盘水市',
					areaList: ['钟山区', '六枝特区', '水城县', '盘　县']
				}, {
					name: '遵义市',
					areaList: ['市辖区', '红花岗区', '汇川区', '遵义县', '桐梓县', '绥阳县', '正安县', '道真仡佬族苗族自治县', '务川仡佬族苗族自治县', '凤冈县', '湄潭县', '余庆县', '习水县', '赤水市', '仁怀市']
				}, {
					name: '安顺市',
					areaList: ['市辖区', '西秀区', '平坝县', '普定县', '镇宁布依族苗族自治县', '关岭布依族苗族自治县', '紫云苗族布依族自治县']
				}, {
					name: '铜仁地区',
					areaList: ['铜仁市', '江口县', '玉屏侗族自治县', '石阡县', '思南县', '印江土家族苗族自治县', '德江县', '沿河土家族自治县', '松桃苗族自治县', '万山特区']
				}, {
					name: '黔西南布依族苗族自治州',
					areaList: ['兴义市', '兴仁县', '普安县', '晴隆县', '贞丰县', '望谟县', '册亨县', '安龙县']
				}, {
					name: '毕节地区',
					areaList: ['毕节市', '大方县', '黔西县', '金沙县', '织金县', '纳雍县', '威宁彝族回族苗族自治县', '赫章县']
				}, {
					name: '黔东南苗族侗族自治州',
					areaList: ['凯里市', '黄平县', '施秉县', '三穗县', '镇远县', '岑巩县', '天柱县', '锦屏县', '剑河县', '台江县', '黎平县', '榕江县', '从江县', '雷山县', '麻江县', '丹寨县']
				}, {
					name: '黔南布依族苗族自治州',
					areaList: ['都匀市', '福泉市', '荔波县', '贵定县', '瓮安县', '独山县', '平塘县', '罗甸县', '长顺县', '龙里县', '惠水县', '三都水族自治县']
				}]
			}, {
				name: '云南',
				cityList: [{
					name: '昆明市',
					areaList: ['市辖区', '五华区', '盘龙区', '官渡区', '西山区', '东川区', '呈贡县', '晋宁县', '富民县', '宜良县', '石林彝族自治县', '嵩明县', '禄劝彝族苗族自治县', '寻甸回族彝族自治县', '安宁市']
				}, {
					name: '曲靖市',
					areaList: ['市辖区', '麒麟区', '马龙县', '陆良县', '师宗县', '罗平县', '富源县', '会泽县', '沾益县', '宣威市']
				}, {
					name: '玉溪市',
					areaList: ['市辖区', '红塔区', '江川县', '澄江县', '通海县', '华宁县', '易门县', '峨山彝族自治县', '新平彝族傣族自治县', '元江哈尼族彝族傣族自治县']
				}, {
					name: '保山市',
					areaList: ['市辖区', '隆阳区', '施甸县', '腾冲县', '龙陵县', '昌宁县']
				}, {
					name: '昭通市',
					areaList: ['市辖区', '昭阳区', '鲁甸县', '巧家县', '盐津县', '大关县', '永善县', '绥江县', '镇雄县', '彝良县', '威信县', '水富县']
				}, {
					name: '丽江市',
					areaList: ['市辖区', '古城区', '玉龙纳西族自治县', '永胜县', '华坪县', '宁蒗彝族自治县']
				}, {
					name: '思茅市',
					areaList: ['市辖区', '翠云区', '普洱哈尼族彝族自治县', '墨江哈尼族自治县', '景东彝族自治县', '景谷傣族彝族自治县', '镇沅彝族哈尼族拉祜族自治县', '江城哈尼族彝族自治县', '孟连傣族拉祜族佤族自治县', '澜沧拉祜族自治县', '西盟佤族自治县']
				}, {
					name: '临沧市',
					areaList: ['市辖区', '临翔区', '凤庆县', '云　县', '永德县', '镇康县', '双江拉祜族佤族布朗族傣族自治县', '耿马傣族佤族自治县', '沧源佤族自治县']
				}, {
					name: '楚雄彝族自治州',
					areaList: ['楚雄市', '双柏县', '牟定县', '南华县', '姚安县', '大姚县', '永仁县', '元谋县', '武定县', '禄丰县']
				}, {
					name: '红河哈尼族彝族自治州',
					areaList: ['个旧市', '开远市', '蒙自县', '屏边苗族自治县', '建水县', '石屏县', '弥勒县', '泸西县', '元阳县', '红河县', '金平苗族瑶族傣族自治县', '绿春县', '河口瑶族自治县']
				}, {
					name: '文山壮族苗族自治州',
					areaList: ['文山县', '砚山县', '西畴县', '麻栗坡县', '马关县', '丘北县', '广南县', '富宁县']
				}, {
					name: '西双版纳傣族自治州',
					areaList: ['景洪市', '勐海县', '勐腊县']
				}, {
					name: '大理白族自治州',
					areaList: ['大理市', '漾濞彝族自治县', '祥云县', '宾川县', '弥渡县', '南涧彝族自治县', '巍山彝族回族自治县', '永平县', '云龙县', '洱源县', '剑川县', '鹤庆县']
				}, {
					name: '德宏傣族景颇族自治州',
					areaList: ['瑞丽市', '潞西市', '梁河县', '盈江县', '陇川县']
				}, {
					name: '怒江傈僳族自治州',
					areaList: ['泸水县', '福贡县', '贡山独龙族怒族自治县', '兰坪白族普米族自治县']
				}, {
					name: '迪庆藏族自治州',
					areaList: ['香格里拉县', '德钦县', '维西傈僳族自治县']
				}]
			}, {
				name: '西藏',
				cityList: [{
					name: '拉萨市',
					areaList: ['市辖区', '城关区', '林周县', '当雄县', '尼木县', '曲水县', '堆龙德庆县', '达孜县', '墨竹工卡县']
				}, {
					name: '昌都地区',
					areaList: ['昌都县', '江达县', '贡觉县', '类乌齐县', '丁青县', '察雅县', '八宿县', '左贡县', '芒康县', '洛隆县', '边坝县']
				}, {
					name: '山南地区',
					areaList: ['乃东县', '扎囊县', '贡嘎县', '桑日县', '琼结县', '曲松县', '措美县', '洛扎县', '加查县', '隆子县', '错那县', '浪卡子县']
				}, {
					name: '日喀则地区',
					areaList: ['日喀则市', '南木林县', '江孜县', '定日县', '萨迦县', '拉孜县', '昂仁县', '谢通门县', '白朗县', '仁布县', '康马县', '定结县', '仲巴县', '亚东县', '吉隆县', '聂拉木县', '萨嘎县', '岗巴县']
				}, {
					name: '那曲地区',
					areaList: ['那曲县', '嘉黎县', '比如县', '聂荣县', '安多县', '申扎县', '索　县', '班戈县', '巴青县', '尼玛县']
				}, {
					name: '阿里地区',
					areaList: ['普兰县', '札达县', '噶尔县', '日土县', '革吉县', '改则县', '措勤县']
				}, {
					name: '林芝地区',
					areaList: ['林芝县', '工布江达县', '米林县', '墨脱县', '波密县', '察隅县', '朗　县']
				}]
			}

		]
	},

	{
		_area: "东北地区",
		Allcity: [

			{
				name: '辽宁',
				cityList: [{
					name: '沈阳市',
					areaList: ['市辖区', '和平区', '沈河区', '大东区', '皇姑区', '铁西区', '苏家屯区', '东陵区', '新城子区', '于洪区', '辽中县', '康平县', '法库县', '新民市']
				}, {
					name: '大连市',
					areaList: ['市辖区', '中山区', '西岗区', '沙河口区', '甘井子区', '旅顺口区', '金州区', '长海县', '瓦房店市', '普兰店市', '庄河市']
				}, {
					name: '鞍山市',
					areaList: ['市辖区', '铁东区', '铁西区', '立山区', '千山区', '台安县', '岫岩满族自治县', '海城市']
				}, {
					name: '抚顺市',
					areaList: ['市辖区', '新抚区', '东洲区', '望花区', '顺城区', '抚顺县', '新宾满族自治县', '清原满族自治县']
				}, {
					name: '本溪市',
					areaList: ['市辖区', '平山区', '溪湖区', '明山区', '南芬区', '本溪满族自治县', '桓仁满族自治县']
				}, {
					name: '丹东市',
					areaList: ['市辖区', '元宝区', '振兴区', '振安区', '宽甸满族自治县', '东港市', '凤城市']
				}, {
					name: '锦州市',
					areaList: ['市辖区', '古塔区', '凌河区', '太和区', '黑山县', '义　县', '凌海市', '北宁市']
				}, {
					name: '营口市',
					areaList: ['市辖区', '站前区', '西市区', '鲅鱼圈区', '老边区', '盖州市', '大石桥市']
				}, {
					name: '阜新市',
					areaList: ['市辖区', '海州区', '新邱区', '太平区', '清河门区', '细河区', '阜新蒙古族自治县', '彰武县']
				}, {
					name: '辽阳市',
					areaList: ['市辖区', '白塔区', '文圣区', '宏伟区', '弓长岭区', '太子河区', '辽阳县', '灯塔市']
				}, {
					name: '盘锦市',
					areaList: ['市辖区', '双台子区', '兴隆台区', '大洼县', '盘山县']
				}, {
					name: '铁岭市',
					areaList: ['市辖区', '银州区', '清河区', '铁岭县', '西丰县', '昌图县', '调兵山市', '开原市']
				}, {
					name: '朝阳市',
					areaList: ['市辖区', '双塔区', '龙城区', '朝阳县', '建平县', '喀喇沁左翼蒙古族自治县', '北票市', '凌源市']
				}, {
					name: '葫芦岛市',
					areaList: ['市辖区', '连山区', '龙港区', '南票区', '绥中县', '建昌县', '兴城市']
				}]
			}, {
				name: '吉林',
				cityList: [{
					name: '长春市',
					areaList: ['市辖区', '南关区', '宽城区', '朝阳区', '二道区', '绿园区', '双阳区', '农安县', '九台市', '榆树市', '德惠市']
				}, {
					name: '吉林市',
					areaList: ['市辖区', '昌邑区', '龙潭区', '船营区', '丰满区', '永吉县', '蛟河市', '桦甸市', '舒兰市', '磐石市']
				}, {
					name: '四平市',
					areaList: ['市辖区', '铁西区', '铁东区', '梨树县', '伊通满族自治县', '公主岭市', '双辽市']
				}, {
					name: '辽源市',
					areaList: ['市辖区', '龙山区', '西安区', '东丰县', '东辽县']
				}, {
					name: '通化市',
					areaList: ['市辖区', '东昌区', '二道江区', '通化县', '辉南县', '柳河县', '梅河口市', '集安市']
				}, {
					name: '白山市',
					areaList: ['市辖区', '八道江区', '抚松县', '靖宇县', '长白朝鲜族自治县', '江源县', '临江市']
				}, {
					name: '松原市',
					areaList: ['市辖区', '宁江区', '前郭尔罗斯蒙古族自治县', '长岭县', '乾安县', '扶余县']
				}, {
					name: '白城市',
					areaList: ['市辖区', '洮北区', '镇赉县', '通榆县', '洮南市', '大安市']
				}, {
					name: '延边朝鲜族自治州',
					areaList: ['延吉市', '图们市', '敦化市', '珲春市', '龙井市', '和龙市', '汪清县', '安图县']
				}]
			}, {
				name: '黑龙江',
				cityList: [{
					name: '哈尔滨市',
					areaList: ['市辖区', '道里区', '南岗区', '道外区', '香坊区', '动力区', '平房区', '松北区', '呼兰区', '依兰县', '方正县', '宾　县', '巴彦县', '木兰县', '通河县', '延寿县', '阿城市', '双城市', '尚志市', '五常市']
				}, {
					name: '齐齐哈尔市',
					areaList: ['市辖区', '龙沙区', '建华区', '铁锋区', '昂昂溪区', '富拉尔基区', '碾子山区', '梅里斯达斡尔族区', '龙江县', '依安县', '泰来县', '甘南县', '富裕县', '克山县', '克东县', '拜泉县', '讷河市']
				}, {
					name: '鸡西市',
					areaList: ['市辖区', '鸡冠区', '恒山区', '滴道区', '梨树区', '城子河区', '麻山区', '鸡东县', '虎林市', '密山市']
				}, {
					name: '鹤岗市',
					areaList: ['市辖区', '向阳区', '工农区', '南山区', '兴安区', '东山区', '兴山区', '萝北县', '绥滨县']
				}, {
					name: '双鸭山市',
					areaList: ['市辖区', '尖山区', '岭东区', '四方台区', '宝山区', '集贤县', '友谊县', '宝清县', '饶河县']
				}, {
					name: '大庆市',
					areaList: ['市辖区', '萨尔图区', '龙凤区', '让胡路区', '红岗区', '大同区', '肇州县', '肇源县', '林甸县', '杜尔伯特蒙古族自治县']
				}, {
					name: '伊春市',
					areaList: ['市辖区', '伊春区', '南岔区', '友好区', '西林区', '翠峦区', '新青区', '美溪区', '金山屯区', '五营区', '乌马河区', '汤旺河区', '带岭区', '乌伊岭区', '红星区', '上甘岭区', '嘉荫县', '铁力市']
				}, {
					name: '佳木斯市',
					areaList: ['市辖区', '永红区', '向阳区', '前进区', '东风区', '郊　区', '桦南县', '桦川县', '汤原县', '抚远县', '同江市', '富锦市']
				}, {
					name: '七台河市',
					areaList: ['市辖区', '新兴区', '桃山区', '茄子河区', '勃利县']
				}, {
					name: '牡丹江市',
					areaList: ['市辖区', '东安区', '阳明区', '爱民区', '西安区', '东宁县', '林口县', '绥芬河市', '海林市', '宁安市', '穆棱市']
				}, {
					name: '黑河市',
					areaList: ['市辖区', '爱辉区', '嫩江县', '逊克县', '孙吴县', '北安市', '五大连池市']
				}, {
					name: '绥化市',
					areaList: ['市辖区', '北林区', '望奎县', '兰西县', '青冈县', '庆安县', '明水县', '绥棱县', '安达市', '肇东市', '海伦市']
				}, {
					name: '大兴安岭地区',
					areaList: ['呼玛县', '塔河县', '漠河县']
				}]
			}

		]
	}

];
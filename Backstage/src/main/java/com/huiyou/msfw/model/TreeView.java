package com.huiyou.msfw.model;

import java.util.List;

import lombok.Data;

/**   

* @Author: 曾源  

* @Date: 2020年5月12日  下午1:39:27

* @Description: 树形组件返回用数据

*/
@Data
public class TreeView {
	private Long id;
	private String title;
	private List<TreeView> children;
	private Boolean checked;
	private Boolean spread=true;

}

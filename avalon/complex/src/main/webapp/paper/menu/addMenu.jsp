<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>增加菜单</title>
<script src="../../static/js/jquery-3.2.1.js"></script>
<script src="../../static/js/layer.js"></script>
<script src="../../static/js/util.js"></script>
<script src="../../static/js/dtree.js"></script>
<link rel="stylesheet" type="text/css" href="../../static/js/skin/default/layer.css">
<link rel="stylesheet" type="text/css" href="../../static/style/dtree.css">
<style type="text/css">
table{
	border: 2px solid grey;
	padding:5px;
	width:100%;
	height:100%;
}
th{
	witdh:50%;
}
</style>
<script type="text/javascript">

d = new dTree('d');
d.add(0,-1,'My example tree');
d.add(1,0,'Node 1','example01.html');
d.add(2,0,'Node 2','example01.html');
d.add(3,1,'Node 1.1','example01.html');
d.add(4,0,'Node 3','example01.html');
d.add(5,3,'Node 1.1.1','example01.html');
d.add(6,5,'Node 1.1.1.1','example01.html');
d.add(7,0,'Node 4','example01.html');
d.add(8,1,'Node 1.2','example01.html');
d.add(9,0,'My Pictures','example01.html','Pictures I\'ve taken over the years','','','img/imgfolder.gif');
d.add(10,9,'The trip to Iceland','example01.html','Pictures of Gullfoss and Geysir');
d.add(11,9,'Mom\'s birthday','example01.html');
d.add(12,0,'Recycle Bin','example01.html','','','img/trash.gif');




$(document).ready(function(){
	$.ajax({
		type:"get",
		url:"../../menu/init",
		data:"",
		dataType:"json",
		success:function(data){
			initMenu(data);
		},
		error:function(){
			alert("error");
		}
	});
	//$("#menu_parent_name").html(d);
	document.getElementById("menu_parent_name").innerHTML=d;
});


function initMenu(data){
var html ="";
$(data).each(function(index,row){
html +="<select>"+row.msMenu.menuname;
$(row.subMenuList).each(function(index,subrow){
	//alert(subrow.menuurl);
	html += "<option>"+subrow.menuname;
	html += "</option>";
});
html +="</select>";
});
alert(html);
$("#roleid").html(html);
}

</script>
<script type="text/javascript">
<!-- 弹出层-->
xOffset = 0;//向右偏移量
yOffset = 25;//向下偏移量
var toshow = "treediv";//要显示的层的id
var target = "menu_parent_name";//目标控件----也就是想要点击后弹出树形菜单的那个控件id
$("#"+target).click(function (){
	$("#"+toshow)
	.css("position", "absolute")
	.css("left", $("#"+target).position().left+xOffset + "px")
	.css("top", $("#"+target).position().top+yOffset +"px").show();
});
//关闭层
$("#closed").click(function(){
	$("#"+toshow).hide();
});

//判断鼠标在不在弹出层范围内
 function   checkIn(id){
	var yy = 20;   //偏移量
	var str = "";
	var   x=window.event.clientX;   
	var   y=window.event.clientY;   
	var   obj=$("#"+id)[0];
	if(x>obj.offsetLeft&&x<(obj.offsetLeft+obj.clientWidth)&&y>(obj.offsetTop-yy)&&y<(obj.offsetTop+obj.clientHeight)){   
		return true;
	}else{   
		return false;
	}   
  }   
//点击body关闭弹出层
	$(document).click(function(){
		var is = checkIn("treediv");
		if(!is){
			$("#"+toshow).hide();
		}
	});
<!--弹出层-->
//生成弹出层的代码

/* //点击菜单树给文本框赋值------------------菜单树里加此方法
function setvalue(id,name){
	$("#menu_parent_name").val(name);
	$("#menu_parent").val(id);
	$("#treediv").hide();
} */
</script>
<!--代码部分end-->

<div id="treediv">
	<div align="right">
		<a href="javascript:viod(0);" id="closed"><font color="#000">关闭</font></a>
    </div>
    <script src="js/lanren.js"></script>
</div>  

</head>
<body>
<div>
	<form id="myform" method="post">
		<table>
			<tr>
				<th>父菜单ID:</th>
				<td><input type="text" id="menu_parent_name" style="width: 150px;">
					<input type="hidden" id="menu_parent" name="menu_parent">
					<input type="hidden" id="oprate" name="oprate">
					<input type="hidden" id="menu_id" name="menu_id">
				</td>
			</tr>
			<tr>
				<th>菜单ID:</th>
				<td><input type="text" id="rolename" name="rolename"></td>
			</tr>
			<tr>
				<th>菜单URl:</th>
				<td><input type="text" id="fatherroleid" name="fatherroleid"></td>
			</tr>
			<tr>
				<th>菜单类型:</th>
				<td><input type="text" id="isactive" name="isactive"></td>
			</tr>
			<tr>
				<th>排序编号:</th>
				<td><input type="text" id="isactive" name="isactive"></td>
			</tr>
			<tr>
				<th>启停标志:</th>
				<td><input type="text" id="isactive" name="isactive"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button>确认</button><button>取消</button></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>
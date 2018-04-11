<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理</title>
<script src="../../static/js/jquery-3.2.1.js"></script>
<script src="../../static/js/layer.js"></script>
<script src="../../static/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="../../static/js/skin/default/layer.css">
<script type="text/javascript">
var curPageNo = 1;
var pageSize = 10;
	$(document).ready(function(){
		//alert((new Date()).format("yyyy-MM-dd hh:mm:ss"));
		//onquery(curPageNo);
		
		$("#add").click(function(){
			//alert("add role");
			//layer.msg('add role',{ icon : 6});
			layer.open({
					  type: 2,
					  offset : ['150px', ''],
					  title : "增加角色",
					  skin: 'layui-layer-rim', //加上边框
					  area: ['320px', '300px'], //宽高
					  content: '../rolemg/addRole.jsp',
					  end : function(){ //层彻底关闭后执行的回调
				      }
			});
		});
		$("#edit").click(function(){
			//alert("edit role");
			layer.msg('edit role',{ icon : 6});
		});
		
		$("#delete").click(function(){
			//alert("delete role");
			layer.msg('delete role',{ icon : 6});
		});
	});
	
	function onquery(curPageNo){
		//alert("curPageNo"+curPageNo);
		$.ajax({
			type:"POST",
			url:"../../user/onquery?curPageNo="+curPageNo,
			data:$("#queryform").serialize(),
			dataType:"json",
			async:true,
			error:function(){
				alert("error");
			},
			success:function(data){
				//alert("success");
				getTotalCount();
				initTable(data);
				$("#curPageNo").text(curPageNo);
				checkPageButton(curPageNo);
			}
		});
	}

	
	

</script>
<style type="text/css">
.usernavbar{
	background-color: #C0C0C0;
	height:50px;
	margin: 10px;
}
.usernavbar-label{
	margin-top: 15px;
	margin-left:15px;
	float: left;
} 
.usernavbar-btn{
	margin-right: 15px;
	margin-top: 15px;
	width:60px;
	float: right;
}
.rolecontainer{
	background-color: white;
	height:520px;
	margin: 10px;
}
.rolecontainerheader{
	margin-top:10px;
	height:30px;
	padding-left:30px;
	padding-top:10px;
	background-color: #C0C0C0;
}
.rolecontainerbody{
	padding-top: 15px;
	padding-left:15px;
}
</style>
</head>
<body>
<div class="usernavbar">
		<label class="usernavbar-label" ><a href="javascript:void()">主页</a>/<a href="javascript:void()">角色管理</a></label>
</div>
<div class="rolecontainer">
	<div class="rolecontainerheader">
		<label>角色管理</label>
	</div>
	<div class="rolecontainerbody">
		<div>
			<button id="add">添加</button>&nbsp;&nbsp;<button id="edit">编辑</button>&nbsp;&nbsp;<button id="delete">删除</button>
		</div>
		<div class="rolecontainerbody-tree">
		</div>
	</div>
</div>
</body>
</html>
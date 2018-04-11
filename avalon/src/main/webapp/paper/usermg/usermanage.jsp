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
		onquery(curPageNo);
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
	
	function checkPageButton(curPageNo){
		var curPage = $("#curPageNo").text();
		var maxPage = $("#MaxPageNo").text();
		if(curPage == maxPage){
			$("#nextPage").attr({"disabled":"disabled"});
		}else{
			 $("#nextPage").removeAttr("disabled");//将按钮可用
		}
	}
	
	function getTotalCount(){
		$.get('../../user/total',
				function(data,status){
			   if(status == 'success'){
				   $("#totalcount").text(data);
				   var maxPage = Number(data)/pageSize;
				   maxPage = Math.ceil(maxPage);
				  $("#MaxPageNo").text(maxPage);
			   }
		});
	}
	
	function searchuser(){
	//	alert("search");
		$.ajax({
			type:"POST",
			url:"../../user/query",
			data:$("#queryform").serialize(),
			dataType:"json",
			async:true,
			error:function(){
				alert("error");
			},
			success:function(data){
				initTable(data);
			}
		});
	}
	
	
	function addUser(){
		layer.open({
	        type : 2,
	        offset : ['150px', ''],
	        title : "增加用户",
	        content: '../usermg/addUser.jsp',	
	        area : ['400px','400px'],
	        end : function(){ //层彻底关闭后执行的回调
	        	onquery(1);
	        }
	    });
	}
	
	function initTable(data){
		var html ="<table>"
			html += writeTableTh();
		if(data==null||data==''){
			
		}else{
			html += writeTableData(data);
		}
		    html +="</table>"
		  // alert("html:"+html);
		$(".datatable").html(html);
	}
	
	function writeTableData(data){
		var tableData = "";
		$(data).each(function(index,row){
			tableData +="<tr>"
				tableData +="<th>"+row.id+"</th>";
				tableData +="<th>"+row.operatorname+"</th>";
				tableData +="<th>"+row.operatorcode+"</th>";
				tableData +="<th>"+row.departmentid+"</th>";
				tableData +="<th>"+row.groupid+"</th>";
				tableData +="<th>"+row.isactive+"</th>";
				tableData +="<th>"+(new Date(row.createtime)).format("yyyy-MM-dd hh:mm:ss")+"</th>";
				tableData +="<th>"+row.creatorcode+"</th>";
				tableData +="<th><button class='editbtn' onclick='javascript:edit("+row.id+")'>编辑</button><button class='delbtn' onclick='javascript:del("+row.id+")'>删除</button></th>";
			tableData +="</tr>"
		});
		return tableData;
	}
	
	function del(id){
		//alert(id);
		$.ajax({
			type:"post",
			url:"../../user/delete",
			data:{'id':id},
			dataType:"json",
			async:true,
			error:function(){
				//alert("error");
				layer.msg('error',{ icon : 2});
			},
			success:function(data){
				//initTable(data);
				layer.msg('delete success',{ icon : 1});
				//alert("delete success");
				onquery(1);
			}
		});
	}
	
	function edit(id){
		
		var localstroage = window.localStorage;
		localstroage.setItem('id',id);
		layer.open({
	        type : 2,
	        offset : ['150px', ''],
	        title : "增加用户",
	        content: '../usermg/editUser.jsp',
	        area : ['400px','400px'],
	        end : function(){ //层彻底关闭后执行的回调
	        	onquery(1);
	        }
	    });
	}
	
	function writeTableTh(){
		var th ="<tr>";
			th +="<th>id</th>";
			th +="<th>用户名</th>";
			th +="<th>用户编号</th>";
			th +="<th>部门</th>";
			th +="<th>组</th>";
			th +="<th>是否启用</th>";
			th +="<th>创建时间</th>";
			th +="<th>创建人</th>";
			th +="<th>操作</th>";
		th +="</tr>";
		return  th;
	}

	function pageQuery(addition){
		var curPageNo = $("#curPageNo").text();
			curPageNo =Number(curPageNo)+addition;
			if( curPageNo <= 0){
				return;
			}else{
				onquery(curPageNo);
			}
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
.query{
	margin: 10px;
	height:100px;
	border:2px solid grey;
}
.query-in{
	height:30px;
	border:1px solid grey;
}
.datatable{
	margin: 10px;
	height:350px;
	border:2px solid grey;
}
.datatable table{
	border:1px solid grey;
	width:100%
}
.datatable th{
	border:1px solid grey;
}
.datatable td{
	border:1px solid grey;
}
.boot{
	margin:10px;
	border:2px solid grey;
}
.boot-left{
	float: left;
}
.boot-right{
	float: right;
}
</style>
</head>
<body>
<div class="usernavbar">
		<label class="usernavbar-label" ><a href="javascript:void()">主页</a>/<a href="javascript:void()">用户管理</a></label>
		<button class="usernavbar-btn" onclick="javascript:addUser()">添加</button>
</div>
<div class="query">
<div class="query-in">
 <label style="align:left">查询条件</label>
</div>
<div>
	<form id="queryform" method="post" onsubmit="return false;">
		<label>用户名:</label>
		<input type="text" id="operatorname" name="operatorname" ></input>
		<!-- <label>邮箱:</label>
		<input type="text" id="email" ></input> -->
		<button onclick="javascript:searchuser()">搜索</button>
	</form>
</div>
</div>
<div class="datatable"></div>
<div class="boot">
<div class="boot-left">
	共搜索到<strong id="totalcount">0</strong>条数据
</div>
<div class="boot-right">
	<label>当前页:<strong id="curPageNo">0</strong></label>
	<label>总页数:<strong id="MaxPageNo">0</strong></label>
	<button id="lastPage" onclick="javascript:pageQuery(-1)">上一页</button>
	<button id="nextPage" onclick="javascript:pageQuery(1)">下一页</button>
</div>
</div>
</body>
</html>
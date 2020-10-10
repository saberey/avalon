<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>增加用户</title>
<script src="../../static/js/jquery-3.2.1.js"></script>
<script src="../../static/js/layer.js"></script>
<link rel="stylesheet" type="text/css" href="../../static/js/skin/default/layer.css">

<script type="text/javascript">
	function addUsers(){
		  $.ajax({
			type:"POST",
			url:"../../user/addUser",
			data:$("#myform").serialize(),
			dataType:"json",
			async:false,
			error: function(data){
				//alert("error");
				//alert(data);
			},
			success: function(result){
				//alert("success");
				parent.layer.closeAll();
			}
		});  
 	}
	
	function cancel(){
		
	}
</script>
<style type="text/css">
table{
	border: 2px solid grey;
	padding:5px;
	width:100%;
	height:100%;
}
th{
	width:50%;
}
</style>
</head>
<body>
<div>
	<form id="myform" method="post">
		<table>
			<tr>
				<th>用&nbsp;&nbsp;户&nbsp;&nbsp;名：</th>
				<td><input type="text" id="operatorname" name="operatorname"></td>
			</tr>
			<tr>
				<th>用户编号：</th>
				<td><input type="text" id="operatorcode" name="operatorcode"></td>
			</tr>
			<tr>
				<th>用户密码：</th>
				<td><input type="text" id="operatorpwd" name="operatorpwd"></td>
			</tr>
			<tr>
				<th>部门编号：</th>
				<td><input type="text" id="departmentid" name="departmentid"></td>
			</tr>
			<tr>
				<th>组&nbsp;&nbsp;编&nbsp;&nbsp;号：</th>
				<td><input type="text" id="groupid" name="groupid"></td>
			</tr>
			<tr>
				<th>启停标志：</th>
				<td><input type="text" id="isactive" name="isactive"></td>
			</tr>
			
		</table>
		<br>
		<div>
				<td colspan="2" align="center"><button onclick="javascript:addUsers()">确认</button>
				<button id="cancel" onclick="javascript:cancel()">取消</button></td>
			</div>
	</form>
</div>
</body>
</html>
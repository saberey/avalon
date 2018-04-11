<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>增加用户</title>
<script src="../../static/js/jquery-3.2.1.js"></script>
<script src="../../static/js/layer.js"></script>
<script src="../../static/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="../../static/js/skin/default/layer.css">
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
	
</script>
</head>
<body>
<div>
	<form id="myform" method="post">
		<table>
			<tr>
				<th>角色id:</th>
				<td><input type="text" id="roleid" name="roleid"></td>
			</tr>
			<tr>
				<th>角色名:</th>
				<td><input type="text" id="rolename" name="rolename"></td>
			</tr>
			<tr>
				<th>父角色id:</th>
				<td><input type="text" id="fatherroleid" name="fatherroleid"></td>
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
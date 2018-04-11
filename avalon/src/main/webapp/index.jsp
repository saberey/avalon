<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MS ST</title>
<link rel="shortcut icon" href="../../book.ico" type="image/x-icon" />
<link rel="icon" href="../../reader.ico" type="image/x-icon" >
<script src="static/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#login").click(function(){
			$.post("login/signin",
					{
						signupusername : $("#signupusername").val(),
						signuppassword : $("#signuppassword").val()
					},
					function(data,status){
						//alert("data:"+data)
						if(data!="error")
						location.href="paper/menu/welcome.jsp";
					});
		});
		$("#reset").click(function(){
			$("#signupusername").val('');
			$("#signuppassword").val('');
		});
		
		//回车事件
		$(document).keydown(function(e){
			if(e.keyCode == 13){
				alert(e.keyCode);
				if($("#signupusername").val()!=null&& $("#signuppassword").val()!=null){
					login();
				}
			}
		});
		
	});
	
	function login(){
		$.post("login/signin",
				{
					signupusername : $("#signupusername").val(),
					signuppassword : $("#signuppassword").val()
				},
				function(data,status){
					//alert("data:"+data)
					if(data!="error")
					location.href="paper/menu/welcome.jsp";
				});
	}
	
	
	
</script>
<style type="text/css">
.center-outer{
	 display: table;
}
.outer-title{
position: absolute;
left: 45%;
top: 30%;
width:220px;
height:50px;
}

.center-container{
position: absolute;
left: 50%;
top: 50%;
width:220px;
height:100px;
margin-left:-100px;
margin-top:-80px;
}
</style>
</head>
<body >
<div class="center-outer">
<div class="outer-title">
	<h2>欢迎登陆MS系统</h2>
</div>
<div class="center-container"> 
<!-- <div class="div1"> -->
	<div >
		<table style="border: 2px solid grey" >
			<tr>
				<th>用户：</th>
				<td><input type="text" id="signupusername" placeholder="请输入用户名"></input></td>
			</tr>
			<tr>
				<th>密码：</th>
				<td><input type="password" id="signuppassword" placeholder="请输入密码"></input></td>
			</tr>
			<!-- <tr>
				<th>email：</th>
				<td><input type="text" id="signupemail"></input></td>
			</tr> -->
			<tr>
				<td colspan="2" align="center"><input type="button" id="login"  value="登陆"><input type="reset" id="reset"  value="重置"></input></td>
			</tr>
		</table>
	</div>
</div>
</div>
</body>

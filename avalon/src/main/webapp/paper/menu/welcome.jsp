<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MS</title>
<link rel="shortcut icon" href="../../book.ico" type="image/x-icon" />
<link rel="icon" href="../../reader.ico" type="image/x-icon" >
<!-- <link href="../../static/style/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="../../static/style/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen"> -->


<style type="text/css">
.navbar{
	height: 100px;
	border: 2px solid grey;
}
.navbar-msg{
	float: left;
	width: 400px;
}
.navbar-right{
	float: right;
}
.navbar-cal{
	width: 200px;
	float: left;
}
.navbar-user{
	width:300px;
	float: right;
}
.navbar-msg p{
	margin-top: 40px;
	margin-left: 20px;
	font-style: italic;
}
.container{
	border: 2px solid grey;
	withd: 100%;
}
.leftmenu{
	border:2px solid grey;
	margin-top:20px;
	margin-right:10px;
	float:left;
	width: 10%;
}
.rightcontent{
    border:2px solid grey;
    margin-left:10px;
    margin-top:20px;
	float:left;
	width: 88%;
	height: 600px;
}
.form_date{
	border:1px solid grey;
}
</style>
</head>
<body>
<div>
<!-- nav bar -->
<div class="navbar">
<div class="navbar-msg">
	<p>遇顺境，处之淡然，遇逆境，处之泰然</p>
</div>
<div	class="navbar-right">
<div class="navbar-cal">
	<!-- <div class="form-group">
                <label for="dtp_input2" class="col-md-2 control-label">Date Picking</label>
                <div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
				<input type="hidden" id="dtp_input2" value="" /><br/>
            </div> -->
     <!-- <div class="form_date"></div> -->
     <div>
     	<p id="date"></p>
     </div>
</div>
<div class="navbar-user">
	<p>用户信息</p>
</div>
</div>
</div>
<!-- menu & content -->
<div class="container">

<!-- left menu -->
<div class="leftmenu">
</div>
<!-- right content -->
<div class="rightcontent">
	<p>你好！欢迎登陆系统</p>
	
</div>
</div>
</div>

</body>
</html>
<script src="../../static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../static/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../../static/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
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
				
				$('#date').text(toDate());
	});
	
	
	function toDate(){
		   var date = new Date();
	        var seperator1 = "-";
	        var year = date.getFullYear();
	        var month = date.getMonth() + 1;
	        var strDate = date.getDate();
	        if (month >= 1 && month <= 9) {
	            month = "0" + month;
	        }
	        if (strDate >= 0 && strDate <= 9) {
	            strDate = "0" + strDate;
	        }
	        var currentdate = year + seperator1 + month + seperator1 + strDate;
	        var str = '';
	        var Week = ['日','一','二','三','四','五','六'];  
	        str += ' 星期' + Week[date.getDay()];  
	        currentdate +=str;
	        return currentdate;
	}
	
	
	$('.form_date').datetimepicker({
		 language:  'zh-CN',
	        weekStart: 1,
	       // todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0,
			show:true
	});
	
	function cLoad(uri){
		$(".rightcontent").load(uri);
	}

	function initMenu(data){
		var html ="";
		$(data).each(function(index,row){
			html +="<h2>"+row.msMenu.menuname+"</h2>";
			html +="<ul>";
			$(row.subMenuList).each(function(index,subrow){
				//alert(subrow.menuurl);
				html += "<li>";
				html += "<a  href=\"javascript:void(0)\" onclick=\"javascript:cLoad(\'"+subrow.menuurl+"\')\">"+subrow.menuname+"</a>";
				html += "</li>";
			});
			html +="</ul>";
		});
		
		//alert(html);
		$(".leftmenu").html(html);
	}
</script>
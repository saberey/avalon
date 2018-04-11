<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script type="text/javascript" src="static/js/jquery-3.2.1.js"></script>
<script>
	function initMenu(){
		
	}
	
    var result = "";
	function query(){
		//var code = $("#code").val();
		//alert(1);
		alert($('#myform').serialize());
		alert(JSON.stringify($('#myform').serialize()));
		var fomdata = $('#myform').serialize();
		$.ajax({
			url: 'city/queryList',
			data: {
				formdata : JSON.stringify($('#myform').serialize())
			},
			type: "POST",
			dataType: "json",
			error: function(request){
				
			},
			success: function(data)
			{
				result = writeHead();
				$.each(data,function(i,field){
				   writeBody(field);
				}); 
				alert(result);
				$("#resultTb").html(result);
			}
		});
	}
	
	
	function writeHead(){
		var html ="<tr>"+
					"<th>代码</th>"+
					"<th>城市</th>"+
					"<th>省会</th>"+
					"<th>id</th>"+
			"</tr>";
			return html;
	}
	
	function writeBody(data){
		result +="<tr>"+
		"<th>"+data.code+"</th>"+
		"<th>"+data.city+"</th>"+
		"<th>"+data.province+"</th>"+
		"<th>"+data.id+"</th>"+
			"</tr>";
	}
	
</script>
<body>

<form id="myform" name="myform" method="post">
	<div>
		<table id="queryTb">
			<tr>
				<th>城市</th>
				<td><input type="text" name='city' id='city'/></td>
			</tr>
			<tr>
				<th>代码</th>
				<td><input type="text" name='code' id='code'/></td>
			</tr>
			<tr>
				<th>省会</th>
				<td><input type="text" name='province' id='province'/></td>
			</tr>
			<tr>
				<th>id</th>
				<td><input type="text" name='id' id='id'/></td>
			</tr>
			<tr>
				<td>
				<input type="button" id='btn' name='btn' value='query' onclick="query()"/>
				</td>
			</tr>
		</table>
	</div>		
			<table id="resultTb">
			</table>
			<table id="menu">
			</table>
</form>
</body>


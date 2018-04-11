<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>zk</title>
<script src="../../static/js/jquery-3.2.1.js"></script>
<script src="../../static/js/layer.js"></script>
<link rel="stylesheet" type="text/css" href="../../static/js/skin/default/layer.css">

<script type="text/javascript">

	function zkDeal(){
		  $.ajax({
			type:"POST",
			url:"../../zk/list",
			data:$("#zkform").serialize(),
			dataType:"json",
			async:false,
			error: function(data){
				//alert("error");
				//alert(data);
			},
			success: function(data){
				alert(data);
				var root = $("#path").val();
			    writeZkData(data,null,root);
			}
		});  
 	}
	
	function writeZkData(result,paths,root){
		var html = "";
		if(result!=null){
			html +="<ul>"
			if(root.charAt(root.length-1)!='/'){
				root +='/';
			}
			$(result).each(function(index,row){
					html += "<li>";
					html += "<a id="+row+" href=\"javascript:void(0)\" onclick=\"javascript:zkLoad(\'"+root+row+"\')\">"+row+"</a>";
					//html +=row;
					html +="</li>"
			});
			html +="</ul>"
		}
		alert(paths);
	 	if(paths==null){
			$("#resultZkTable").html(html);
	 	}else{
			$("#\'"+path+"\'").html(html);
		} 
	}
	
	 function zkLoad(path){
		 alert("zkload"+path);
		 $.ajax({
				type:"POST",
				url:"../../zk/list",
				data:"path="+path,
				dataType:"json",
				async:false,
				error: function(data){
					//alert("error");
					//alert(data);
				},
				success: function(data){
					alert(data);
				    writeZkData(data,path);
				}
			});  
	}

</script>
<style type="text/css">
table{
	border: 2px solid grey;
	padding:5px;
	width:100%;
	height:100%;
}
/* th{
	width:50%;
} */
</style>
</head>
<body>
<div>
	<form id="zkform" method="post" onsubmit="return false;">
		<table>
			<tr>
				<th>路径：</th>
				<td><input type="text" id="path" name="path"></td>
			</tr>		
		    <tr>
				<td colspan="2" align="center"><button onclick="javascript:zkDeal()">query</button>
				</td>
			</tr>
		</table>
		<div id="showZkTable" >
			<table id="resultZkTable" cellspacing="0" cellpadding="0" width="100%" class="table-list">
			</table>
		</div>
	</form>
</div>
</body>
</html>
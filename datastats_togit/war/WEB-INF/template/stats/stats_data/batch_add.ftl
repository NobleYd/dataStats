<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<meta name="author" content="APP TEAM" />
<meta name="copyright" content="APP" />

<link href="${base}/resources/stats/css/common.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${base}/resources/stats/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/stats/js/common.js"></script>

<script type="text/javascript">
$().ready(function() {

	[@flash_message /]

});
</script>

<style>
	th,td{
		border:1px solid black;
		padding:10px;
	}

	tr{
		height:40px;
	}
	
	input:not(.button){
		width:300px;
	}
	
</style>
</head>
<body style="width:65%; margin:30px auto" >
	<form action="batch_save.jhtml" method="post">
		<table id="listTable" >
			<tr>	
				<th>
					<span>dataTime</span>
				</th>
				<td><input name="dataTimes" /></td>
			</tr>
			<tr>
				<th>
					<span>numberValue</span>
				</th>
				<td><input type="text" name="numberValues" /></td>
			</tr>
			<tr>
				<th>
					<span>textValue</span>
				</th>
				<td><input type="text" name="textValues" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input class="button" type="submit" value="submit" /></td>
			</tr>
		</table>
	<form>
</body>
</html>
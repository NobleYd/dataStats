<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<meta name="author" content="APP TEAM" />
<meta name="copyright" content="APP" />
<script type="text/javascript" src="${base}/resources/stats/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/stats/js/common.js"></script>

<script type="text/javascript">
$().ready(function() {

	[@flash_message /]
	
	$statsCycle = $("#statsCycleId");
	$divNumber = $("#divNumberId");
	
	$statsCycle.change(function(){
		$divNumber.attr('disabled', true);
		$divNumber.val('');
		$this = $(this);
		if( 'selfDefine' == $this.val() ){
			$divNumber.attr('disabled', false);
			$divNumber.val('1');
		}
	});

});
</script>

<style>
	th,td{
		border:1px solid black;
	}

</style>
</head>
<body style="width:65%; margin:30px auto" >
	<form  action="save.jhtml" method="post">
		<table id="listTable" >
			<tr>	
				<th>
					<span>partnerId</span>
				</th>
				<td><input type="text" name="partnerId" /></td>
			</tr>
			<tr>
				<th>
					<span>title</span>
				</th>
				<td><input type="text" name="title" /></td>
			</tr>
			<tr>
				<th>
					<span>subTitle</span>
				</th>
				<td><input type="text" name="subTitle" /></td>
			</tr>
			<tr>
				<th>
					<span>showTitle</span>
				</th>
				<td><input type="text" name="showTitle" /></td>
			</tr>
			<tr>
				<th>
					<span>subShowTitle</span>
				</th>
				<td><input type="text" name="subShowTitle" /></td>
			</tr>
			<tr>
				<th>
					<span>statsValueType</span>
				</th>
				<td>
					<select name="statsValueType">
						[#list statsValueTypes as statsValueType]
							<option value="${statsValueType}" >${statsValueType.title}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span>statsCycle</span>
				</th>
				<td>
					<select id="statsCycleId" name="statsCycle">
						[#list statsCycles as statsCycle]
							<option value="${statsCycle}" >${statsCycle.title}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span>divNumber</span>
				</th>
				<td>
					<input id="divNumberId" type="text" name="divNumber" />
				</td>
			</tr>
			<tr>
				<th>
					<span>statsMethod</span>
				</th>
				<td>
					<select name="statsMethod">
						[#list statsMethods as statsMethod]
							<option value="${statsMethod}" >${statsMethod.title}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="submit" /></td>
			</tr>
		</table>
	<form>
</body>
</html>
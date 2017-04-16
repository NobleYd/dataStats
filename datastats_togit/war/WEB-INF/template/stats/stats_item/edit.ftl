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
	
	//init
	if( 'selfDefine' != $statsCycle.val() ){
		$divNumber.attr('disabled', true);
		$divNumber.val('');
	}

});
</script>

<style>
	th,td{
		border:1px solid black;
	}

</style>
</head>
<body style="width:65%; margin:30px auto" >
	<form action="update.jhtml" method="post">
		<input type="hidden" name="id" value="${statsItem.id}" />
		<table id="listTable" >
			<tr>	
				<th>
					<span>partnerId</span>
				</th>
				<td><input type="text" value="${statsItem.partnerId}" disabled="disabled" /></td>
			</tr>
			<tr>
				<th>
					<span>title</span>
				</th>
				<td><input type="text" value="${statsItem.title}" disabled="disabled" /></td>
			</tr>
			<tr>
				<th>
					<span>subTitle</span>
				</th>
				<td><input type="text" value="${statsItem.subTitle}" disabled="disabled" /></td>
			</tr>
			<tr>
				<th>
					<span>showTitle</span>
				</th>
				<td><input type="text" name="showTitle" value="${statsItem.showTitle}" /></td>
			</tr>
			<tr>
				<th>
					<span>subShowTitle</span>
				</th>
				<td><input type="text" name="subShowTitle" value="${statsItem.subShowTitle}" /></td>
			</tr>
			<tr>
				<th>
					<span>statsValueType</span>
				</th>
				<td>
					${statsItem.statsValueType.title}
				</td>
			</tr>
			<tr>
				<th>
					<span>statsCycle</span>
				</th>
				<td>
					<select id="statsCycleId" name="statsCycle">
						[#list statsCycles as statsCycle]
							<option value="${statsCycle}" [#if statsItem.statsCycle == statsCycle ]selected="selected"[/#if] >${statsCycle.title}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span>divNumber</span>
				</th>
				<td>
					<input id="divNumberId" type="text" name="divNumber" value="${statsItem.divNumber}" />
				</td>
			</tr>
			<tr>
				<th>
					<span>statsMethod</span>
				</th>
				<td>
					<select name="statsMethod">
						[#list statsMethods as statsMethod]
							<option value="${statsMethod}" [#if statsItem.statsMethod == statsMethod ]selected="selected"[/#if] >${statsMethod.title}</option>
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
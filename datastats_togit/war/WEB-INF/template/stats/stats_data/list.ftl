<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<meta name="author" content="APP TEAM" />
<meta name="copyright" content="APP" />
<script type="text/javascript" src="${base}/resources/stats/js/jquery.js"></script>

<script type="text/javascript">
$().ready(function() {

	[@flash_message /]

});
</script>

<style>
	th,td{
		border:1px solid black;
	}

</style>
</head>
<body style="width:65%; margin:30px auto" >
	<table>
		<tr>
			<th>
				<span>id</span>
			</th>
			<th>
				<span>statsItem</span>
			</th>
			<th>
				<span>dataTime</span>
			</th>
			<th>
				<span>numberValue</span>
			</th>
			<th>
				<span>textValue</span>
			</th>
		</tr>
		[#list statsDatas as statsData]
			<tr>
				<td>
					<span>${statsData.id}</span>
				</td>
				<td>
					<span title="${statsData.statsItem.id}" >${statsData.statsItem.title}</span>
				</td>
				<td>
					<span>${statsData.dataTime}</span>
				</td>
				<td>
					<span>${statsData.numberValue}</span>
				</td>
				<td>
					<span>${statsData.textValue}</span>
				</td>
			</tr>
		[/#list]
	</table>
</body>
</html>
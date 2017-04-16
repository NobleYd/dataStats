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
	<div>
		<a href="${base}/stats_item/add.jhtml" >AddStatsItem</a>
		<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<a href="${base}/stats_data/list.jhtml" >ViewAllData</a>
		<a href="${base}/stats_data/add.jhtml" >AddData</a>
		<a href="${base}/stats_data/batch_add.jhtml" >BatchAddData</a>
	</div>
	<table>
		<tr>
			<th></th>
			<th>
				<span>id</span>
			</th>
			<th>
				<span>partnerId</span>
			</th>
			<th>
				<span>title</span>
			</th>
			<th>
				<span>subTitle</span>
			</th>
			<th>
				<span>showTitle</span>
			</th>
			<th>
				<span>subShowTitle</span>
			</th>
			<th>
				<span>statsValueType</span>
			</th>
			<th>
				<span>statsCycle</span>
			</th>
			<th>
				<span>statsMethod</span>
			</th>
			<th>
				<span>Operation</span>
			</th>
		</tr>
		[#list statsItems as statsItem]
			<tr>
				<td>
					<a href="${base}/stats_item/edit.jhtml?id=${statsItem.id}" >edit</a>
					<a href="${base}/stats_item/delete.jhtml?id=${statsItem.id}" >delete</a>
					<a href="${base}/stats_data/list.jhtml?id=${statsItem.id}" >viewData</a>
				</td>
				<td>
					<span>${statsItem.id}</span>
				</td>
				<td>
					<span>${statsItem.partnerId}</span>
				</td>
				<td>
					<span>${statsItem.title}</span>
				</td>
				<td>
					<span>${statsItem.subTitle}</span>
				</td>
				<td>
					<span>${statsItem.showTitle}</span>
				</td>
				<td>
					<span>${statsItem.subShowTitle}</span>
				</td>
				<td>
					<span>${statsItem.statsValueType.title}</span>
				</td>
				<td>
					<span>
						${statsItem.statsCycle.title}
						[#if 'selfDefine' == statsItem.statsCycle]
						- ${statsItem.divNumber}
						[/#if]
					</span>
				</td>
				<td>
					<span>${statsItem.statsMethod.title}</span>
				</td>
				<td>
					<span><a href="${base}/stats_item/chart/${statsItem.id}.jhtml" target="_blank">statsChart</a></span>
					<span><a href="${base}/stats_data/stats_list.jhtml?statsItemId=${statsItem.id}&statsCycle=${statsItem.statsCycle}" target="_blank">statsList</a></span>
				</td>
			</tr>
		[/#list]
	</table>
</body>
</html>
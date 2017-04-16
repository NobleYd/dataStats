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

	$("input.submit").click(function(){
		$.ajax({
			url:"save.jhtml",
			dataType:"json",
			data:$("#form").serialize(),
			success:function(msg){
				$.message(msg);
				$("input.button.inc").trigger('click');
			}
		});
	});
	
	$("input.button.inc").click(function(){
		var $this = $(this);
		var $incVal = $this.next("input.text").val();
		var $dest = $this.parent("td").prev().find(".text");
		$dest.val( parseInt($dest.val()) + parseInt($incVal) );
	});
	

});
</script>

<style>

	tr{
		height:40px;
	}

</style>


</head>
<body style="width:65%; margin:30px auto" >
	<form style="margin:20px;" id="form" action="save.jhtml" method="post">
		<table id="listTable" >
			<tr>	
				<th>
					<span>statsItemId</span>
				</th>
				<td><input class="text" name="statsItemId" /></td>
			</tr>
			<tr>	
				<th>
					<span>dataTime</span>
				</th>
				<td><input class="text" name="dataTime" /></td>
				<td>
					&nbsp;
					<input type="button" class="button inc" value="增加" />
					<input class="text" style="width:50px;" />
				</td>
			</tr>
			<tr>
				<th>
					<span>numberValue</span>
				</th>
				<td><input class="text" name="numberValue" /></td>
				<td>
					&nbsp;
					<input type="button" class="button inc" value="增加" />
					<input class="text" style="width:50px;" />
				</td>
			</tr>
			<tr>
				<th>
					<span>textValue</span>
				</th>
				<td><input class="text" name="textValue" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" class="button" value="提交" /></td>
			</tr>
		</table>
	<form>
</body>
</html>
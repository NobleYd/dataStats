<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Error - Powered By APP</title>
<meta name="author" content="APP TEAM" />
<meta name="copyright" content="APP" />
<link href="${base}/resources/stats/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/stats/css/error.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="wrap">
		<div class="error">
			<dl>
				<dd>一旦：${Request.exception!'no Exception exist.'}</dd>
				[#if content??]
					<dd>${content}</dd>
				[/#if]
				[#if constraintViolations?has_content]
					[#list constraintViolations as constraintViolation]
						<dd>[${constraintViolation.propertyPath}] ${constraintViolation.message}</dd>
					[/#list]
				[/#if]
				<dd>
					<a href="javascript:;" onclick="window.history.back(); return false;">Return</a>
				</dd>
			</dl>
		</div>
	</div>
</body>
</html>
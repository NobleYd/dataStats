<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<meta name="author" content="APP TEAM" />
<meta name="copyright" content="APP" />
<title></title>

<script type="text/javascript" src="${base}/resources/stats/js/jquery.js"></script>

<script src="${base}/resources/stats/js/echarts/echarts.js" type="text/javascript" charset="utf-8"></script>

<style type="text/css">
	*{
		font-family: "微软雅黑";
	}
</style>


<script type="text/javascript">
$(function(){
	
	$legend = [];
	[#list statsItems as statsItem]
		$legend.push("${(statsItem.showTitle)!''}");
	[/#list]
	
	$statsItemNumber = parseInt("${statsItems?size}");

	//注意下面俩个变量会以相同的次序被加入数据。
	$statsItemTitles = [];
	$statsDataObjs = [];

	$intervalHandler = null;

	$myChart = null;
	$option = null;

	require.config({
        paths: {
            echarts: '${base}/resources/stats/js/echarts'
        }
    });
    
    function prefix2AlignVertical($oldStr, $lineNumber){
    	$n = $lineNumber;
    	if($oldStr != null){
	    	//求当前已经存在几行
	    	$currentLineNumber = $oldStr.split("\n").length;
	    	//因为行index从0计数，所以正常情况oldStr的行数应该为接下来要添加的行的index。
	    	$n = $lineNumber - $currentLineNumber;
    	}
    	$ret = '';
    	for(i = 0 ; i < $n; i++){
    		$ret += "\n";
    	}
    	return $ret;
    }
    
	require(
        [
            'echarts',
            'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
            'echarts/chart/bar'
        ],
        function (ec) {
            //myChart
            $myChart = ec.init(document.getElementById('chartContainer'));
            //auto resize
            window.onresize = $myChart.resize;
            //option
            $option = {
		        title : {
			        text: '${(statsItem.showTitle)!''}',
			        subtext: '${(statsItem.subShowTitle)!''}'
			    },
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {
			            type : 'shadow'
			        }
			    },
			    legend: {
			        data: $legend
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : false,
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    xAxis : [
			        {
			            type : 'category',
			            data : []
			        },
			    ],
			    series : [
			    	[#list statsItems as statsItem]
			        {
			            name:'',
			            type:'line',
			            itemStyle : { normal: {label : {show: true, position: 'top'}, barBorderRadius:5}},
			            data:[]
			        },
			        [/#list]
			    ]
			};
			
			//ajax data
			[#list statsItems as statsItem]

			$.ajax({
				url:"${base}/stats_data/stats.jhtml",
				dataType:"json",
				data:{
					statsItemId:"${(statsItem.id)!''}",
					statsCycle:"${(statsItem.statsCycle)!''}",
					statsMethod:"${(statsItem.statsMethod)!''}",
					startDate:"${startDate}",
					endDate:"${endDate}",
					start:"${start}",
					end:"${end}"
				},
				async:"true",
				success:function(obj){
					$statsItemTitles.push("${statsItem.showTitle}");
					$statsDataObjs.push(obj);
				}
			});
			
			[/#list]
			
			$intervalHandler = setInterval( showData, 1000 );
			
        }//end of function
    );//end of require

	function showData(){
		if( $statsDataObjs.length == $statsItemNumber ){
			//取消重复
			clearInterval($intervalHandler);
			
			//第一列坐标位置用于显示统计项标题。
			$option.xAxis[0].data[0] = $statsItemTitles.join("\n");
			
			//为了理解和编码方便，此处使用俩次循环
			//第一次扫描
			$maxColumnNumber = 0;
			$($statsDataObjs).each(function(i, obj){
				//更新最大列数
				if( $(obj).size() > $maxColumnNumber )
					$maxColumnNumber = $(obj).size();
				
				//更新数据series的name
				$option.series[i].name = $statsItemTitles[i];
				//第一列无数据。
				$option.series[i].data.push('');

			});
			//初始化坐标为空
			for( j = 0; j < $maxColumnNumber; j++ ){
				$option.xAxis[0].data[j + 1] = "";
			}
			//第二次扫描
			$($statsDataObjs).each(function(i, obj){
				//循环最大列数
				for( j = 0; j < $maxColumnNumber; j++ ){
					if(obj[j] != undefined){
						if(i == 0){
							$option.xAxis[0].data[j + 1] += obj[j].dataTime;
						}else{
							$option.xAxis[0].data[j + 1] += "\n" + obj[j].dataTime;
						}
						$option.series[i].data.push( obj[j].value );
					}else{
						if(i == 0){
							$option.xAxis[0].data[j + 1] += "";
						}else{
							$option.xAxis[0].data[j + 1] += "\n";
						}
					}
				}
			});
			//apply data
			$myChart.setOption($option);
			
		}
	}

});//end of $(function(){})
     
</script>
</head>
<body>
	<div id="chartContainer" style="width: 100%; height: 500px;">
	</div>
</body>
</html>

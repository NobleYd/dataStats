<!DOCTYPE html>
<html>
<head>
	
<meta charset="utf-8" />
<title></title>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="js/echarts/echarts.js" type="text/javascript" charset="utf-8"></script>

<style type="text/css">
	*{
		font-family: "微软雅黑";
	}
</style>


<script type="text/javascript">
$(function(){
	
	require.config({
        paths: {
            echarts: 'js/echarts'
        }
    });
     
	require(
        [
            'echarts',
            'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
            'echarts/chart/bar'
        ],
        function (ec) {
            var myChart = ec.init(document.getElementById('chartId'));
            var option = {
			        title : {
				        text: '日注册用户数量',
				        subtext: '按照男女分类'
				    },
				    tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				    },
				    legend: {
				        data:['男性注册用户数量','女性注册用户数量']
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
				            data : ['周一','周二','周三','周四','周五','周六','周日']
				        }
				    ],
				    series : [
				        {
				            name:'男性注册用户数量',
				            type:'bar',
				            itemStyle : { normal: {label : {show: true, position: 'top'}, barBorderRadius:5}},
				            data:[120, 132, 101, 134, 90, 230, 210]
				        },
				        {
				            name:'女性注册用户数量',
				            type:'bar',
				            itemStyle : { normal: {label : {show: true, position: 'top'}, barBorderRadius:5}},
				            data:[320, 302, 301, 334, 390, 330, 320]
				        }
				    ]
				};
            myChart.setOption(option);
            //auto resize
            window.onresize = myChart.resize;
        }//end of function
    );//end of require

});//end of $(function(){})
     
</script>
</head>
<body>
	<div id="chartId" style="width: 100%; height: 800px;">
	</div>
</body>
</html>

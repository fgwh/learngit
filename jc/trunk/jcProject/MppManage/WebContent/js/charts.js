



function DrawPoeBase(id,titles,datas,colorArray,bgColor) {//需要jquery的饼图
	var color = colorArray || ['#339933','#cc0000','#1aadce','#492970','#f28f43','#77a1e5','#ffd966','#a6c96a'];
    var bgc = bgColor || null;
    $('#'+id).highcharts({
        chart: {
            plotBackgroundColor: bgc,
            plotBorderWidth: 0,//null,
            plotShadow: false
        },
        colors:color,
        title: {
            text: titles,
            textStyle:{fontWeight: 'bold'}
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f}%',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
		credits: {  
		  enabled: false  
		},
        series: [{
            type: 'pie',
            name: '所占百分比',
            data: datas
        }]
    });
}


function DrawMobilePie(domID,titles,datas,radiu){//饼图
	require.config({
        paths: {
            echarts: "./js"
        }
    });
	//动态加载js
    require(
        [
            'echarts',
            'echarts/chart/pie'
        ],
        function (ec) {
                var myChart = ec.init(document.getElementById(domID));
                option = {
			title : {
				text: titles,
				//subtext: '纯属虚构',
				x:'center'
			},
			tooltip : {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				orient : 'vertical',
				x : 'left',
				data:[]
			},
			toolbox: {
				show : false
			},
			calculable : true,
			series : [
				{
					type:'pie',
					radius : radiu,
					//center: ['100%', '60%'],
					/*data:[
						{value:335, name:'直接访问'},
						{value:310, name:'邮件营销'},
						{value:234, name:'联盟广告'},
						{value:135, name:'视频广告'},
						{value:1548, name:'搜索引擎'}
					]*/
					data:datas
				}
			]
		};
                    
                myChart.setOption(option);
            }
    );
}


function DrawMobileBar(domID,category,datas,ObjectName){//叠加柱状图
	require.config({
        paths: {
            echarts: './js'
        }
    });
	//动态加载js
    require(
        [
            'echarts',
            'echarts/chart/bar'
        ],
        function (ec) {
                var myChart = ec.init(document.getElementById(domID));
                option = {
                    title : {
                        text: datas[2] || "",
                        x:'center'
                    },
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        },
        formatter: function (params){
            return params[0].name + '<br/>'
                   + params[0].seriesName + ' : ' + params[0].value + '<br/>'
                   + params[1].seriesName + ' : ' + (params[1].value + params[0].value);
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            data : category,
            axisLabel:{margin:20}
        }
    ],
    yAxis : [
        {
            type : 'value',
            boundaryGap: [0, 0.1]
        },

    ],
    series : [
        {
            name:ObjectName[0],
            type:'bar',
            stack: 'sum',
            barCategoryGap: '50%',
            itemStyle: {
                normal: {
                    color: 'green',
                    //barBorderColor: 'green',
                    //barBorderWidth: 0,
                    //barBorderRadius:0,
                    label : {
                        show: true, position: 'insideTop',
                        textStyle:{color:'black'}
                    }
                }
            },
            data:datas[0]
        },
        {
            name:ObjectName[1],
            type:'bar',
            stack: 'sum',
            itemStyle: {
                normal: {
                    color: '#BB3D00',
                    //barBorderColor: 'tomato',
                    //barBorderWidth: 6,
                    //barBorderRadius:0,
                    label : {
                        show: true, 
                        position: 'top',
                        formatter: function (a, b, c) {
                            for (var i = 0, l = option.xAxis[0].data.length; i < l; i++) {
                                if (option.xAxis[0].data[i] == b) {
                                    return option.series[0].data[i] + c;
                                }
                            }
                        },
                        textStyle: {
                            color: 'tomato'
                        }
                    }
                }
            },
            data:datas[1]
        }
    ]
};
                    
                myChart.setOption(option);
            }
    );
}
/*
*参数
* "dom的ID"
* ,["横坐标1","横坐标2","横坐标3"]
* , [[
 {
 name:'邮件营销',
 type:'line',
 data:[120, 132, 101]
 },
 {
 name:'联盟广告',
 type:'line',
 data:[220, 182, 191]
 }]
 ,"图表标题"
 ,["邮件营销","联盟广告"]]
* */
function DrawMobileLine(domID,category,datas){//折线图
    require.config({
        paths: {
            echarts: './js'
        }
    });
    //动态加载js
    require(
        [
            'echarts',
            'echarts/chart/line'
        ],
        function (ec) {
            var myChart = ec.init(document.getElementById(domID));
            option = {
                title : {
                text: datas[1],
                x:'center'
            },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:datas[2],
                    x:"left",
                    y:"25"
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : category
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : datas[0]
            };

            myChart.setOption(option);
        }
    );
}

/**
 *
 * @param domID
 * @param datas属性有{title:"标题",leftTag:['蒸发量','降水量'],xAxis:['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],data:[
				{
					name:'蒸发量',
					type:'bar',
					data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
				},
				{
					name:'降水量',
					type:'bar',
					data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
				}
			]}
 * @constructor
 */
function DrawSingleBar(domID,datas){//单个柱状图
    require.config({
        paths: {
            echarts: './js'
        }
    });
    //动态加载js
    require(
        [
            'echarts',
            'echarts/chart/bar'
        ],
        function (ec) {
            var myChart = ec.init(document.getElementById(domID));
            option = {
                title : {
                    text: datas.title,
                    x:'center'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:datas.leftTag,
                    x:"left",
                    y:"25"
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data :datas.xAxis
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : datas.data
            };

            myChart.setOption(option);
        }
    );
}

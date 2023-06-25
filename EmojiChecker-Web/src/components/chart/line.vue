<template>
	<!--为echarts准备一个具备大小的容器dom-->
        
	    <div id="chartLineBox" style="width: 90%;height: 70vh;"> </div>
</template>
<script>
import * as echarts from "echarts";
	export default {
		mounted() {
            this.chartLine = echarts.init(document.getElementById('chartLineBox'));

// 指定图表的配置项和数据
var option = {
    tooltip: {              //设置tip提示
        trigger: 'axis'
    },
    
    legend: {               //设置区分（哪条线属于什么）
        data:["angry", "confused", "laughing", "sleepy", "worried", "smile", "upside","worried", "beam-face", "raised-eyebrow"]
    },
    color: ['#8AE09F', '#FA6F53'],       //设置区分（每条线是什么颜色，和 legend 一一对应）
    xAxis: {                //设置x轴
        type: 'category',
        boundaryGap: false,     //坐标轴两边不留白
        data: ['00', '02', '04', '06', '08', '10', '12','14','16','18','20','22'],
        name: 'time',           //X轴 name
        nameTextStyle: {        //坐标轴名称的文字样式
            color: '#FA6F53',
            fontSize: 16,
            padding: [0, 0, 0, 20]
        },
        axisLine: {             //坐标轴轴线相关设置。
            lineStyle: {
                color: '#FA6F53',
            }
        }
    },
    yAxis: {
        name: '',
        nameTextStyle: {
            color: '#FA6F53',
            fontSize: 16,
            padding: [0, 0, 10, 0]
        },
        axisLine: {
            lineStyle: {
                color: '#FA6F53',
            }
        },
        type: 'value'
    },
    title:{
        text: '24小时情绪变化记录',
        left: 'center',
        top: 'bottom',
        textStyle: {
            color: 'black'
        }

    },
    series: [
      {
        name: 'angry',
        data:  [0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2],
        type: 'line',               // 类型为折线图
        lineStyle: {                // 线条样式 => 必须使用normal属性
            normal: {
                color: '#8AE09F',
            }
        },
      },
      {
        name: 'confused',
        data:  [0, 1, 2, 4, 4, 4, 8, 8, 8, 10, 10, 10],
        type: 'line',
        lineStyle: {
            normal: {
                color: '#FA6F53',
            }
        },
      },
      {
        name: 'laughing',
        data:  [0, 1, 6, 8, 8, 8, 17, 18, 18, 18, 18, 18],
        type: 'line',
        lineStyle: {
            normal: {
                color: '#FF1493',
            }
        },
      },
      {
        name: 'sleepy',
        data:  [2, 2, 2, 4, 4, 5, 4, 4, 6, 6, 6, 6],
        type: 'line',
        lineStyle: {
            normal: {
                color: '#EE82EE',
            }
        },
      },
      {
        name: 'worried',
        data:  [1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2],
        type: 'line',
        lineStyle: {
            normal: {
                color: '#8A2BE2',
            }
        },
      },
      {
        name: 'smile',
        data:  [12, 13, 16, 16, 16, 16, 16, 19, 22, 22, 25, 28],
        type: 'line',
        lineStyle: {
            normal: {
                color: '#000080',
            }
        },
      },
      {
        name: 'upside',
        data:  [5, 4, 4, 4, 4, 4, 6, 6, 8, 8, 10, 10],
        type: 'line',
        lineStyle: {
            normal: {
                color: '#FFFF00',
            }
        },
      },
      {
        name: 'worried',
        data:  [1, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4],
        type: 'line',
        lineStyle: {
            normal: {
                color: '#3232CD',
            }
        },
      },
      {
        name: 'beam-face',
        data:  [3, 5, 5, 8, 8, 8, 8, 10, 11, 16, 16, 16],
        type: 'line',
        lineStyle: {
            normal: {
                color: '#855E42',
            }
        },
      },
      {
        name: 'raised-eyebrow',
        data:  [3, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6],
        type: 'line',
        lineStyle: {
            normal: {
                color: '#FF7F00',
            }
        },
      }
  ]
};

// 使用刚指定的配置项和数据显示图表。
this.chartLine.setOption(option);
		}
	}
</script>
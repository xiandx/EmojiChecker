<template>
  <div>
    <div>    
            <span>选择时间  </span>
        </div>
        <div>
            <span>开始时间：     </span>
            <input type="text" style="width: 70px;">
            <span>结束时间：     </span>
            <input type="text" style="width: 70px;">
            <button>确定</button>
        </div>
    <div class="echart" id="mychart" :style="myChartStyle"></div>
  </div>
  </template>
  
  <script>
  import * as echarts from "echarts";
  
  export default {
    data() {
      return {
        myChart: {},
        pieData: [
          {
            value: 2,
            name: "angry"
          },
          {
            value: 10,
            name: "confused"
          },
          {
            value: 18,
            name: "laughing"
          },
          {
            value: 6,
            name: "sleepy"
          },
          {
            value: 2,
            name: "worried"
          },
          {
            value: 28,
            name: "smile"
          },
          {
            value: 10,
            name: "upside"
          },
          {
            value: 4,
            name: "worried"
          },
          {
            value: 16,
            name: "beam-face"
          },
          {
            value: 7,
            name: "raised-eyebrow"
          }
        ],
        pieName: [],
        myChartStyle: { float: "left", width: "80%", height: "500px" } //图表样式
      };
    },
    mounted() {
      this.initDate(); //数据初始化
      this.initEcharts();
    },
    methods: {
      initDate() {
        for (let i = 0; i < this.pieData.length; i++) {
          // this.xData[i] = i;
          // this.yData =this.xData[i]*this.xData[i];
          this.pieName[i] = this.pieData[i].name;
        }
      },
      initEcharts() {
        // 饼图
        const option = {
          legend: {
            // 图例
            data: this.pieName,
            right: "5%",
            top: "20%",
            orient: "vertical"
          },
          title: {
            // 设置饼图标题，位置设为顶部居中
            text: "",
            top: "0%",
            left: "center"
          },
          series: [
            {
              type: "pie",
              label: {
                show: true,
                formatter: "{b} : {c} ({d}%)" // b代表名称，c代表对应值，d代表百分比
              },
              radius: "50%", //饼图半径
              data: this.pieData
            }
          ]
        };
        console.log(this.seriesData);
        const optionFree = {
          xAxis: {},
          yAxis: {},
          series: [
            {
              data: this.seriesData,
              type: "line",
              smooth: true
            }
          ]
        };
        this.myChart = echarts.init(document.getElementById("mychart"));
        this.myChart.setOption(option);
        //随着屏幕大小调节图表
        window.addEventListener("resize", () => {
          this.myChart.resize();
        });
      }
    }
  };
  </script>
  
  
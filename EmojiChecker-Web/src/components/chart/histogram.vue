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
      yData: [2, 10, 18, 6, 2, 28, 10, 4, 16, 7], //数据
      myChartStyle: { float: "left", width: "90%", height: "400px" } //图表样式
    };
  },
  mounted() {
    this.initEcharts();
  },
  methods: {
    initEcharts() {
      // 基本柱状图
      const option = {
        xAxis: {
          type: 'category',
          data: ["angry", "confused", "laughing", "sleepy", "worried", "smile", "upside", "worried", "beam-face", "raised-eyebrow"],
          axisLabel: {
            interval:0
          }
        },


        yAxis: {
          type: 'value'
        },
        
        series: [
          {
            type: "bar", //形状为柱状图
            data: this.yData
          }
        ]
      }
      const myChart = echarts.init(document.getElementById("mychart"));
      myChart.setOption(option);
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    }
  }
};
</script>
import { Component, AfterViewInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import * as Chart from 'chart.js';
import * as $ from 'jquery';

@Component({
  selector: 'app-chart-one',
  templateUrl: './chart-one.component.html',
  styleUrls: ['./chart-one.component.css']
})
export class ChartOneComponent implements AfterViewInit {


stompClient;

/* Chart Configuration */
config = {
  type: 'line',
  data: {
    labels: [],
    datasets: [{
      label: 'CPU Utilization',
      color: 'black',
      backgroudColor: 'rgb(255, 255, 255)',
      borderColor: 'rgb(255, 255, 255)',
      data: [],
      fill: false

    }]
  },
  options: {
    responsive: true,
    title: {
      display: true,
      text: 'CPU Used',
      fontColor: 'black',
    },
    tooltips: {
      mode: 'index',
      intersect: false
    },
    hover: {
      mode: 'nearest',
      intersect: true
    },
    scales: {
      xAxes: [{
        display: true,
        type: 'time',
        time: {
          displayFormats: {
            quarter: 'h:mm:ss a'
          }
        },
        scaleLabel: {
          display: true,
          fontColor: 'black',
          labelString: 'Time'
        },
        ticks: {
          fontColor: 'black',
        },
      }],
      yAxes: [{
        display: true,
        scaleLabel: {
          display: true,
          fontColor: 'black',
          labelString: 'Value(%)'
        },
        ticks: {
          fontColor: 'black',
        },
      }]
    }
  }
};

/* After View Initialization Event */
ngAfterViewInit() {
  const that = this;
  const canvas = <HTMLCanvasElement> document.getElementById('lineChart');
  const ctx = canvas.getContext('2d');
  const myLine = new Chart(ctx, this.config);

  /* Configuring WebSocket on Client Side */
  const socket = new SockJS('http://172.23.239.205:8888/live-metrics');
  this.stompClient = Stomp.over(socket);
  this.stompClient.connect({}, function (frame) {
    that.stompClient.subscribe('/topic/cpu-metrics', function (temperature) {
      console.log(temperature.body);
      $('#temperature').text(temperature.body);
      /* Push new data On X-Axis of Chart */
      that.config.data.labels.push(new Date());
      /* Push new data on Y-Axis of chart */
      that.config.data.datasets.forEach(function (dataset) {
        dataset.data.push(temperature.body);
      });
      myLine.update();
    });
  });
}
}

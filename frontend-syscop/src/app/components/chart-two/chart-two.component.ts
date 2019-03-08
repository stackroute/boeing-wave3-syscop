import { Component, AfterViewInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import * as Chart from 'chart.js';
import * as $ from 'jquery';
import * as webstomp from 'webstomp-client';


@Component({
  selector: 'app-chart-two',
  templateUrl: './chart-two.component.html',
  styleUrls: ['./chart-two.component.css']
})
export class ChartTwoComponent implements AfterViewInit {

  constructor() { }

  private stompClient = null;
config = {
  type: 'line',
  data: {
    labels: [],
    datasets: [{
      label: 'NetIO Utilization',
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
      text: 'NetIO Used',
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
          fontColor: 'black', // this here
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
          fontColor: 'black', // this here
        },
      }]
    }
  }
};

/* After View Initialization Event */
ngAfterViewInit() {
  const that = this;
  const canvas = <HTMLCanvasElement> document.getElementById('lineChart2');
  const ctx = canvas.getContext('2d');
  const myLine = new Chart(ctx, this.config);

  /* Configuring WebSocket on Client Side */
  const socket = new WebSocket('ws://13.232.165.99:8095/monitoring-service/live-metrics');
  this.stompClient = Stomp.over(socket);
  this.stompClient.connect({}, function (frame) {
    that.stompClient.subscribe('/topic/netIO-metrics', function (temperature) {
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

import { Component, OnInit, Input, AfterViewInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import * as Chart from 'chart.js';
import * as $ from 'jquery';
import * as webstomp from 'webstomp-client';


@Component({
  selector: 'app-chart-three',
  templateUrl: './chart-three.component.html',
  styleUrls: ['./chart-three.component.css']
})
export class ChartThreeComponent implements AfterViewInit {
  private stompClient = null;
  config = {
    type: 'line',
    data: {
      labels: [],
      datasets: [{
        label: 'Memory Utilization',
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
        text: 'Memory Used',
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

  constructor() { }

  ngAfterViewInit() {
    const that = this;
    const canvas = <HTMLCanvasElement>document.getElementById('lineChart3');
    const ctx = canvas.getContext('2d');
    const myLine = new Chart(ctx, this.config);
    /* Configuring WebSocket on Client Side */
    /* Url of monitoring service */
    const socket = new SockJS('http://13.232.165.99:8095/monitoring-service/live-metrics');
    this.stompClient = webstomp.over(socket);
    this.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe('/topic/mem-metrics', function (temperature) {
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

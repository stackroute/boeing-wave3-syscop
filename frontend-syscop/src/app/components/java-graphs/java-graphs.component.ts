import { Component, OnInit, AfterViewInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import * as Chart from 'chart.js';
import * as $ from 'jquery';

@Component({
  selector: 'app-java-graphs',
  templateUrl: './java-graphs.component.html',
  styleUrls: ['./java-graphs.component.css']
})
export class JavaGraphsComponent implements AfterViewInit {

  constructor() { }

  private stompClient = null;

  config = {
    type: 'line',
    data: {
      labels: [],
      datasets: [{
        label: 'Java1 ',
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
        text: 'Metrics Data',
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


  config2 = {
    type: 'line',
    data: {
      labels: [],
      datasets: [{
        label: 'Java2',
        color: 'black',
        backgroudColor: 'rgb(255, 255, 255)',
        borderColor: 'rgb(255, 255, 255)',
        data: [],
        fill: false,

      }]
    },
    options: {
      responsive: true,
      title: {
        display: true,
        text: 'Metrics Data',
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

  ngAfterViewInit() {
  const canvas = <HTMLCanvasElement> document.getElementById('lineChartjava');
  const ctx = canvas.getContext('2d');
  const myLine = new Chart(ctx, this.config);

  const canvas2 = <HTMLCanvasElement> document.getElementById('lineChartjava2');
  const ctx2 = canvas2.getContext('2d');
  const myLine2 = new Chart(ctx2, this.config2);

  const that = this;

  /* Configuring WebSocket on Client Side */
  const socket = new SockJS('http://13.232.165.99:8018/live-metrics');
  this.stompClient = Stomp.over(socket);
  this.stompClient.connect({}, function (frame) {
    that.stompClient.subscribe('/topic/javaMetric/0', function (temperature) {
      console.log(temperature.body);
       $('#temperature').text(temperature.body);
      /* Push new data On X-Axis of Chart */
       that.config.data.labels.push(new Date());
      /* Push new data on Y-Axis of chart */
      const x = temperature.body.split(' ');
       that.config.data.datasets.forEach(function (dataset) {
         dataset.data.push(parseFloat(x[1]));
       });
       myLine.update();
    });
    that.stompClient.subscribe('/topic/javaMetric/1', function (temperature) {
      console.log(temperature.body);
       $('#temperature').text(temperature.body);
      /* Push new data On X-Axis of Chart */
       that.config2.data.labels.push(new Date());
      /* Push new data on Y-Axis of chart */
       const x = temperature.body.split(' ');
       that.config2.data.datasets.forEach(function (dataset) {
         dataset.data.push(parseFloat(x[1]));
       });
       myLine2.update();
    });
  });

}
}

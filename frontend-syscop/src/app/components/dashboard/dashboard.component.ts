import { Component, AfterViewInit } from '@angular/core';
import { DataService } from 'src/app/service/data.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import * as Chart from 'chart.js';
import * as $ from 'jquery';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements AfterViewInit {
  constructor(private dataService: DataService) { }
  public stompClient = null;
  public username = localStorage.getItem('AuthUsername');
  public url = 'http://13.232.165.99:8018/live-metrics';
  public services = this.dataService.services;

  /*This option is used in ChartJS */
  options = {
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
  };
  /* After View Initialization Event */
  ngAfterViewInit() {
    this.connect(this.url, this.services);
  }

  /*This method connects to socket of backend and renders graph */
  connect(url, services) {

    /*Arrays needed for ChartJS configuration for cpu graph*/
    const cpucanvas = [];
    const cpuctx = [];
    const cpuLine = [];
    const cpudata = [];

    /*Arrays needed for ChartJS configuration for netIO graph*/
    const netIOcanvas = [];
    const netIOctx = [];
    const netIOLine = [];
    const netIOdata = [];

    /*Arrays needed for ChartJS configuration for memory graph*/
    const memcanvas = [];
    const memctx = [];
    const memLine = [];
    const memdata = [];

    /*Arrays needed for ChartJS configuration for memory graph*/
    for (let i = 0; i < services.length; i++) {
      cpudata[i] = {
        labels: [],
        datasets: [{
          label: 'CPU Utilization',
          color: 'black',
          backgroudColor: 'rgb(255, 255, 255)',
          borderColor: 'rgb(255, 255, 255)',
          data: [],
          fill: false
        }]
      };

      /*Datasets and labels needed for ChartJS configuration for cpu graph*/
      netIOdata[i] = {
        labels: [],
        datasets: [{
          label: 'netIO Utilization',
          color: 'black',
          backgroudColor: 'rgb(255, 255, 255)',
          borderColor: 'rgb(255, 255, 255)',
          data: [],
          fill: false
        }]
      };

      /*Datasets and labels needed for ChartJS configuration for netIO graph*/
      memdata[i] = {
        labels: [],
        datasets: [{
          label: 'Memory Utilization',
          color: 'black',
          backgroudColor: 'rgb(255, 255, 255)',
          borderColor: 'rgb(255, 255, 255)',
          data: [],
          fill: false
        }]
      };

    }

    /*This loop renders cpu graph for all the registered services */
    for (let i = 0; i < services.length; i++) {
      cpucanvas[i] = <HTMLCanvasElement>document.getElementById('lineChart0' + i);
      cpuctx[i] = cpucanvas[i].getContext('2d');
      cpuLine[i] = new Chart(cpuctx[i], { type: 'line', data: cpudata[i], options: this.options });

      netIOcanvas[i] = <HTMLCanvasElement>document.getElementById('lineChart1' + i);
      netIOctx[i] = netIOcanvas[i].getContext('2d');
      netIOLine[i] = new Chart(netIOctx[i], { type: 'line', data: netIOdata[i], options: this.options });

      memcanvas[i] = <HTMLCanvasElement>document.getElementById('lineChart2' + i);
      memctx[i] = memcanvas[i].getContext('2d');
      memLine[i] = new Chart(memctx[i], { type: 'line', data: memdata[i], options: this.options });

      const that = this;
      /* Configuring WebSocket on Client Side */
      const socket = new SockJS(url);
      this.stompClient = Stomp.over(socket);
      this.stompClient.connect({}, function (frame) {
        that.stompClient.subscribe(`/topic/graphMetrics/${that.username}`, function (metric) {
          console.log(JSON.parse(metric.body).cpu);
          $('#metric').text(JSON.parse(metric.body).cpu);
          /* Push new data On X-Axis of Chart */
          cpudata[i].labels.push(new Date());
          /* Push new data on Y-Axis of chart */
          cpudata[i].datasets.forEach(function (dataset) {
            dataset.data.push(parseFloat(JSON.parse(metric.body).cpu));
          });
            console.log(JSON.parse(metric.body).netIO);
            $('#metric').text(JSON.parse(metric.body).netIO);
            /* Push new data On X-Axis of Chart */
            netIOdata[i].labels.push(new Date());
            /* Push new data on Y-Axis of chart */
            netIOdata[i].datasets.forEach(function (dataset) {
              dataset.data.push(parseFloat(JSON.parse(metric.body).netIO));
            });

              console.log(JSON.parse(metric.body).memory);
              $('#metric').text(JSON.parse(metric.body).memory);
              /* Push new data On X-Axis of Chart */
              memdata[i].labels.push(new Date());
              /* Push new data on Y-Axis of chart */
              memdata[i].datasets.forEach(function (dataset) {
                dataset.data.push(parseFloat(JSON.parse(metric.body).memory));
              });
              cpuLine[i].update();
              netIOLine[i].update();
              memLine[i].update();
            });
      });
    }
  }
}

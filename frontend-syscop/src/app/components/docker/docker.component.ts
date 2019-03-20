import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { DataService } from 'src/app/service/data.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSelect } from '@angular/material';
import { UserService } from 'src/app/service/user.service';
import { Data } from '@angular/router';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import * as Chart from 'chart.js';
import * as $ from 'jquery';



@Component({
  selector: 'app-docker',
  templateUrl: './docker.component.html',
  styleUrls: ['./docker.component.css']
})
export class DockerComponent implements OnInit, AfterViewInit {

  dockerform: FormGroup;
  idx;
  socket: any;
  constructor(private dataService: DataService, private userService: UserService) { }
  public stompClient = null;
  public username = localStorage.getItem('AuthUsername');
  public url = 'http://13.232.165.99:8018/live-metrics';
  public services = this.dataService.services;
  @ViewChild('select') select: MatSelect;
  cpuLine;
  netIOLine;
  memLine;



  /*This option is used in ChartJS */
  private options = {
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
  ngOnInit() {
    this.dockerform = new FormGroup({
      serviceName: new FormControl(''),
    });
  }
  ngAfterViewInit() {

  }
  /*This method connects to socket of backend and renders graph */
  getGraph() {
    /*Arrays needed for ChartJS configuration for cpu graph*/
    let cpucanvas;
    let cpuctx;

    /*Arrays needed for ChartJS configuration for netIO graph*/
    let netIOcanvas;
    let netIOctx;

    /*Arrays needed for ChartJS configuration for memory graph*/
    let memcanvas;
    let memctx;


    /*Arrays needed for ChartJS configuration for memory graph*/
    const cpudata = {
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
    const netIOdata = {
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
    const memdata = {
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

    if (this.cpuLine !== undefined) {
      this.cpuLine.destroy();
      this.netIOLine.destroy();
      this.memLine.destroy();
    }
    /*This loop renders cpu graph for all the registered services */
    cpucanvas = <HTMLCanvasElement>document.getElementById('lineChart0');
    cpuctx = cpucanvas.getContext('2d');
    this.cpuLine = new Chart(cpuctx, { type: 'line', data: cpudata, options: this.options });

    netIOcanvas = <HTMLCanvasElement>document.getElementById('lineChart1');
    netIOctx = netIOcanvas.getContext('2d');
    this.netIOLine = new Chart(netIOctx, { type: 'line', data: netIOdata, options: this.options });

    memcanvas = <HTMLCanvasElement>document.getElementById('lineChart2');
    memctx = memcanvas.getContext('2d');
    this.memLine = new Chart(memctx, { type: 'line', data: memdata, options: this.options });

    const that = this;

    /* Configuring WebSocket on Client Side */
    this.socket = new SockJS(this.url);
    this.stompClient = Stomp.over(this.socket);
    this.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe(`/topic/graphMetrics/${that.username}`, function (metric) {
        if (JSON.parse(metric.body).serviceName === that.dockerform.get('serviceName').value) {
          console.log(JSON.parse(metric.body).cpu);
          $('#metric').text(JSON.parse(metric.body).cpu);
          /* Push new data On X-Axis of Chart */
          cpudata.labels.push(new Date());
          /* Push new data on Y-Axis of chart */
          cpudata.datasets.forEach(function (dataset) {
            dataset.data.push(parseFloat(JSON.parse(metric.body).cpu));
          });
          console.log(JSON.parse(metric.body).netIO);
          $('#metric').text(JSON.parse(metric.body).netIO);
          /* Push new data On X-Axis of Chart */
          netIOdata.labels.push(new Date());
          /* Push new data on Y-Axis of chart */
          netIOdata.datasets.forEach(function (dataset) {
            dataset.data.push(parseFloat(JSON.parse(metric.body).netIO));
          });

          console.log(JSON.parse(metric.body).memory);
          $('#metric').text(JSON.parse(metric.body).memory);
          /* Push new data On X-Axis of Chart */
          memdata.labels.push(new Date());
          /* Push new data on Y-Axis of chart */
          memdata.datasets.forEach(function (dataset) {
            dataset.data.push(parseFloat(JSON.parse(metric.body).memory));
          });
          that.cpuLine.update();
          that.netIOLine.update();
          that.memLine.update();
        }
      });
    });
  }
}

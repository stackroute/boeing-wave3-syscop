import { Component, AfterViewInit, OnInit, ViewChild, } from '@angular/core';
import { DataService } from 'src/app/service/data.service';
import * as Chart from 'chart.js';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSelect } from '@angular/material';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css']
})

export class DataComponent implements OnInit, AfterViewInit {
  form: FormGroup;

  constructor(private dataService: DataService , private userService: UserService) { }
  public stompClient = null;
  public username = localStorage.getItem('AuthUsername');
  public url = 'https://13.232.165.99:8018/api/v1/history';
  public services = this.dataService.services;
  @ViewChild('select') select: MatSelect;


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
    this.form = new FormGroup({
      serviceName: new FormControl('', ),
      fromDate: new FormControl('', ),
      toDate: new FormControl('', )
    });
  }
  ngAfterViewInit() {
    this.select.optionSelectionChanges.subscribe(res => {
      console.log(res);
    });
    console.log(this.form.value);
    this.userService.getMonitoringData(this.form.value);
    this.connect(this.url);
  }
  connect(url) {
    /*Arrays needed for ChartJS configuration for cpu graph*/
    let cpucanvas;
    let cpuctx;
    let cpuLine;
    let cpudata;
    /*Arrays needed for ChartJS configuration for netIO graph*/
    let netIOcanvas;
    let netIOctx;
    let netIOLine;
    let netIOdata;

    /*Arrays needed for ChartJS configuration for memory graph*/
    let memcanvas;
    let memctx;
    let memLine;
    let memdata;
    /*Arrays needed for ChartJS configuration for memory graph*/
    cpudata = {
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
    netIOdata = {
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
    memdata = {
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
    cpucanvas = <HTMLCanvasElement>document.getElementById('lineChart0');
    cpuctx = cpucanvas.getContext('2d');
    cpuLine = new Chart(cpuctx, { type: 'line', data: cpudata, options: this.options });

    netIOcanvas = <HTMLCanvasElement>document.getElementById('lineChart1');
    netIOctx = netIOcanvas.getContext('2d');
    netIOLine = new Chart(netIOctx, { type: 'line', data: netIOdata, options: this.options });

    memcanvas = <HTMLCanvasElement>document.getElementById('lineChart2');
    memctx = memcanvas.getContext('2d');
    memLine = new Chart(memctx, { type: 'line', data: memdata, options: this.options });
    /* Push new data On X-Axis of Chart */
    cpudata.labels.push(new Date());
    /* Push new data on Y-Axis of chart */
    cpudata.datasets.forEach(function (dataset) {
      dataset.data.push();
    });
    /* Push new data On X-Axis of Chart */
    netIOdata.labels.push(new Date());
    /* Push new data on Y-Axis of chart */
    netIOdata.datasets.forEach(function (dataset) {
      dataset.data.push();
    });

    /* Push new data On X-Axis of Chart */
    memdata.labels.push(new Date());
    /* Push new data on Y-Axis of chart */
    memdata.datasets.forEach(function (dataset) {
      dataset.data.push();
    });
    cpuLine.update();
    netIOLine.update();
    memLine.update();
  }

}

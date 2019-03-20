import { Component, AfterViewInit, OnInit, ViewChild, } from '@angular/core';
import { DataService } from 'src/app/service/data.service';
import * as Chart from 'chart.js';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSelect } from '@angular/material';
import { UserService } from 'src/app/service/user.service';
import { Data } from '@angular/router';
import { connect } from 'net';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css']
})

export class DataComponent implements OnInit, AfterViewInit {
  form: FormGroup;
  idx;
  constructor(private dataService: DataService, private userService: UserService) { }
  public stompClient = null;
  public username = localStorage.getItem('AuthUsername');
  public url = 'http://13.232.165.99:8018/api/v1/history';
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
    this.form = new FormGroup({
      serviceName: new FormControl(''),
      fromDate: new FormControl(''),
      toDate: new FormControl('')
    });
  }
  ngAfterViewInit() {
    this.select.optionSelectionChanges.subscribe(res => {
      console.log(res);
    });
  }
  onSubmit() {
    const cpuudata = [];
    const memmdata = [];
    const time = [];
    const netIOOdata = [];
    console.log(this.form.value);
    this.userService.getMonitoringData(this.form.value).subscribe((res: []) => {
      res.forEach((y: Data ) => {
          cpuudata.push(parseFloat(y.cpu));
          memmdata.push(parseFloat(y.mem));
          time.push(y.time);
      });
      console.log(res);
      console.log(cpuudata);
      console.log(memmdata);
      this.connect(cpuudata, memmdata, time, netIOOdata);
    });
  }
  connect(cpuudata, memmdata, time, netIOOdata) {
    /*Arrays needed for ChartJS configuration for cpu graph*/
    let cpucanvas;
    let cpuctx;
    let cpudata;
    /*Arrays needed for ChartJS configuration for netIO graph*/
    let netIOcanvas;
    let netIOctx;
    let netIOdata;
    /*Arrays needed for ChartJS configuration for memory graph*/
    let memcanvas;
    let memctx;
    let memdata;
    /*Arrays needed for ChartJS configuration for cpu graph*/
    cpudata = {
      labels: time,
      datasets: [{
        label: 'CPU Utilization',
        color: 'black',
        backgroudColor: 'rgb(255, 255, 255)',
        borderColor: 'rgb(255, 255, 255)',
        data: cpuudata,
        fill: false
      }]
    };
    /*Datasets and labels needed for ChartJS configuration for cpu graph*/
    netIOdata = {
      labels: time,
      datasets: [{
        label: 'netIO Utilization',
        color: 'black',
        backgroudColor: 'rgb(255, 255, 255)',
        borderColor: 'rgb(255, 255, 255)',
        data: netIOOdata,
        fill: false
      }]
    };
    /*Datasets and labels needed for ChartJS configuration for memory graph*/
    memdata = {
      labels: time,
      datasets: [{
        label: 'Memory Utilization',
        color: 'black',
        backgroudColor: 'rgb(255, 255, 255)',
        borderColor: 'rgb(255, 255, 255)',
        data: memmdata,
        fill: false
      }]
    };
    if (this.cpuLine !== undefined) {
      this.cpuLine.destroy();
      this.netIOLine.destroy();
      this.memLine.destroy();
    }
    cpucanvas = <HTMLCanvasElement>document.getElementById('lineChart0');
    cpuctx = cpucanvas.getContext('2d');
    this.cpuLine = new Chart(cpuctx, { type: 'line', data: cpudata, options: this.options });

    netIOcanvas = <HTMLCanvasElement>document.getElementById('lineChart1');
    netIOctx = netIOcanvas.getContext('2d');
    this.netIOLine = new Chart(netIOctx, { type: 'line', data: netIOdata, options: this.options });

    memcanvas = <HTMLCanvasElement>document.getElementById('lineChart2');
    memctx = memcanvas.getContext('2d');
    this.memLine = new Chart(memctx, { type: 'line', data: memdata, options: this.options });
    this.cpuLine.update();
    this.netIOLine.update();
    this.memLine.update();

  }

}

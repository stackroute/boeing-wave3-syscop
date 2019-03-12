import { Component, OnInit, Input, AfterViewInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import * as Chart from 'chart.js';
import * as $ from 'jquery';

@Component({
  selector: 'app-chart-four',
  templateUrl: './chart-four.component.html',
  styleUrls: ['./chart-four.component.css']
})
export class ChartFourComponent implements OnInit, AfterViewInit {
  stompClient: any;
  config: any;
  constructor(private us: UserService) { }

  ngOnInit() {
  }
  ngAfterViewInit() {
    const that = this;
    const canvas = <HTMLCanvasElement> document.getElementById('lineChart');
    const ctx = canvas.getContext('2d');
    const myLine = new Chart(ctx, this.config);
  /* Configuring WebSocket on Client Side */
    const socket = new SockJS('http://13.232.165.99:8018/live-metrics');
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, function () {
      that.stompClient.subscribe('/topic/username', function (temperature) {
        console.log(temperature.body);
      });
    });
  }
}


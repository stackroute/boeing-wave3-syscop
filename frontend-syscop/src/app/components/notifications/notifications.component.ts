import { Component, OnInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import * as $ from 'jquery';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit {
  stompClient: any;
  notifications = [];

  constructor() { }

  ngOnInit() {
  const that = this;
  const socket = new SockJS('http://13.232.165.99:8020/live-temperature');
  this.stompClient = Stomp.over(socket);
  this.stompClient.connect({}, function (frame) {
    that.stompClient.subscribe('/topic/temperature', function (temperature) {
      console.log(temperature.body);
      $('#temperature').text(temperature.body);
      that.addMessage(temperature.body);
    });
  });
  }
  addMessage(input) {
    this.notifications.push(input);
  }
}

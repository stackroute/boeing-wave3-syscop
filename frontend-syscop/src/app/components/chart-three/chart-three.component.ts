import { Component, OnInit, Input } from '@angular/core';
import * as $ from 'jquery';
import * as CanvasJS from 'src/canvasjs-2.3.1/canvasjs.min.js';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-chart-three',
  templateUrl: './chart-three.component.html',
  styleUrls: ['./chart-three.component.css']
})
export class ChartThreeComponent implements OnInit {

  @Input() data$: Observable<any>;

  constructor(private us: UserService) { }

  ngOnInit() {
    const dataPoints = [];
    let dpsLength = 50;
    const chart = new CanvasJS.Chart('chartContainer3', {
      theme: 'light1',
      exportEnabled: true,
      title: {
        text: 'Live Monitoring of Memory'
      },
      data: [{
        type: 'spline',
        dataPoints: dataPoints,
      }]
    });
    $.getJSON('http://172.23.239.205:8018/api/v1/data', function (data) {
      $.each(data, function () {
        dataPoints.push({
          x: dpsLength,
          y: parseFloat(data.mem) });
        // console.log('mem' + data.mem);
      });
      dpsLength = dataPoints.length;
      chart.render();
      updateChart();
    });
    function updateChart() {
      $.getJSON('http://172.23.239.205:8018/api/v1/data', function (data) {
        $.each(data, function () {
          dataPoints.push({
            x: dpsLength,
            y: parseFloat(data.mem)
          });
          if (dpsLength > 20) {
            dataPoints.shift();
          }
          // console.log('netIO' + data.mem);
          dpsLength++;
        });
        chart.render();
        setTimeout(function () { updateChart(); }, 6000);
      });
    }
  }
}

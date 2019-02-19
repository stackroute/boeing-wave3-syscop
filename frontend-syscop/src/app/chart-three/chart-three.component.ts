import { Component, OnInit, Input } from '@angular/core';
import * as $ from 'jquery';
import * as CanvasJS from 'src/canvasjs-2.3.1/canvasjs.min.js';
import { UserService } from '../service/user.service';
import { Observable } from 'rxjs';

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
    let dpsLength = 0;
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
    $.getJSON('http://172.23.239.170:9999/api/v1/data', function (data) {
      $.each(data, function () {
        dataPoints.push({ y: parseFloat(data.mem) });
        // console.log('mem' + data.mem);
      });
      dpsLength = dataPoints.length;
      chart.render();
      updateChart();
    });
    function updateChart() {
      $.getJSON('http://172.23.239.170:9999/api/v1/data', function (data) {
        $.each(data, function () {
          dataPoints.push({
            y: parseFloat(data.mem)
          });
          // console.log('netIO' + data.mem);
          dpsLength++;
        });

        if (dataPoints.length > 10) {
          dataPoints.shift();
        }
        chart.render();
        setTimeout(function () { updateChart(); }, 1000);
      });
    }
  }
}

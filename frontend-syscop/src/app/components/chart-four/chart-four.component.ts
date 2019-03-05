import { Component, OnInit, Input } from '@angular/core';
import * as $ from 'jquery';
import * as CanvasJS from 'src/canvasjs-2.3.1/canvasjs.min.js';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-chart-four',
  templateUrl: './chart-four.component.html',
  styleUrls: ['./chart-four.component.css']
})
export class ChartFourComponent implements OnInit {

  @Input() data$: Observable<any>;

  constructor(private us: UserService) { }

  ngOnInit() {
    const dataPoints = [];
    let dpsLength = 0;
    const chart = new CanvasJS.Chart('chartContainer4', {
      theme: 'light1',
      exportEnabled: true,
      title: {
        text: 'Live Monitoring of netIO'
      },
      data: [{
        type: 'spline',
        dataPoints: dataPoints,
      }]
    });
    /*Url of datasource */
    $.getJSON('http://172.23.239.205:8888/api/v1/data', function (data) {
      $.each(data, function () {
        dataPoints.push({y: parseFloat(data.netIO)});
        console.log('netIO' + data.netIO);
      });
      dpsLength = dataPoints.length;
      chart.render();
      updateChart();
    });
    /*Url of datasource */
    function updateChart() {
      $.getJSON('http://172.23.239.205:8888/api/v1/data' , function (data) {
        $.each(data, function () {
          dataPoints.push({
            y: parseFloat(data.netIO)
          });
          console.log('netIO' + data.netIO);
          dpsLength++;
        });

        if (dataPoints.length > 10) {
          dataPoints.shift();
        }
        chart.render();
        setTimeout(function () { updateChart(); } , 1000);
        });
        }
  }
}


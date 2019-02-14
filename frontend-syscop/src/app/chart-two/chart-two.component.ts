import { Component, OnInit } from '@angular/core';
import * as CanvasJS from 'src/canvasjs-2.3.1/canvasjs.min.js';

@Component({
  selector: 'app-chart-two',
  templateUrl: './chart-two.component.html',
  styleUrls: ['./chart-two.component.css']
})
export class ChartTwoComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    const chart = new CanvasJS.Chart('chartContainer2', {
      theme: 'light2',
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: 'Monthly Expense'
      },
      data: [{
        type: 'pie',
        showInLegend: true,
        toolTipContent: '<b>{name}</b>: ${y} (#percent%)',
        indexLabel: '{name} - #percent%',
        dataPoints: [
          { y: 450, name: 'Food' },
          { y: 120, name: 'Insurance' },
          { y: 300, name: 'Traveling' },
          { y: 800, name: 'Housing' },
          { y: 150, name: 'Education' },
          { y: 150, name: 'Shopping' },
          { y: 250, name: 'Others' }
        ]
      }]
    });

    chart.render();
  }

}

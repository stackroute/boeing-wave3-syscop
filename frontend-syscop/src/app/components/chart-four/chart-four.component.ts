import { Component, OnInit, Input } from '@angular/core';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-chart-four',
  templateUrl: './chart-four.component.html',
  styleUrls: ['./chart-four.component.css']
})
export class ChartFourComponent implements OnInit {
  constructor(private us: UserService) { }

  ngOnInit() {
  }
}


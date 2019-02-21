import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
@Component({
  selector: 'app-initial-view',
  templateUrl: './initial-view.component.html',
  styleUrls: ['./initial-view.component.css']
})

export class InitialViewComponent implements OnInit {

  tiles: object;
  constructor(private breakpointObserver: BreakpointObserver) { }

  ngOnInit() {
    this.tiles = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
      map(({ matches }) => {
        if (matches) {
          return [
            {  cols: 2, rows: 2, color: 'rgba(20,30,45,1)' },
            {  cols: 2, rows: 2, color: 'white' },
          ];
        } else {
          return [
            {  cols: 1, rows: 1, color: 'rgba(20,30,45,1)' },
            {  cols: 1, rows: 1, color: 'white' },
          ];
        }
      })
    );
  }
}



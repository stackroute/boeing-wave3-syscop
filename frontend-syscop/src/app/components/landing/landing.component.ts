import { Component, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';
import * as $ from 'jquery';
import { WOW } from 'wowjs/dist/wow.min';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements AfterViewInit {

  constructor(public router: Router) { }

  ngAfterViewInit() {
    $(function() {
      new WOW().init();
    });
  }
  register() {
    this.router.navigateByUrl('signup');
  }
  login() {
    this.router.navigateByUrl('login');
  }

}

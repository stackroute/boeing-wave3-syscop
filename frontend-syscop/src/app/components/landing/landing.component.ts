import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WOW } from 'wowjs/dist/wow.min';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

  constructor(public router: Router) { }

  ngOnInit() {
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

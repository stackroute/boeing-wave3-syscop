import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-app-registration',
  templateUrl: './app-registration.component.html',
  styleUrls: ['./app-registration.component.css']
})
export class AppRegistrationComponent implements OnInit {

  username = localStorage.getItem('AuthUsername');
  constructor() { }

  ngOnInit() {
  }

}

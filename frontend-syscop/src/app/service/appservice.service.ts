import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Application } from '../models/application';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppserviceService {

  public username = localStorage.getItem('AuthUsername');
  public url = 'http://13.232.165.99:8095/appRegistration-service/api/v2/syscop/appregistration';


  constructor(private http: HttpClient) { }
  /* saveUser method saves data into the appregistration service service */
  saveApplication(app) {
    const obj = {
      'userName': this.username,
      'applications': [
        app
      ]
    };
    return this.http.post(`${this.url}/new`, obj, { responseType: 'text' }).subscribe((data) => {
      console.log(obj);
    });

  }
  /*getUserBoard method fetches data from login service*/
  getApplications(): Observable<Application> {
    return this.http.get<Application>(`${this.url}/showApp/username/${this.username}`);
  }
  editApplication(app) {
    const obj = {
    'userName': this.username,
    'applications': [
      app
    ]
  };
    return this.http.put(`${this.url}/updateApp`, obj, { responseType: 'text' }).subscribe((data) => {
    console.log(obj);
  });

  console.log(obj);
  }
  deleteApp(app) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: {
        'userName': this.username,
        'applications': [
        app
      ]
      },
    };
    return this.http.delete(`${this.url}/deleteApp`, options);

  }
}

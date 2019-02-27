import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Application } from '../models/application';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppserviceService {

  public username = localStorage.getItem('AuthUsername');
  public url = 'http://172.23.239.88:8088/api/v2/syscop/appregistration';


  constructor(private http: HttpClient) { }
  /* saveUser method saves data into the appregistration service service */
  saveApplication(app) {
    const obj = {
      'userName': this.username,
      'applications': [
        app
      ]
    };
    this.http.post(`${this.url}/new`, obj, { responseType: 'text' }).subscribe((data) => {
      console.log(obj);
    });

    console.log(obj);
  }
  /*getUserBoard method fetches data from login service*/
  getApplications(): Observable<Application> {
    return this.http.get<Application>(`${this.url}/showApp/username/${this.username}` );
  }
}

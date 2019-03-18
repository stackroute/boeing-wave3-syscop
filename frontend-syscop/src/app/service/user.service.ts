import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  // public url = 'http://13.232.165.99:8096/register-service/api/v1/register';
  // public datarUrl: 'http://13.232.165.99:8018/api/v1/data';

  /*this url is used for registration service */
  public registerurl = 'https://13.232.165.99:8095/register-service/api/v1/register';
  /*the data url is used for getting data from monitoring service */
  public dataUrl = 'https://13.232.165.99:8018/api/v1/history';
  public username = localStorage.getItem('AuthUsername');

  constructor(private http: HttpClient) { }
  /* saveUser method saves data into the registration service */
  saveUser(user: any) {
    this.http.post(this.registerurl, user, {responseType: 'text'} ).subscribe((data) => {
               console.log(data);
     });
    console.log(user);
  }
  /* getData method gets data from monitoring service */
  getMonitoringData(obj) {
    return this.http.post(`${this.dataUrl}/${this.username}+${obj.serviceName}`, obj);
  }

}

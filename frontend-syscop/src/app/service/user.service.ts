import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  /*this url is used for registration service */
  public url = 'http://13.232.165.99:8095/register-service/api/v1/register';
  /*the userUrlrl is used for login service */
  userUrl: string;


  constructor(private http: HttpClient) { }
  /* saveUser method saves data into the registration service */
  saveUser(user: any) {
    this.http.post(this.url, user, {responseType: 'text'} ).subscribe((data) => {
               console.log(data);
     });

    console.log(user);
  }

  /*getUserBoard method fetches data from login service*/
  getUserBoard(): Observable<string> {
    return this.http.get(this.userUrl, { responseType: 'text' });
  }
  getData() {
    return this.http.get('http://172.23.239.170:9999/api/v1/data');
  }

}

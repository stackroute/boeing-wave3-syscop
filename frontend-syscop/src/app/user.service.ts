import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public url = 'http://localhost:8095/register-service/api/v1/register';



  constructor(private http: HttpClient) { }
  saveUser(user: any) {
    this.http.post(this.url, user, {responseType: 'text'} ).subscribe((data) => {
               console.log(data);
     });

    console.log(user);
  }

  // getUsers(): Observable<User[]> {
  //   return this
  //          .http
  //          .get<User[]>(`${this.url}/users`);
  // }
  // deleteuser(id) {
  //   return this.http.delete(`${this.url}/user/${id}`, {responseType: 'text'});

  // }
  // updateUser(): Observable<Object> {
  //   return this.http.put(`${this.url}/user`, User, {responseType: 'text'}) ;
  // }

}

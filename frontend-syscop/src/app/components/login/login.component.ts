
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthLoginInfo } from 'src/app/auth/login-info';
import { AuthService } from 'src/app/auth/auth.service';
import { TokenStorageService } from 'src/app/auth/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  hide = true;
  form: FormGroup;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo: AuthLoginInfo;
  msg: string;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
    }
    this.form = new FormGroup({
            username: new FormControl('', [Validators.required ]),
            password: new FormControl('', [Validators.required ]),
    });
  }

  onSubmit() {
    if (this.form.invalid) {
            console.log('form is invalid ');
            return;
          }
          console.log('form.value : ', this.form.value);
     console.log(this.form);

    this.loginInfo = new AuthLoginInfo(
      this.form.controls['username'].value,
      this.form.controls['password'].value);

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        this.router.navigateByUrl('home');
      },
      error => {
        alert('Username or Password Incorrect.Try again');
        console.log(error);
        this.errorMessage = error.message;
        this.isLoginFailed = true;
      }
    );
  }
  onCancel() {
    this.router.navigateByUrl('');
  }
}

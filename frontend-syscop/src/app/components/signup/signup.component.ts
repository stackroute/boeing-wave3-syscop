import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { UserService } from 'src/app/service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  hide = true;
  myForm: FormGroup;
  constructor(private userService: UserService, public router: Router) { }

  ngOnInit() {
    this.myForm = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(20) ]),
      password: new FormControl('', [Validators.required ]),
      phoneNumber: new FormControl('', [Validators.required, ]),
      email: new FormControl('', [Validators.required, Validators.email]),
      company: new FormControl('', [Validators.required ]),
      dob: new FormControl('', [Validators.required ]),
   });
  }
  onSubmit() {
    // stop print if form is invalid
    if (this.myForm.invalid) {
      alert('Form is Invalid ');
      return;
    }
    console.log('registerForm.value : ', this.myForm.value);
    this.userService.saveUser(this.myForm.value);
    this.router.navigateByUrl('login');
  }
  onCancel() {
    this.router.navigateByUrl('');
  }

}

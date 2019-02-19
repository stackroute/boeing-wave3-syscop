import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';
import { FormControl, Validators, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  hide;
  myForm: FormGroup;
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.myForm = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(20) ]),
      password: new FormControl('', [Validators.required ]),
      phoneNumber: new FormControl('', [Validators.required, Validators.pattern('[0-9]{10}')]),
      email: new FormControl('', [Validators.required, Validators.email]),
      company: new FormControl('', [Validators.required ]),
      dob: new FormControl('', [Validators.required ]),
   });
  }
  onSubmit() {
    // stop print if form is invalid
    if (this.myForm.invalid) {
      console.log('register form is invalid ');
      return;
    }
    console.log('registerForm.value : ', this.myForm.value);
    this.userService.saveUser(this.myForm.value);
  }

}

import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder } from '@angular/forms';
import { AppserviceService } from 'src/app/service/appservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-mydialog',
  templateUrl: './mydialog.component.html',
  styleUrls: ['./mydialog.component.css']
})
export class MydialogComponent implements OnInit {
  appGroup: FormGroup;

  constructor(public dialogRef: MatDialogRef<MydialogComponent>, private appService: AppserviceService, private router: Router ) { }

  ngOnInit() {
    this.appGroup = new FormGroup({
      applicationName: new FormControl('', [Validators.required ]),
      applicationType: new FormControl('', Validators.required),
      ipAddress: new FormControl('', [Validators.required ]),
      services: new FormArray([this.addServiceGroup()])
   });
  }
  addServiceGroup() {
    return new FormGroup({
      serviceName: new FormControl('', [Validators.required ]),
      serviceType: new FormControl('', Validators.required ),
      portNumber: new FormControl('', [Validators.required , ]),
    });
  }
  addService() {
    this.serviceArray.push(this.addServiceGroup());
  }
  removeService(index) {
    this.serviceArray.removeAt(index);
  }
  get serviceArray() {
    return <FormArray>this.appGroup.get('services');
  }
  onSubmit() {
    this.appService.saveApplication(this.appGroup.value);
    console.log(this.appGroup.value);
    this.dialogRef.close();
  }
  onCancel() {
    this.dialogRef.close();
  }
}

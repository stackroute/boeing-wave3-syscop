import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { MydialogComponent } from '../mydialog/mydialog.component';
import { AppserviceService } from 'src/app/service/appservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-editdialog',
  templateUrl: './editdialog.component.html',
  styleUrls: ['./editdialog.component.css']
})
export class EditdialogComponent implements OnInit {

  appGroup: FormGroup;

  constructor(public dialogRef: MatDialogRef<MydialogComponent>, private appService: AppserviceService,
    @Inject(MAT_DIALOG_DATA) public data: any, private router: Router) { }

  ngOnInit() {
    this.appGroup = new FormGroup({
      applicationName: new FormControl('', [Validators.required ]),
      applicationType: new FormControl('', Validators.required),
      ipAddress: new FormControl('', [Validators.required ]),
      services: new FormArray([this.addServiceGroup()])
   });
   for (let i = 1 ; i < this.data.services.length; i++) {
        this.addService();
   }
   this.appGroup.patchValue({
     applicationName: this.data.applicationName,
     applicationType: this.data.applicationType,
     ipAddress: this.data.ipAddress,
     services: this.data.services
   });
   console.log(this.data.services);
  }
  addServiceGroup() {
    return new FormGroup({
      serviceName: new FormControl('', [Validators.required ]),
      serviceType: new FormControl('', Validators.required ),
      portNumber: new FormControl('', [Validators.required ]),
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
    this.appService.editApplication(this.appGroup.value);
    this.dialogRef.close();
  }
  onCancel() {
    this.dialogRef.close();
  }

}

import { Component, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-mydialog',
  templateUrl: './mydialog.component.html',
  styleUrls: ['./mydialog.component.css']
})
export class MydialogComponent implements OnInit {
  appGroup: any;

  constructor(public dialogRef: MatDialogRef<MydialogComponent>) { }

  ngOnInit() {
    this.appGroup = new FormGroup({
      appname: new FormControl('', [Validators.required ]),
      selectFormControl: new FormControl('', Validators.required)
   });
  }

}

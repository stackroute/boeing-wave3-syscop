import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { AppserviceService } from 'src/app/service/appservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-deletedialog',
  templateUrl: './deletedialog.component.html',
  styleUrls: ['./deletedialog.component.css']
})
export class DeletedialogComponent implements OnInit {
  constructor(public dialogRef: MatDialogRef<DeletedialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private appService: AppserviceService, private router: Router) { }

  ngOnInit() {}

  onCancel(): void {
    this.dialogRef.close();
  }

  confirmDelete(): void {
    this.appService.deleteApp(this.data);
    this.appService.getApplications();
    this.dialogRef.close();
  }

}

import { Component, OnInit, Inject, OnChanges } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { MydialogComponent } from '../mydialog/mydialog.component';
import { AppserviceService } from 'src/app/service/appservice.service';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { EditdialogComponent } from '../editdialog/editdialog.component';
import { DeletedialogComponent } from '../deletedialog/deletedialog.component';
import { Router } from '@angular/router';
import { DataService } from 'src/app/service/data.service';

@Component({
  selector: 'app-app-registration',
  templateUrl: './app-registration.component.html',
  styleUrls: ['./app-registration.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0', visibility: 'hidden' })),
      state('expanded', style({ height: '*', visibility: 'visible' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})

export class AppRegistrationComponent implements OnInit, OnChanges {
  applicationName: string;
  constructor(public dialog: MatDialog, private appService: AppserviceService, private router: Router , private dataService: DataService) {}
  panelOpenState = false;
  displayedColumns: string[] = ['applicationName', 'applicationType', 'ipAddress', 'registrationDateandTime', 'actions' ];
  dataSource ;
  expandedElement: any;
  isExpansionDetailRow = (i: number, row: Object) => row.hasOwnProperty('detailRow');

  addNew(): void {
    const dialogRef = this.dialog.open(MydialogComponent, {
      width: '40vw', maxHeight: '400px',
    });
    dialogRef.afterClosed().subscribe(result => {
      this.onLoad();
    });
  }

  startEdit(applicationName: string, applicationType: string, ipAddress: string, services: Array<Object> ) {
    const dialogRef = this.dialog.open(EditdialogComponent, {
      width: '40vw', maxHeight: '400px', data: {applicationName: applicationName, applicationType: applicationType,
        ipAddress: ipAddress, services: services}
    });
    dialogRef.afterClosed().subscribe(result => {
      this.onLoad();
    });
  }
  deleteItem(applicationName: string, applicationType: string, ipAddress: string, services: Array<Object> ) {
    const dialogRef = this.dialog.open(DeletedialogComponent, {
      width: '40vw', maxHeight: '400px', data: {applicationName: applicationName, applicationType: applicationType,
        ipAddress: ipAddress, services: services}
    });
    console.log(this.dataSource);

    dialogRef.afterClosed().subscribe(result => {
      this.onLoad();
    });
  }

  ngOnInit() {
      this.onLoad();
  }
  ngOnChanges() {
    this.onLoad();
  }
  onLoad() {
    this.appService.getApplications().subscribe((data) => {
      this.dataSource = data.applications;
      this.dataService.services =  data.applications[0].services;
      console.log(data.applications);
    });
  }

}


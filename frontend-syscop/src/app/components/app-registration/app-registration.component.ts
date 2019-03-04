import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { MydialogComponent } from '../mydialog/mydialog.component';
import { AppserviceService } from 'src/app/service/appservice.service';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { EditdialogComponent } from '../editdialog/editdialog.component';
import { DeletedialogComponent } from '../deletedialog/deletedialog.component';

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

export class AppRegistrationComponent implements OnInit {
  applicationName: string;

  constructor(public dialog: MatDialog, private appService: AppserviceService, ) {}

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
      console.log('The dialog was closed');
    });
    this.appService.getApplications();
  }

  startEdit(applicationName: string, applicationType: string, ipAddress: string, services: Array<Object> ) {
    const dialogRef = this.dialog.open(EditdialogComponent, {
      width: '40vw', maxHeight: '400px', data: {applicationName: applicationName, applicationType: applicationType,
        ipAddress: ipAddress, services: services}
    });
    console.log(this.dataSource);

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
      }
    });
  }
  deleteItem(applicationName: string, applicationType: string, ipAddress: string, services: Array<Object> ) {
    const dialogRef = this.dialog.open(DeletedialogComponent, {
      width: '40vw', maxHeight: '400px', data: {applicationName: applicationName, applicationType: applicationType,
        ipAddress: ipAddress, services: services}
    });
    console.log(this.dataSource);

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
      }
    });
  }

  ngOnInit() {
      this.appService.getApplications().subscribe((data) => {
        this.dataSource = data.applications;
        console.log(data.applications);
      });
  }

}


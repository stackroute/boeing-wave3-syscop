import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { MydialogComponent } from '../mydialog/mydialog.component';
import { AppserviceService } from 'src/app/service/appservice.service';
import { animate, state, style, transition, trigger } from '@angular/animations';

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

  constructor(public dialog: MatDialog, private appService: AppserviceService) {}

  panelOpenState = false;
  displayedColumns: string[] = ['applicationName', 'applicationType', 'ipAddress', 'registrationDateandTime', 'services', 'actions' ];
  dataSource ;
  expandedElement: any;
  isExpansionDetailRow = (i: number, row: Object) => row.hasOwnProperty('detailRow');

  addNew(): void {
    const dialogRef = this.dialog.open(MydialogComponent, {
      width: '60vw', maxHeight: '400px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  ngOnInit() {
      this.appService.getApplications().subscribe((data) => {
        this.dataSource = data.applications;
        console.log(data.applications);
      });
  }

}


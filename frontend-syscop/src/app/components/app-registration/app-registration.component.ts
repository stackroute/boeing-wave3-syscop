import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { MydialogComponent } from '../mydialog/mydialog.component';
import { AutofillMonitor } from '@angular/cdk/text-field';
import { AppserviceService } from 'src/app/service/appservice.service';
import { Application } from 'src/app/models/application';
export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
];

@Component({
  selector: 'app-app-registration',
  templateUrl: './app-registration.component.html',
  styleUrls: ['./app-registration.component.css']
})
export class AppRegistrationComponent implements OnInit {

  constructor(public dialog: MatDialog, private appService: AppserviceService) {}
  APP_DATA: Application[];
  displayedColumns: string[] = ['appName', 'appType', 'ipAddress', 'services', 'actions' ];
  dataSource = this.APP_DATA[0];

  animal: string;
  name: string;

  addNew(): void {
    const dialogRef = this.dialog.open(MydialogComponent, {
      maxWidth: '400px', maxHeight: '400px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.animal = result;
    });
  }
  ngOnInit() {
    this.appService.getApplications();
  }

}


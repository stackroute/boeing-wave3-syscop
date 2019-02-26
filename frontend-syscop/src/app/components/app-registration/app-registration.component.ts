import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { MydialogComponent } from '../mydialog/mydialog.component';
import { AppserviceService } from 'src/app/service/appservice.service';
import { animate, state, style, transition, trigger } from '@angular/animations';
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
  styleUrls: ['./app-registration.component.css'],
})

export class AppRegistrationComponent implements OnInit {

  panelOpenState = false;

  constructor(public dialog: MatDialog, private appService: AppserviceService) {}
  APP_DATA;
  displayedColumns: string[] = ['applicationName', 'applicationType', 'ipAddress', 'registrationDateandTime', 'services', 'actions' ];
  dataSource ;

  animal: string;
  name: string;

  addNew(): void {
    const dialogRef = this.dialog.open(MydialogComponent, {
      width: '60vw', maxHeight: '400px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.animal = result;
    });
  }

  ngOnInit() {
      this.appService.getApplications().subscribe((data) => {
        this.dataSource = data.applications;
        console.log(data.applications);
      });
  }

}


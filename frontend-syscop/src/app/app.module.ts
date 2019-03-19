import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MatTabsModule, MatSidenavModule, MatToolbarModule, MatIconModule,
  MatButtonModule, MatListModule, MatNativeDateModule, MatGridListModule,
  MatMenuModule, MatSelectModule, MatPaginatorModule, MatSortModule, MatAutocompleteModule, MatChipsModule, } from '@angular/material';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent, } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MatFormFieldModule, MatError } from '@angular/material/form-field';
import { MatCardModule} from '@angular/material/card';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MatInputModule} from '@angular/material/input';
import { MatDatepickerModule} from '@angular/material/datepicker';
import { HttpClientModule } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatBadgeModule } from '@angular/material/badge';
import { MatTableModule } from '@angular/material/table';
import { MatRadioModule } from '@angular/material/radio';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { AppRegistrationComponent } from './components/app-registration/app-registration.component';
import { MydialogComponent } from './components/mydialog/mydialog.component';
import { ScrollDispatchModule } from '@angular/cdk/scrolling';
import { SignupComponent } from './components/signup/signup.component';
import { MatExpansionModule } from '@angular/material/expansion';
import { EditdialogComponent } from './components/editdialog/editdialog.component';
import { DeletedialogComponent } from './components/deletedialog/deletedialog.component';
import { LandingComponent } from './components/landing/landing.component';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { NgMatSearchBarModule } from 'ng-mat-search-bar';
import { DataComponent } from './components/data/data.component';
import { MatDatetimepickerModule } from '@mat-datetimepicker/core';
import { MatMomentDatetimeModule } from '@mat-datetimepicker/moment';
import { JavaGraphsComponent } from './components/java-graphs/java-graphs.component';
import { DockerComponent } from './components/docker/docker.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    HomeComponent,
    AppRegistrationComponent,
    MydialogComponent,
    EditdialogComponent,
    DeletedialogComponent,
    LandingComponent,
    NotificationsComponent,
    DataComponent,
    JavaGraphsComponent,
    DockerComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    NgMatSearchBarModule,
    MatTabsModule,
    MatSidenavModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    LayoutModule,
    MatListModule,
    MatFormFieldModule,
    MatCardModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    FlexLayoutModule,
    MatBadgeModule,
    MatGridListModule,
    MatMenuModule,
    MatGridListModule,
    MatSelectModule,
    MatTableModule,
    MatRadioModule,
    MatSelectModule,
    ScrollDispatchModule,
    MatExpansionModule,
    MatPaginatorModule,
    MatSortModule,
    MatAutocompleteModule,
    MatChipsModule,
    MatDatetimepickerModule,
    MatMomentDatetimeModule,

  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  providers: [],
  bootstrap: [AppComponent ],
  entryComponents : [MydialogComponent, EditdialogComponent, DeletedialogComponent ]
})
export class AppModule { }

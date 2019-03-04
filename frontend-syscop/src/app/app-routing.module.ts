import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InitialViewComponent } from './components/initial-view/initial-view.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AppRegistrationComponent } from './components/app-registration/app-registration.component';
import { SignupComponent } from './components/signup/signup.component';

const routes: Routes = [
  {path: '', component: InitialViewComponent},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  { path: 'home',
    component: HomeComponent,
    children: [
      {path: 'dashboard', component: DashboardComponent },
      {path: 'appRegistration', component: AppRegistrationComponent }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

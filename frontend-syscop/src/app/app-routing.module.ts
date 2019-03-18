import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AppRegistrationComponent } from './components/app-registration/app-registration.component';
import { SignupComponent } from './components/signup/signup.component';
import { LandingComponent } from './components/landing/landing.component';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { DataComponent } from './components/data/data.component';

const routes: Routes = [
  {path: '', component: LandingComponent},
  {path: 'login', component: LoginComponent, },
  {path: 'signup', component: SignupComponent},
  { path: 'home',
    component: HomeComponent,
    children: [
      {path: 'dashboard', component: DashboardComponent },
      {path: 'appRegistration', component: AppRegistrationComponent },
      {path: 'notifications', component: NotificationsComponent },
      {path: 'data', component: DataComponent },
    ]
  }, ];


@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true }), ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
